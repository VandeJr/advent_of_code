import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
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

                    System.out.printf("F -> %d S -> %d + -> %d\n", first, second, (first * 10) + second);

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
}