var Logger = {};
var ClientCache={};
var HTTPClient = {};
var Localize={};
var View = {};

//================================= ClientCache ===============================
ClientCache.getLocalCache = function (name) {
    try {
        if (('localStorage' in window) && window['localStorage'] !== null && name != undefined) {
            return localStorage.getItem(name);
        }
    } catch (ex) {
        Logger.clientLog("local caching failed");
    }
    return undefined;
}
ClientCache.getLocalCacheJSON = function(name){
    var result = ClientCache.getLocalCache(name);
    if(result !== undefined && result !== "" && result !== null){
        try{
           return JSON.parse(result);
        }catch(ex){
            Logger.serverLog("ex");
        }
    }
    return undefined;
}
ClientCache.localCaching = function (name, value) {
    try {
        if (('localStorage' in window) && window['localStorage'] !== null && name != undefined && value != undefined) {
            if (localStorage.getItem(name) != undefined) {
                localStorage.removeItem( name);
            }
            localStorage.setItem(name, value);
        }
    } catch (ex) {
        Logger.serverLog("local caching failed" + ex);
    }
}
ClientCache.getCookie = function (cname) {
    Logger.clientLog("all cookies: " + document.cookie);
    var ret = "";
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            ret = c.substring(name.length, c.length);
        }
    }
    return ret;
}
ClientCache.setCookie = function (key, value) {
	document.cookie = key+"=" + value + "; path=/";
}


// ========================== HTTPClient ==========================
HTTPClient.callAjaxService = function (targetUrl, params,requestMethod, callback,callbackParams, cachedKey, template) {
    var havedata = false;
    $.ajax({
        url: targetUrl,
        type: requestMethod,
        data: params,
        dataType: 'json',
        trycount: 0,
        async: true,
        timeout: 15000,
        success: function (json) {
            var args = new Array();
            args[0] = json;
            args[1] = callbackParams;
            if (template != null && template != undefined) {
                args[2] = template;
            }
            
            if (callback != undefined && callback != null) {
                HTTPClient.callJSFunc(callback, args);
            }
            if (cachedKey != undefined && cachedKey != null && json.err >= 0) {
                ClientCache.localCaching(cachedKey, JSON.stringify(json));
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
            if (textStatus == "timeout") {
                this.trycount++;
                if (this.trycount < 3 && !havedata) {
                    $.ajax(this);
                } else if (this.trycount >= 3 && !havedata) {
                    Logger.clientLog("Load timeout: url=" + targetUrl + "; params= " + params + " request method=" + requestMethod);
                    try {
                        Logger.scribeLog("Load timeout: url=" + targetUrl + "; params= " + params + " request method=" + requestMethod + "; errorThrown= " + errorThrown + " status=" + jqXHR.status, index_hit, -8);
                    } catch (ex) {
                    }
                }
            }
        }
    });
}

HTTPClient.callSyncAjaxService = function (targetUrl, params, requestMethod, cachedKey) {
    var result = $.ajax({
        url: targetUrl + "&_=" + new Date().getTime(),
        type: requestMethod,
        data: params,
        dataType: 'json',
        trycount: 0,
        async: false,
        timeout: 15000,
        success: function (json) {
            if (cachedKey != undefined && cachedKey != null && json.err >= 0) {
                localCaching(cachedKey, JSON.stringify(json));
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
            if (textStatus == "timeout") {
                this.trycount++;
                if (this.trycount < 3 && !havedata) {
                    $.ajax(this);
                } else if (this.trycount >= 3 && !havedata) {
                    Logger.clientLog("Load timeout: url=" + targetUrl + "; params= " + params + " request method=" + requestMethod);
                    try {
                        jtp.scribeLog("Load timeout: url=" + targetUrl + "; params= " + params + " request method=" + requestMethod + "; errorThrown= " + errorThrown + " status=" + jqXHR.status, index_hit, -8);
                    } catch (ex) {
                    }
                }
            }
        }
    }).responseText;
    return result;
};

HTTPClient.callJSFunc = function (name, args) {
    var fn = window[name];
    if (args != null) {
        if (typeof fn === "function")
            fn.apply(null, args);
    } else {
        if (typeof fn === "function")
            fn();
    }
}
//===================================View=====================
View.renderHTML = function(containerId,jsonData, templateUrl, append){
    var template = View.getTemplate(templateUrl);
    var html = template.render(jsonData);
    if(append == true){
        $("#"+containerId).append(html);
    }else{
        $("#"+containerId).html(html);
    }
    
}

View.scrollPosition={"_25_PERCENT":0.25,"_30_PERCENT":0.30,"_35_PERCENT":0.35,"_40_PERCENT":0.40,"_50_PERCENT":0.5,"_70_PERCENT":0.7,"_100_PERCENT":1};
View.scroll = function(position, callBack){
	var scrollPosition = 0;
	$( window ).scroll(function() {
		scrollPosition = $(window).scrollTop()/$( document ).height();
		if(roundToTwo(scrollPosition) >= position){
			callBack();
		}
	});
}
function roundToTwo(num) {    
    return +(Math.round(num + "e+2")  + "e-2");
}
View.getTemplate = function(templateUrl){
    try {
        var cacheKey = templateUrl.substring(templateUrl.lastIndexOf("/")+1,templateUrl.length);
        var templateText = ClientCache.getLocalCache(cacheKey);
        var template;
        //templateText = "";
        if (templateText === null || templateText === undefined || templateText === "") {
            template = new EJS({
                url: templateUrl
            });
            ClientCache.localCaching(cacheKey, template.text);
        } else {
            template = new EJS({
                text: templateText
            });
        }
        return template;
    } catch (e) {
    }
    return undefined;
}
//===================================Localize==================
Localize.get = function(key){
    var resource = Localize.loadResource();
    return resource.get(key);
}
Localize.loadResource = function(){
    var locale = ClientCache.getCookie("zlanguage");//TODO: get from cookie
    var params = "";
    var resource = HTTPClient.callSyncAjaxService("/store/locale",params,"GET",locale);
    return resource;
}


Logger.log=function(app, functionName,type,log){
    try{
        var params = {"app":app,"function":functionName,"type":type,"log":log};
        $.ajax({
            url: "/store/zplog",
            type: "POST",
            data: params,
            dataType: 'json',
            trycount: 0,
            async: true,
            timeout: 10000
        });
    }catch(ex){
    }
}
Logger.serverLog = function (msg) {
    var param = {};
    param.msg = msg;
    $.ajax({
        url: '/store/log',
        type: "POST",
        data: param,
        async: true
    });
}

Logger.scribeLog = function (msg, index, errCode) {
    var param = {};
    param.msg = msg;
    param.index = index;
    param.errcode = errCode;
    $.ajax({
        url: '/store/log',
        type: "POST",
        data: param,
        async: true
    });
}

var debugLog = "";
var enableClientLog=false;
Logger.clientLog = function (msg) {
    if(enableClientLog){
        debugLog = debugLog + msg + ";";
        document.getElementById("log").innerHTML = debugLog;
    }
}
Logger.clearClientLog = function () {
    debugLog = "";
    document.getElementById("log").innerHTML = "";
}

