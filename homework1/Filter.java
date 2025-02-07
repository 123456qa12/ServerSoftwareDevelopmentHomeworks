import java.io.*;
import java.util.*;

public class Filter {
	private static <T> void writeToFile(String fileName, List<T> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл:" + fileName);
        }
    }
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Программа запущена без аргументов");
            return;
        }

        List<String> stringArray = new ArrayList<>();
        List<Integer> integerArray = new ArrayList<>();
        List<Double> doubleArray = new ArrayList<>();
        
        for (String fileName : args) {
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Файл не найден:" + fileName);
                continue;
            }
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        if (line.contains(".")) {
                            doubleArray.add(Double.parseDouble(line));
                        } else {
                            integerArray.add(Integer.parseInt(line));
                        }
                    } catch (NumberFormatException e) {
                        stringArray.add(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + fileName);
            }
        }
        writeToFile("strings.txt", stringArray);
        writeToFile("integers.txt", integerArray);
        writeToFile("floats.txt", doubleArray);
    }
}
