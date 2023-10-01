package Bank;


import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Employee.*;

public class Bank {
    public static HashMap<Integer, Employee> Employeedata = new HashMap<>();
    public static HashMap<Long, Bank> Customer_Data = new HashMap<>();
    long accad = 4;

    static Scanner sc = new Scanner(System.in);
    public long Account_No;
    String Customer_Name;
    String Customer_Address;
    String Customer_Number;
    int Customer_Age;
    double Customer_Balance;

    String url = "jdbc:postgresql://localhost:5432/GRPDbms";
    String pass = "1234";
    String user = "postgres";
    Connection con = DriverManager.getConnection(url, user, pass);

    public Bank() throws SQLException {
    }

    public long getAccount_No() {
        return Account_No;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "Account_No=" + Account_No +
                ", Customer_Name='" + Customer_Name + '\'' +
                ", Customer_Address='" + Customer_Address + '\'' +
                ", Customer_Number='" + Customer_Number + '\'' +
                ", Customer_Age=" + Customer_Age +
                ", Customer_Balance=" + Customer_Balance +
                '}';
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getCustomer_Address() {
        return Customer_Address;
    }

    public String getCustomer_Number() {
        return Customer_Number;
    }

    public int getCustomer_Age() {
        return Customer_Age;
    }

    public double getCustomer_Balance() {
        return Customer_Balance;
    }

    public void setCustomer_Balance(double Customer_Balance) {
        this.Customer_Balance = Customer_Balance;
    }

    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

    public  void setCustomer_Address(String Customer_Address) {
        Customer_Address = Customer_Address;
    }

    public void setCustomer_Number(String Customer_Number) {
        this.Customer_Number = Customer_Number;
    }

    public static boolean check_PhoneNumber(String Customer_Number) {

        while (true) {
            if ((Customer_Number.charAt(0) == '9' || Customer_Number.charAt(0) == '7' || Customer_Number.charAt(0) == '8') && Customer_Number.length() == 10) {
                return true;
            } else {
                System.out.println("Enter again:");
                Customer_Number = sc.next();
            }
        }
    }


    public boolean checkpassword(String password) {
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        while (b) {
            if (password.equalsIgnoreCase("1234")) {                       //default pass change it later
                b = false;
            } else {
                System.out.println("enter again");
                password = sc.next();
            }
        }
        return false;
    }

    public Bank(long Account_No, String Customer_Name, String Customer_Address, String Customer_Number, int Customer_Age, double Customer_Balance) throws SQLException {
        this.Account_No = Account_No;
        this.Customer_Name = Customer_Name;
        this.Customer_Address = Customer_Address;
        this.Customer_Number = Customer_Number;
        this.Customer_Age = Customer_Age;
        this.Customer_Balance = Customer_Balance;
    }

    public void createNew_BankAccount() throws SQLException {


        System.out.println("Enter the name");
        Customer_Name = sc.nextLine();
        System.out.println("Enter the address");
        Customer_Address = sc.nextLine();
        System.out.println("Enter the number");
        Customer_Number = sc.nextLine();
        if (!check_PhoneNumber(Customer_Number)) {
            System.out.println("Enter the age");
            Customer_Age = sc.nextInt();
            System.out.println("Enter the balance");
            Customer_Balance = sc.nextDouble();
            Account_No = 100000 + accad;
            accad++;
            Bank bank = new Bank(Account_No, Customer_Name, Customer_Address, Customer_Number, Customer_Age, Customer_Balance);
            System.out.println("Account created for customer:- " + Customer_Name);
            System.out.println("Having balance:- " + Customer_Balance);
            System.out.println("With Account number:- " + Account_No);
            AddingToDatabaseOfCustomers(con,Account_No, Customer_Name, Customer_Address, Customer_Number, Customer_Age, Customer_Balance);
        }
    }
    public void AddingToDatabaseOfCustomers(Connection con, long Account_No,String Customer_Name,String Customer_Address, String Customer_Number,  int Customer_Age, double Customer_Balance) throws SQLException {
        String sql3 = "insert into customers values(?,?,?,?,?)";

        PreparedStatement pst3 = con.prepareStatement(sql3);
        pst3.setLong(1, Account_No);
        pst3.setString(2,Customer_Name);
        pst3.setString(3, Customer_Number);
        pst3.setDouble(4,Customer_Balance);
        pst3.setInt(5,Customer_Age);
        pst3.execute();

    }
    public void AddingToDatabaseOfEmployees(Connection con,int Employee_E_id,String Employee_Name, String Employee_designation,double Employee_salary,String Employee_Numbersc) throws SQLException, IOException {
        String sql4 = "insert into employee values(?,?,?,?,?)";
        PreparedStatement pst4 = con.prepareStatement(sql4);
        pst4.setInt(1,Employee_E_id);
        pst4.setString(2,Employee_Name);
        pst4.setString(3,Employee_designation);
        pst4.setDouble(4,Employee_salary);
        pst4.setString(5,Employee_Numbersc);
        pst4.execute();
    }


    public void displayBalance(long Account_Number) {
        if (Customer_Data.containsKey(Account_Number)) {
            Bank customerBank = Customer_Data.get(Account_Number);
            System.out.println("Account Number: " + Account_Number);
            System.out.println("Customer Balance: " + customerBank.getCustomer_Balance());
        } else {
            System.out.println("Account number not found.");
        }
    }


    public void withdraw_Amount(long Account_No) {
        if (Customer_Data.containsKey(Account_No)) {
            Bank customerBank = Customer_Data.get(Account_No); // Retrieve the Bank instance
            Bank customerBank1 = Customer_Data.get(Account_No); // Retrieve the Bank instance

            System.out.println("Enter the amount you want to withdraw");
            double Amtwithdraw = sc.nextDouble();
            if (Amtwithdraw > customerBank.getCustomer_Balance()) {
                System.out.println("Insufficient balance");
            } else {
                double newBalance = customerBank.getCustomer_Balance() - Amtwithdraw;
                System.out.println(" Withdraw  successfully!.Available Balance " + newBalance);
                customerBank.setCustomer_Balance(newBalance);
                Customer_Data.replace(Account_No, customerBank1, customerBank); // Modify the address variable within the Bank instance
            }

        } else {
            System.out.println("Wrong Account number");
        }
    }

    int j = 1;

    public void displaying_Employess() throws IOException {
        for (Map.Entry<Integer, Employee> a : Employeedata.entrySet()) {
            Employee emp = Employeedata.get(a.getKey());
            String line = "The Employee Id id "+a.getKey()+"\n"+
                    "The Name of the Employee is " + emp.getEmployee_Name() + "\n" +
                    "The age is " + emp.getEmployee_age() + "\n" +
                    "Having phone number number is" + emp.getEmployee_Number() + "\n" +
                    "Is being recurited as the " + emp.getEmployee_designation() + "\n" +
                    "with the salary of Rupess " + emp.getEmployee_salary() + "\n" + "\n";
            System.out.println(line);
            System.out.println("----------------------------");
        }

    }

    public void displaying_Customers() throws IOException {

        for (Map.Entry<Long, Bank> a : Customer_Data.entrySet()) {
            Bank customer = Customer_Data.get(a.getKey());

            String line = "The Account number is " + a.getKey() + "\n" +
                    "The Name of the customer is  " + customer.getCustomer_Name() + "\n" +
                    "The Age of " + customer.getCustomer_Name() + " " + "is " + customer.getCustomer_Age() + "\n" +
                    "The Balance is " + customer.getCustomer_Balance() + "\n" +
                    "The Address is " + customer.getCustomer_Address() + "\n" +
                    "The number is " + customer.getCustomer_Number() + "\n" + "\n";
            System.out.println(line);
            System.out.println("----------------------------");

        }
    }


    int c = 4;

    /*Adding default employees to table and hashmaps*/
    public void adddefault() throws SQLException, IOException {
        Employeedata.put(1, new Employee(14, 124578, "Heet", "Main Manager", "9327915740"));
        Employeedata.put(2, new Employee(15, 124579, "Gautii", "Cashier", "9388915740"));
        Employeedata.put(3, new Employee(16, 124570, "utii", "Cashier1", "9333915740"));

        AddingToDatabaseOfEmployees(con,1,"Heet","Main Manager",124578,"9327915740");
        AddingToDatabaseOfEmployees(con,2,"Gautii","Cashier",124579,"9388915740");
        AddingToDatabaseOfEmployees(con,3,"utii","Cashier1",124570,"9333915740");
    }



    /*Adding default employees to table and hashmaps*/
    public void adddefaultcustomers() throws SQLException {
        Customer_Data.put(100000L, new Bank(100000, "Name1", "xyz", "9876543210", 19, 457896));
        Customer_Data.put(100001L, new Bank(100001, "Name2", "abc", "9876543211", 20, 457897));
        Customer_Data.put(100002L, new Bank(100002, "Name3", "efg", "9876543212", 21, 457898));
        AddingToDatabaseOfCustomers(con,100000,"Name1","xyz","9876543210",19,457896);
        AddingToDatabaseOfCustomers(con,100001,"Name2","abc","9876543211",20,457897);
        AddingToDatabaseOfCustomers(con,100002,"Name3","efg","9876543212",21,457898);
    }

    public void recurit_new_employee() throws IOException, SQLException {


        Employee Empdata = new Employee();
        Empdata.setEmployee_E_id(c);

        System.out.println("Enter the name of the employee to be recruted");
        String Employee_Name = sc.next();
        Empdata.setEmployee_Name(Employee_Name);

        System.out.println("Enter the designation of the employee to be recruted");
        String Employee_designation = sc.next();
        Empdata.setEmployee_designation(Employee_designation);

        System.out.println("Enter the number of the employee to be recruted");
        sc.nextLine();
        String Employee_Numbersc = sc.next();
        if (check_PhoneNumber(Employee_Numbersc)) {
            Empdata.setEmployee_Number(Employee_Numbersc);
        }

        System.out.println("Enter the age of the employee to be recruted");
        int Employee_age = sc.nextInt();
        Empdata.setEmployee_age(Employee_age);

        System.out.println("Enter the salary of the employee to be recruted");
        double Employee_salary = sc.nextDouble();
        Empdata.setEmployee_salary(Employee_salary);
        Employeedata.put(Empdata.Employee_E_id, Empdata);
        int j = 1;
        String Line1 = " Employee no. " + j;
        String line = " The Name of the Employee is " + Employee_Name + "\n The age is " + Employee_age + "\n Having phone number number is" +
                Employee_Numbersc + "\n Is being recurited as the " + Employee_designation + "\n with the salary of Rupess " + Employee_salary;

        AddingToDatabaseOfEmployees(con,c,Employee_Name,Employee_designation,Employee_salary,Employee_Numbersc);
        c++;
    }

    public void add_BalanceToAccout(long Account_Number) throws SQLException {
        if (Customer_Data.containsKey(Account_Number)) {
            Bank customerBank = Customer_Data.get(Account_Number); // Retrieve the Bank instance
            Bank customerBank1 = Customer_Data.get(Account_Number); // Retrieve the Bank instance

            System.out.println("Enter amount you want add:");
            double temp = sc.nextDouble();
            double New_Salary = temp + customerBank.getCustomer_Balance();

            customerBank.setCustomer_Balance(New_Salary); // Modify the address variable within the Bank instance

            // Put the modified instance back into the HashMap
            Customer_Data.replace(Account_Number, customerBank1, customerBank);
            PreparedStatement cst = con.prepareStatement("update customers set balance = ? where account_no = ?");
                cst.setLong(1,Account_Number);
                cst.setDouble(2,New_Salary);
            System.out.println("Balance updated successfully new balance is = " + New_Salary);

        } else {
            System.out.println("Account number not found.");
        }
    }
    public void insertprofilephoto(long id,File f){
        try {
            FileInputStream fis = new FileInputStream(f);
            PreparedStatement pst=con.prepareStatement("update customers set customer_photo = ? where account_no = ?");
            pst.setLong(2,id);
            pst.setBinaryStream(1,fis,f.length());
            pst.executeUpdate();
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addprofilepicofemployee(int id,File f){
        try {
            FileInputStream fis = new FileInputStream(f);
            PreparedStatement pst=con.prepareStatement("update Employee set profile_photo = ? where Employee_E_id = ?");
            pst.setInt(2,id);
            pst.setBinaryStream(1,fis,f.length());
            pst.executeUpdate();
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}


