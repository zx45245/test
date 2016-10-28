package com.cehome.wx.wxweb.vo.message.from;


// 文本消息 

public class TextMessage extends BaseMessage {  
    // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }

	public void setFuncFlag(int i) {
		// TODO Auto-generated method stub
		
	}  
}  