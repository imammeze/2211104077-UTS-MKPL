import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	public enum Gender {
    	MALE, FEMALE	
	}

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	private LocalDate joinDate = LocalDate.of(yearJoined, monthJoined, dayJoined);
	
	private boolean isForeigner;
	private Gender gender;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	public void setMonthlySalary(int grade) {
		int baseSalary;
		switch (grade) {
			case 1:
				baseSalary = 3000000;
				break;
			case 2:
				baseSalary = 5000000;
				break;
			case 3:
				baseSalary = 7000000;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade: " + grade);
		}

		if (isForeigner) {
			baseSalary *= 1.5;
		}

		this.monthlySalary = (int) baseSalary;
	}

	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber;
	}

	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public boolean isMarried() {
    return spouseIdNumber != null && !spouseIdNumber.isEmpty();
}

	
	public int getAnnualIncomeTax() {
        LocalDate now = LocalDate.now();
        int monthWorkingInYear = (now.getYear() == joinDate.getYear()) ? (now.getMonthValue() - joinDate.getMonthValue()) : 12;

        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthWorkingInYear,
            annualDeductible,
            isMarried(),
            childIdNumbers.size()
        );
    }
}
