package com.integrador.msproductos.service;

import com.integrador.msproductos.model.Producto;
import com.integrador.msproductos.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = productoRepo.findAll();
        return listaProductos;
    }


    @Override
    public void deleteProducto(int id) {
        productoRepo.deleteById(id);
    }

    @Override
    public Producto findProducto(int id) {
        Producto producto = productoRepo.findById(id).orElse(null);
        return producto;
    }

    @Override
    public void saveProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void editProducto(Producto producto) {
        this.saveProducto(producto);
    }
}
