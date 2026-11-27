import heroes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Juego {
    private static final ArrayList<Personaje> personajes = new ArrayList<>();
    private static final HashMap<Integer, Personaje> porId = new HashMap<>();
    private static final HashMap<String, Integer> contadorAtaques = new HashMap<>();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opcion: ");

            if (opcion == 7) {
                System.out.println("Saliendo del Sistema.........");
                break;
            }

            switch (opcion) {
                case 1: registrarPersonaje(); break;
                case 2: listarPersonaje(); break;
                case 3: buscarPorID(); break;
                case 4: atacar(); break;
                case 5: recibirDanio(); break;
                case 6: estadisticaJuego(); break;
                default: System.out.println("La opción ingresada no es válida"); break;
            }
        } while (true);
    }

    private static void mostrarMenu(){
        System.out.println("\n================= ARENA DE HEROES ==================");
        System.out.println("1. Registrar personaje");
        System.out.println("2. Listar personaje");
        System.out.println("3. Buscar por ID");
        System.out.println("4. Atacar");
        System.out.println("5. Recibir daño");
        System.out.println("6. Estadistica del Juego");
        System.out.println("7. Salir");
    }

    private static int leerEntero(String mensaje){
        while(true){
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Ingresar un número válido");
            }
        }
    }

    private static void registrarPersonaje(){
        try {
            System.out.println("--------- TIPOS DE PERSONAJES ---------");
            System.out.println("1. Guerrero \n2. Mago \n3. Arquero \n4. Arquero Elite");

            int tipo = leerEntero("Seleccione una opcion: ");
            if (tipo <= 0 || tipo > 4 ){
                System.out.println("El tipo de personaje seleccionado no es válido");
                return;
            }
            int id = leerEntero("Ingrese el ID del personaje: ");

            if(porId.containsKey(id)){
                System.out.println("Ya existe un personaje registrado con ese ID");
                return;
            }
            System.out.print("Nombre del Personaje: ");
            String nombre = sc.nextLine().trim();
            int vida = leerEntero("Vida del Personaje (0-100): ");
            int nivel = leerEntero("Nivel del Personaje igual o superior a 1:  ");

            Personaje p = null;
            switch (tipo){
                case 1: p = new Guerrero(id, nombre, vida, nivel); break;
                case 2: p = new Mago(id, nombre, vida, nivel); break;
                case 3 : p = new Arquero(id, nombre, vida, nivel); break;
                case 4 : p = new ArqueroElite(id,nombre, vida, nivel); break;
                default:  System.out.println("Ingrese una opcion valida"); return;
            }
            personajes.add(p);
            porId.put(id, p);
            System.out.println("El personaje registrado: \n"+p.mostrarInformacion());
        }catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void listarPersonaje() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados aún");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println(p.mostrarInformacion());
        }
    }

    private static void buscarPorID(){
        int id = leerEntero("Ingrese el ID del personaje: ");
        Personaje p = porId.get(id);

        if(p == null){
            System.out.println("No existe un personaje registrado con ese ID");
        }else{
            System.out.println(p.mostrarInformacion());
        }
    }

    private static void atacar(){
        int atacante = leerEntero("ID del atacante: ");
        int objetivo = leerEntero("ID del objetivo: ");

        Personaje idP1 = porId.get(atacante);
        Personaje idP2 = porId.get(objetivo);

        if(idP1 == null || idP2 == null){
            System.out.println("No existe un personaje registrado con ese ID");
            System.out.println("Ataque cancelado");
            return;
        }
        if(!idP1.estaVivo()){
            System.out.println("El atacante fue derrotado, no puede atacar");
            return;
        }
        if(!idP2.estaVivo()){
            System.out.println("El objetivo ya fue derrotado");
            return;
        }
        int danio = idP1.atacar();
        idP2.recibirDanio(danio);

        String nombreClase = idP1.getClass().getSimpleName();

        if (contadorAtaques.containsKey(nombreClase)) {
            int numAtaques = contadorAtaques.get(nombreClase);
            contadorAtaques.put(nombreClase, numAtaques + 1);
        } else {
            contadorAtaques.put(nombreClase, 1);
        }

        System.out.println(idP1.getNombre() + " ataca a " + idP2.getNombre() + "\tDaño: " + danio);
        System.out.println("Estado objetivo: " + idP2.mostrarInformacion());
    }

    private static void recibirDanio(){
        int id = leerEntero("ID personaje: ");
        Personaje p = porId.get(id);
        if(p == null){
            System.out.println("No existe un personaje registrado con ese ID");
            return;
        }
        int danio = leerEntero("Daño: ");
        int op = leerEntero("¿Aplicar defensa? \n1= Si /2=No:   ");

        if(op == 1){
            int defensa = leerEntero("Defensa: ");
            p.recibirDanio(danio, defensa);
        }else if(op == 2) {
            p.recibirDanio(danio);
        }else{
            System.out.println("El valor ingresado no es válido");
            return;
        }
        System.out.println("Estado personaje: "+p.mostrarInformacion());
    }

    private static void estadisticaJuego() {
        System.out.println("--------- ESTADÍSTICA DEL JUEGO ---------");
        System.out.println("Personajes registrados: "+personajes.size());
        if(personajes.isEmpty()){return;}
        long vivos = 0;
        int sumNivel = 0;
        int sumVida = 0;
        for (Personaje p : personajes) {
            if(p.estaVivo()){ vivos++;}
            sumNivel += p.getNivel();
            sumVida += p.getVida();
        }
        double nivelPromedio = (double) sumNivel / personajes.size();
        double vidaPromedio = (double) sumVida / personajes.size();

        System.out.println("Personajes vivos: "+vivos);
        System.out.printf("Nivel promedio: %.2f%n", nivelPromedio);
        System.out.printf("Vida promedio: %.2f%n", vidaPromedio);
        System.out.println("Ataques por clase: "+contadorAtaques);
    }
}
