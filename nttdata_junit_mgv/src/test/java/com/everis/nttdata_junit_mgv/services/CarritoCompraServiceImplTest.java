package com.everis.nttdata_junit_mgv.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.nttdata_junit_mgv.model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {
	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	@Mock
	private BaseDatosI baseDatos;

	@Test
	public void testLimpiarCesta() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {
		carritoService.addArticulo(new Articulo("Mochila", 19.6));
		assertEquals(1, carritoService.getNumArticulos());
	}

	@Test
	public void testGetNumArticulos() {
		carritoService.addArticulo(new Articulo("Zapato", 25.4));
		carritoService.addArticulo(new Articulo("Calcetín", 5.4));
		carritoService.addArticulo(new Articulo("Camiseta Interior", 10.8));
		assertEquals(3, carritoService.getNumArticulos());
	}

	@Test
	public void testGetArticulos() {
		carritoService.addArticulo(new Articulo("Bañador", 20.9));
		carritoService.addArticulo(new Articulo("Gafas de Sol", 15.4));
		carritoService.addArticulo(new Articulo("Sombrero", 13.8));
		carritoService.addArticulo(new Articulo("Jersey", 25.4));
		carritoService.addArticulo(new Articulo("Botas", 23.6));
		assertEquals(carritoService.getArticulos().size(), carritoService.getNumArticulos());
	}

	@Test
	public void testTotalPrice() {
		carritoService.addArticulo(new Articulo("Gorra", 15.0));
		carritoService.addArticulo(new Articulo("Sudadera", 30.0));
		assertEquals(45.0, carritoService.totalPrice());
	}

	@Test
	public void testCalculadorDescuento() {
		assertEquals(18.75, carritoService.calculadorDescuento(25.0, 25.0));
	}

	@Test
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta", 20.0);
		when(baseDatos.buscarArticulo(1)).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(Double.valueOf(18D), res);
		verify(baseDatos, atLeast(1)).buscarArticulo(1);
	}
	
	@Test
	public void testInsertar() {
		Articulo articulo = new Articulo("Chaqueta Vaquera",22.0);
		when(baseDatos.insertarArticulo(articulo)).thenReturn(1);
		Integer id=carritoService.insertar(articulo);
		assertEquals(1, id);
		assertEquals("Chaqueta Vaquera", carritoService.getArticulos().get(0).getNombre());
		System.out.println("Nombre del producto: "+carritoService.getArticulos().get(0).getNombre());
		assertEquals(22.0, carritoService.getArticulos().get(0).getPrecio());
		System.out.println("Precio del producto: "+carritoService.getArticulos().get(0).getPrecio());
		verify(baseDatos, times(1)).insertarArticulo(articulo);
	}

}
