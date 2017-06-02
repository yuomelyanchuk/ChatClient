package com.gmail.yuomelyanchuk;


import org.apache.http.cookie.Cookie;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Menu menu= new Menu();
        while (true){
           OnMenuSelect.getInstance().onMenuSelect(menu.printMenu());
        }

    }
}
