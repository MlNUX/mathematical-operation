import minux.model.Parser;

import java.util.Scanner;

/**
 * Main class to start the program and get the user input.
 *
 * @author minux
 */
public class Main {

    /**
     * Main function.
     *
     * @param args nothing
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         // I know its not the best way to use a while loop but it's still in progress.
        // TODO Improve the wile loop. So that it uses a boolean variable.
        while (true) {
            System.out.println(Parser.parse(sc.nextLine()).calculate());
        }
    }
}