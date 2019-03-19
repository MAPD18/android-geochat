package com.zv.geochat.model;

import java.util.Date;

public class ChatMessage {
    private String id;
    private String userName;
    private String body;
    private long timestamp;
    private boolean statusUpdate;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String body) {
        this(null, userName, body, false);
    }

    public ChatMessage(String userName, String body, boolean statusUpdate) {
        this(null, userName,body,statusUpdate);
    }

    private ChatMessage(String id, String userName, String body, boolean statusUpdate) {
        this.id = id;
        this.userName = userName;
        this.body = body;
        this.statusUpdate = statusUpdate;
        this.timestamp = new Date().getTime();
    }


    public String getUserName() {
        return userName;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isStatusUpdate() {
        return statusUpdate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", body='" + body + '\'' +
                ", date='" + timestamp + '\'' +
                '}';
    }
}
