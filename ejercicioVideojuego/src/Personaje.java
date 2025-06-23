public class Personaje {
    int nivel = 1;
    int p_vida = 100;
    String nombre;

    public Personaje(int nivel, int p_vida) {
        nivel = nivel;
        p_vida = p_vida;
    }

    // Metodos
    public void atacar(){
        if (p_vida > 20){
            System.out.println("El personaje realiza un ataque!!");
            p_vida -= 20;
            nivel += 1;
        }else {
            System.out.println("El personaje no tiene suficiente vida para realizar un ataque :(");
        }
    }
    public void defender(){
        if (p_vida > 10){
            System.out.println("El personaje realiza una defensa!!");
            p_vida -= 10;
            nivel +=1;
        }else {
            System.out.println("El personaje no tiene suficiente vida para realizar una defensa :(");
        }
    }

    // mostar info
    public void mostrarInfo(){
        System.out.println("El personaje " + nombre + "tiene nivel " + nivel + " y " + p_vida + " puntos de vida.");
    }


}
