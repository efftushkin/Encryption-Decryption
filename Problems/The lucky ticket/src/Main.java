import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                num1 += Character.getNumericValue(input.charAt(i));
            } else {
                num2 += Character.getNumericValue(input.charAt(i));
            }
        }

        if (num1 == num2) {
            System.out.println("Lucky");
        } else {
            System.out.println("Regular");
        }
    }
}