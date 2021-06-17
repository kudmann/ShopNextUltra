package by.kudman.newone.dao;

import by.kudman.newone.dao.DAO;
import by.kudman.newone.service.Product;

import java.sql.SQLException;

public class ProductManagement implements DAO<Product,Integer> {

    @Override
    public Product findBy(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }
}
