public class Main {
    public static void main(String[] args) {
        String name = "Valeria Shapran";
        System.out.println(name);

        int i,y;
        for (i = 0, y = 5; i <=10; i++, y += 2){
                System.out.println("Step" + " " + i + "," + "meaning" + " " + y);
        }

        for ( i = 0; i < 10; i ++){
            if(i==3){
                continue;
            }
            if (i == 6){
                break;
            }
            System.out.println("Step" + " " + i);
        }
    }
}