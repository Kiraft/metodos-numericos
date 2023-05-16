package metodos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Secante {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#.###");
        DecimalFormat dff = new DecimalFormat("#.#####");

        System.out.print("Ingrese el punto Xo: ");
        double Xo = sc.nextDouble();

        System.out.print("Ingrese el punto X1: ");
        double X1 = sc.nextDouble();
        // double Xo = 0;
        // double X1 = 1;

        double Xn;
        double error;
        System.out.println("Xo es = " + Xo +" | No hay error");
        System.out.println("X1 es = " + X1 + " | El error: " +MargenError(X1, Xo)+"%");

        for (int i = 0; i < 200; i++) {
            Xn = secante(Xo, X1, formula(Xo), formula(X1));
            error = MargenError(Xn, X1);
            System.out.println("X1 es = " + dff.format(Xn) + " | El error: " +df.format(error)+"%");
            Xo = X1;
            X1 = Xn;
            if (error == 0) { //Esto para las iteraciones dependiendo de nuestra condicion en el porcentaje de error
                System.out.println("La raiz se encuentra en: " + Xn);
                break;
            }

        }
    }

    public static double formula(double a) {
        return Math.pow(a, 3) + 2*Math.pow(a, 2) + 10*a - 20;
    }

    public static double secante(double Xo, double X1, double FXo, double FX1) {
        return X1 - ((X1-Xo)*FX1)/(FX1-(FXo));
    }

    public static double MargenError(double realValue, double estimatedValue){

        return  Math.abs(realValue - estimatedValue) / realValue * 100;
    }

}
