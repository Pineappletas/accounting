import java.util.HashMap;
import java.util.ArrayList;

public class MonthlyReport {

    HashMap<String, ArrayList<Months>> monthlyReports = new HashMap<>();

    public void dividingMonthlyFiles(ArrayList<String> contentsMonthlyFile) {
        int nameMonth = 1;
        for (String monthlyLine : contentsMonthlyFile) {
            String[] monthlyLines = monthlyLine.split("\\n");
            ArrayList<Months> months = new ArrayList<>();
            for (int i = 1; i < monthlyLines.length; i++) {
                String[] lineMonthlyContents = monthlyLines[i].split(";");
                months.add(new Months(lineMonthlyContents[0],
                        Boolean.parseBoolean(lineMonthlyContents[1]),
                        Integer.parseInt(lineMonthlyContents[2]),
                        Integer.parseInt(lineMonthlyContents[3])));
            }
            monthlyReports.put(("0" + nameMonth), months);
            nameMonth++;
        }
    }

    public String getMonthName(int monthNumber) {
        String[] monthName = {"Январь", "Февраль", "Март"};
        return monthName[monthNumber - 1];
    }

    public void outputInformationOfMonth(ArrayList<Months> contents, String monthName) {
        int maxMonthlyExpenses = 0;
        String mostExpensiveProduct = null;

        int maxMonthlyIncome = 0;
        String mostProfitProduct = null;

        for (Months months : contents) {
            if (months.isExpense) {
                int monthlyExpenses = months.quantity * months.sumOfOne;
                if (monthlyExpenses > maxMonthlyExpenses) {
                    maxMonthlyExpenses = monthlyExpenses;
                    mostExpensiveProduct = months.itemName;
                } else {
                    int monthlyIncome = months.quantity * months.sumOfOne;
                    if (monthlyIncome > maxMonthlyIncome) {
                        maxMonthlyIncome = monthlyIncome;
                        mostProfitProduct = months.itemName;
                    }
                }
            }
        }
        System.out.println("Отчёт за " + monthName);
        System.out.println("Максимальная трата составила: " + maxMonthlyExpenses + " " + mostExpensiveProduct);
        System.out.println("Максимальная прибыль составила: " + maxMonthlyIncome + " " + mostProfitProduct);
    }
}


