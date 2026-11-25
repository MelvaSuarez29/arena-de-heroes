package heroes;

public class Arquero extends Personaje{

    public Arquero(int id, String nombre, int vida, int nivel) {
        super(id, nombre, vida, nivel);
    }

    @Override
    public int atacar() {
        return 9 + 2 * getVida();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nClase: Arquero";
    }
}
