package by.kudman.old;

import by.kudman.newone.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Shop {
    public static Connection connection;
    public static void main(String[] args) {
        try {
            Shop.connection = DataBase.connectDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Shopping.setShopList();
        LoggingIn.setUserList(Serial.deserialize());
        Commands com = new Commands();
        Scanner scan = new Scanner(System.in);
        com.rebound();
        do{
            com.console(scan.next());
        }while(com.repeater);
       /* Serial.serialize(LoggingIn.getUserList());*/
        System.out.println("Thanks for using our shop. Please return ASAP :)");
    }
}
