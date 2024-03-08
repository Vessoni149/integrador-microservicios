package com.integrador.msventas.service;

import com.integrador.msventas.dto.CarritoDto;
import com.integrador.msventas.dto.VentaDto;
import com.integrador.msventas.model.Venta;

import java.util.List;

public interface IVentaService {
    public List<VentaDto> getVentas();
    public void saveVenta(Venta venta, int idCarrito);
    public void deleteVenta(int id);
    public VentaDto findVenta(int id);
    public CarritoDto getCarrito(int id);
}
