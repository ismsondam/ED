import java.util.Scanner;
import java.util.Random;

public class Adivina {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        int numeroAleatorio = random.nextInt(1000) + 1;
        int max = 1000;
        int min = 1;
        int cont = 1;
        
        System.out.println("Adivina el número entre " + min + " y " + max);
        int num = sc.nextInt();
        while (num != numeroAleatorio)
        {
            if (num < numeroAleatorio)
                System.out.println("El número es mayor que " + num);
            else
                System.out.println("El número es menor que " + num);
            
            cont++;
            num = sc.nextInt();
        }
        
        System.out.println("Enhorabuena, lo has adivinado en " + cont + " intentos.");
    }

}
