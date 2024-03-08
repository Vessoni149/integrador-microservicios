package com.integrador.msproductos.service;

import com.integrador.msproductos.model.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> getProductos();
    public void saveProducto(Producto producto);
    public void deleteProducto(int id);
    public Producto findProducto(int id);
    public void editProducto(Producto producto);
}
