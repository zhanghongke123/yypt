/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.yypt.common.utils.codec;

import com.yypt.common.utils.lang.ExceptionUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装各种格式的编码解码工具类.
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * @author calvin
 * @version 2013-01-15
 */
public class EncodeUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(EncodeUtils.class);
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}
	
	/**
	 * Base64编码.
	 */
	public static String encodeBase64(String input) {
		try {
			return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

//	/**
//	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
//	 */
//	public static String encodeUrlSafeBase64(byte[] input) {
//		return Base64.encodeBase64URLSafe(input);
//	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input.getBytes());
	}
	
	/**
	 * Base64解码.
	 */
	public static String decodeBase64String(String input) {
		try {
			return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}



	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String encodeUrl(String part) {
		return encodeUrl(part, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String encodeUrl(String part, String encoding) {
		if (part == null){
			return null;
		}
		try {
			return URLEncoder.encode(part, encoding);
		} catch (UnsupportedEncodingException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String decodeUrl(String part) {
		return decodeUrl(part, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String decodeUrl(String part, String encoding) {
		if (part == null){
			return null;
		}
		try {
			return URLDecoder.decode(part, encoding);
		} catch (UnsupportedEncodingException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}
	


	
	// 预编译SQL过滤正则表达式
	private static Pattern sqlPattern = Pattern.compile("(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|case when)\\b)", Pattern.CASE_INSENSITIVE);
	
	/**
	 * SQL过滤，防止注入，传入参数输入有select相关代码，替换空。
	 * @author ThinkGem
	 */
	public static String sqlFilter(String text){
		if (text != null){
			String value = text;
			Matcher matcher = sqlPattern.matcher(value);
			if (matcher.find()) {
				value = matcher.replaceAll(StringUtils.EMPTY);
			}
			if (logger.isWarnEnabled() && !value.equals(text)){
				logger.info("sqlFilter: {}   <=<=<=   {}", value, text);
				return StringUtils.EMPTY;
			}
			return value;
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		int i = 0;
//		xssFilter((++i)+"你好，<script>alert(document.cookie)</script>我还在。");
//		xssFilter((++i)+"你好，<strong>加粗文字</strong>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，\"><strong>加粗文字</strong>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<iframe src=\"abcdef\"></iframe><strong>加粗文字</strong>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<iframe src=\"abcdef\"/><strong>加粗文字</strong>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<iframe src=\"abcdef\"><strong>加粗文字</strong>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script type=\"text/javascript\">alert(document.cookie)</script>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script\n type=\"text/javascript\">\nalert(document.cookie)\n</script>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script src='' onerror='alert(document.cookie)'></script>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script type=text/javascript>alert()我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script>alert(document.cookie)</script>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<script>window.location='url'我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，</script></iframe>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，eval(abc)我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，xpression(abc)我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<img src='abc.jpg' onerror='location='';alert(document.cookie);'></img>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<img src='abc.jpg' onerror='alert(document.cookie);'/>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<img src='abc.jpg' onerror='alert(document.cookie);'>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<a onload='alert(\"abc\")'>hello</a>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<a href=\"/abc\">hello</a>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<a href='/abc'>hello</a>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<a href='vbscript:alert(\"abc\");'>hello</a>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，<a href='javascript:alert(\"abc\");'>hello</a>我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，?abc=def&hello=123&world={\"a\":1}我还在。");
//		xssFilter("<!--HTML-->"+(++i)+"你好，?abc=def&hello=123&world={'a':1}我还在。");
//		sqlFilter((++i)+"你好，select * from xxx where abc=def and 1=1我还在。");
//		sqlFilter((++i)+"你好，insert into xxx values(1,2,3,4,5)我还在。");
//		sqlFilter((++i)+"你好，delete from xxx我还在。");
//		sqlFilter((++i)+"a.audit_result asc,case when 1 like case when length(database())=6 then 1 else exp(11111111111111111) end then 1 else 1/0 end");
//	}
	
}
