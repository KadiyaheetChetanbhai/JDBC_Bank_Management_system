package Employee;


import java.sql.Blob;

public class Employee {
    public int Employee_E_id = 0;
    int Employee_age;
    double Employee_salary;
    String Employee_Name;
    String Employee_designation;
    String Employee_Number;



    public Employee(int Employee_age, double Employee_salary, String Employee_Name, String Employee_designation, String Employee_Number) {
        this.Employee_age = Employee_age;
        this.Employee_salary = Employee_salary;
        this.Employee_Name = Employee_Name;
        this.Employee_designation = Employee_designation;
        this.Employee_Number = Employee_Number;

    }
    public Employee() {
    }

    public int getEmployee_E_id() {
        return Employee_E_id;
    }

    public void setEmployee_E_id(int Employee_E_id) {
        this.Employee_E_id = Employee_E_id;
    }

    public int getEmployee_age() {
        return Employee_age;
    }

    public void setEmployee_age(int Employee_age) {
        this.Employee_age = Employee_age;
    }

    public double getEmployee_salary() {
        return Employee_salary;
    }

    public void setEmployee_salary(double Employee_salary) {
        this.Employee_salary = Employee_salary;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String Employee_Name) {
        this.Employee_Name = Employee_Name;
    }

    public String getEmployee_designation() {
        return Employee_designation;
    }

    public void setEmployee_designation(String Employee_designation) {
        this.Employee_designation = Employee_designation;
    }

    public String getEmployee_Number() {
        return Employee_Number;
    }

    public void setEmployee_Number(String Employee_Number) {
        this.Employee_Number = Employee_Number;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Employee_age=" + Employee_age +
                ", Employee_salary=" + Employee_salary +
                ", Employee_Name='" + Employee_Name + '\'' +
                ", Employee_designation='" + Employee_designation + '\'' +
                ", Employee_Number='" + Employee_Number + '\'' +
                '}';
    }
}
