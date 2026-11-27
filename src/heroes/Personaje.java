package heroes;

public abstract class Personaje {
    private int id;
    private String nombre;
    private int vida;
    private int nivel;

    public Personaje(int id, String nombre, int vida, int nivel) {
        setId(id);
        setNombre(nombre);
        setVida(vida);
        setNivel(nivel);
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public int getVida() {return vida;}
    public int getNivel() {return nivel;}

    public void setId(int id) {
        if(id <= 0){throw new IllegalArgumentException("El ID debe ser mayor a cero");}
        this.id = id;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){throw new IllegalArgumentException("El nombre no puede estar vacío");}
        this.nombre = nombre.trim();
    }

    public void setVida(int vida) {
        if(vida < 0){vida = 0;}
        if(vida > 100){vida = 100;}
        this.vida = vida;
    }

    public void setNivel(int nivel) {
        if(nivel < 1){throw new IllegalArgumentException("El nivel debe ser mayor a uno");}
        this.nivel = nivel;
    }

    public abstract int atacar();

    public void recibirDanio(int puntos){
        if(puntos < 0){
            throw new IllegalArgumentException("El daño no puede ser un valor negatico");
        }
        setVida(this.getVida() - puntos);
    }

    public void recibirDanio(int puntos, int defensa){
        if(puntos <0){
            throw new IllegalArgumentException("El valor del daño no puede ser negativo");
        }
        if(defensa < 0){defensa = 0;}
        int efectivo = Math.max(0, puntos - defensa);
        setVida(this.vida - efectivo);
    }

    public boolean estaVivo(){
        return vida > 0;
    }

    public String mostrarInformacion(){
        return String.format("[%d] %s | Vida: %d/100 | Nivel: %d", id, nombre, vida, nivel);
    }
}
