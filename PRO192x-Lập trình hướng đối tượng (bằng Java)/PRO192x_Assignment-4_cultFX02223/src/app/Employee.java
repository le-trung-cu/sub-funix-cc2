package app;

import java.time.LocalDate;

public class Employee extends Staff implements ICalculator {
    // số giờ làm thêm
    private double timeOT;
    

    // trả về lương nhân viên.
    public double calculateSalary(){
        return coeffSalary * 3000000 + timeOT * 200000; 
    }

    public Employee(String id, String name, String departmentId, LocalDate birthDay, LocalDate startWorkDay, double coeffSalary,
            int numberOfDaysOff, double timeOT) {
        super(id, name, departmentId, birthDay, startWorkDay, coeffSalary, numberOfDaysOff);
        this.timeOT = timeOT;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20s%-10d%-20s%-20.2f%-10d%-10.1f%-20s" ,
         id, name, departmentId, getAge(), startWorkDay.format(dateFormat), coeffSalary, numberOfDaysOff, timeOT, "N/N");
    }

}