package com.xyz.hayhay.embeddedserver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

import com.xyz.hayhay.util.ConfigurationHelper;
import com.xyz.hayhay.worker.CoreWorker;

public class JettyServer {
	public JettyServer() throws Exception {
		
		PropertyConfigurator.configure(ConfigurationHelper.getInstance().getValue("log4j"));
        Resource fileserver_xml = Resource.newSystemResource("jetty.xml");
        XmlConfiguration configuration = new XmlConfiguration(fileserver_xml.getInputStream());
        Server server = (Server)configuration.configure();
        WebAppProvider p = new WebAppProvider();
        p.setMonitoredDirName("webapps");
        p.setDefaultsDescriptor("webapps/WEB-INF/web.xml");
        server.getBean(DeploymentManager.class).addAppProvider(p);
        WebAppContext context = new WebAppContext();
        context.setResourceBase("webapps");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        context.setWelcomeFiles(new String[]{"/home.html"});
        context.setErrorHandler(new ErrorHandler(){
        	@Override
        	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        		if(response.getStatus()==404)
        			response.sendRedirect("/");
        		else
        			super.handle(target, baseRequest, request, response);
        	}
        });
        server.setHandler(context);
        //Start worker
        new CoreWorker().start();
        
		server.start();
		server.join();
		
		System.out.println("Server running...");
	}
	public static void main(String[] args) throws Exception {
		new JettyServer();
	}
}
