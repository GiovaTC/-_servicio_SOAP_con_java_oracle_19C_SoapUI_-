package com.ejemplo.soap.dao;

import com.ejemplo.soap.config.ConexionOracle;
import com.ejemplo.soap.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarProductos() {

        List<Producto> productos = new ArrayList<>();

        String sql =
                "SELECT id, nombre, precio, stock " +
                        "FROM productos ORDER BY id";

        try (
                Connection connection =
                        ConexionOracle.obtenerConexion();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet =
                        statement.executeQuery()
        ) {
            while (resultSet.next()) {

                Producto producto = new Producto();

                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setStock(resultSet.getInt("stock"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public Producto buscarProducto(int id) {

        String sql =
                "SELECT id, nombre, precio, stock " +
                        "FROM productos WHERE id = ?";

        try (
                Connection connection =
                        ConexionOracle.obtenerConexion();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new Producto(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getDouble("precio"),
                            resultSet.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean crearProducto(
            String nombre,
            double precio,
            int stock) {

        String sql =
                "INSERT INTO productos " +
                        "(nombre, precio, stock) VALUES (?, ?, ?)";

        try (
                Connection connection =
                        ConexionOracle.obtenerConexion();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, stock);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql =
                "DELETE FROM productos WHERE id = ?";

        try (
                Connection connection =
                        ConexionOracle.obtenerConexion();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }   
}
