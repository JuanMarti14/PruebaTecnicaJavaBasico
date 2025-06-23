// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       Mago mago_1 = new Mago(1,100,100);
       Guerrero guerrero_1 = new Guerrero(1,100,100);

       mago_1.mostrarInfo();
       guerrero_1.mostrarInfo();

/*        while (mago_1.magia > 10){
           mago_1.atacar();
           guerrero_1.defender();
       } */

       guerrero_1.atacar();
       mago_1.defender();

       mago_1.mostrarInfo();
       guerrero_1.mostrarInfo();



    }
}