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

        assertEquals(200.0, m1.getPosY(),0);
        assertEquals(6900.0, m2.getPosY(),0);
    }
} 
