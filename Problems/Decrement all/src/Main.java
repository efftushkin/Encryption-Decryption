import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();

                if (result.length() > 0) {
                    result.append(' ');
                }

                result.append(--num);
            }
        }

        System.out.println(result);
    }
}