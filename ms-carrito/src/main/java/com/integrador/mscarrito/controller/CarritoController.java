package com.integrador.mscarrito.controller;

import com.integrador.mscarrito.dto.ProductoDto;
import com.integrador.mscarrito.model.Carrito;
import com.integrador.mscarrito.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CarritoController {
    @Autowired
    private ICarritoService carritoServ;

    @GetMapping("/productos/traer")
    public List<ProductoDto> getProductos(){
        return carritoServ.getProductos();
    }

    @GetMapping("/carritos/traer")
    public List<Carrito> getCarritos(){
        return carritoServ.getCarritos();
    }

    @GetMapping("/carritos/traer/{codigo}")
    public Carrito getCarrito(@PathVariable int codigo){
        Carrito carr = carritoServ.findCarrito(codigo);
        return carr;
    }

    @PostMapping("/carritos/crear")
    public String saveCarrito(@RequestBody Carrito carrito){
        carritoServ.saveCarrito(carrito);
        return "Carrito creado";
    }



    @DeleteMapping("/carritos/borrar/{id}")
    public String deleteCarrito(@PathVariable int id){
        carritoServ.deleteCarrito(id);
        return "Carrito eliminado";
    }

//    @PutMapping("/carritos/editar/{idCarrito}/{codigoProducto}")
//    public String editCarrito(@PathVariable int idCarrito, @PathVariable int codigoProducto) {
//        carritoServ.agregarProducto(idCarrito,codigoProducto);
//        return "Carrito editado";
//    }

    @PutMapping("/carritos/productos/agregar/{idCarrito}/{codigoProducto}")
    public String agregarProducto(@PathVariable int idCarrito, @PathVariable int codigoProducto){
        return carritoServ.agregarProducto(idCarrito,codigoProducto);

    }

    @PutMapping("/carritos/productos/eliminar/{idCarrito}/{codigoProducto}")
    public String eliminarProducto(@PathVariable int idCarrito, @PathVariable int codigoProducto) {
        return carritoServ.eliminarProducto(idCarrito, codigoProducto);
    }
}
