package metodos;

import java.util.Scanner;

public class MargenError {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el valor real: ");
        double real = sc.nextDouble();

        System.out.print("Ingrese el valor estimado: ");
        double estimado = sc.nextDouble();

        System.out.println(Math.abs(real - estimado) / real * 100);
    }
}
