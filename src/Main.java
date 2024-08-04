import minux.model.parsing.Parser;
import minux.model.parsing.ParserWithTree;

import java.util.Scanner;

/**
 * Main class to start the program and get the user input.
 *
 * @author minux
 */
public final class Main {

    private Main() {
    }

    /**
     * Main function.
     *
     * @param args nothing
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        // I know its not the best way
        // to use a while loop but it's still in progress.
        // TODO Improve the wile loop. So that it uses a boolean variable.
        // TODO -> (2-1) throws a exception
        while (true) {
            System.out.println(ParserWithTree.parse(sc.nextLine()).calculate());
        }
    }
}
