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
            System.out.println("Ошибка записи в файл: " + fileName);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Программа запущена без аргументов");
            return;
        }

        List<String> files = new ArrayList<>();
        boolean showShortStatistics = false;
        boolean showFullStatistics = false;
        boolean filesHaveNumbers = true;
        boolean filesHaveStrings = true;
        String prefix = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-s")) {
                showShortStatistics = true;
            } else if (args[i].equals("-f")) {
                showFullStatistics = true;
            } else if (args[i].equals("-p") && i + 1 < args.length) {
                prefix = args[i + 1];
                i++;
            } else {
                files.add(args[i]);
            }
        }

        List<String> stringArray = new ArrayList<>();
        List<Integer> integerArray = new ArrayList<>();
        List<Double> doubleArray = new ArrayList<>();

        int stringCounter = 0;
        int integerCounter = 0;
        int doubleCounter = 0;

        if (files.isEmpty()) {
            System.out.println("Программе не переданы файлы для фильтрации");
            return;
        }

        for (String fileName : files) {
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Файл не найден: " + fileName);
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        if (line.contains(".")) {
                            double value = Double.parseDouble(line);
                            doubleArray.add(value);
                            doubleCounter++;
                        } else {
                            int value = Integer.parseInt(line);
                            integerArray.add(value);
                            integerCounter++;
                        }
                    } catch (NumberFormatException e) {
                        stringArray.add(line);
                        stringCounter++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + fileName);
            }
        }

        if (doubleCounter + integerCounter == 0) {
            filesHaveNumbers = false;
        }
        if (stringCounter == 0) {
            filesHaveStrings = false;
        }

        double maxNumber = Double.NEGATIVE_INFINITY;
        double minNumber = Double.POSITIVE_INFINITY;
        double sum = 0.0;
        double average = 0.0;

		int maxStringSize = Integer.MIN_VALUE;
		int minStringSize = Integer.MAX_VALUE;

        if (showFullStatistics && filesHaveNumbers) {
            for (int num : integerArray) {
                sum += num;
                if (num < minNumber) {
                    minNumber = num;
                }
                if (num > maxNumber) {
                    maxNumber = num;
                }
            }
            for (double num : doubleArray) {
                sum += num;
                if (num < minNumber) {
                    minNumber = num;
                }
                if (num > maxNumber) {
                    maxNumber = num;
                }
            }
            average = sum / (doubleCounter + integerCounter);
        }

		if (showFullStatistics && filesHaveStrings) {
			for (String str : stringArray){
				if (str.length() < minStringSize) {
					minStringSize = str.length();
				}
				if (str.length() > maxStringSize) {
					maxStringSize = str.length();
				}
			}
		}
		
        writeToFile(prefix + "strings.txt", stringArray);
        writeToFile(prefix + "integers.txt", integerArray);
        writeToFile(prefix + "floats.txt", doubleArray);

        if (showShortStatistics || showFullStatistics) {
            System.out.println("Строки: " + stringCounter);
            System.out.println("Целые числа: " + integerCounter);
            System.out.println("Вещественные числа: " + doubleCounter);
        }

        if (showFullStatistics) {
            if (!filesHaveNumbers) {
                System.out.println("Невозможно найти полную статистику для чисел: В файлах нет чисел.");
            } else {
                System.out.println("Максимальное число: " + maxNumber);
                System.out.println("Минимальное число: " + minNumber);
                System.out.println("Сумма всех чисел: " + sum);
                System.out.println("Среднее арифметическое: " + average);
            }
			
			if (!filesHaveStrings) {
				System.out.println("Невозможно найти полную статистику для строк: В файлах нет строк.");
			} else {
				System.out.println("Размер самой длинной строки: " + maxStringSize);
				System.out.println("Размер самой короткой строки: " + minStringSize);
			}
        }
    }
}

