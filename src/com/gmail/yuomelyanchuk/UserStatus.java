package com.gmail.yuomelyanchuk;

/**
 * Created by Urec on 02.06.2017.
 */
public class UserStatus {
    private String login;
    private String status;
    private String channel;



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserStatus(String login, String status, String channel) {

        this.login = login;
        this.status = status;
        this.channel=channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return  login + ';' + status+";"+channel;

    }
}
