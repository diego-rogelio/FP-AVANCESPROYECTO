package bisonclick;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveLoad {

//=======================GUARDAR DATOS=================    
    public static void saveGame(Player player){
    try{
                                     //Crea un archivo para guardar
        FileWriter fw = new FileWriter("Save.txt");
        
//Guardar datos
        //El \n es un salto de linea
        fw.write(player.getName() + "\n");  //Nombre
        fw.write(player.getBisontes() + "\n");  //Bisontes actuales
        fw.write(player.getBisontesPorSeg() + "\n");  //BPS actuales
        fw.write(player.getBisontesPorClick() + "\n");  //Bisontes por click
        fw.write(player.getCant() + "\n");  //Cantidad edificios
        fw.write(player.getTotalBison() + "\n"); //Bisontes en la partida
        fw.write(player.getCantClick() + "\n");  //Clicks en la partida
        fw.close();
    
    }catch(Exception e){
        System.out.println("Error en el guardado");
    
    }
    
    }

//====================CARGAR DATOS===============================
    //Regresa un objeto Player
    public static Player loadGame(){
    try{
        //Se lee el archivo en un buffer, linea por linea
        BufferedReader br = new BufferedReader(new FileReader("Save.txt"));
        
//Cargar datos
        String Name = br.readLine();  //Nombre
        double Bisontes = Double.parseDouble(br.readLine()); //Convierte lo leido a un double //Bisontes actuales
        double BisontesPorSeg = Double.parseDouble(br.readLine());  //BPS actuales
        double BisontesPorClick = Double.parseDouble(br.readLine()); //Bisontes por click
        double Cant = Double.parseDouble(br.readLine());  //Cantidad edificios
        double TotalBison = Double.parseDouble(br.readLine());  //Bisontes en la partida
        double CantClick = Double.parseDouble(br.readLine());  //Clicks en la partida
        br.close();
        
//Poner los datos en sus variables correspondientes        
        Player player = new Player();
        
        //Se guardan las variables en player
        player.setName(Name);
        player.setBisontes(Bisontes);
        player.setBPS(BisontesPorSeg);
        player.setBPC(BisontesPorClick);
        player.setCant(Cant);
        player.setTotalBison(TotalBison);
        player.setCantClicks(CantClick);
        
        //Se regresan los datos a player
        return player;
        
        
    
    
    }catch(Exception e){
        System.out.println("Error al cargar");
        return null;
    }
    }
         
    
}
