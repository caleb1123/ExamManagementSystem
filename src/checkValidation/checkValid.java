package checkValidation;

import java.util.Scanner;

public class checkValid {

    public static Scanner sc = new Scanner(System.in);

    public static int checkInt(String msg, int min, int max) {
        int t;
        String input;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        
        boolean OK;
        do {
            System.out.println(msg + ": ");
            input = sc.nextLine().trim();
            t = Integer.parseInt(input);
            OK = (t >= min && t <= max);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return t;
    }

    public static int checkInt(String msg, int min) {
        return checkInt(msg, min, Integer.MAX_VALUE);
    }

    public static double checkDouble(String msg, double min) {
        double result;
        String input;
        boolean OK;
        do {
            System.out.println(msg + ": ");
            input = sc.nextLine().trim();
            result = Double.parseDouble(input);
            OK = result >= min;
        } while (!OK);
        return result;
    }

    public static String checkLength(String msg, int min, int max) {
        int t = 0;
        String s = "";
        boolean OK;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        do {
            System.out.println(msg + ": ");
            s = sc.nextLine().trim();
            int L = s.length();
            OK = (L >= min && L <= max);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return s;
    }

    public static boolean validStr(String Str, String regex) {
        return Str.matches(regex);
    }

    public static String InputPattern(String msg, String regex) {
        String s = "";
        boolean OK;
        do {
            System.out.println(msg + ": ");
            s = sc.nextLine().trim();
            OK = validStr(s, regex);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return s;
    }
}
