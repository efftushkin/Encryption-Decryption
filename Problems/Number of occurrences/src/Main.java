import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();

        int counter = 0;
        int lastIndex = 0;

        for (; ; ) {
            lastIndex = input1.indexOf(input2, lastIndex);

            if (lastIndex >= 0) {
                counter++;

                lastIndex += input2.length();
            } else {
                break;
            }
            if (lastIndex >= input1.length() - 1) {
                break;
            }
        }

        System.out.println(counter);
    }
}