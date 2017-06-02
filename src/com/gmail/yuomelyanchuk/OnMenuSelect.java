package com.gmail.yuomelyanchuk;

/**
 * Created by Urec on 02.06.2017.
 */
public class OnMenuSelect {

    private static final OnMenuSelect menuSelect = new OnMenuSelect();

    private OnMenuSelect() {

    }

    public static OnMenuSelect getInstance() {
        return menuSelect;
    }

    public void onMenuSelect(int menu_number) {
        switch (menu_number) {
            case 0:
                Registration.registration();
                break;
            case 1:
                Authorization.authorization();
                break;
            case 2:
                Commands.sendGet("/channelList?");
                break;
            case 3:
                Commands.sendPost("/createChannel");
                break;
            case 4:
                Commands.sendPost("/deleteChannel");
                break;
            case 5:
                Commands.sendPost("/enterToChannel");
                break;
            case 6:
                Commands.sendMessage(0);
                break;
            case 7:
                Commands.sendMessage(2);
                break;
            case 8:
                Commands.getMessages();
                break;
            case 9:
                Commands.sendPost("/changeStatus");
                break;
            case 10:
                Commands.sendGet("/userStatus?");
                break;
            case 11:
                Commands.sendGet("/logout?");
                Authorization.setSecId("");
                break;
        }
    }
}
