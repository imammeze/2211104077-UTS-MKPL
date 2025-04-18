public class TaxFunction {

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
            numberOfMonthWorking = 12;
        }

        numberOfChildren = Math.min(numberOfChildren, 3);

        int ptkp = 54000000;
        if (isMarried) {
            ptkp += 4500000;
        }
        ptkp += numberOfChildren * 1500000;

        int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = annualIncome - deductible - ptkp;

        int tax = (int) Math.round(0.05 * Math.max(taxableIncome, 0));
        return tax;
    }
}