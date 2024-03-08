package com.integrador.msventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private int id;
    private LocalDate fecha;
    private CarritoDto carrito;

}
