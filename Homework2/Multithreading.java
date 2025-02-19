import java.util.Random;
import java.io.IOException;

public class Multithreading {

	public static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int threadNumber = Integer.parseInt(args[0]);
        int calculationLength = Integer.parseInt(args[1]);

        int[] firstArray = new int[calculationLength];
        int[] result = new int[calculationLength];
        double[] threadExecutionTime = new double[threadNumber];

        Random random = new Random();

        for (int i = 0; i < calculationLength; i++) {
            firstArray[i] = random.nextInt(1000);
        }

        Thread[] threads = new Thread[threadNumber];
        int chunkSize = calculationLength / threadNumber;
        String[] progressBars = new String[threadNumber];

        Object lock = new Object();

        for (int t = 0; t < threadNumber; t++) {
            final int threadId = t;
            final int start = t * chunkSize;
            final int end = (t == threadNumber - 1) ? calculationLength : (start + chunkSize);

            threads[t] = new Thread(() -> {
                long startTime = System.nanoTime();

                int progressStep = Math.max((end - start) / 10, 1);

                for (int i = start; i < end; i++) {
                    result[i] = (int) factorial(firstArray[i] % 15);

                    if ((i - start + 1) % progressStep == 0 || i == end - 1) {
                        int progress = ((i - start + 1) * 100 / (end - start));
                        synchronized (lock) {
                            if (progress == 100) { 
                                long endTime = System.nanoTime();
                                threadExecutionTime[threadId] = (endTime - startTime) / 1_000_000_000.0;
                            }

                            progressBars[threadId] = "Поток " + threadId + " Идентификатор: " + Thread.currentThread().threadId() + " ["
                                    + "#".repeat(progress / 10) + " ".repeat(10 - progress / 10)
                                    + "] " + progress + "%"
                                    + (progress == 100 ? String.format(" (Время: %.3f сек)", threadExecutionTime[threadId]) : "");

                            clear();
                            for (String bar : progressBars) {
                                if (bar == null) {
                                    System.out.println(" ");
                                } else {
                                    System.out.println(bar);
                                }
                            }

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





