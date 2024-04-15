package com.example.easylearn;

public class ChatMessages {

    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    String messages;
    String sentBy;

    public String getMessage() {
        return messages;
    }

    public void setMessage(String message) {
        this.messages = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public ChatMessages(String message, String sentBy) {
        this.messages = message;
        this.sentBy = sentBy;
    }
}
