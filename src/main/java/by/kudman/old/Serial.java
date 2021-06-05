package by.kudman.old;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serial implements Serializable {

    public static void serialize(List<User> users) {
        File file = new File("users.data");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try (FileOutputStream fileOStream = new FileOutputStream(file, true);
             ObjectOutputStream objOStream = new ObjectOutputStream(fileOStream)) {
            objOStream.writeInt(users.size());
            for (User user : users) {
                objOStream.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("User list saved successfully");
    }

    public static List<User> deserialize() {
        List<User> userList = new ArrayList<>();
        File file = new File("users.data");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            int size = objectInputStream.readInt();
            for (int i = 0; i < size; i++) {
                userList.add((User) objectInputStream.readObject());
                userList.get(i).setCart();
                userList.get(i).setThings();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("User list loading failed!");
            System.out.println("making default user list....");
            file.delete();
            LoggingIn.defaultUserList();
            return deserialize();
        }
        System.out.println("User list loading successful");
        return userList;
    }
}
