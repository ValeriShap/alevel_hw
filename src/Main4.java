

public class Main4 {
    public static void main(String[] args) {
        String str = "Hello world!";
        System.out.println("First symbol: " + str.charAt(0));
        System.out.println("Last symbol: " + str.charAt(str.length()-1));

        String a1 = "Java Exercises";
        String a2 = "se";
        String a3 = "Java Exercise";
        System.out.println(a1.endsWith(a2));
        System.out.println(a3.endsWith(a2));

        String name1 = "Stephen Edwin King";
        String name2 = "Walter Winchell";
        String name3 = "Edwin";
        System.out.println(name1.contains(name2));
        System.out.println(name1.contains(name3));

        String king = "Stephen Edwin King";
        String king2 = "Walter Winchell";
        String king3 = "stephen edwin king";
        System.out.println(king.equalsIgnoreCase(king2));
        System.out.println(king.equalsIgnoreCase(king3));

        String color1 = "Red is favorite color";
        String color2 = "Orange is also my favorite color";
        String color3 = "Red";
        System.out.println(color1.startsWith(color3));
        System.out.println(color2.startsWith(color3));

    }
}
