package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao{

    private Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Product obj) {

        if (obj.getName().length() < 1) {
            System.out.println("The product name can't be empty");
            return;
        }
        
        if (isProductExists(obj.getName())) {
            System.out.println("Product with the same name already exists");
            return;
        }

        if (obj.getDescription().length() < 10) {
            System.out.println("The description needs at least 10 characters");
            return;
        }
        
        if (obj.getValue() < 0) { 
            System.out.println("The value must be positive");
            return;
        }

        if (obj.getQuantity() < 1) {
            System.out.println("Quantity needs at least 1 item");
            return;
        }



        PreparedStatement st = null; 

        try {
            st = conn.prepareStatement (
                
                "INSERT INTO products "
                + "(id, name, value, description, quantity) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)", 
                Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.setDouble(3, obj.getValue());
            st.setString(4, obj.getDescription());
            st.setInt(5, obj.getQuantity());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);

            } else {
                throw new DbException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    private boolean isProductExists(String productName) {
        String sql = "SELECT COUNT(*) AS count FROM products WHERE name = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, productName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking product existence: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void update(Product obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
