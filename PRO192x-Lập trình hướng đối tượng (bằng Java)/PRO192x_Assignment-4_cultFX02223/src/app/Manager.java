package app;

import java.time.LocalDate;

public class Manager extends Staff implements ICalculator {
    public static final String BUSINESS_LEADER = "Business Leader";
    public static final String PROJECT_LEADER = "Project Leader";
    public static final String TECHNICAL_LEADER = "Technical Leader";
    // chức danh
    private String title;

    public Manager(String id, String name, String departmentId, LocalDate birthDay, LocalDate startWorkDay, double coeffSalary,
            int numberOfDaysOff, String title) {
        super(id, name, departmentId, birthDay, startWorkDay, coeffSalary, numberOfDaysOff);
        this.title = title;
    }

    @Override
    public double calculateSalary() {
        try {
            return coeffSalary * 5000000 + salaryTrachNhiem();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return coeffSalary * 5000000;
        }
    }

    public double salaryTrachNhiem() throws Exception {
        switch (title) {
        case BUSINESS_LEADER:
            return 8000000;
        case PROJECT_LEADER:
            return 5000000;
        case TECHNICAL_LEADER:
            return 6000000;
        }
        throw new Exception("title manager is uncorect!");
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20s%-10d%-20s%-20f%-10d%-10s%-20s" ,
         id, name, departmentId, getAge(), startWorkDay.format(dateFormat), coeffSalary, numberOfDaysOff, "N/N", title);
    }
}
