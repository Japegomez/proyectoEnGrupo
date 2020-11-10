package logica;

import javax.swing.*;

public class Cronometro extends JLabel{
	private int hora;
	private int minutos;
	private int segundos;
	private int milesimas;
	private boolean cronometroActivo;
	
	public Cronometro() {
		JLabel tiempo = new JLabel( "00:00:000" );
		this.add(tiempo);
		
		
		new Thread() {
			public void run() {
				boolean cronometroActivo = true; 
				String min="", seg="", mil="";
		        try {
		            while( cronometroActivo ){
		                Thread.sleep( 4 );
		                milesimas += 4;
		                if( milesimas == 1000 ){
		                    milesimas = 0;
		                    segundos += 1;
		                    if( segundos == 60 ){
		                        segundos = 0;
		                        minutos++;
		                    }
		                }
		                tiempo.setText( min + ":" + seg + ":" + mil );                
		            }
		        }catch(Exception e){}
		        //Cuando se reincie se coloca nuevamente en 00:00:000
		        tiempo.setText( "00:00:000" );
		    }
			
			
		};
		
		
	}
	public void pausarCrono() {
		setCronometroActivo(false);
	}
	public boolean isCronometroActivo() {
		return cronometroActivo;
	}
	public void setCronometroActivo(boolean cronometroActivo) {
		this.cronometroActivo = cronometroActivo;
	}

}
