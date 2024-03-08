package com.integrador.mscarrito.repository;

import com.integrador.mscarrito.dto.ProductoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name="ms-productos")
public interface ApiProductos {
    @GetMapping("/productos/traer/{codigo}")
    public ProductoDto getProducto(@PathVariable int codigo);
    @GetMapping("/productos/traer")
    public List<ProductoDto> getProductos();
}
