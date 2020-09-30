class Main {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            if (result.length() > 0) {
                result.append(' ');
            }

            result.append(i);
        }

        System.out.println(result);
    }
}