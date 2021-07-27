package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SourceModel {
    static int aCode = (int) 'a';

    String name, path;
    double[][] characterProbs;

    public SourceModel(String name, String path) {
        this.name = name;
        this.path = path;
        this.characterProbs = new double[26][26];

        double[][] characterCounts = new double[26][26];
        String text = "";
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine())
                text += scanner.nextLine().toLowerCase() + "\n";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.printf("Training %s model ... ", name);

        for (int i = 1; i < text.length(); i++) {
            char pCharCode = text.charAt(i - 1);
            char nCharCode = text.charAt(i);
            if (!Character.isAlphabetic(pCharCode) || !Character.isAlphabetic(nCharCode))
                continue;

            characterCounts[(int) pCharCode - aCode][(int) nCharCode - aCode]++;
        }

        for (int i = 0; i < 26; i++) {
            int sum = 0;
            for (int j = 0; j < 26; j++)
                sum += characterCounts[i][j];
            for (int j = 0; j < 26; j++) {
                this.characterProbs[i][j] = characterCounts[i][j] != 0 ? characterCounts[i][j] / sum : 0.01;
            }
        }

        System.out.println("done");
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        String result = "";
        result += " \t";
        for (int a = 0; a < 26; a++)
            result += (char)(aCode + a) + "\t";
        result += "\n";

        for (int i = 0; i < 26; i++) {
            result += (char)(aCode + i) + "\t";
            for (int j = 0; j < 26; j++)
                result += String.format("%.2f\t", this.characterProbs[i][j]);
            result += "\n";
        }
        return result;
    }

    public double probability(String string) {
        string = string.toLowerCase();
        double prob = 1;
        for (int i = 1; i < string.length(); i++) {
            char pCharCode = string.charAt(i - 1);
            char nCharCode = string.charAt(i);
            if (!Character.isAlphabetic(pCharCode) || !Character.isAlphabetic(nCharCode))
                continue;

            prob *= this.characterProbs[(int) pCharCode - aCode][(int) nCharCode - aCode];
        }
        return prob;
    }

    public static void main(String[] args) {
        SourceModel[] models = new SourceModel[args.length - 1];
        for (int i = 0; i < args.length - 1; i++)
            models[i] = new SourceModel(args[i].substring(0, args[i].length() - 7), args[i]);

        double[] probs = new double[args.length - 1];
		double totalProb = 0;
        int maxIndex = 0;
        for (int i = 0; i < args.length - 1; i++) {
            probs[i] = models[i].probability(args[args.length - 1]);
			totalProb += probs[i];
            if (probs[maxIndex] < probs[i])
                maxIndex = i;
        }
		for (int i = 0; i < args.length - 1; i++)
			System.out.printf("Probability that test string is %-10s:%.2f\n", models[i].getName(), probs[i] / totalProb);
    }
}