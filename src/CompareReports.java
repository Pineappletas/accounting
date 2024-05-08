import java.util.ArrayList;
import java.util.HashMap;

public class CompareReports {
    void compare(ArrayList<Year> years, HashMap<String, ArrayList<Months>> monthlyReports) {
        for (Year year : years) {
            String month = year.month;
            int monthlyExpenses = 0;
            int monthlyIncome = 0;

            ArrayList<Months> contents = monthlyReports.get(month);
            for (Months months : contents) {
                if (months.isExpense) {
                    monthlyExpenses += months.quantity * months.sumOfOne;
                } else {
                    monthlyIncome += months.quantity * months.sumOfOne;
                }
            }

            if (year.isExpense) {
                if (monthlyExpenses == year.amount) {
                    System.out.println("Расходы за месяц совпадают");
                } else {
                    System.out.println("Расходы за месяц не совпадают");
                }
            } else {
                if (monthlyIncome == year.amount) {
                    System.out.println("Доходы за месяц совпадают");
                } else {
                    System.out.println("Доходы за месяц не совпадают");
                }
            }
        }
    }
}
