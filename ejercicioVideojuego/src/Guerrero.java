public class Guerrero extends Personaje {

    int resistencia = 100;


    public Guerrero(int nivel, int p_vida, int resistencia) {
        super(nivel, p_vida);
    }

    // mostar info Guerrero
    @Override
    public void mostrarInfo(){
        System.out.println("El guerrero " + "tiene nivel " + nivel + " , " + p_vida
                + " puntos de vida." + " y " + resistencia + " de resistencia");
    }

    public void defender(){

        if (resistencia > 10){
            System.out.println("El guerrero realiza una defensa!!");
            resistencia -= 10;
            nivel +=1;
        }else {
            System.out.println("El guerrero no tiene suficiente vida para realizar una defensa :(");
        }
    }

    public void atacar(){
        if (resistencia > 20){
            System.out.println("El guerrero realiza un ataque!!");
            resistencia -= 20;
            nivel += 1;
        }else {
            System.out.println("El guerrero no tiene suficiente vida para realizar un ataque :(");
        }
    }

}
