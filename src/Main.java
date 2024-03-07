import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] n_upper_lower;
        String newfile = "Results.txt";
        String name = "Input.txt";
        createNewFile(name);
        FillTheFile(name);
        System.out.println("Всего слов в тексте:");
        System.out.println(CountWords(name));
        n_upper_lower = CountUpperLower(name);
        System.out.println("Количество строчных букв:");
        System.out.println(n_upper_lower[0]);
        System.out.println("Количество заглавных букв:");
        System.out.println(n_upper_lower[1]);
        System.out.println("Сумма заглавных и строчных букв:");
        System.out.println(n_upper_lower[2]);
        System.out.println("Количество пробелов в файле:");
        System.out.println(CountSpaces(name));
        CountDigsAndFloats(name);
        System.out.println("Amount of separators in file:");
        System.out.println(GetSepsAmount(name));
        DeleteSeps(name, newfile);
        FindTheWord(name);
    }

    public static void createNewFile(String filename) {
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("file created");
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("Unexpected error while creating a file");
            e.printStackTrace();
        }
    }

    public static void FillTheFile(String filename) {
        try {
            System.out.println("Type some text:");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            FileWriter writer = new FileWriter(filename);
            writer.write(String.valueOf(input));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int CountWords(String filename) {
        int counter = 0;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.trim().split(" ");
                counter += splitted.length;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    public static int[] CountUpperLower(String filename) {
        int[] result = new int[3];
        int lowercounter = 0;
        int uppercounter = 0;
        int sum = 0;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char s : line.toCharArray()) {
                    if (Character.isLetter(s)) {
                        if (Character.isUpperCase(s)) {
                            uppercounter++;
                        }
                        if (Character.isLowerCase(s)) {
                            lowercounter++;
                        }
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sum = lowercounter + uppercounter;
        result[0] = lowercounter;
        result[1] = uppercounter;
        result[2] = sum;
        return result;
    }

    public static int CountSpaces(String filename) {
        int spaces_counter = 0;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char s : line.toCharArray()) {
                    if (Character.isSpaceChar(s)) {
                        spaces_counter++;
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spaces_counter;
    }

    public static void CountDigsAndFloats(String filename) {
        int float_counter = 0;
        int digit_counter = 0;
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.split(" ");
                lines.add(Arrays.toString(splitted));
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            String[] els = l.replace("[", "").replace("]", "").split(", ");
            for (String word : els) {
                if (word.matches("\\d+")) {
                    System.out.println(word + " in 16 dig:");
                    System.out.println(Integer.parseInt(word, 16));
                    digit_counter++;
                }
                if (word.matches("[-+]?[0-9]*\\.\\d+")) {
                    float_counter++;
                    float MyFloat = Float.parseFloat(word);
                    System.out.println(MyFloat + " with 2f:");
                    System.out.printf("%.2f%n", MyFloat);

                }
            }
        }

        System.out.println("Amount of digits in file:");
        System.out.println(digit_counter);
        System.out.println("Amount of float numbers in file:");
        System.out.println(float_counter);

    }

    public static int GetSepsAmount(String filename) {
        int sepscounter = 0;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char ch : line.toCharArray()) {
                    if (!(Character.isDigit(ch) || Character.isSpaceChar(ch) || Character.isLetter(ch))) {
                        sepscounter ++;
                    }
                }
            }
            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sepscounter;
    }

    public static void DeleteSeps(String filename, String newfile) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            PrintWriter writer = new PrintWriter(newfile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String NewLine = line.replaceAll("[,.?!]", "");
                writer.println(NewLine);
            }
            scanner.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data from " + filename + " is written to " + newfile + " without any separators");
    }

    public static void FindTheWord(String filename) {
        String sequence = "everyone";
        try {
            Scanner scanner = new Scanner(new File(filename));
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append(" ");
            }
            scanner.close();
            int first_index = text.indexOf(sequence);
            int last_index = first_index + sequence.length() - 1;
            System.out.println("Индекс первой буквы слова " + sequence);
            System.out.println(first_index);
            System.out.println("Индекс последней буквы слова " + sequence);
            System.out.println(last_index);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
