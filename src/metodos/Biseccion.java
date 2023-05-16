package metodos;

import java.text.DecimalFormat;

public class Biseccion {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.###");

        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
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
