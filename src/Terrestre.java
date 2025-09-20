public class Terrestre extends Envio {
    private int tarifaPorKM = 1500;
    private int tarifaPorKilos = 2000;

    public Terrestre(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

     @Override
    public double calcularTarifa() {
        return (getPeso() * tarifaPorKM) + (getDistancia() * tarifaPorKilos);
    }
}
