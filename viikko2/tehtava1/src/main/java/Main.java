import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier three = new Multiplier(3);
        System.out.println("Give me a number, noob ");
        int number = scanner.nextInt();

        System.out.println("that times three equals " + three.multipliedBy(number) );
    }
}

