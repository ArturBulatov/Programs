package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String sign = "+-/*";
        boolean cycle = true;
        while (cycle == true) {
            int operand1 = -999;
            int operand2 = -999;
            int res = -999;
            boolean numType = false;
            String operator = "+";
            System.out.println("Введите арифметическое выражение:");
            String initString = sc.nextLine();

            // Проверка на выход пользователем:
            if ((initString.toUpperCase()).equals("Q")){
                System.out.println("Пользователь завершил выполнение программы");
                break;
            }
            String[] part = initString.split(" ");

            // Проверка на количество аргументов и пробелы:
            if (part.length != 3) {
                throw new Exception("Ошибка! Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) ");
            }

            // Проверка на арабские числа:
            if (part[0].matches("[-+]?\\d+") && part[2].matches("[-+]?\\d+")) {
                operand1 = Integer.valueOf(part[0]);
                operand2 = Integer.valueOf(part[2]);
                operator = part[1];
                numType = true;

            }

            // Проверка на римские числа и использование разных систем счисления:
            if (numType == false) {
                try {
                    operand1 = RimNumber.valueOf(part[0].toUpperCase()).ordinal() + 1;
                    operand2 = RimNumber.valueOf(part[2].toUpperCase()).ordinal() + 1;
                    operator = part[1];
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Ошибка! Используются некорректные аргументы.");
                }
            }

            // Проверка корректности операнда:
            if (sign.contains(operator) == false) {
                throw new Exception("Ошибка! Оператор должен быть вида \"+, -, /, *\".");
            }

            // Проверка на принадлежность диапазону от 1 до 10:
            if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) {
                throw new Exception("Ошибка! Числа должны быть в диапазоне от 1 до 10 включительно.");
            }

            // Выполнение арифметических операций +,-,*,/:
            switch (operator) {
                case "+":
                    res = (operand1) + (operand2);
                    break;
                case "-":
                    res = (operand1) - (operand2);
                    break;
                case "/":
                    res = (operand1) / (operand2);
                    break;
                case "*":
                    res = (operand1) * (operand2);
                    break;
            }

            // Вывод результата и проверка на наличие отрицательного числа в римских числах:
            if (numType) {
                System.out.println(res);
            } else if (numType == false && res < 1) {
                throw new Exception("Ошибка! В римской системе только положительные числа.");
            } else {
                System.out.println(RimNumber.values()[res - 1]);
            }
            System.out.println("Для выхода нажмите на клавишу Q");
            System.out.println();
        }
    }
}

// Перечисления римских чисел:
enum RimNumber
{ I , II , III , IV , V , VI , VII , VIII , IX , X , XI , XII , XIII , XIV , XV ,
    XVI , XVII , XVIII , XIX , XX , XXI , XXII , XXIII , XXIV , XXV , XXVI ,
    XXVII , XXVIII , XXIX , XXX , XXXI , XXXII , XXXIII , XXXIV , XXXV ,
    XXXVI , XXXVII , XXXVIII , XXXIX , XL , XLI , XLII , XLIII , XLIV ,
    XLV , XLVI , XLVII , XLVIII , XLIX , L , LI , LII , LIII , LIV , LV , LVI
    , LVII , LVIII , LIX , LX , LXI , LXII , LXIII , LXIV , LXV , LXVI ,
    LXVII , LXVIII , LXIX , LXX , LXXI , LXXII , LXXIII , LXXIV , LXXV ,
    LXXVI , LXXVII , LXXVIII , LXXIX , LXXX , LXXXI , LXXXII , LXXXIII
    , LXXXIV , LXXXV , LXXXVI , LXXXVII , LXXXVIII , LXXXIX , XC ,
    XCI , XCII , XCIII , XCIV , XCV , XCVI , XCVII , XCVIII , XCIX , C
}



