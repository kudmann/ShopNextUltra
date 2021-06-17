package by.kudman.newone.dao;

import by.kudman.newone.service.Product;

import java.sql.SQLException;
import java.util.List;

public interface DAO<DOMAIN, ID> {

    DOMAIN findBy(ID id) throws SQLException;

    DOMAIN insert(DOMAIN domain) throws SQLException;

    DOMAIN update(DOMAIN domain) throws SQLException;

    boolean delete(DOMAIN domain);

}
