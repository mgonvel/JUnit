package com.everis.nttdata_junit_mgv.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.nttdata_junit_mgv.model.Articulo;

public class CarritoCompraServiceImpl implements CarritoCompraServiceI {

	private List<Articulo> cesta = new ArrayList<>();
	@Autowired
	private BaseDatosI baseDatos;

	@Override
	public void limpiarCesta() {
		// TODO Auto-generated method stub
		cesta.clear();

	}

	@Override
	public void addArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		cesta.add(articulo);
	}

	@Override
	public Integer getNumArticulos() {
		// TODO Auto-generated method stub
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		// TODO Auto-generated method stub
		return cesta;
	}

	@Override
	public Double totalPrice() {
		// TODO Auto-generated method stub
		Double total = 0D;
		for (Articulo articulo : cesta) {
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentaje) {
		// TODO Auto-generated method stub
		return precio - precio * porcentaje / 100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		// TODO Auto-generated method stub
		baseDatos.iniciar();
		Articulo articulo = baseDatos.buscarArticulo(idArticulo);
		if (Optional.ofNullable(articulo).isPresent()) {
			return calculadorDescuento(articulo.getPrecio(), porcentaje);
		} else {
			System.out.println("No se ha encontrado el artículo con Id:" + idArticulo);
		}
		return 0D;
	}

	@Override
	public Integer insertar(Articulo articulo) {
		// TODO Auto-generated method stub
		Integer id = baseDatos.insertarArticulo(articulo);
		System.out.println("ID: "+id);
		cesta.add(articulo);
		return id;
	}
}
