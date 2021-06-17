package by.kudman.newone.dao;

import by.kudman.newone.service.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    private static ProductDAO productDAO;

    private ProductDAO() {
    }

    public static ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    public ArrayList<Product> getAssortmentFromDataBase() throws SQLException {
        String findAllCommand = "SELECT PL.product_name,PT.type,P.ID,S.size,PL.price,P.description FROM product_list PL\n" +
                "LEFT JOIN product P ON PL.id = p.product_list_id\n" +
                "LEFT JOIN sizes s on P.size_id = s.id\n" +
                "LEFT JOIN product_types PT on P.product_type_id = PT.id";
        ArrayList<Product> list = new ArrayList<>();
        try (Connection connection = ConnectionPull.getConnection()) {
            ResultSet resultSet = DataBaseCommand.getDataBaseCommand().sqlConsoleExecute(connection, findAllCommand);
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString(1));
                product.setType(resultSet.getString(2));
                product.setID(resultSet.getInt(3));
                product.setSize(resultSet.getString(4));
                product.setPrice(resultSet.getInt(5));
                product.setDescription(resultSet.getString(6));
                list.add(product);
            }
        }
        return list;
    }
}
