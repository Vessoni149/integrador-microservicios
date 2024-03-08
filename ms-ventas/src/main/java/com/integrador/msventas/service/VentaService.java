package com.integrador.msventas.service;

import com.integrador.msventas.dto.CarritoDto;
import com.integrador.msventas.dto.VentaDto;
import com.integrador.msventas.model.Venta;
import com.integrador.msventas.reposiroty.ICarritoApi;
import com.integrador.msventas.reposiroty.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private ICarritoApi carritoApi;

    @Override
    @CircuitBreaker(name="ms-carrito", fallbackMethod="fallbackGetVentas")
    @Retry(name="ms-carrito")
    public List<VentaDto> getVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        List<VentaDto> listaVentasDto = new ArrayList<>();

        for(Venta venta : listaVentas){
            VentaDto ventaDto = new VentaDto();
            CarritoDto carritoDto = new CarritoDto();

            ventaDto.setFecha(venta.getFecha());
            ventaDto.setId(venta.getId());

            carritoDto.setId(venta.getIdCarrito());
            carritoDto.setProductos(carritoApi.getCarrito(venta.getIdCarrito()).getProductos());
            carritoDto.setPrecioTotal(carritoApi.getCarrito(venta.getIdCarrito()).getPrecioTotal());

            ventaDto.setCarrito(carritoDto);

            listaVentasDto.add(ventaDto);
        }
        createException();
        return listaVentasDto;
    }
    public List<VentaDto> fallbackGetVentas(Throwable throwable){
        List<VentaDto> listaVentasDto = new ArrayList<>();
        return listaVentasDto;

    }

    public void createException(){
        throw  new IllegalArgumentException("Prueba Resilience4j y circuit breaker");
    }
    @Override
    public void deleteVenta(int id) {
        ventaRepo.deleteById(id);
    }

    @Override
    @CircuitBreaker(name="ms-carrito", fallbackMethod = "fallbackFindVenta")
    public VentaDto findVenta(int id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        VentaDto ventaDto = new VentaDto();
        ventaDto.setId(venta.getId());
        ventaDto.setFecha(venta.getFecha());

        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setId(venta.getIdCarrito());
        carritoDto.setProductos(carritoApi.getCarrito(venta.getIdCarrito()).getProductos());
        carritoDto.setPrecioTotal(carritoApi.getCarrito(venta.getIdCarrito()).getPrecioTotal());
        ventaDto.setCarrito(carritoDto);

        createException();;
        return ventaDto;
    }

    public VentaDto fallbackFindVenta(Throwable throwable){
        VentaDto ventaDto = new VentaDto();
        return ventaDto;
    }

    @Override
    public void saveVenta(Venta venta, int idCarrito) {
        // Obtener el carrito correspondiente al ID proporcionado
        CarritoDto carrito = carritoApi.getCarrito(idCarrito);

        // Asociar la venta con el carrito obtenido
        venta.setIdCarrito(carrito.getId());


        // Guardar la venta en la base de datos
        ventaRepo.save(venta);
    }



    public CarritoDto getCarrito(int id){
        CarritoDto carrito = carritoApi.getCarrito(id);
        return carrito;
    }

}
