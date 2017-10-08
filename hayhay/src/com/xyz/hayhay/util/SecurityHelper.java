package com.xyz.hayhay.util;

import java.security.MessageDigest;
import java.util.Formatter;

import com.google.gxp.com.google.common.io.MessageDigestAlgorithm;

public class SecurityHelper {
	public static String endcodeSHA256(String text) {
		MessageDigest m = MessageDigestAlgorithm.SHA_256.get();
		m.update(text.getBytes());
		byte[] endCode = m.digest();
		String a = hexEncode(endCode);
		return a;
	}

	private static String hexEncode(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		final Formatter formatter = new Formatter(sb);
		for (int i = 0; i < buf.length; i++) {
			formatter.format("%02x", buf[i]);
		}
		formatter.close();
		return sb.toString();
	}
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	public static void main(String[] args) {
		System.out.println(SecurityHelper.endcodeSHA256("admin"));
	}
}
