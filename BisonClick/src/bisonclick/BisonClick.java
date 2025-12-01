package bisonclick;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/* Se usa ======= para titulos
y ---------- para subtitulos*/


public class BisonClick {
//====================================COLORES TABLA ANSI===================================================================================================
    public static final String NEGRO = "\u001B[30m";         //30 significa negro        
    public static final String ROJO = "\u001B[31m";          // 31 significa rojo
    public static final String VERDE = "\u001B[32m";         //32 significa verde 
    public static final String AMARILLO = "\u001B[33m";      //33 significa amarillo 
    public static final String AZUL = "\u001B[34m";          //34 significa azul
         //System.out.println(NEGRO + "Texto Negro" + RESET);   negro, es la variable y el reset bloquea que se pase al otro print
    
    
//======================================MAIN=============================================================================================================    
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Player player = new Player();
    
    int entrada;
    Instant lastUpdate = Instant.now();
   
    
//====================================LISTA DE MEJORAS==================================================================================================
        List<Upgrades> upgrades = new ArrayList<>();
                                  //En upgrades se declaro que seria string, double, double
        upgrades.add(new Upgrades(ROJO + "Dos enamorados", 15, 0.1, 0));   //nombre, precio, cuantos bps da, cuantos tienes
        upgrades.add(new Upgrades(ROJO + "Salon TEC2", 100, 1.5, 0));
        upgrades.add(new Upgrades(ROJO + "Edificio TEC2", 750.0, 5.0, 0));
        upgrades.add(new Upgrades(ROJO + "BisonForo", 2000.0, 12.0, 0));
        upgrades.add(new Upgrades(ROJO + "Gimnasio TEC2", 5000.0, 30.0, 0));
        upgrades.add(new Upgrades(ROJO + "Cafeteria TEC2", 12000.0, 60.0, 0));
        upgrades.add(new Upgrades(ROJO + "Biblioteca TEC2", 30000.0, 150.0, 0));
        upgrades.add(new Upgrades(ROJO + "TECNM", 10000000.0, 10000.0, 0));
        
//====================================LISTA DE MEJORAS DE PAGO===========================================================================================  
        List<PAYING> paying = new ArrayList<>();
        paying.add(new PAYING(VERDE+"500 bisontes", 500.0, 500.0, 0.0)); //nombre, precio, cuantos bisontes da, cuanto bps da
        paying.add(new PAYING(VERDE+"1000 bisontes", 1000.0, 1000.0, 0.0));
        paying.add(new PAYING(VERDE+"10 BPS", 2000.0, 0.0, 10.0));
        paying.add(new PAYING(VERDE+"Zoologico de Aldama (80 BPS)", 10000.0, 0.0, 100.0));
        paying.add(new PAYING(VERDE+"TEC2 (100 BPS)", 10000.0, 0.0, 100.0));
        
             
        
        System.out.println(ROJO + "================== BIENVENIDO ===================" );
        System.out.println( AZUL + "============= Presione 1 para jugar ============= ");
        System.out.println(NEGRO + "=================================================");
        System.out.println( AZUL + "===== Presione tecla cualquiera para salir ====== ");
        System.out.println(NEGRO +"=================================================");
        System.out.print("                        ");
        String entradaTexto = input.nextLine();   // Lee la entrada como texto

          
        
     
        
        //Si se desea jugar se inicia todo el bucle y demas
        if(entradaTexto.equals("1")){
            
            System.out.println("");
            System.out.println(AZUL+ "======== Presiona 1 para cargar partida ========");
            System.out.println(AZUL +"= Presiona cualquier numero para nueva partida =");
            System.out.println(NEGRO +"================================================");
            System.out.print("                        ");
            entrada = input.nextInt();
            input.nextLine(); 
            
//===============================================CARGAR PARTIDA================================================================================================
            if(entrada == 1){
                player = SaveLoad.loadGame();
            
            if(player == null){
                
                System.out.println(ROJO + "No se pudo cargar. Creando partida nueva");
                player = new Player();
                System.out.println("");
                System.out.println(AMARILLO + "Nombre del jugador: ");
                String nombre = input.nextLine();
            
                player.setName(nombre);
            
                System.out.println(VERDE +"Diviertete " + player.getName() + "!!!");     
                System.out.println("");
                
            }else{
                System.out.println("");
                System.out.println(AMARILLO + "Partida cargada correctamente");
                System.out.println("");
            }
//-------------------PARTIDA NUEVA---------------------------------
            }else{
                
                System.out.println("");
                System.out.println(AZUL +"Nombre del jugador: ");
                String nombre = input.nextLine();
             
                player.setName(nombre);
            
                System.out.println(VERDE + "Diviertete " + player.getName() + "!!!");       
                System.out.println("");
            }
        }else{
        System.exit(0);
    }
            

//================================================MUSICA=====================================================================================================
        //Empieza la musica
         Sonido musica = new Sonido();  
         musica.startSonido();
        
        
//============================================BUCLE DEL JUEGO=================================================================================================
    
