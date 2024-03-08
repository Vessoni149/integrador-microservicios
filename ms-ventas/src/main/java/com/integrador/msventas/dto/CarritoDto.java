package com.integrador.msventas.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDto {
    private int id;
    private double precioTotal;
    private List<String> productos;
}
