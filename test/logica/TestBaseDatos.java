package logica;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import logica.BaseDatos;

class TestBaseDatos {

	
	@Before
	void abrirConexion() {
		BaseDatos.abrirConexion("baseDatos.bd");
		BaseDatos.registrarUsuario("jon", "hola");
	}
	
	@Test
	void compruebaUsuarioTest() {
		boolean result = BaseDatos.compruebaUsuario("jon");
		assertEquals(true, result);
	}

	@Test
	void compruebaContrasenyaTest() {
		boolean result = BaseDatos.compruebaContrasenya("jon", "hola");
		assertEquals(true,result);
	}
	
}
