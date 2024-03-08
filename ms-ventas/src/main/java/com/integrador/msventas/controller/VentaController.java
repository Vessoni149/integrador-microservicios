package com.integrador.msventas.controller;

import com.integrador.msventas.dto.CarritoDto;
import com.integrador.msventas.dto.VentaDto;
import com.integrador.msventas.model.Venta;
import com.integrador.msventas.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {
    @Autowired
    private IVentaService ventaServ;


    @GetMapping("/ventas/traer")
    public List<VentaDto> getVentas(){
        List<VentaDto> listaVentas = ventaServ.getVentas();
        return listaVentas;
    }

    @GetMapping("/ventas/traer/{codigo}")
    public VentaDto getVenta(@PathVariable int codigo){
        VentaDto ventaDto = ventaServ.findVenta(codigo);
        return ventaDto;
    }

    @PostMapping("/ventas/crear/{idCarrito}")
    public String saveVenta(@RequestBody Venta venta, @PathVariable int idCarrito){
        ventaServ.saveVenta(venta, idCarrito);
        return "Venta creada";
    }




    @DeleteMapping("/ventas/borrar/{id}")
    public String deleteVenta(@PathVariable int id){
        ventaServ.deleteVenta(id);
        return "Venta eliminada";
    }



    @GetMapping("/carritos/traer/{id}")
    public CarritoDto getCarrito(@PathVariable int id){
        return ventaServ.getCarrito(id);

    }

}
