package com.ejemplo.soap.service;

import com.ejemplo.soap.dao.ProductoDAO;
import com.ejemplo.soap.model.Producto;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;

import java.util.List;

@WebService(
        serviceName = "ProductoService",
        targetNamespace = "http://soap.ejemplo.com/"
)
public class ProductoService {

    private final ProductoDAO productoDAO =
            new ProductoDAO();

    @WebMethod
    public List<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }

    @WebMethod
    public Producto buscarProducto(int id) {
        return productoDAO.buscarProducto(id);
    }

    @WebMethod
    public String crearProducto(
            String nombre,
            double precio,
            int stock) {

        boolean resultado =
                productoDAO.crearProducto(
                        nombre,
                        precio,
                        stock
                );

        if (resultado) {
            return "PRODUCTO creado correctamente";
        }

        return "NO fue posible crear el producto";
    }

    @WebMethod
    public String actualizarProducto(
            int id,
            String nombre,
            double precio,
            int stock) {

            boolean resultado =
                    productoDAO.actualizarProducto(
                            id,
                            nombre,
                            precio,
                            stock
                    );

            if (resultado) {
                return "PRODUCTO actualizado correctamente";
            }

            return "NO fue posible actualizar el producto";
    }

    @WebMethod
    public String eliminarProducto(int id) {

        boolean resultado =
                productoDAO.eliminarProducto(id);

        if (resultado) {
            return "PRODUCTO eliminado correctamente!";
        }

        return "NO fue posible eliminar el producto";
    }
}
