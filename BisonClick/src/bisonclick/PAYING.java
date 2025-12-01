package bisonclick;

public class PAYING {
    private String nombre;
    private double plusBisontes;
    private double plusBps;
    private double precio;
    
    

    // Constructor con 3 parámetros
    public PAYING(String nombre,double precio, double plusBisontes, double plusBps) {
        this.nombre = nombre;
        this.plusBisontes = plusBisontes;
        this.plusBps = plusBps;
        this.precio = precio;
    }

    // Getters correctos
    public String getNombre() { 
        return nombre; 
    }

    public double getPlusBisonte() { 
        return plusBisontes; 
    }
    public double getPlusBps(){
    return plusBps;
    }
    public double getPrecio(){
    return precio;
    }

}
