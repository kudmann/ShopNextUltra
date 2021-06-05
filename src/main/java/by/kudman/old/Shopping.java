package by.kudman.old;

import by.kudman.newone.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Shopping {
    private static List<Product> shopList;

    public static List<Product> getShopList() {
        return Shopping.shopList;
    }

    public static void setShopList(){
        shopList = new ArrayList<>();
        try{
        ResultSet rs = DataBase.sqlScriptQuery(Shop.connection,"select\n" +
                "pl.product_name,p.id,s.size,pt.type,p.amount,pl.price\n" +
                "from product_list as pl\n" +
                "left join product as p on pl.id = p.product_list_id\n" +
                "left join product_types pt on p.product_type_id = pt.id\n" +
                "left join sizes s on p.size_id = s.id\n");
                while(rs.next()){
                Product cloth = new Product();
                cloth.setName(rs.getString(1));
                cloth.setArticle(String.valueOf(rs.getInt(2)));
                cloth.setSize(rs.getString(3));
                cloth.setType(rs.getString(4));
                cloth.setPrice(rs.getInt(6));
                shopList.add(cloth);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
