package org.cryptanalyzer;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    public static void encrypt() {
        System.out.println("Введите файл для шифровки или нажмите Enter для выбора C:\\cryptanalyzer\\text.txt");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        if (filePath.isEmpty()) {
            filePath = "C:\\cryptanalyzer\\text.txt";
        }
        System.out.println("Введите ключ для шифрования:");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Куда сохранить файл? Нажмите Enter для сохранения в туже папку.");
        String fileOut = scanner.nextLine();
        try {
            String result = Encrypt.caesarEncrypt(FileManager.readFile(filePath), key);
            if (fileOut.isEmpty()) {
                FileManager.writeFile(FileManager.getOutputPath(filePath, "_encrypted.txt"), result);
            } else {
                FileManager.writeFile(fileOut, result);
            }
        } catch (IOException e) {
            System.out.println("Введён неверный путь к файлу");
        }
        System.out.println("Файл зашифрован");


    }

    public static void decrypt() {
        System.out.println("Введите путь к зашифрованному файлу или нажмите Enter для выбора C:\\cryptanalyzer\\text_encrypted.txt");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        if (filePath.isEmpty()) {
            filePath = "C:\\cryptanalyzer\\text_encrypted.txt";
        }
        System.out.println("Введите ключ для расшифровки:");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Куда сохранить файл? Нажмите Enter для сохранения в туже папку.");
        String fileOut = scanner.nextLine();

        try {
            String result = Decrypt.caesarDecrypt(FileManager.readFile(filePath), key);
            if (fileOut.isEmpty()) {
                FileManager.writeFile(FileManager.getOutputPath(filePath, "_decrypted.txt"), result);
            } else {
                FileManager.writeFile(fileOut, result);
            }
        } catch (IOException e) {
            System.out.println("Введён неверный путь к файлу");
        }
        System.out.println("Файл расшифрован");

    }

    public static void bruteForce() {
        System.out.println("Введите путь к зашифрованному файлу или нажмите Enter для выбора C:\\cryptanalyzer\\text_encrypted.txt");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        if (filePath.isEmpty()) {
            filePath = "C:\\cryptanalyzer\\text_encrypted.txt";
        }

        System.out.println("Куда сохранить файл? Нажмите Enter для сохранения в туже папку.");
        String fileOut = scanner.nextLine();

        try {
            int key = BruteForce.caesarBruteForce(FileManager.readFile(filePath));
            System.out.println("Файл был зашифрован ключом: " + key);
            String result = Decrypt.caesarDecrypt(FileManager.readFile(filePath), key);
            if (fileOut.isEmpty()) {
                FileManager.writeFile(FileManager.getOutputPath(filePath, "_BruteForce.txt"), result);
            } else {
                FileManager.writeFile(fileOut, result);
            }
        } catch (IOException e) {
            System.out.println("Введён неверный путь к файлу");
        }
        System.out.println("Файл расшифрован");
    }

    public static void statistical() {
        System.out.println("Введите путь к зашифрованному файлу или нажмите Enter для выбора C:\\cryptanalyzer\\text_encrypted.txt");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        if (filePath.isEmpty()) {
            filePath = "C:\\cryptanalyzer\\text_encrypted.txt";
        }

        System.out.println("Куда сохранить файл? Нажмите Enter для сохранения в туже папку.");
        String fileOut = scanner.nextLine();
        if (fileOut.isEmpty()) {
            fileOut = FileManager.getOutputPath(filePath, "_statistical.txt");
        }

        try {
            String result = StatisticalAnalysis.getResult(FileManager.readFile(filePath), FileManager.readFile("C:\\cryptanalyzer\\reference.txt"));
            FileManager.writeFile(fileOut, result);
        } catch (IOException e) {
            System.out.println("Введён неверный путь к файлу");
        }
        while (true) {
            System.out.println("Какой символ хотите поменять? Нажмите 0 для выхода");
            char change = scanner.nextLine().charAt(0);
            ;
            if (change == '0') {
                return;
            }
            System.out.println("С камим символом хотите поменять?");
            char change2 = scanner.nextLine().charAt(0);
            char temp = '\u0000';
            try {
                String text = FileManager.readFile(fileOut);
                text = text.replace(change, temp);
                text = text.replace(change2, change);
                text = text.replace(temp, change2);
                FileManager.writeFile(fileOut, text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
