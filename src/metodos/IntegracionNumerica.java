package metodos;


import java.util.Scanner;

public class IntegracionNumerica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("ESTE PROGRAMA FUNCIONA CON UNA INTEGRAL CONCRETA");

        System.out.print("Ingrese el limite superior: ");
        double b = sc.nextDouble();
//      double b = 2;

        System.out.print("Ingrese el limite inferior: ");
        double a = sc.nextDouble();
//      double a = 1;

        System.out.print("Ingrese el valor de n: ");
        double n = sc.nextDouble();


        double h = (b-a)/n;
        double count = a;
        double acumulado = 0;

        double ArrX[] = new double[1 + (int) n];
        double ArrFuntionX[] = new double[1 + (int) n];

        for (int i = 0; i < ArrX.length; i++) {
            ArrX[i] = count;
            count += h;
        }

        for (int i = 0; i < ArrFuntionX.length; i++) {
            ArrFuntionX[i] = Integral(ArrX[i]);
        }


        for (int i = 0; i < ArrFuntionX.length; i++) {

            if (i == 0 || i == ArrFuntionX.length - 1) {
                acumulado += ArrFuntionX[i];
            }else {
                acumulado += 2 * ArrFuntionX[i];
            }

        }

        System.out.println("El area es: "+(h/2)*acumulado);

    }

    public static double Integral(double x){
        double resultado = Math.cbrt(x)*Math.pow(2.71828, x);
        return resultado;
    }

}
