import MenuRegex.LoginMenuRegex;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE) != null){
            System.out.println(1);
        }
    }

}
