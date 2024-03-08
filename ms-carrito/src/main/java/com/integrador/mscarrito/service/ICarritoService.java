
package com.integrador.mscarrito.service;

import com.integrador.mscarrito.dto.ProductoDto;
import com.integrador.mscarrito.model.Carrito;
import java.util.List;


public interface ICarritoService {
    public List<Carrito> getCarritos();
    public void saveCarrito(Carrito carrito);
    public void deleteCarrito(int id);
    public Carrito findCarrito(int id);
    public void editCarrito(Carrito carrito);
    public String agregarProducto(int idCarrito, int codigoProducto);
    public String eliminarProducto(int idCarrito, int codigoProducto);
    public List<ProductoDto> getProductos();
}
