package heroes;

public class ArqueroElite extends Arquero {
    public ArqueroElite(int id, String nombre, int vida, int nivel) {
        super(id, nombre, vida, nivel);
    }

    @Override
    public int atacar() {
        return super.atacar() + 5;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n Clase: Elite";
    }
}
