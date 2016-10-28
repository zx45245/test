package com.cehome.wx.wxweb.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cehome.wx.wxweb.common.MessageUtil;
import com.cehome.wx.wxweb.common.SignUtil;
import com.cehome.wx.wxweb.vo.message.to.TextMessage;


@RestController
public class IndexController {
	private static final String TOKEN = "tiejia";
	private static final String FAIL = "Error";
	/**
	开发者通过检验signature对请求进行校验（下面有校验方式）。
	若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
	加密/校验流程如下：
	1、将token、timestamp、nonce三个参数进行字典序排序
	2、将三个参数字符串拼接成一个字符串进行sha1加密
	3、开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	*/
	@RequestMapping("/")
	public Object startCheck(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		String echostr = request.getParameter("echostr");
		if (SignUtil.checkSignature(TOKEN, signature, timestamp, nonce)) {
			return echostr;
		}
		return FAIL;
	}
	@RequestMapping("/startCheck")
	public void replay(HttpServletRequest request,HttpServletResponse response){
		String nr = null;
		Map<String, String> requestMap = null;
		try {
			requestMap = MessageUtil.parseXml(request);
		} catch (Exception e1) {
			
		}
		String fromUserName = (String)requestMap.get("FromUserName");
		String toUserName = (String)requestMap.get("ToUserName");
		String msgType = (String)requestMap.get("MsgType");
		
		String respContent = null;
		
		// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			respContent = "您发送的是文本消息！";
		}
		// 图片消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			respContent = "您发送的是图片消息！";
		}
		// 地理位置消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			respContent = "您发送的是地理位置消息！";
		}
		// 链接消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			respContent = "您发送的是链接消息！";
		}
		// 音频消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
			respContent = "您发送的是音频消息！";
		}
		// 事件推送
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 订阅
			if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "谢谢您的关注！";
			}
			// 取消订阅
			else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			}
			// 自定义菜单点击事件
			else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				// 事件KEY值，与创建自定义菜单时指定的KEY值对应
				String eventKey = requestMap.get("EventKey");
			}	
		}
		
		// 默认回复此文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		
		// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
		textMessage.setContent(respContent);
		
		
		
		// 将文本消息对象转换成xml字符串
		nr = MessageUtil.textMessageToXml(textMessage);
			
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(nr);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.err.println("ocg9aw-P33sqPvvue9FYbtSbp9zE".length());
	}
}
