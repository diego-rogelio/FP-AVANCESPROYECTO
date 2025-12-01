
package bisonclick;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sonido {
    
    private Clip clip;
    
    public void startSonido(){
        
        try{
            if(clip!= null && clip.isRunning()){  //Si el soniddo ya esta corriendo no se vuelve a poner
            return;
            }
            
            
            InputStream in = getClass().getResourceAsStream("/bisonclick/musica/musiquita.wav");  //Ruta de la musica
            AudioInputStream audio = AudioSystem.getAudioInputStream(in);
            
            //Abre el archivo y lo pone en loop
            clip = AudioSystem.getClip();
            clip.open(audio);
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        
        }catch(Exception e){ //Por si falla al abrir el archivo
            System.out.println("Error al reproducir el audio");
        
        }
    
    
    
    }
    
    public void pararSonido(){  //Para el sonido 
       if(clip!= null && clip.isRunning()){
           clip.stop();
    
    }
    
    
    }
    public void alternarSonido(){  //Para o empieza el sonido
        if(clip != null && clip.isRunning()){
            pararSonido();
        
        }else{
        startSonido();
        }
    
    }
    
}
