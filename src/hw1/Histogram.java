package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Histogram {
    public String path;
    public int binCount, binStep;
    public int[] bins;

    public Histogram(String path, int binCount) {
        this.path = path;
        this.binCount = binCount;
        this.bins = new int[binCount];
        this.binStep = 100 / binCount;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String name = scanner.next(), line = scanner.nextLine();
            int grade = Integer.parseInt(line.substring(line.indexOf(',') + 1).trim());
            int bin = grade == 0 ? 0 : (grade - 1) / binStep;
            bins[bin]++;
        }
    }

    @Override
    public String toString() {
        String[] binStrings = new String[binCount];
        for (int i = 0; i < binCount; i++) {
            int upperBound = binStep * (1 + i);
            int lowerBound = i == 0 ? 0 : binStep * i + 1;
            String binString = "[]".repeat(bins[i]);
            binStrings[binCount - i - 1] = String.format("%3d -%3d | %s", upperBound, lowerBound, binString);
        }
        return String.join("\n", binStrings);
    }

    public static void main(String[] args) {
        String file = args[0];
        int binCount;
        if (args.length == 1) {
            System.out.println("How many bins would you like?");
            Scanner scanner = new Scanner(System.in);
            binCount = scanner.nextInt();
        } else
            binCount = Integer.parseInt(args[1]);

        Histogram histogram = new Histogram(file, binCount);
        System.out.println(histogram);
    }
}
