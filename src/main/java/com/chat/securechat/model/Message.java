package com.chat.securechat.model;

public class Message {

    private String sender;
    private String content;

    // Getter
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    // Setter
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
