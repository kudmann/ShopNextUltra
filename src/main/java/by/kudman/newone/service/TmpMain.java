package by.kudman.newone.service;

import java.sql.SQLException;
import java.util.Scanner;

public class TmpMain {

    public static void main(String[] args) {
        UserInputData userInputData = UserInputData.getUserInputData();
        Scanner scanner = new Scanner(System.in);
        UserOperations UO = UserOperations.getUserOperations();
        UserAuthorization UA = UserAuthorization.getUserAuthorization();


        System.out.println("create or exist?");
        switch (scanner.next()) {
            case "c" -> {
                System.out.println("your login");
                userInputData.inputLogin(scanner.next());
                System.out.println("your password");
                userInputData.inputPassword(scanner.next());
                try {
                   if(UO.create(User.getUser().getLogin(), User.getUser().getPassword())){
                       System.out.println("User creation successful");
                   }else {
                       System.out.println("User with such name exists");
                   }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            case "e" ->{
                System.out.println("your login");
                userInputData.inputLogin(scanner.next());
                System.out.println("your password");
                userInputData.inputPassword(scanner.next());
                try{
                   if(UA.logIn(User.getUser().getLogin(), User.getUser().getPassword())){
                       System.out.println("login successful");
                   }else{
                       System.out.println("Wrong password!");
                   }
                } catch(SQLException ex){
                    ex.printStackTrace();
                }catch(NullPointerException npe){
                    System.out.println("Wrong Login!");
                }
            }

        }
    }
}
