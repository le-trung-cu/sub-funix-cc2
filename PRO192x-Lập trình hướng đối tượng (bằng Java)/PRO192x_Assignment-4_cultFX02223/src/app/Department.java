package app;

public class Department {
    //mã bộ phận, tên bộ phận
    private String id, name;
    // số lượng nhân viên hiện tại
    private int countEmployee;

    public Department(String id, String name, int countEmployee) {
        this.id = id;
        this.name = name;
        this.countEmployee = countEmployee;
    }

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

    public int getCountEmployee() {
        return countEmployee;
    }

    public void setCountEmployee(int countEmployee) {
        this.countEmployee = countEmployee;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-10s%-20d",id, name, countEmployee);
    }

}
