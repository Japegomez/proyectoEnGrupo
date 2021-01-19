package logica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestPartida {
    
    @Test
    public void  equals(){
        Partida p1 = new Partida(100,1294);
        Partida p2 = new Partida(200,23847);
        Partida p3 = new Partida(232,1294);

        assertEquals(false,p1.equals(p2));
        assertEquals(true,p1.equals(p3));
    }
    @Test
    public void testToString(){
        Partida p4 = new Partida(23423,644234);
        Partida p5 = new Partida(2341234,834643);

        String s1 = "puntuacion: 23423.0 / fecha: Thu Jan 01 01:10:44 CET 1970";
        String s2 = "puntuacion: 2341234.0 / fecha: Thu Jan 01 01:13:54 CET 1970";
        
        assertEquals(s1,p4.toString());
        assertEquals(s2,p5.toString());
        
    }
}
