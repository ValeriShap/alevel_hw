import java.util.Random;
import java.util.Scanner;

    public class MainTwo {

        //Задание 1

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            double p = (a + b + c) / 2;
            double square = (p * (p - a) * (p - b) * (p - c));
            System.out.println(square);

            //Задание 2

            Random rand = new Random();
            int numb1 = rand.nextInt();
            int numb2 = rand.nextInt();
            int numb3 = rand.nextInt();
            System.out.println("Random number:" + numb1 + ", " + numb2 + ", " + numb3);
            int min = numb1 < numb2 ? numb1 : numb2;
            min = numb3 < min ? numb3 : min;
            System.out.println("Minimum number: " + min);

            //Задание 3

            int d = 0;
            int e = 200;
            int x = d + (int) (Math.random() * ((e - d) + 1));
            System.out.println("Random number: " + x);
            if (x % 2 == 0) {
                System.out.println("The number is even");
            } else {
                System.out.println("The number is odd");
            }

            //Дополнительное задание

            int i = 270;                                      //решение методом
            String bin = Integer.toBinaryString(i);
            System.out.println("Binar number: " + bin);

            System.out.println("Enter a decimal number");      //решение циклом
            binar(sc.nextInt());
        }

        public static void binar(int a) {
            String rec = "";
            while (a != 0) {
                int b = a % 2;
                rec = b + rec;
                a = a / 2;
            }
            System.out.print("Binar number: " + rec);
        }
}
