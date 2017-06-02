package com.gmail.yuomelyanchuk;


import java.util.Scanner;

public class Utils {
    private static final String URL = "http://127.0.0.1";
    private static final int PORT = 8080;
    public static Scanner sc = new Scanner(System.in);

    public static String getURL() {
        return URL + ":" + PORT;
    }
}
