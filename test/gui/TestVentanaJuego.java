package gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.MeteoritoEnemigo;
import logica.Usuario;

class TestVentanaJuego {
	VentanaJuego v1;
	@BeforeEach
	void setUp() throws Exception {
		Usuario usu = new Usuario("javi", "usu");
		v1 = new VentanaJuego(usu, new VentanaMenu("menu", usu, new VentanaLogin("login")));
		v1.creaCronometro();
		ArrayList<MeteoritoEnemigo> arrayMeteoritosEnPantalla = new ArrayList<>();
		MeteoritoEnemigo m1 = new MeteoritoEnemigo(v1.cro.getSegundos()); //PosX = 150
		arrayMeteoritosEnPantalla.add(m1);
		v1.arrayMeteoritosEnPantalla = arrayMeteoritosEnPantalla;

		
	}

	@Test
	void testMeteoritoCorrecto() {
		MeteoritoEnemigo m2 = new MeteoritoEnemigo(v1.cro.getSegundos()); //PosX = 150
		assertEquals(false, v1.nuevoMeteoritoCorrecto(m2.getPosX()));
	}
	
	@Test
	void testEstaMuerto() {
		v1.setVidaNavePartida(-50);
		assertEquals(true, v1.estaMuerto());
		v1.setVidaNavePartida(50);
		assertEquals(false, v1.estaMuerto());
		v1.setNave(null);
		assertEquals(false, v1.estaMuerto());
		
	}

}
