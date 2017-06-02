package com.gmail.yuomelyanchuk;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Authorization {
    private static String login;
    private static Cookie cookie;
    private static String secId = "";
    private static int nChannel;
    private static int nPrivate;

    public Authorization() {

    }


    private static String login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("input login");
        return sc.nextLine();
    }

    public Cookie getCookie() {
        return cookie;
    }

    public static String getLogin() {
        return login;
    }

    public static String getSecId() {
        return secId;
    }

    public static void setSecId(String secId) {
        Authorization.secId = secId;
    }

    public static int getnChannel() {
        return nChannel;
    }

    public static void setnChannel(int nChannel) {
        Authorization.nChannel = nChannel;
    }

    public static void addnChannel(int k) {
        Authorization.nChannel = Authorization.nChannel + k;
    }

    public static int getnPrivate() {
        return nPrivate;
    }

    public static void setnPrivate(int nPrivate) {
        Authorization.nPrivate = nPrivate;
    }

    public static void addnPrivate(int k) {
        Authorization.nPrivate = Authorization.nPrivate + k;
    }


    public static void authorization() {
        login = login();
        Scanner sc = new Scanner(System.in);
        System.out.println("input password");
        String pwd = sc.nextLine();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Utils.getURL() + "/Authorization");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("login", login));
        nvps.add(new BasicNameValuePair("pwd", pwd));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpClientContext context = HttpClientContext.create();
            CloseableHttpResponse response = httpclient.execute(httpPost, context);
            List<Cookie> cookies = context.getCookieStore().getCookies();
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));

            for (Cookie c : cookies) {
                if (c.getName().equals("secId")) {
                    cookie = c;
                    secId = c.getValue();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Authorization.nChannel = 0;
        Authorization.nPrivate = 0;

        sc.nextLine();


    }
}
