package com.gmail.yuomelyanchuk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Commands {
    public static void sendGet(String srvlLink) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login", Authorization.getLogin()));
        params.add(new BasicNameValuePair("secId", Authorization.getSecId()));
        HttpGet httpGet = new HttpGet(Utils.getURL() + srvlLink + URLEncodedUtils.format(params, "utf-8"));
        Gson gson = new GsonBuilder().create();
        try {
            HttpResponse response = httpclient.execute(httpGet);
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            if(!srvlLink.equals("/logout")) {
                try {
                    gson.fromJson(responseString, List.class).stream().forEach(c -> System.out.println(c.toString()));
                } catch (NullPointerException e) {
                    System.out.println(responseString);
                } catch (JsonSyntaxException e) {
                    System.out.println(responseString);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static void sendPost(String srvlLink) {
        Scanner sc = new Scanner(System.in);
        if(!srvlLink.equals("/changeStatus")) {
            System.out.println("input channel name");
        }else{
            System.out.println("enter your status");
        }
        String chName = sc.nextLine();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost;
        httpPost = new HttpPost(Utils.getURL() + srvlLink);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login", Authorization.getLogin()));
        params.add(new BasicNameValuePair("secId", Authorization.getSecId()));
        params.add(new BasicNameValuePair("chName", chName));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
            if (srvlLink.equals("/enterToChannel")) {
                Authorization.setnChannel(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.nextLine();
    }

    public static void sendMessage(int messageType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input message");
        String messageText = sc.nextLine();

        String toLogin = "";
        if (messageType == 2) {
            System.out.println("input to");
            toLogin = sc.nextLine();
        }

        Message msg = new Message(Authorization.getLogin(), toLogin, messageText, messageType);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost;
        httpPost = new HttpPost(Utils.getURL() + "/sendMessage");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login", Authorization.getLogin()));
        params.add(new BasicNameValuePair("secId", Authorization.getSecId()));
        params.add(new BasicNameValuePair("message", msg.toJson()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getMessages() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login", Authorization.getLogin()));
        params.add(new BasicNameValuePair("secId", Authorization.getSecId()));
        params.add(new BasicNameValuePair("nChannel", "" + Authorization.getnChannel()));
        params.add(new BasicNameValuePair("nPrivate", "" + Authorization.getnPrivate()));
        HttpGet httpGet = new HttpGet(Utils.getURL() + "/messageList?" + URLEncodedUtils.format(params, "utf-8"));
        Gson gson = new GsonBuilder().create();
        try {
            HttpResponse response = httpclient.execute(httpGet);
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            try {
                JsonMessages messages = gson.fromJson(responseString, JsonMessages.class);
                for (Message msg : messages.getList()) {
                    if (msg.getMessageType() == 2) {
                        Authorization.addnPrivate(1);
                        System.err.println(msg.toString());
                    } else {
                        Authorization.addnChannel(1);
                        System.out.println(msg.toString());
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(responseString);
            } catch (JsonSyntaxException e) {
                System.out.println(responseString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }


}
