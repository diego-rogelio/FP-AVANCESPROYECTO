package bisonclick;


public class Upgrades {
    private String nombre;
    private double costo;
    private double bisontesPorSegundo;
    private double cantidad = 0;

    public Upgrades (String nombre, double costo, double bps, double cantidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.bisontesPorSegundo = bps;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public double getCosto() { return costo; }
    public double getBisontesPorSeg() { return bisontesPorSegundo; }
    public double getCantidad() { return cantidad;}
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
}
