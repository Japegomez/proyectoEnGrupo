package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import logica.BaseDatos;

class TestBaseDatos {

	
	@Before
	void abrirConexion() {
	//
	}
	
	@Test
	void compruebaUsuarioTest() {
		BaseDatos.abrirConexion("baseDatos.bd");
		boolean result = BaseDatos.compruebaUsuario("joni");
		assertEquals(true, result);
	}

	@Test
	void compruebaContrasenyaTest() {
		BaseDatos.abrirConexion("baseDatos.bd");
		boolean result = BaseDatos.compruebaContrasenya("joni", "1234");
		assertEquals(true,result);
	}
	
}
