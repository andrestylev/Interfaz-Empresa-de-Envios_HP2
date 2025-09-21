package models;

public class Aereo extends Envio {
    private double tarifaPorKM = 5000;
    private double tarifaPorKilos = 4000;

    public Aereo(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    @Override
    public double calcularTarifa() {
        return (getPeso() * tarifaPorKM) + (getDistancia() * tarifaPorKilos);
    }
}
 
