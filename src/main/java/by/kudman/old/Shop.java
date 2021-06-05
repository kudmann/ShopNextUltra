package by.kudman.old;

import java.io.IOException;
import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        try {
            Shopping.setShopList();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        LoggingIn.setUserList(Serial.deserialize());
        Commands com = new Commands();
        Scanner scan = new Scanner(System.in);
        com.rebound();
        do{
            com.console(scan.next());
        }while(com.repeater);
        Serial.serialize(LoggingIn.getUserList());
        System.out.println("Thanks for using our shop. Please return ASAP :)");
    }
}