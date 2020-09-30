package encryptdecrypt;

import java.io.*;
import java.math.BigDecimal;

interface Encryptor {
    String encrypt(String data, int key);
}

interface Decryptor {
    String decrypt(String data, int key);
}

class ShiftAlgorithm implements Encryptor, Decryptor {
    //A = 65, Z = 90, a = 97, z = 122
    private final int BIG_A = 65;
    private final int BIG_Z = 90;
    private final int SMALL_A = 97;
    private final int SMALL_Z = 122;

    @Override
    public String encrypt(String data, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (char sym : data.toCharArray()) {
            int code = (int) sym;

            if (code < BIG_A || code > BIG_Z && code < SMALL_A || code > SMALL_Z) {
                encrypted.append(sym);
                continue;
            }

            boolean isBig = code <= BIG_Z;

            code += key;

            if (isBig) {
                if (code > BIG_Z) {
                    code = BIG_A - 1 + code - BIG_Z;
                }
            } else {
                if (code > SMALL_Z) {
                    code = SMALL_A - 1 + code - SMALL_Z;
                }
            }

            encrypted.append((char) code);
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        StringBuilder decrypted = new StringBuilder();

        for (char sym : data.toCharArray()) {
            int code = (int) sym;

            if (code < BIG_A || code > BIG_Z && code < SMALL_A || code > SMALL_Z) {
                decrypted.append(sym);
                continue;
            }

            boolean isBig = code <= BIG_Z;

            code -= key;

            if (isBig) {
                if (code < BIG_A) {
                    code = BIG_Z + 1 + code - BIG_A;
                }
            } else {
                if (code < SMALL_A) {
                    code = SMALL_Z + 1 + code - SMALL_A;
                }
            }

            decrypted.append((char) code);
        }

        return decrypted.toString();
    }
}

class UnicodeAlgorythm implements Encryptor, Decryptor {
    @Override
    public String encrypt(String data, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (char sym : data.toCharArray()) {
            int code = (int) sym;

            code += key;

            encrypted.append((char) code);
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        StringBuilder decrypted = new StringBuilder();

        for (char sym : data.toCharArray()) {
            int code = (int) sym;

            code -= key;

            decrypted.append((char) code);
        }

        return decrypted.toString();
    }
}

enum Algorithms {
    SHIFT,
    UNICODE
}

enum EncDec {
    ENCRIPTION,
    DECRIPTION
}

class Params {
    public String inputFilePath = "";
    public String outputFilePath = "";
    public EncDec encDec = EncDec.ENCRIPTION;
    public Algorithms algorithm = Algorithms.SHIFT;
    public String data = "";
    public int key = 0;
}

public class Main {
    public static void main(String[] args) {
        Params params = readParams(args);

        if (!readData(params)) {
            return;
        }

        String result;

        switch (params.encDec) {
            case DECRIPTION: {
                Decryptor decryptor = decryptorAlgorythm(params);
                result = decryptor.decrypt(params.data, params.key);
                break;
            }
            default: {
                Encryptor encryptor = encryptorAlgorythm(params);
                result = encryptor.encrypt(params.data, params.key);
            }
        }

        if (!writeResult(params, result)) {
            return;
        }
    }

    private static Params readParams(String[] args) {
        Params params = new Params();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode": {
                    if (i < args.length - 1) {
                        if (args[i + 1].equals("dec")) {
                            params.encDec = EncDec.DECRIPTION;
                        }

                        i++;
                    }
                    break;
                }
                case "-alg": {
                    if (i < args.length - 1) {
                        if (args[i + 1].equals("unicode")) {
                            params.algorithm = Algorithms.UNICODE;
                        }

                        i++;
                    }
                    break;
                }
                case "-key": {
                    if (i < args.length - 1) {
                        params.key = new BigDecimal(args[i + 1]).intValue();
                        i++;
                    }
                    break;
                }
                case "-data": {
                    if (i < args.length - 1) {
                        params.data = args[i + 1];
                        i++;
                    }
                    break;
                }
                case "-in": {
                    if (i < args.length - 1) {
                        params.inputFilePath = args[i + 1];
                        i++;
                    }
                    break;
                }
                case "-out": {
                    if (i < args.length - 1) {
                        params.outputFilePath = args[i + 1];
                        i++;
                    }
                    break;
                }
            }
        }

        return params;
    }

    private static boolean readData(Params params) {
        if (params.data.isBlank() && !params.inputFilePath.isBlank()) {
            try (InputStream inputStream = new FileInputStream(params.inputFilePath)) {
                byte[] symbols;

                try {
                    symbols = inputStream.readAllBytes();
                } catch (IOException e) {
                    System.out.println("Error: Can't read file: " + e.getMessage());
                    return false;
                }

                params.data = new String(symbols);
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found: " + params.inputFilePath);
                return false;
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    private static boolean writeResult(Params params, String result) {
        if (!params.outputFilePath.isBlank()) {
            File file = new File(params.outputFilePath);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error: Can't create file: " + params.outputFilePath);
                    return false;
                }
            }

            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(result);
            } catch (IOException e) {
                System.out.println("Error: Can't write to file " + params.outputFilePath + ": " + e.getMessage());
                return false;
            }
        } else {
            System.out.println(result);
        }

        return true;
    }

    private static Encryptor encryptorAlgorythm(Params params) {
        switch (params.algorithm) {
            case UNICODE: {
                return new UnicodeAlgorythm();
            }
            default: {
                return new ShiftAlgorithm();
            }
        }
    }

    private static Decryptor decryptorAlgorythm(Params params) {
        switch (params.algorithm) {
            case UNICODE: {
                return new UnicodeAlgorythm();
            }
            default: {
                return new ShiftAlgorithm();
            }
        }
    }
}
