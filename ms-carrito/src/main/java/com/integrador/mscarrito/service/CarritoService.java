package com.integrador.mscarrito.service;

import com.integrador.mscarrito.dto.ProductoDto;
import com.integrador.mscarrito.model.Carrito;
import com.integrador.mscarrito.repository.ApiProductos;
import com.integrador.mscarrito.repository.ICarritoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService implements ICarritoService{
    @Autowired
    private ICarritoRepository carritoRepo;
    @Autowired
    private ApiProductos produApi;
    @Override
    public List<Carrito> getCarritos() {
        List<Carrito> listaCarritos = carritoRepo.findAll();
        return listaCarritos;
    }


    @Override
    public void deleteCarrito(int id) {
        carritoRepo.deleteById(id);
    }

    @Override
    public Carrito findCarrito(int id) {
        Carrito carrito = carritoRepo.findById(id).orElse(null);
        return carrito;
    }

    @Override
    public void saveCarrito(Carrito carrito) {
        carritoRepo.save(carrito);

    }

    @Override
    public void editCarrito(Carrito carrito) {
        this.saveCarrito(carrito);
    }

    @Override
    @CircuitBreaker(name="ms-productos", fallbackMethod = "fallbackAgregarProducto")
    public String agregarProducto(int idCarrito, int codigoProducto){
        Carrito carr = this.findCarrito(idCarrito);

        ProductoDto produDto = produApi.getProducto(codigoProducto);
        List<String> listaProductos = carr.getProductos();
        listaProductos.add(produDto.getNombre());
        carr.setProductos(listaProductos);

        carr.setPrecioTotal(carr.getPrecioTotal() + produDto.getPrecio());


        carritoRepo.save(carr);
        createException();
        return "Producto añadido al carrito";
    }
    public String fallbackAgregarProducto(Throwable throwable){
        return "No se pudo añadir productos al carrito";
    }

    public void createException(){
        throw  new IllegalArgumentException("Prueba Resilience4j y circuit breaker");
    }
    @Override
    public String eliminarProducto(int idCarrito, int codigoProducto) {
        Carrito carr = this.findCarrito(idCarrito);

        ProductoDto produDto = produApi.getProducto(codigoProducto);
        List<String> listaProductos = carr.getProductos();
        listaProductos.remove(Integer.valueOf(codigoProducto));
        carr.setProductos(listaProductos);

        carr.setPrecioTotal(carr.getPrecioTotal() - produDto.getPrecio());


        carritoRepo.save(carr);

        return "Producto eliminado";
    }

    @Override
    public List<ProductoDto> getProductos() {

        return (List<ProductoDto>) produApi.getProductos();
    }


}
