package metodos;
import java.text.DecimalFormat;
import java.util.Scanner;
public class NewtonRapsody {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#.####");

        // double Xo = 2; // PUNTO INICIAL
        System.out.print("Inserte el punto de inicio: ");
        double Xo = sc.nextDouble();

        double X1;
        double error = 2;

        System.out.println("Xn | " +"VALOR "+" | PORCENTAJE |");
        System.out.println("----------------------");
        System.out.println("Xo | "+ Xo +"    | NADA ");

        for (int i = 0; i < 1000; i++) {

            X1 = newtonR(Xo, Xo);

            error = MargenError(X1, Xo);
            Xo = X1;
            System.out.println("X"+ (i+1) + " | "+ df.format(Xo) +" | " + df.format(error) + " %");


            if (error == 0) {
                break;
            }


        }

        System.out.println("\nLa raiz se encuentra: " + Xo);
    }

    public static double newtonR(double Xn, double x ) {
        double funcion = Math.log(x);
        double derivada = 1/x;
        return Xn - ((funcion)/(derivada));
    }

    public static double MargenError(double ValorReal, double ValorEstimado){
        return  Math.abs(ValorReal - ValorEstimado) / ValorReal * 100;
    }
}
