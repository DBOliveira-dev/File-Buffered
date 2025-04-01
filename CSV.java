import java.io.*;
import java.nio.file.*;
import java.util.*;

    public class CSV {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Digite o caminho do arquivo CSV: ");
            String filePath = sc.nextLine();
            sc.close();

            Path sourcePath = Paths.get(filePath);
            Path outDir = sourcePath.getParent().resolve("out");
            Path outputFile = outDir.resolve("summary.csv");

            try {
                Files.createDirectories(outDir);
                List<String> lines = Files.readAllLines(sourcePath);
                List<String> outputLines = new ArrayList<>();

                for (String line : lines) {
                    String[] fields = line.split(",");
                    if (fields.length == 3) {
                        String name = fields[0];
                        double price = Double.parseDouble(fields[1]);
                        int quantity = Integer.parseInt(fields[2]);
                        double total = price * quantity;
                        outputLines.add(name + "," + String.format("%.2f", total));
                    }
                }

                Files.write(outputFile, outputLines);
                System.out.println("Arquivo gerado em: " + outputFile.toAbsolutePath());

            } catch (IOException e) {
                System.out.println("Erro ao processar o arquivo: " + e.getMessage());
            }
        }
    }


