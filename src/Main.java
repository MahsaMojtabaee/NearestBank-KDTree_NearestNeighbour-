import java.util.Scanner;

public class Main {
    public void PrintMenu(){
        System.out.println("Welcome");
        System.out.println("Here are your options. print the number of option to start the command.");
        System.out.println("1. add a region");
        System.out.println("2. add main bank");
        System.out.println("3. add branches to a bank");
        System.out.println("4. delete a bank branch");
        System.out.println("5. The names of banks in a region");
        System.out.println("6. Coordinates of all branches of a bank");
        System.out.println("7. Nearest bank");
        System.out.println("8. Nearest branch of a bank");
        System.out.println("9. Available banks in a range");

    }
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        while (true){
            String order = scr.nextLine();
        }
    }

}
