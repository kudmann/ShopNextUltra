package by.kudman.newone.service;

import by.kudman.newone.dao.ProductDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AssortmentOperations {
    private static AssortmentOperations assortmentOperations;
    private ArrayList<Product> assortmentList;
    private AssortmentOperations(){
    }
    public static AssortmentOperations getAssortmentOperations(){
        if(assortmentOperations ==null){
            assortmentOperations = new AssortmentOperations();
        }
        return assortmentOperations;
    }
    public void getAssortmentFromDAO() throws SQLException{
        this.assortmentList = ProductDAO.getProductDAO().getAssortmentFromDataBase();
    }

    public ArrayList<Product> getAssortmentList() {
        return assortmentList;
    }
}
