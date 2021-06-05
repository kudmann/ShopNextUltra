package by.kudman.old;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Shopping {
    private static List<Product> shopList;

    public static List<Product> getShopList() {
        return Shopping.shopList;
    }

    public static void setShopList() throws IOException {
        File list = new File("shop.txt");
        List<String> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(list);
             BufferedReader bReader = new BufferedReader(fileReader)) {
            String line = bReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bReader.readLine();
            }
        }
        shopList = new ArrayList<>();
        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            Product product = new Product();
            product.setName(scanner.next());
            product.setType(scanner.next());
            product.setArticle(scanner.next());
            product.setSize(scanner.next());
            product.setPrice(scanner.nextInt());
            shopList.add(product);
        }
    }

    public static void printList(List<Product> list) {
        int i =1;
        Formatter form = new Formatter();
        String cute = "_".repeat(82);
        form.format("%s%n%s%s%9s%6s%10s%7s%15s%4s%8s%5s%11s%4s%n%s%-2s%n",cute, "Num", "|", "Type", "|", "Name",
                "|", "Article", "|", "Size", "|", "Price, p.", "|" ,cute,"|");
        System.out.print(form.toString());
        for (Product product : list) {
           System.out.print(i+++". ");
           if (i<=10){
               System.out.print(" ");
           }
            System.out.print(product.toString());
        }
        System.out.print(cute+"|");
        form.close();
    }

}
