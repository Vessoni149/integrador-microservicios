
package com.integrador.mscarrito.model;

import com.integrador.mscarrito.dto.ProductoDto;
import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double precioTotal;

    @ElementCollection
    private List<String> productos;
}
