package com.ejemplo.soap;

import com.ejemplo.soap.service.ProductoService;
import jakarta.xml.ws.Endpoint;


public class Main {
    public static void main(String[] args) {

        String direccion =
                "http://localhost:8080/soap/productos";

        Endpoint.publish(
                direccion,
                new ProductoService()
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                " SERVICIO SOAP INICIADO"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "WSDL:"
        );

        System.out.println(
                direccion + "?wsdl"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Presione Ctrl + C para detener el servicio."
        );
    }   
}