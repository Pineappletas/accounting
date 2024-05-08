import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<Year> years = new ArrayList<>();
    String contentsYearlyFile;

    public void dividingYearlyFile(String contentsYearlyFile) {
        this.contentsYearlyFile = contentsYearlyFile;
        String[] yearlyLines = contentsYearlyFile.split("\\n");
        for (int i = 1; i < yearlyLines.length; i++) {
            String[] lineYearlyContents = yearlyLines[i].split(";");
            years.add(new Year(lineYearlyContents[0],
                    Integer.parseInt(lineYearlyContents[1]),
                    Boolean.parseBoolean(lineYearlyContents[2])));
        }
    }

    public void divisionYear(ArrayList<Year> years) {
        HashMap<String, ArrayList<Year>> yearlyReports = new HashMap<>();
        this.years = years;
        for (Year year : years) {
            if (yearlyReports.containsKey(year.month)) {
                yearlyReports.get(year.month).add(year);
            } else {
                ArrayList<Year> months = new ArrayList<>();
                months.add(year);
                yearlyReports.put(year.month, months);
            }
        }

        for (HashMap.Entry<String, ArrayList<Year>> yearLine : yearlyReports.entrySet()) {
            int monthExpense = 0;
            int monthIncome = 0;
            int profit = 0;
            for (Year partOfLine : yearLine.getValue()) {
                if (partOfLine.isExpense) {
                    monthExpense = partOfLine.amount;
                } else {
                    monthIncome = partOfLine.amount;
                }
                profit = monthIncome - monthExpense;

            }
            System.out.println("Прибыль за " + yearLine.getKey() + " месяц 2021 года составила: " + profit);
        }
    }

    public void outputInformationOfYear(ArrayList<Year> years) {
        this.years = years;

        double averageExpense = 0;
        double averageIncome = 0;

        for (Year year : years) {
            if (year.isExpense) {
                averageExpense += (double) year.amount / (double) (years.size() / 2);
            } else {
                averageIncome += (double) year.amount / (double) (years.size() / 2);

            }
        }
        System.out.println("Средний расход за все месяцы в году: " + averageExpense);
        System.out.println("Средний доход за все месяцы в году: " + averageIncome);
    }
}