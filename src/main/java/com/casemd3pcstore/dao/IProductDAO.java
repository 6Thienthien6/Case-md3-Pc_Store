package com.casemd3pcstore.dao;

import com.casemd3pcstore.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    List<Product> selectAllProduct();
    Product selectProduct(int id);
    boolean insertProduct(Product product) throws SQLException;
    Product findById(int id) throws SQLException;
    List<Product> searchProduct(String search) throws SQLException;
    List<Product> getNumberPage(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException;
    List<Product> getNumberPageSortPriceASC(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException;
    List<Product> getNumberPageSortPriceDESC(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException;

    boolean delete(int id) throws SQLException;
}
