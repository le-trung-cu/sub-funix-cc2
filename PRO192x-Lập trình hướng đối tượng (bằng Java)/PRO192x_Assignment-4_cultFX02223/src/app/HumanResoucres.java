package app;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HumanResoucres {
    Scanner in = new Scanner(System.in);
    List<Staff> staffs = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    // định dạng tiền tệ Việt Nam
    NumberFormat vndFormat;

    // 1 Hiển thị danh sách nhân viên hiện có trong công ty
    public void displayStaffs() {
        display(staffs);
    }

    // 2 Hiển thị các bộ phận trong công ty.
    public void displayDepartments() {
        System.out.println(String.format("%-5s%-10s%-20s", "id", "name", "count Employee"));
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    // 3 Hiển thị các nhân viên theo từng bộ phận.
    public void displayStaffsByDepartment() {
        System.out.print("Nhập Department Id: ");
        String id = in.nextLine();
        if (!containDepartment(id)) {
            System.out.println("Không tìm thấy department.");
            return;
        }

        List<Staff> employeeList = new ArrayList<>();
        for (Staff staff : staffs) {
            if (staff.getDepartmentId().equalsIgnoreCase(id)) {
                employeeList.add(staff);
            }
        }

        display(employeeList);
    }

    // 4. Thêm nhân viên mới vào công ty.
    public void addStaff() throws ParseException {
        System.out.println("Thêm nhân viên mới vào công ty: ");
        System.out.println("\t1. Thêm nhân viên thông thường.");
        System.out.println("\t2. Thêm nhân viên là cấp quản lý (có thêm chức vụ).");
        System.out.print("\tYour choise: ");
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            System.out.println("\tTạo nhân viên thông thường:");
        } else if (choice == 2) {
            System.out.println("\tTạo nhân viên quản lý:");
        } else {
            System.err.println("\tKhông hợp lệ");
            return;
        }

        System.out.print("\tId: ");
        String id = in.nextLine();

        // check id.
        if (containEmployee(id)) {
            System.out.println("\tId nhân viên đã tồn tại:");
            return;
        }

        System.out.print("\tTên nhân viên: ");
        String name = in.nextLine();

        System.out.print("\tdepartment Id: ");
        String departmentId = in.nextLine();

        // check department Id
        if (!containDepartment(departmentId)) {
            System.out.println("Không tìm thấy department.");
            return;
        }

        System.out.print("\tNgày sinh (dd/MM/yyyy): ");
        String birthDayStr = in.nextLine();

        System.out.print("\tNgày vào làm (dd/MM/yyyy):: ");
        String startWorkDayStr = in.nextLine();

        System.out.print("\tHệ số lương: ");
        double coeffSalary = in.nextDouble();

        System.out.print("\tSố ngày nghỉ phép: ");
        int numberOfDaysOff = in.nextInt();
        in.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDay = LocalDate.parse(birthDayStr, formatter);
        LocalDate startWorkDay = LocalDate.parse(startWorkDayStr, formatter);

        Staff staff = null;

        if (choice == 1) {
            System.out.print("\tSố giờ làm thêm: ");
            double timeOT = in.nextDouble();
            in.nextLine();
            staff = new Employee(id, name, departmentId, birthDay, startWorkDay, coeffSalary, numberOfDaysOff, timeOT);
        } else if (choice == 2) {
            System.out.println("\tChức danh: ");
            System.out.println("\t\t1. " + Manager.BUSINESS_LEADER);
            System.out.println("\t\t2. " + Manager.PROJECT_LEADER);
            System.out.println("\t\t3. " + Manager.TECHNICAL_LEADER);
            System.out.print("\t\tyour choise: ");
            int titleNum = in.nextInt();
            in.nextLine();
            String title = null;
            switch (titleNum) {
            case 1:
                title = Manager.BUSINESS_LEADER;
                break;
            case 2:
                title = Manager.PROJECT_LEADER;
                break;
            case 3:
                title = Manager.TECHNICAL_LEADER;
                break;
            default:
                System.out.println("\tChức danh không đúng");
                return;
            }
            staff = new Manager(id, name, departmentId, birthDay, startWorkDay, coeffSalary, numberOfDaysOff, title);
        }

        if (staff != null) {
            this.staffs.add(staff);
            // update count employee
            for (Department department : departments) {
                if (department.getId().equalsIgnoreCase(staff.getId())) {
                    department.setCountEmployee(department.getCountEmployee() + 1);
                    return;
                }
            }
        }
    }

    // 5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.
    public void search() {
        System.out.print("Tìm kiếm nhân viên: ");
        String search = in.nextLine().toUpperCase();

        List<Staff> empList = new ArrayList<>();

        for (Staff staff : staffs) {
            if (staff.getId().toUpperCase().contains(search) || staff.getName().toUpperCase().contains(search)) {
                empList.add(staff);
            }
        }
        if (empList.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên.");
        } else {
            display(empList);
        }
    }

    // 6. Hiển thị bảng lương của nhân viên toàn công ty.
    public void displaySalarys() {

        System.out.println(String.format("%-10s%-20s%-15s%-15s", "Id", "Name", "Department", "Salary"));
        for (Staff staff : staffs) {
            if (staff instanceof ICalculator) {
                double salary = ((ICalculator) staff).calculateSalary();
                String salaryStr = vndFormat.format(salary);
                System.out.println(String.format("%-10s%-20s%-15s%15s", staff.getId(), staff.getName(),
                        staff.getDepartmentId(), salaryStr));
            }
        }
    }

    // 7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần.
    public void displaySalarysAsc() {
        List<ICalculator> employees = new ArrayList<>();
        for (Staff staff : staffs) {
            if (staff instanceof ICalculator) {
                employees.add((ICalculator) staff);
            }
        }

        employees.sort((o1, o2) -> {
            if (o1.calculateSalary() - o2.calculateSalary() > 0)
                return 1;
            if (o1.calculateSalary() - o2.calculateSalary() < 0)
                return -1;
            return 0;
        });

        System.out.println(String.format("%-10s%-20s%-15s%-15s", "Id", "Name", "Department", "Salary"));

        for (ICalculator calculator : employees) {
            if (calculator instanceof Staff) {
                Staff staff = (Staff) calculator;
                String salaryStr = vndFormat.format(calculator.calculateSalary());
                System.out.println(String.format("%-10s%-20s%-15s%-15s", staff.getId(), staff.getName(),
                        staff.getDepartmentId(), salaryStr));
            }
        }
    }

    public static void main(String[] args) {
        HumanResoucres app = new HumanResoucres();
        app.run();
    }

    public void run() {
        while (true) {
            try {

                System.out.println("1. Hiển thị danh sách nhân viên hiện có trong công ty.");
                System.out.println("2. Hiển thị các bộ phận trong công ty.");
                System.out.println("3. Hiển thị các nhân viên theo từng bộ phận.");
                System.out.println("4. Thêm nhân viên mới vào công ty.");
                System.out.println("5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
                System.out.println("6. Hiển thị bảng lương của nhân viên toàn công ty.");
                System.out.println("7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần.");

                System.out.print("Your choise: ");
                int choise = in.nextInt();
                in.nextLine();

                switch (choise) {
                case 1:
                    System.out.println("Danh sách nhân viên:");
                    displayStaffs();
                    break;
                case 2:
                    System.out.println("Danh sách phòng ban:");
                    displayDepartments();
                    break;
                case 3:
                    System.out.println("Danh sách nhân viên theo phòng ban:");
                    displayStaffsByDepartment();
                    break;
                case 4:
                    try {
                        System.out.println("Thêm Nhân viên:");
                        addStaff();
                    } catch (ParseException e) {
                        System.out.println("Đã xãy ra lỗi.");
                    }
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    System.out.println("Danh sách lương nhân viên");
                    displaySalarys();
                    break;
                case 7:
                    System.out.println("Danh sách lương nhân viên tăng dần");
                    displaySalarysAsc();
                    break;
                default:
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error!: " + e.getMessage());
            }

        }
    }

    public void display(List<Staff> staffs) {
        System.out.println(String.format("%-10s%-20s%-20s%-10s%-20s%-20s%-10s%-10s%-20s", "id", "name", "department",
                "Age", "startWorkDay", "coeffSalary", "Days Off", "timeOT", "Title"));
        for (Staff staff : staffs) {
            System.out.println(staff.toString());
        }
    }

    private boolean containEmployee(String id) {
        for (Staff item : staffs) {
            if (item.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean containDepartment(String id) {
        for (Department department : departments) {
            if (department.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }

    public HumanResoucres() {
        this.in = new Scanner(System.in);
        this.staffs = new ArrayList<>();
        this.departments = new ArrayList<>();

        Locale vn = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(vn);
        vndFormat = NumberFormat.getCurrencyInstance(vn);
        seedData();
    }

    private void seedData() {
        departments.add(new Department("DP1", "Name DP1", 3));
        departments.add(new Department("DP2", "Name DP2", 4));
        departments.add(new Department("DP3", "Name DP3", 2));
        int employeeId = 0;
        for (int index = 0; index < departments.size(); index++) {
            Department department = departments.get(index);
            for (int i = 0; i < department.getCountEmployee(); i++) {
                employeeId++;
                int yearBirthDay = (int) (1995 + 5 * Math.random());
                int monthBirthDay = (int) (1 + 11 * Math.random());
                int dayBirthDay = (int) (1 + 20 * Math.random());

                LocalDate birthDay = LocalDate.of(yearBirthDay, monthBirthDay, dayBirthDay);

                int yearWorkDay = (int) (2015 + 5 * Math.random());
                int monthWorkDay = (int) (1 + 11 * Math.random());
                int dayWorkDay = (int) (1 + 20 * Math.random());

                LocalDate startWorkDay = LocalDate.of(yearWorkDay, monthWorkDay, dayWorkDay);
                double coeffSalary = 1 + Math.floor(100 * 3 * Math.random()) / 100;
                int numberOfDaysOff = (int) (5 * Math.random());
                int timeOT = (int) (20 * Math.random());

                if (i == 0) {
                    String title = "";
                    int x = (int) (3 * Math.random());
                    switch (x) {
                    case 0:
                        title = Manager.BUSINESS_LEADER;
                        break;
                    case 1:
                        title = Manager.PROJECT_LEADER;
                        break;
                    case 2:
                        title = Manager.TECHNICAL_LEADER;
                        break;
                    }
                    staffs.add(new Manager("EM" + employeeId, "Name EM " + employeeId + department.getId(),
                            department.getId(), birthDay, startWorkDay, coeffSalary, numberOfDaysOff, title));

                    continue;
                }

                staffs.add(new Employee("EM" + employeeId, "Name EM " + employeeId + department.getId(),
                        department.getId(), birthDay, startWorkDay, coeffSalary, numberOfDaysOff, timeOT));

            }
        }

    }
}
