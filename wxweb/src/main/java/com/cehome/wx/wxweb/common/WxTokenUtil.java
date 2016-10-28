package com.cehome.wx.wxweb.common;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class WxTokenUtil {
	private static final String APPID="wx58ff31e0bd68f7ac";
	private static final String APPSECRET="27a8a78a31b0161f90e39ecb38aaaa06";
	private static final String GETTOKENURL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
	public static void main(String[] args) throws ParseException, ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(GETTOKENURL);
		String result = EntityUtils.toString(client.execute(get).getEntity(),"utf-8");
//		System.err.println(result);
	}
}
