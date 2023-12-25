import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne() {
        File file = new File("input.txt");
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            int first = -1;
            int second = -1;
            int soma = 0;

            boolean con = true;

            do {
                int read = reader.read();
                if (read == 10 || read == -1) {
                    if (second == -1) second = first;

                    soma += (first * 10) + second;

                    // System.out.printf("F -> %d S -> %d + -> %d\n", first, second, (first * 10) + second);

                    con = !(read == -1);
                    first = -1;
                    second = -1;

                    if (!con)
                        System.out.printf("Result -> %d\n", soma);

                    continue;
                }

                read = read - 48;
                if (read >= 0 && read <= 9) {
                    if (first == -1) {
                        first = read;
                    } else {
                        second = read;
                    }
                }
            } while (con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void partTwo() {
        File file = new File("input.txt");
        try {
            byte[] content = Files.readAllBytes(file.toPath());
            int first = -1;
            int second = -1;
            int soma = 0;
            for (int i = 0; i <= content.length; i++) {

                if (i == content.length || content[i] == 10) {
                    if (second == -1) second = first;

                    soma += (first * 10) + second;

                    System.out.printf(" F -> %d S -> %d + = %d\n", first, second, (first * 10) + second);

                    first = -1;
                    second = -1;

                    if ((content.length - i) < 3) System.out.printf("Result -> %d", soma);

                    continue;
                }

                Optional<Integer> maybe = readInt(getStringValue(content, i));

                int read = content[i] - 48;
                if (read >= 0 && read <= 9) {
                    if (first == -1) {
                        first = read;
                    } else {
                        second = read;
                    }
                } else if (maybe.isPresent()) {
                    if (first == -1) {
                        first = maybe.get();
                    } else {
                        second = maybe.get();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringValue(byte[] content, int i) {
        char[] cs = new char[0];

        int more = content.length - i;
        if (more == 3) {
            cs = new char[]{(char) content[i], (char) content[i + 1], (char) content[i + 2]};
        } else if (more == 4) {
            cs = new char[]{
                    (char) content[i],
                    (char) content[i + 1],
                    (char) content[i + 2],
                    (char) content[i + 3]
            };
        } else if (more > 5) {
            cs = new char[]{
                    (char) content[i],
                    (char) content[i + 1],
                    (char) content[i + 2],
                    (char) content[i + 3],
                    (char) content[i + 4]
            };
        }

        return String.valueOf(cs);
    }

    static Optional<Integer> readInt(String val) {
        if (val.startsWith("one")) return Optional.of(1);
        else if (val.startsWith("two")) return Optional.of(2);
        else if (val.startsWith("three")) return Optional.of(3);
        else if (val.startsWith("four")) return Optional.of(4);
        else if (val.startsWith("five")) return Optional.of(5);
        else if (val.startsWith("six")) return Optional.of(6);
        else if (val.startsWith("seven")) return Optional.of(7);
        else if (val.startsWith("eight")) return Optional.of(8);
        else if (val.startsWith("nine")) return Optional.of(9);
        else return Optional.empty();
    }
}