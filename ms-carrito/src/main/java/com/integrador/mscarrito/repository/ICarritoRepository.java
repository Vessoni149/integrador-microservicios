
package com.integrador.mscarrito.repository;

import com.integrador.mscarrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer>{
    
}
