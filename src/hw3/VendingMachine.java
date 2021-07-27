package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VendingMachine {

    private static double totalSales = 0;
    private VendingItem[][][] shelf;
    private Random random = new Random();
    private int luckyChance = 0;

    public VendingMachine() {
        this.restock();
    }

    public VendingItem vend(String code) {
        int row = (int) code.toLowerCase().charAt(0) - (int) 'a';
        int column = Integer.parseInt(code.substring(1)) - 1;

        if (row >= shelf.length || column >= shelf[row].length) {
            System.out.printf("Invalid code %s, parsed to row %d, column %d\n", code, row, column);
            return null;
        }

        VendingItem[] items = shelf[row][column];
        VendingItem item = items[0];

        if (item == null) {
            System.out.printf("No items at %s in row %d, column %d\n", code, row, column);
            return null;
        }

        System.out.println(Arrays.toString(items));
        for (int i = 1; i < items.length; i++)
            items[i - 1] = items[i];
        items[items.length - 1] = null;
        System.out.println(Arrays.toString(items));


        boolean free = this.free();
        if (free)
            System.out.println("Your item is free.");
        else
            totalSales += item.getPrice();

        return item;
    }

    private boolean free() {
        if (random.nextDouble() <= luckyChance / 100.0) {
            luckyChance = 0;
            return true;
        }

        luckyChance++;
        return false;
    }

    public void restock() {
        shelf = new VendingItem[6][3][5];
        VendingItem[] items = VendingItem.values();
        for (int i = 0; i < shelf.length; i++)
            for (int j = 0; j < shelf[i].length; j++) {
                for (int k = 0; k < shelf[i][j].length; k++)
                    shelf[i][j][k] = items[(random.nextInt(items.length))];
            }
    }

    public int getNumberOfItems() {
        int count = 0;
        for (VendingItem[][] row : shelf)
            for (VendingItem[] column : row)
                for (VendingItem item : column)
                    if (item != null)
                        count++;
        return count;
    }

    public double getTotalValue() {
        int value = 0;
        for (VendingItem[][] row : shelf)
            for (VendingItem[] column : row)
                for (VendingItem item : column)
                    if (item != null)
                        value += item.getPrice();
        return value;
    }

    public int getLuckyChance() {
        return luckyChance;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
                + "------------\n");
        s.append("                            VendaTron 9000                "
                + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                    + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                        (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
                + "------------\n");
        s.append(String.format("There are %d items with a total "
                + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
                + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }

    public static double getTotalSales() {
        return totalSales;
    }
}
