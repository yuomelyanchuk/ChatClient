package com.gmail.yuomelyanchuk;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Registration {
    private static  String login;
    private static  String pwd;

    static void inputLoginAndPwd(){

        String logintmp="";
        String pwd1="";
        String pwd2="";
        boolean b=false;
        while(!b){
            System.out.println("input login");
            logintmp = Utils.sc.nextLine();

            System.out.println("input password");
            pwd1 = Utils.sc.nextLine();

            System.out.println("confirm password");
            pwd2 = Utils.sc.nextLine();

            if(logintmp.length()>0 && pwd1.length()>0 && pwd1.equals(pwd2)){
                b=true;
            }
        }

        login=logintmp;
        pwd=pwd1;

    }
    static void tryRegisteringLogin (){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Utils.getURL()+"/Registration");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("login", login));
        nvps.add(new BasicNameValuePair("pwd", pwd));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static  void registration(){
        Registration.inputLoginAndPwd();
        Registration.tryRegisteringLogin();
        Utils.sc.nextLine();
    }
}
