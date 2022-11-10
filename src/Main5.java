import java.util.Arrays;
import java.util.Random;

public class Main5 {

    public static void main(String[] args) {

        //Задание 1

        Random ran = new Random();

        int[] arr = new int[12];
        int maxNumb = -16;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ran.nextInt(31) - 15;
            System.out.print(arr[i] + " ");
            if (maxIndex <= arr[i]) {
                maxNumb = arr[i];
                maxIndex = i;
            }
        }
        System.out.println("\n" + "Maximum index: " + maxIndex);

        //Задание 2

        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = ran.nextInt(10) + 1;
        }
        System.out.println(Arrays.toString(arr2));
        for (int i = 0; i < arr2.length; i++) {
            if (i % 2 == 1) {
                arr2[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr2));

        //Задание 3

        int[] arr3 = new int[4];
        int min = 10;
        int max = 99;
        int dif = max - min;
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = ran.nextInt(dif + 1);
            arr3[i] += min;
        }
        System.out.println(Arrays.toString(arr3));
        for (int i = 0; i < arr3.length; i++) {
            if (i > 0) {
                if (arr3[i] <= arr3[i - 1]) {
                    System.out.println("Array is not strictly increasing sequence");
                    break;
                }
            }
            if (i == arr3.length - 1) {
                System.out.println("Array in strictly increasing sequence");
            }
        }

        //Задание 4
        int[] arr4 = new int[5];
        int[] arr5 = new int[5];
        for (int i = 0; i < arr4.length; i++) {
            arr5[i] = ran.nextInt(6);
            arr4[i] = ran.nextInt(6);
        }
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.toString(arr5));

        double element1 = 0;
        double element2 = 0;
        for (int i = 0; i < 5; i++) {
            element1 += arr4[i];
            element2 += arr5[i];
        }
        element1 /= 5;
        element2 /= 5;
        if (element1 > element2) {
            System.out.println("Arithmetic mean of the first array " + element1 + "\n" +
                    "greater than the arithmetic mean of the second array " + element2);
        } else if (element1 < element2) {
            System.out.println("Arithmetic mean of the first array " + element1 + "\n" +
                    "less than the arithmetic mean of the second array " + element2);
        } else {
            System.out.println("Arithmetic averages of arrays are equal " + element1);
        }

        //bubble sort
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ran.nextInt(100);
        }
        System.out.println(Arrays.toString(numbers));

        boolean unsorted = true;
        while (unsorted) {
            unsorted = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                int firstNumber = numbers[i];
                int secondNumbers = numbers[i + 1];
                if (firstNumber > secondNumbers) {
                    int temp = firstNumber;
                    firstNumber = secondNumbers;
                    secondNumbers = temp;
                    unsorted = true;
                }
                numbers[i] = firstNumber;
                numbers[i + 1] = secondNumbers;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}