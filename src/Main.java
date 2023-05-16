import java.text.DecimalFormat;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("=======================");
            System.out.println("     MENÚ DE MÉTODOS     ");
            System.out.println("=======================");
            System.out.println("Seleccione un método:");
            System.out.println("1. Margen de Error");
            System.out.println("2. Integración Numérica");
            System.out.println("3. Bisección");
            System.out.println("4. Método de Newton-Raphson");
            System.out.println("5. Método de la Secante");
            System.out.println("0. Salir");

            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    MargenError2 me = new MargenError2();
                    System.out.print("Ingrese el valor real: ");
                    double real = input.nextDouble();

                    System.out.print("Ingrese el valor estimado: ");
                    double estimado = input.nextDouble();

                    me.Ejecutar(real, estimado);

                    break;
                case 2:

                    IntegracionNumerica2 in = new IntegracionNumerica2();
                    System.out.println("ESTE PROGRAMA FUNCIONA CON UNA INTEGRAL CONCRETA");

                    System.out.print("Ingrese el limite superior: ");
                    double b = input.nextDouble();
                    // double b = 2;

                    System.out.print("Ingrese el limite inferior: ");
                    double a = input.nextDouble();
                    // double a = 1;

                    System.out.print("Ingrese el valor de n: ");
                    double n = input.nextDouble();

                    in.Ejecucion(b, a, n);

                    break;
                case 3:
                    Biseccion2 bi = new Biseccion2();
                    bi.Ejecucion();
                    break;
                case 4:
                    NewtonRapsody2 nr = new NewtonRapsody2();
                    // double Xo = 2; // PUNTO INICIAL
                    System.out.print("Inserte el punto de inicio: ");
                    double Xo = input.nextDouble();

                    nr.Ejecutar(Xo);
                    break;
                case 5:
                    Secante2 sc = new Secante2();
                    System.out.print("Ingrese el punto Xo: ");
                    double Xoo = input.nextDouble();

                    System.out.print("Ingrese el punto X1: ");
                    double X1 = input.nextDouble();
                    // double Xo = 0;
                    // double X1 = 1;
                    sc.Ejecutar(Xoo, X1);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }

        input.close();
    }
}

class IntegracionNumerica2 {

    public static void Ejecucion(double b, double a, double n){

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
        double resultado = (Math.pow(x, 3))/(1 + Math.sqrt(x));
        return resultado;
    }

}

class MargenError2 {
    public static void Ejecutar(double real, double estimado) {
        System.out.println(Math.abs(real - estimado) / real * 100);
    }
}

class Biseccion2 {

    public static void Ejecucion() {
        DecimalFormat df = new DecimalFormat("#.###");

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        // DATOS DE INTERVALO
        double a = 14, b = 15;

        double Fa, Fb, xr = 0, Fxr, FaXFxr, error = 2, xr_Anterior = 0;

        for (int i = 0; error != 0; i++) {

            Fa = Ecuacion(a);
            Fb = Ecuacion(b);
            xr = Xr(a, b);
            Fxr = Ecuacion(xr);
            FaXFxr = Fa * Fxr;

            if (i == 0) {

                System.out.println(i + "| " + "|" + df.format(a) + "|" + " |" + df.format(b) + "|" + " |" + df.format(Fa) + "|"
                        + " |" + df.format(Fb) + "|" + ANSI_RED + " |" + df.format(xr) + "|" + ANSI_RESET + " |" + df.format(Fxr)
                        + "|" + " | " + df.format(FaXFxr) + "|" + ANSI_RED + " |" + "------" + "|\n" + ANSI_RESET);
            } else {
                error = MargenError(xr, xr_Anterior);

                System.out.println(i + "| " + "|" + df.format(a) + "|" + " |" + df.format(b) + "|" + " |" + df.format(Fa) + "|"
                        + " |" + df.format(Fb) + "|" + ANSI_RED + " |" + df.format(xr) + "|" + ANSI_RESET + " |" + df.format(Fxr)
                        + "|" + " |" + df.format(FaXFxr) + "|" + ANSI_RED + " |" + df.format(error) + "%|\n" + ANSI_RESET);
            }

            if (FaXFxr < 0) {
                b = xr;
            } else if (FaXFxr > 0) {
                a = xr;
            }
            xr_Anterior = xr;

            // ESTE CONDICIONAL ES LO QUE PERMITE QUE EL BUCLE PARE EN CUALQUIER CONDICION
            // QUE PONGAMOS AL ERROR
            if (error <= 0) {
                break;
            }

        }

        System.out.println("La raiz se encuentra en: " + df.format(xr));
    }

    public static double Xr(double a, double b) {
        return (a + b) / 2.0;
    }

    public static double Ecuacion(double f) {

        // double resultado = Math.pow(f, 3) - 3;
        double resultado2 = (9.8 * 68.1) / f;
        double resultado3 = 1 - Math.pow(2.718, -1 * ((f / 68.1) * 10));
        double resultado = (resultado2 * resultado3) - 40;
        return resultado;

    }

    public static double MargenError(double realValue, double estimatedValue) {
        return Math.abs(realValue - estimatedValue) / realValue * 100;
    }
}

class NewtonRapsody2 {
    public static void Ejecutar(double Xo) {
        Scanner sc = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#.####");


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

class Secante2 {
    public static void Ejecutar(double Xo, double X1) {

        DecimalFormat df = new DecimalFormat("#.###");
        DecimalFormat dff = new DecimalFormat("#.#####");

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