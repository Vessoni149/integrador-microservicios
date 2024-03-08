package com.integrador.msventas.reposiroty;

import com.integrador.msventas.dto.CarritoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="carritoApi", url="localhost:8082")
public interface ICarritoApi {
    @GetMapping("/carritos/traer/{id}")
    public CarritoDto getCarrito(@PathVariable int id);

}
