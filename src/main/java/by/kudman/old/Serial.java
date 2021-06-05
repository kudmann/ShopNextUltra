package by.kudman.old;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serial implements Serializable {

    public static List<User> deserialize() {
        List<User> userList = new ArrayList<>();
        try (
                Connection connect = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "postgres")) {
            PreparedStatement ps = connect.prepareStatement("""
                    select
                    ul.login,ul.password
                    from user_list as ul     
                    """);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("User list loading successful");
        return userList;
    }
}
