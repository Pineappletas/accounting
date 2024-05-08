import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> contentsMonthlyFile = new ArrayList<>();
        String contentsYearlyFile = null;

        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        CompareReports compareReports = new CompareReports();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                for (int i = 1; i < 4; i++) {
                    String path = "E:\\ЯП\\dev\\accounting\\m.20210" + i + ".csv";
                    contentsMonthlyFile.add(readFileContentsOrNull(path));
                }
                monthlyReport.dividingMonthlyFiles(contentsMonthlyFile);
            } else if (command == 2) {
                String path = "E:\\ЯП\\dev\\accounting\\y.2021.csv";
                contentsYearlyFile = readFileContentsOrNull(path);
                yearlyReport.dividingYearlyFile(contentsYearlyFile);
            } else if (command == 3) {
                if (!contentsMonthlyFile.isEmpty() && contentsYearlyFile != null) {
                    compareReports.compare(yearlyReport.years, monthlyReport.monthlyReports);
                } else {
                    System.out.println("Необходимо выполнить команды 1 и 2");
                }
            } else if (command == 4) {
                if (!contentsMonthlyFile.isEmpty() && contentsYearlyFile != null) {
                    for (String monthKey : monthlyReport.monthlyReports.keySet()) {
                        ArrayList<Months> contents = monthlyReport.monthlyReports.get(monthKey);
                        String monthName = monthlyReport.getMonthName(Integer.parseInt(monthKey));
                        monthlyReport.outputInformationOfMonth(contents, monthName);
                    }
                } else {
                    System.out.println("Необходимо выполнить команды 1 и 2");
                }
            } else if (command == 5) {
                System.out.println("2021 год");
                if (!contentsMonthlyFile.isEmpty() && contentsYearlyFile != null) {
                    yearlyReport.divisionYear(yearlyReport.years);
                    yearlyReport.outputInformationOfYear(yearlyReport.years);

                } else {
                    System.out.println("Необходимо выполнить команды 1 и 2");
                }
            } else if (command == 0) {
                break;
            } else {
                System.out.println("Вы ввели неверную команду");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действите, которое требуется выполнить:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выход");
    }

    static String readFileContentsOrNull(String path) {
        try {
            System.out.println("Считывание успешно проведено.");
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}


