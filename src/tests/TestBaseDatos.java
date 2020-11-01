package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logica.BaseDatos;

class TestBaseDatos {

	BaseDatos base = new BaseDatos();
	@SuppressWarnings("static-access")
	@Test
	void compruebaUsuariotest() {
		boolean result = base.compruebaUsuario("jonlopez");
		assertEquals(false, result);
		fail("Not yet implemented");
	}

}
