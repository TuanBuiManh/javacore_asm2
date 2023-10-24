package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl implements ProductDao{
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_PRODUCT = "INSERT INTO product (name, quantity, soldQuantity) VALUES (?, ?, ?)";
    private final String SQL_GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE ID = ?";
    private final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final String SQL_DELETE_PRODUCT = "DELETE FROM product WHERE ID = ?";
    private final String SQL_PRODUCT_0_QUANTITY = "SELECT * FROM product WHERE quantity = 0";
    private final String SQL_PRODUCT_SOLD_QUANTITY = "SELECT * FROM product WHERE soldQuantity = " +
            "(SELECT MAX(soldQuantity) FROM product)";

    public ProductDaoImpl() throws SQLException {
    }

    @Override
    public void createProduct(Product product) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_PRODUCT,
                Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, product.getName());
            pstm.setInt(2, product.getQuantity());
            pstm.setInt(3, product.getSoldQuantity());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product getProductById(int id) {
        Product product = new Product();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_PRODUCT_BY_ID)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setQuantity(rs.getInt(3));
                    product.setSoldQuantity(rs.getInt(4));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> allProducts = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_PRODUCTS);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setQuantity(rs.getInt(3));
                product.setSoldQuantity(rs.getInt(4));
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }

    @Override
    public void deleteProduct(int id) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_PRODUCT)) {
            pstm.setInt(1, id);
            int ud = pstm.executeUpdate();
            if (ud > 0) {
                System.out.println("Update Successful");
            } else {
                System.out.println("Update Fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Product> getProduct0Quantity() {
        ArrayList<Product> allProducts = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_PRODUCT_0_QUANTITY);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setQuantity(rs.getInt(3));
                product.setSoldQuantity(rs.getInt(4));
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }

    @Override
    public ArrayList<Product> getProductSold() {
        ArrayList<Product> allProducts = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_PRODUCT_SOLD_QUANTITY);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setQuantity(rs.getInt(3));
                product.setSoldQuantity(rs.getInt(4));
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }
}
