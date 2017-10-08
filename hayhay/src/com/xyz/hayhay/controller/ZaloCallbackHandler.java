package com.xyz.hayhay.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.entirty.TelcoCode;
import com.xyz.hayhay.socialplugin.SocialServiceFactory;
import com.xyz.hayhay.socialplugin.ZaloMessage;
import com.xyz.hayhay.util.ConfigurationHelper;
import com.xyz.hayhay.util.MyUtil;
import com.xyz.hayhay.util.ValidationHelper;
import com.xyz.hayhay.worker.FindLuckyUser;

import facebook4j.internal.logging.Logger;

@Controller
public class ZaloCallbackHandler {
	Logger log = Logger.getLogger(ZaloCallbackHandler.class);
	private List<Long> codes = MyUtil.readFile(ConfigurationHelper.getInstance().getValue("appDir") + "vouchercode.txt");
	private List<Long> usedcodes = new ArrayList<Long>();

	@RequestMapping(value = "/zalocallback", method = RequestMethod.GET)
	public void ZaloCallback(HttpServletRequest req, HttpServletResponse resp) {
		Arrays.asList(new String[] {});
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>. zalocallback");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>. zalocallback");
		String fromuid = req.getParameter("fromuid");
		String message = req.getParameter("message");// #12345:viettel,mobifone,vinaphone
		log.info("fromuid=" + fromuid + " message=" + message);
		System.out.println(("fromuid=" + fromuid + " message=" + message));
		System.out.println("Code chưa check=" + codes.size());
		System.out.println("Code đã checked =" + usedcodes.size());
		if (ValidationHelper.isLong(fromuid) && message != null) {
			ZaloMessage msg = null;
			if (message != null && message.startsWith("#") && message.indexOf(":") > 0 && message.indexOf(":") < message.length()) {
				String telco = message.substring(message.indexOf(":") + 1, message.length()).trim().toLowerCase();
				String code = message.substring(1, message.indexOf(":"));
				msg = new ZaloMessage(Long.valueOf(fromuid), "Chương trình tăng thẻ nạp đã kết thúc, cảm ơn bạn đã quan tâm.");
				/*if (!"viettel".equals(telco) && !"mobifone".equals(telco) && !"vinaphone".equals(telco)) {
					msg = new ZaloMessage(Long.valueOf(fromuid), "Chỉ hỗ trợ các nhà mạng sau: viettel, mobifone, vinaphone");
				} else if (!ValidationHelper.isNumber(code)) {
					msg = new ZaloMessage(Long.valueOf(fromuid), "Mã số không hợp lệ.");
				} else {
					if (usedcodes.contains(Long.valueOf(code.trim()))) {
						msg = new ZaloMessage(Long.valueOf(fromuid), "Mã số đã dùng.");
					} else if (!codes.contains(Long.valueOf(code.trim()))) {
						msg = new ZaloMessage(Long.valueOf(fromuid), "Mã số không tồn tại.");
					} else {
						try {
							usedcodes.add(Long.valueOf(code.trim()));
							codes.remove(Long.valueOf(code.trim()));
							TelcoCode tcode = FindLuckyUser.getTelcoCode(telco,fromuid);
							if (tcode != null) {
								msg = new ZaloMessage(Long.valueOf(fromuid), "Chúc mừng bạn đã là người may mắn nhận được thẻ nạp tiền " + telco + " trị giá " + tcode.getValue() + ". Mã nạp tiền là " + tcode.getCode());
							} else {
								msg = new ZaloMessage(Long.valueOf(fromuid), "Cảm ơn bạn đã tham gia, chúc bạn may mắn lần sau.");
							}
						} catch (Exception e) {
							e.printStackTrace();
							msg = new ZaloMessage(Long.valueOf(fromuid), "Đã có lỗi xẩy ra, xin vui lòng thử lại sau ít phút.");
						}
					}
				}*/
			} /*else {
				msg = new ZaloMessage(Long.valueOf(fromuid), "Cú pháp bạn nhập không hợp lệ. \n" +
						"Cú pháp hợp lệ có dạng #mã số:nhà mạng\n " + "nhà mạng là mạng telco bạn đang dùng như: viettel,mobifone,vinaphone \n" + "Ví dụ: #12343:viettel hoặc #12343:mobifone hoặc #12343:vinaphone \n");
			}*/
			if (msg != null) {
				log.info("fromuid=" + fromuid + " message=" + message + " response msg = " + msg.getMessage());
			}
		} else {
			log.info("INVALID: fromuid=" + fromuid + " message=" + message);
			System.out.println(("INVALID: fromuid=" + fromuid + " message=" + message));
		}
	}

	public static void main(String[] args) {
		// ,18259,316684,62442
		// System.out.println(ZaloCallbackHandler.codes.contains("3166"));
	}
}
