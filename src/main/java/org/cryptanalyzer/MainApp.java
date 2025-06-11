package org.cryptanalyzer;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.print("""
                Выберите пункт:
                1: Шифрование
                2: Расшифровать по ключу
                3: Brute force
                4: Статистический анализ
                0: Выход
                """);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        switch (n) {
            case 0: {
                break;
            }
            case 1: {
                Console.encrypt();
                break;
            }
            case 2: {
                Console.decrypt();
                break;
            }
            case 3: {
                Console.bruteForce();
                break;
            }
            case 4: {
                Console.statistical();
                break;
            }
        }
    }
}