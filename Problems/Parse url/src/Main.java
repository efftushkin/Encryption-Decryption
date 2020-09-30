import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String password = "";

        int index = input.indexOf('?');

        if (index > 0 && index < input.length() - 1) {
            input = input.substring(index + 1);

            boolean isThereAnyParamsLeft = true;

            while (isThereAnyParamsLeft) {
                String pass;

                index = input.indexOf('&');

                if (index >= 0) {
                    pass = readParam(input.substring(0, index));

                    if (index < input.length() - 1) {
                        input = input.substring(index + 1);
                    } else {
                        isThereAnyParamsLeft = false;
                    }
                } else {
                    pass = readParam(input);

                    isThereAnyParamsLeft = false;
                }

                if (password.isEmpty() && !pass.isEmpty()) {
                    password = pass;
                }
            }

            if (!password.isEmpty()) {
                System.out.println(password);
            }
        }
    }

    public static String readParam(String paramValue) {
        int index = paramValue.indexOf('=');

        if (index < 0) {
            return "";
        }

        String param = paramValue.substring(0, index).toLowerCase();
        String value = index < paramValue.length() - 1 ? paramValue.substring(index + 1) : "not found";

        System.out.println(param + " : " + value);

        if ("pass".equals(param)) {
            return "password : " + value;
        } else {
            return "";
        }
    }
}