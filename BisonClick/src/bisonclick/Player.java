
package bisonclick;


public class Player {
     //Es privada para que solo se pueda modificar aqui
    private double bisontes;
    private double bisontesPorClick;
    private double bisontesPorSeg;
    private double cant;
    private boolean enMenu = false;
    private double cantidadClicks;
    private double totalBison;
    private String playerName;

    //valores de inicio
    public Player() {
        bisontes = 10;
        bisontesPorClick = 1;
        bisontesPorSeg= 0;
        
    }
    
    public void click() {
        cantidadClicks = cantidadClicks + 1;
        bisontes += bisontesPorClick;
        totalBison += bisontesPorClick;  //Para la suma de bisontes total
    }
//===========================ACTUALIZACION DEL JUEGO =========================== 
 
//--------------Para parar el juego cuando estas en un menu --------------------
   public void setEnMenu (boolean valor){ //Modifica el valor de enMenu
        this.enMenu = valor;
   }
   
                 //para booleanos se utiliza is, no get
   public boolean isEnMenu() {  //Permite saber el estado de EnMenu
    return enMenu;
   }

//---------------------- Update de bisontes ------------------------------------
    public void update(double delta) {
        if(!enMenu){ 
        bisontes += bisontesPorSeg * delta;  //Aunque el juego no se actualice siempre igual, ejemplo: delta = 0.5, bisontesPorSeg = 2, si pasaron 0.5 segundos ganas 1 bisonte 
        double ganados = bisontesPorSeg * delta;  // bisontes generados en este tick
        totalBison += ganados;    // registro histórico acumulado
    }                                   //delta representa los segundos entre una vuelta del bucle y la siguiente
                                                                                
    }

//=============================REGRESA VALORES====================    
    public double getCantClick(){  //Cuantos clicks se ha dado
        return cantidadClicks;
    }
    
    public double getTotalBison (){
        return totalBison;
    }
  
    public String getName(){
        return playerName;
    }
    
    public double getBisontes() {
        return bisontes;
    }
    
    public double getBisontesPorSeg() {
        return bisontesPorSeg;
    }
    
    public double getBisontesPorClick() {
        return bisontesPorClick;
    }
    public double getCant(){ //Cantidad de edificios
        return cant;
    }
//==========================ESTABLECE VALORES==================================
    public void setName(String playerName){
        this.playerName = playerName;     
    }
     
    public void setBisontes (double bisontes){
        this.bisontes = bisontes;
    }
    
    public void setBPC (double bisontesPorClick){
        this.bisontesPorClick = bisontesPorClick;   
    }
    
    public void setBPS (double bisontesPorSeg){
        this.bisontesPorSeg = bisontesPorSeg;
    }
    
    public void setCant (double cant){
        this.cant = cant;
    }
    
    public void setCantClicks (double cantidadClicks){
        this.cantidadClicks = cantidadClicks;
    }
    
    public void setTotalBison(double totalBison){
        this.totalBison = totalBison;   
    }
    
 
    
    
    
//-------------------------Sumar bisontes y bps-----------------------------
    public void addBisontes(double cantidad) {
    this.bisontes += cantidad;
}
    public void addbps(double bisontesportiempo){
     this.bisontesPorSeg += bisontesportiempo; //metodo para añadir bisonportiempos
    }
    
    
//================================== COMPRAR MEJORAS=================================
    public void buyUpgrade(Upgrades u) {
        
        if (bisontes >= u.getCosto()) {
            bisontes  -= u.getCosto();
            bisontesPorSeg += u.getBisontesPorSeg();
            u.setCantidad(u.getCantidad() + 1);
            System.out.println("Compraste: " + u.getNombre() + " (+"
                    + u.getBisontesPorSeg() + " cookies/seg)");
        } else {
            System.out.println("No tienes suficientes bisontes");
        }
    }
 //========================================= VENDER MEJORAS ==========================
    public void sellUpgrade(Upgrades u){
   
        if (u.getCantidad() >= 1) {
            bisontes  += u.getCosto();
            bisontesPorSeg -= u.getBisontesPorSeg();
            u.setCantidad(u.getCantidad() - 1);;
            System.out.println("Vendiste: " + u.getNombre() + " (+"
                    + u.getCosto() + " bisontes)" + " Quedan: " + u.getCantidad());
        } else {
            System.out.println("No cuentas con esto");
        }
    }
//===============================MEJORAS DE PAGA =========================
    public void payUpgrade (PAYING p){
    bisontes += p.getPlusBisonte();
    bisontesPorSeg += p.getPlusBps();
    }
 
}

    

