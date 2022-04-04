package com.everis.nttdata_junit_mgv.services;

import com.everis.nttdata_junit_mgv.model.Articulo;

public interface BaseDatosI {
	public void iniciar();
	public Integer insertarArticulo(Articulo articulo);
	public Articulo buscarArticulo(Integer identificador);
}
