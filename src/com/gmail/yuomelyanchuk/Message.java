package com.gmail.yuomelyanchuk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class Message {



    private  Date date = new Date();
    private  String from;
    private  String to;
    private  String text;
    private int messageType;//0 public, 1 channel, 2 private



    public Message(String from, String to, String text, int messageType) {

        this.from = from;
        this.to = to;
        this.text = text;
        this.messageType = messageType;
    }

    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public  String toJson(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJson(String message){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(message,Message.class);
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
