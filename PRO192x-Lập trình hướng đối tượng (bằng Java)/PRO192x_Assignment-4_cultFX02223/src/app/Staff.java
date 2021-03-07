package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Staff{
    protected static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // mã nhân viên,
    protected String id;
    // tên nhân viên
    protected String name;
    // ngày sinh nhân viên
    protected LocalDate birthDay;
    // hệ số lương,
    protected double coeffSalary;
    // ngày vào làm,
    protected LocalDate startWorkDay;
    // bộ phận làm việc
    protected String departmentId;
    // số ngày nghỉ phép
    protected int numberOfDaysOff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDate getStartWorkDay() {
        return startWorkDay;
    }

    public void setStartWorkDay(LocalDate startWorkDay) {
        this.startWorkDay = startWorkDay;
    }

    public double getCoeffSalary() {
        return coeffSalary;
    }

    public void setCoeffSalary(double coeffSalary) {
        this.coeffSalary = coeffSalary;
    }

    public int getNumberOfDaysOff() {
        return numberOfDaysOff;
    }

    public void setNumberOfDaysOff(int numberOfDaysOff) {
        this.numberOfDaysOff = numberOfDaysOff;
    }

    public Staff(String id, String name, String departmentId, LocalDate birthDay, LocalDate startWorkDay, double coeffSalary,
            int numberOfDaysOff) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.birthDay = birthDay;
        this.startWorkDay = startWorkDay;
        this.coeffSalary = coeffSalary;
        this.numberOfDaysOff = numberOfDaysOff;
    }

    public int getAge(){
        LocalDate current = LocalDate.now();
        int years = (int) ChronoUnit.YEARS.between(this.birthDay, current);
        return years;
    }
}
