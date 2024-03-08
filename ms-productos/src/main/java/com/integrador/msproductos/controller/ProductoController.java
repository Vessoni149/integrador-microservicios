package com.integrador.msproductos.controller;

import com.integrador.msproductos.model.Producto;
import com.integrador.msproductos.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService productoServ;

    @Value("${server.port}")
    private int serverPort;
    @GetMapping("/productos/traer")
    public List<Producto> getProductos(){
        System.out.println("--------Estoy en el puerto" + serverPort);
        return productoServ.getProductos();
    }

    @GetMapping("/productos/traer/{codigo}")
    public Producto getProducto(@PathVariable int codigo){
        Producto produ = productoServ.findProducto(codigo);
        return produ;
    }

    @PostMapping("/productos/crear")
    public String saveProducto(@RequestBody Producto producto){
        productoServ.saveProducto(producto);
        return "Producto creado";
    }


    @DeleteMapping("/productos/borrar/{id}")
    public String deleteProducto(@PathVariable int id){
        productoServ.deleteProducto(id);
        return "Producto eliminado";
    }

    @PutMapping("/productos/editar")
    public String editProducto(@RequestBody Producto producto){
        productoServ.editProducto(producto);
        return "Producto editado";
    }


}
