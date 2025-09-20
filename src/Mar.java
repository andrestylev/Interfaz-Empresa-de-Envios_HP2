public class Mar extends Envio {
    private int tarifaPorKM = 800;
    private int tarifaPorKilos = 1000;

    public Mar(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    
    @Override
    public double calcularTarifa() {
        return (getPeso() * tarifaPorKM) + (getDistancia() * tarifaPorKilos);
    }
}

