package com.gmail.yuomelyanchuk;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private  final List<String> menu = new ArrayList<>();


    public Menu() {
        menu.add("registration");
        menu.add("authorization");
        menu.add("get channels list");
        menu.add("create channel");
        menu.add("delete channel");
        menu.add("enter to channel");
        menu.add("send message on channel");
        menu.add("send private massage");
        menu.add("get new messages");
        menu.add("set my status");
        menu.add("get users status");
        menu.add("log out");


    }

    public  int printMenu(){
        if(Authorization.getSecId().equals("")){
            for (int i = 0; i < 2; i++) {
                System.out.println(i+". "+menu.get(i));
            }
        }else{
            for (int i = 0; i < menu.size(); i++) {
                System.out.println(i+". "+menu.get(i));
            }
        }
        System.out.println("input menu number");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()){
            int menu_number= sc.nextInt();
            if(Authorization.getSecId().equals("") && menu_number>1){
                return -1;
            }
            return menu_number;
        }

        return -1;

    }
}
