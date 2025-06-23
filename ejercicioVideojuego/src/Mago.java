public class Mago extends Personaje{

    int magia = 100;

    public Mago(int nivel, int p_vida, int magia){
        super(nivel, p_vida);
    }

    // mostar info Guerrero
    @Override
    public void mostrarInfo(){
        System.out.println("El Mago " + "tiene nivel " + nivel + " , " + p_vida
                + " puntos de vida." + " y " + magia + " de mágia.");
    }

    public void defender(){

        if (magia > 10){
            System.out.println("El mago realiza una defensa!!");
            magia -= 10;
            nivel +=1;
        }else {
            System.out.println("El mago no tiene suficiente vida para realizar una defensa :(");
        }
    }

    public void atacar(){
        if (magia > 20){
            System.out.println("El mago realiza un ataque!!");
            magia -= 20;
            nivel += 1;
        }else {
            System.out.println("El mago no tiene suficiente vida para realizar un ataque :(");
        }
    }
}
