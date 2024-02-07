package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import db.DB;
import db.DbException;
//import db.DbIntegrityException;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {

    private Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createProduct(Product obj) {

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
            st = conn.prepareStatement(

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
	public void deleteById(Integer id) {
    	 // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}
    
    
    @Override
    public void updateProduct(Product obj) {
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
            st = conn.prepareStatement(
                    "UPDATE products "
                    + "SET name = ?, value = ?, description = ?, quantity = ? "
                    + "WHERE id = ?");

                st.setString(1, obj.getName());
                st.setDouble(2, obj.getValue());
                st.setString(3, obj.getDescription());
                st.setInt(4, obj.getQuantity());
                st.setInt(5, obj.getId());

                st.executeUpdate();

            System.out.println("Updated!");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
    }

    // busca todos os produtos armazenados no banco de dados
    @Override
    public List<Product> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM products");
            rs = st.executeQuery();

            List<Product> listProducts = new ArrayList<>();

            while (rs.next()) {
                Product product = instantiateProduct(rs);
                listProducts.add(product);
            }

            return listProducts;

        } catch (SQLException e) {
            throw new DbException("Error when searching all products: " + e.getMessage(), 500);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // Método para buscar no banco de dados o produto com o id que o usário informou
    @Override
    public Optional<Product> findByIdProduct(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Product product = instantiateProduct(rs);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DbException("Error while fetching product: " + e.getMessage(), 500);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // Método para buscar no banco de dados os produtos com o nome que o usário
    // informou
    @Override
    public List<Product> findByNameProduct(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM products WHERE name LIKE ?");
            st.setString(1, "%" + name + "%");
            rs = st.executeQuery();

            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = instantiateProduct(rs);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new DbException("Error while fetching product: " + e.getMessage(), 500);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // Método auxiliar para instanciar um produto a partir do ResultSet, para evitar
    // repetir trechos de códigos
    private Product instantiateProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setValue(rs.getDouble("value"));
        product.setDescription(rs.getString("description"));
        product.setQuantity(rs.getInt("quantity"));
        return product;

    }
}
