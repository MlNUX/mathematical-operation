import minux.model.Parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Predictable p = new Plus(new Number(5),new Bracket(new Times(new Number(3),new Bracket(new Minus(new Number(7), new Number(5))))));
/*
        Formula a = new Times(new Number(3), new Plus(new Number(5), new Number(5)));
        Formula b = new Plus(new Times(new Number(3),new Number(5)),new Number(5));
        Formula c = new Times(new Number(3),new Bracket(new Plus(new Number(5),new Number(5))));

        System.out.println(a.print());
        System.out.println(a.calculate());
        System.out.println(b.print());
        System.out.println(b.calculate());
        System.out.println(c.print());
        System.out.println(c.calculate());
 */
        Scanner sc = new Scanner(System.in);
        while (true) {

           System.out.println(Parser.parse(sc.nextLine()).calculate());
        }
    }
}