        //El while true crea un bucle infinito
        while (true) {
            
          
            
            // CALCULAR TIEMPO ENTRE ACTUALIZACIONES
            
            //Obtiene el momento actual
            Instant now = Instant.now();
            
            //Calcula la diferencia entre el instante anterior y el actual
            double delta = Duration.between(lastUpdate, now).toMillis() / 1000.0;  //Convierte milisegundos a segundos
            lastUpdate = now;

            // Actualizar bisontes automáticas
            player.update(delta); //Representa el tiempo entre actualizaciones en segundos
                       

            // Mostrar información
                                              //Da formato, %f = que es un numero decimal, .1 = cuantos decimales se quieren mostrar
            System.out.println("Bisontes: " + String.format("%.1f", player.getBisontes())  + "| BPS: " + String.format("%.1f",player.getBisontesPorSeg())
            + " ('P' para opciones)");

            
        System.out.print("> ");
            String menu = input.nextLine().trim().toUpperCase();
            
            //Muestra un menu con las opciones
            if (menu.equals("P")){
            System.out.println("");
                System.out.println(VERDE + "========================Elige una opcion========================");
                System.out.println(AZUL + "'2' para salir, 'C' para comprar, 'V' para vender");
                System.out.println(AZUL+ "'I' para info, 'T' para compra online, 'S' para guardar");
                System.out.println(AZUL+ "'R' para jugar a la ruleta ");
                System.out.println(AZUL+ "'M' para quitar/poner musica, 'E' para salir de este menu");
                menu = input.nextLine().trim().toUpperCase();
                System.out.println("");
                
            }


            
//========================================================MENU==================================================================================================
        Random random = new Random(); //ruleta
        switch(menu) {
            
            //Opciones 
            
            //Para salir de algun menu
            case "E":
                break;
                
            //Cuando se presiona B cuenta como un click
            case "B":
                player.click();       
                break;
                
            //Cuando se presiona 2 se cierra el programa
            case "2":
                System.out.println(ROJO + "Quieres salir? 1 = no, 2 = si");
                entrada = input.nextInt();
                if(entrada == 2){
                    System.out.println(VERDE +"Gracias por jugar");
                    musica.pararSonido();
                System.exit(0);
                }
                else{
                System.out.println("");
                break;
                }
             
            //pone pausa o play a la musica
            case "M":
                musica.alternarSonido();
                break;
                
//---------------------------------------Compra---------------------------------------
            case "C":
                System.out.println("");
                System.out.println(NEGRO + "==========================");
                System.out.println(VERDE + "Mejoras disponibles:");  
                System.out.println(AZUL + "Tienes: " + String.format("%.0f", player.getBisontes()) + " bisontes");
                System.out.println("");
                System.out.println(AZUL +"Producto           / Costo    ");
                                     //Tamano de la lista
                    for (int i = 0; i < upgrades.size(); i++) {
                        Upgrades u = upgrades.get(i);
                        
                        //Imprime los elementos de la lista
                        
                        System.out.println((i + 1) + ". " + u.getNombre() +" (" + u.getCosto() + " bisontes)  (+" +
                                            String.format("%.2f", u.getBisontesPorSeg()) + " bps)" + "  Actualmente tienes: " + u.getCantidad());
                    }
                    
                    System.out.println(ROJO + "'9' para salir de este menu");
                    
                    System.out.print(AZUL +"Selecciona numero: ");
                    //Esta dentro de try porque es probable que pueda ocurrir un error, si llega a ocurrir
                    //se pasa directamente a catch, en donde dice que la entrada no es valida
                    try {
                        int opcion =input.nextInt();
                        if(opcion == 9){
                            System.out.println(AZUL + "Regresando al menu de inicio...");
                            System.out.println("");
                            break;
                        }
                        if (opcion >= 1 && opcion <= upgrades.size()) {
                            player.buyUpgrade(upgrades.get(opcion - 1));
                            System.out.println(NEGRO+ "==========================");
                            System.out.println("");
                        } else {
                            System.out.println(ROJO+"Opcion invalida.");
                            System.out.println(NEGRO+"==========================");
                            System.out.println("");
                        }
                        
                    //Atrapa el error, en este caso una excepcion de las entradas validas
                    } catch (Exception e) {
                        System.out.println(ROJO+ "Entrada no valida.");
                        System.out.println(NEGRO+ "==========================");
                        System.out.println("");
                    }
                    break;
                    
//======================================================RULETA====================================================================================================
                        case "R":
                                System.out.println(VERDE + "Bienvenido a la ruleta de bisontes" );
                                System.out.println("Que quieres apostar? Bisontes (G) o Bisontes por segundos (Q)?");

                                try {
                                menu = input.nextLine().trim().toUpperCase();

                                if(menu.equals("G") || menu.equals("Q")){
                                    System.out.println(AZUL + "Presiona O para girar la ruleta, si cae en par te doblamos tu apuesta, si no pierdes toda tu apuesta");
                               
                                String giro = input.nextLine().trim().toUpperCase();
                                if(!giro.equals("O")){
                                    System.out.println(VERDE + "Entrada inválida.");
                                    break;
                                }

                                int numero = random.nextInt(12) + 1; // 1 a 12

                                System.out.println(AZUL + "Procesando...");
                                
                                   // Mostrar ruleta
                               
                                System.out.println(" 11  12  1   2");
                                System.out.println("              ");
                                System.out.println(" 10          3");
                                System.out.println("              ");
                                System.out.println(" 9           4");
                                System.out.println("              ");
                                System.out.println(" 8   7   6   5");
                                
                                

                                try {
                                    Thread.sleep(2000); // espera 2 segundos
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                             
          
                                if(numero % 2 == 0){
                                    System.out.println(AMARILLO + "Felicidades! Has ganado." );
                                    if(menu.equals("G")) {
                                        player.setBisontes(player.getBisontes() * 2);
                                        System.out.println("Tus bisontes se han doblado.");
                                    } else {
                                        player.setBPS(player.getBisontesPorSeg() * 2);
                                        System.out.println("Tu BPS se ha doblado.");
                                    }
                                } else {
                                    System.out.println(NEGRO + "Mala suerte, no ganaste." );
                                    if(menu.equals("G")) player.setBisontes(0);
                                    else player.setBPS(0);
                                }

                            } else {
                                System.out.println(ROJO+"Opción invalida.");
                            }
                        } catch(Exception e){
                            System.out.println(ROJO+ "Error en la ruleta " + e.getMessage());
                        }
                        break;

//---------------------------------------VENTA--------------------------------------                 
                    
            case "V":
                System.out.println("");
                System.out.println(NEGRO+"==========================");
                System.out.println(VERDE+"Disponibles para venta:");
               for (int i = 0; i < upgrades.size(); i++) {
                        Upgrades u = upgrades.get(i);
                        
                        //Imprime los elementos de la lista
                        
                        System.out.println((i + 1) + ". " + u.getNombre() + " ( Tienes: " + u.getCantidad() + " )");
                         
               }    
                    System.out.print(AZUL+"Selecciona numero: ");      
                    //Esta dentro de try porque es probable que pueda ocurrir un error, si llega a ocurrir
                    //se pasa directamente a catch, en donde dice que la entrada no es valida
                    try {
                        int opcion =input.nextInt();
                        if(opcion == 9){
                            System.out.println(AZUL+"Regresando al menu de inicio...");
                            System.out.println("");
                            break;
                        }
                        if (opcion >= 1 && opcion <= upgrades.size()) {
                            player.sellUpgrade(upgrades.get(opcion - 1));
                            System.out.println("==========================");
                            System.out.println("");
                        } else {
                            System.out.println(ROJO+"Opcion invalida.");
                            System.out.println(NEGRO+"==========================");
                            System.out.println("");
                        }
                        
                    //Atrapa el error, en este caso el intentar poner un input diferente a 1,2,3
                    } catch (NumberFormatException e) {
                        System.out.println(ROJO+"Entrada no valida.");
                        System.out.println(NEGRO+"==========================");
                        System.out.println("");
                    }
                    break;   
                    
               case "T":
                    String numeroTarjeta;
                    String nombreTarjeta;
                    String expiracion;
                    String cvv;
                   

                    System.out.println("");
                    System.out.println(VERDE+"============MENU DE COMPRA============");
                    System.out.println(AZUL+"Este menu es para comprar cosas usando dinero real");
                    System.out.println(AZUL+"Selecciona cual opcion quieres comprar ");
                    System.out.println("");
                    
                    String producto;
                    
                    for (int i = 0; i < paying.size(); i++) {
                        PAYING p = paying.get(i);
                        System.out.println((i + 1) + ") " + p.getNombre() + " | Costo: $" + p.getPrecio() + " pesos");
                    }
                    System.out.println(AZUL+"'9' Para salir de este menu");
                      
//===========================================OPCION DE COMPRA DINERO==============================================================================================
                    System.out.println("");
                    System.out.println(VERDE+"Que deseas comprar?");
                      producto = input.nextLine();
                      
                      if(producto.equals("9")){
                            System.out.println(AZUL+"Regresando al menu de inicio...");
                            System.out.println("");
                            break;
                        }
                  
                    PAYING compraElegida = null;  //Se declara compra elegida,si esta dentro del try no se puede usar despues
                    
                    try{
                    int indice = Integer.parseInt(producto)-1; //agarra el numero y le resta 1 para coincidir con el id del array
                    
                      compraElegida = paying.get(indice);
                      
                      
                    switch(indice){
                      
                    case 0: {
                        paying.get(0);
                        System.out.println(VERDE+"Elegiste '500 bisontes' por un precio de $500 pesos");
                        
                        break;
                    }

                    case 1: {
                        paying.get(1);
                        System.out.println(VERDE+"Elegiste '1000 bisontes' por un precio de $1000 pesos");
                        break;
                    }

                    case 2: {
                        paying.get(2);
                        System.out.println(VERDE+"Elegiste '10 BPS' por un precio de $2000 pesos");
                        break;
                    }

                    case 3: {
                        paying.get(3);
                        System.out.println(VERDE+"Elegiste 'TEC2' (100 bps) por un precio de $10000 pesos");
                        break;
                    }

                    
                }
                    }catch(Exception e){
                        System.out.println(ROJO+"Opcion invalida");
                        System.out.println("");
                        break;
                    }    

                      
// ====================== SWITCH DE METODO DE PAGO ============================================================================================================
                    //if para elegir la compra
                    
                    int cantidadDigitos;
                    String correo;
                    String opcion;
                    System.out.println("");
                     System.out.println(AMARILLO+"============Metodo de pago============");
                     System.out.println("PayPal = 1 / Tarjeta = 2");
                    opcion = input.nextLine();
                    switch (opcion) {
                        
//-----------------------------PAYPAL--------------------------------------------------
                        case "1":  
                            
                            player.setEnMenu(true);// Pone el juego en pausa
                            
                            System.out.println("");
                            System.out.println(VERDE+"-----Bienvenido al sistema de PayPal-----");
                            System.out.println(AZUL+"Inserta correo:");
                            correo = input.nextLine();

                            if (correo.contains("@gmail.com")) {
                                
                                
                                
                                System.out.println("");
                                System.out.println(NEGRO+"=======================================");
                                System.out.println(AZUL+"Procesando.........");
                                System.out.println("");
                                
                                //para poner un tiempo entre el procesando y el sig mensaje
                                try {
                                 Thread.sleep(1500); // 1.5 segundos 
                                } catch (InterruptedException e) {
                                 e.printStackTrace();
                                  }
                                
                                System.out.println(VERDE+"Transaccion exitosa");
                                System.out.println(VERDE+"Recibiste: " + compraElegida.getNombre());  //AQUI VAN EL CODIGO DE COMPRA ONLINE
                                                               
                               player.payUpgrade(compraElegida); //Se actualiza los datos de la compra en player
                               
                              
                            } else {
                                System.out.println(ROJO+"Correo invalido, intenta de nuevo mas tarde");
                                System.out.println("");
                            }
                            
                             player.setEnMenu(false); //Reanuda el juego
                             lastUpdate = Instant.now(); //Se pone para que la ultima actualizacion se tome como este momento,
                                                          //Si no se pone, el tiempo acumulado entre los EnMenu se juntan y se actualizan despues
                               
                            break;
// -------------------------------------------------- TARJETA -----------------------------------------------------------
                        case "2": 
                            player.setEnMenu(true);
                            
                            System.out.println("");
                            System.out.println(VERDE+"============Datos de tarjeta============");
                            System.out.println(AZUL+"Numero de tarjeta:  ____  ____  ____  ____");
                            numeroTarjeta = input.nextLine();
                            
                            cantidadDigitos = numeroTarjeta.length(); //cuenta los digitos que tiene el numero
                            
                            //Filtro apra que continue si tiene 16 digitos
                            if (cantidadDigitos == 16) {
                                System.out.println("");  

                                 
                            } else {
                              
                                System.out.println(ROJO+"Datos incorrectos");
                                System.out.println("");
                            break;    
                            }

                            System.out.println(AZUL+"Nombre en la tarjeta:  ________________________");
                            nombreTarjeta = input.nextLine();
                            
                            System.out.println("");
                            System.out.println(AZUL+"Fecha de expiración (MM/AA):  __ / __");
                            expiracion = input.nextLine();
                            
                            //Filtro para que continue si tiene 4 o 6 digitos
                            cantidadDigitos = expiracion.length();
                            if(cantidadDigitos == 4 || cantidadDigitos == 6){
                                System.out.println("");
                            }else{
                                System.out.println(ROJO+"Datos incorrectos");
                                System.out.println("");
                                break;
                            }

                            System.out.println(VERDE+"CVV:  ___");
                            cvv = input.nextLine();
                            
                            //Filtro para que continue si tiene 3 digitos
                            cantidadDigitos = cvv.length();
                            if(cantidadDigitos == 3){
                                System.out.println("");
                            }else{
                                System.out.println(ROJO+"Datos incorrectos");
                                System.out.println("");
                                break;
                            }
                            

                            System.out.println(NEGRO+"=======================================");
                            
                                System.out.println(AZUL+"Procesando.........");
                                System.out.println("");
                                //para poner un tiempo entre el procesando y el sig mensaje
                                try {                                  
                                 Thread.sleep(1500); // 1.5 segundo 
                                } catch (InterruptedException e) {
                                 e.printStackTrace();
                                  }                           
                             System.out.println(VERDE+"Transaccion exitosa");
                                System.out.println(AZUL+"Recibiste " + compraElegida.getNombre());  //AQUI VAN EL CODIGO DE COMPRA ONLIN
                                
                              player.payUpgrade(compraElegida);//Se actualiza los datos de la compra en player
                            
                            
                            player.setEnMenu(false); //Reanuda el juego
                            lastUpdate = Instant.now(); //Se pone para que la ultima actualizacion se tome como este momento,
                                                         //Si no se pone, el tiempo acumulado entre los EnMenu se juntan y se actualizan despues
                            break;
                            
                        default:
                            System.out.println(ROJO+"Opcion invalida");
                            System.out.println("");
                            break;
                    }

                    break; 
//======================================== INFO DEL JUGADOR ===================================================================================================                 
               case "I":
                   System.out.println("");
                   System.out.println("\u001B[38;2;255;0;0mTexto rojo en RGB\u001B[0m"+"============Datos del jugador============");
                   System.out.println("");
                   System.out.println(AZUL+"Nombre: " + player.getName());
                   System.out.println(AZUL+"Tienes: " +player.getBisontes() + " bisontes");
                   System.out.println(AZUL+"Actualmente generas: " + player.getBisontesPorSeg() + " bisontes por segundo");
                   System.out.println(AZUL+"Haz generado: " + player.getCantClick() + " bisontes manualmente");
                   System.out.println(AZUL+"Haz generado: " + player.getTotalBison() + " bisontes desde el inicio");
                   System.out.println("");
                   System.out.println("");
                
                   break;
                   
//==================================================GUARDAR PARTIDA=========================================================================================
                case "S":
                    SaveLoad.saveGame(player);
                    System.out.println("Partida guardada.");
                    break;
                    
              
                   

                } //  Aquí termina el switch correctamente

        } //  Aquí termina el while, bucle del juego
        } // Aquí termina el main
    }  // aqui termina la clase



                
        
     
    
    

               
              