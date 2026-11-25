package heroes;

public class Mago extends Personaje {
    private int mana;

    public Mago(int id, String nombre, int vida, int nivel) {
        this(id, nombre, vida, nivel, 50);
    }

    public Mago(int id, String nombre, int vida, int nivel, int mana) {
        super(id, nombre, vida, nivel);
        setMana(mana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if(mana < 0){mana = 0;}
        if(mana > 100){mana = 100;}
        this.mana = mana;
    }

    @Override
    public int atacar() {
        if(mana < 5){
            System.out.println(getNombre()+"no posee mana suficiente para atacar");
            return 0;
        }
        mana -= 5;
        return 8 + 3 * getNivel();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nClase: Mago \n Mana: "+mana;
    }
}
