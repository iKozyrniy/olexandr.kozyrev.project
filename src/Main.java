import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main {
    public static void main(String[] args) {
        BruteForce bruteForce = new BruteForce();
        CaesarCipher caesarCipher = new CaesarCipher();

        if (args.length < 2) {
            System.out.println("Недостатньо аргументів.");
            return;
        }

        String command = args[0];
        String filePath = args[1];

        try {
            String fileContent = readFile(filePath);

            String result;
            switch (command) {
                case "ENCRYPT", "DECRYPT" -> {
                    if (args.length < 3) {
                        System.out.println("Введіть ключ.");
                        return;
                    }
                    int key = Integer.parseInt(args[2]);
                    if (command.equals("ENCRYPT")) {
                        result = caesarCipher.encrypt(fileContent, key);
                    } else {
                        result = caesarCipher.decrypt(fileContent, key);
                    }
                }
                case "BRUTE_FORCE" -> result = bruteForce.bruteForce(fileContent);
                default -> {
                    System.out.println("Такої операції не існує.");
                    return;
                }
            }

            String methodName = command.toUpperCase();
            String newFileName = getNewFileName(filePath, "[" + methodName + "ED" + "]");
            Path newFilePath = Paths.get(filePath).resolveSibling(newFileName);
            Files.write(newFilePath, result.getBytes());

            System.out.println("Операція була виконана. Був создан новий файл: " + newFilePath);

        } catch (IOException e) {
            System.out.println("Сталася помилка при обробці файла: " + e.getMessage());
        }
    }

    private static String readFile(String filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }

    private static String getNewFileName(String filePath, String addition) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String nameWithoutExtension = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);

        return nameWithoutExtension + addition + fileExtension;
    }
}