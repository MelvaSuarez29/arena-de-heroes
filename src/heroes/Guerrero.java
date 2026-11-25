package heroes;

public class Guerrero extends Personaje{

    public Guerrero(int id, String nombre, int vida, int nivel) {
        super(id, nombre, vida, nivel);
    }

    @Override
    public int atacar() {
        return 10 + 2 * getNivel();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nClase: Guerrero";
    }
}
