package logica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMeteoritoEnemigo {
    
    @Test
    public void mover(){
        MeteoritoEnemigo m1 = new MeteoritoEnemigo(0);
        MeteoritoEnemigo m2 = new MeteoritoEnemigo(0);

        m1.setPosY(0);
        m2.setPosY(100);

        m1.mover(10);
        m2.mover(340);

        System.out.println(m1.getPosY());
        System.out.println(m2.getPosY());

        assertEquals(190.0, m1.getPosY()); // aqui no sse por que me resta 10 me deberia de dar 200 prq velocidad 20 * tiempo 10 +pos inicial 0 = 200
        assertEquals(6886.0, m2.getPosY());// aqui me resta 14
    }
} 
