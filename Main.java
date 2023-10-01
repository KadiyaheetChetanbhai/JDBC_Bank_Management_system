import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import DatesAndTime.*;
import Employee.*;
import Bank.*;

import static Bank.Bank.Employeedata;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Bank bank = new Bank();
        bank.adddefault();
        bank.adddefaultcustomers();
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/GRPDbms";
        String pass = "1234";
        String user = "postgres";
        Connection con = DriverManager.getConnection(url, user, pass);
        if (con != null) {
            Scanner sc = new Scanner(System.in);
            Statement st = con.createStatement();
            String sql = "create table IF NOT EXISTS Employee(Employee_E_id serial primary key,Employee_Name varchar, Employee_designation varchar,Employee_salary numeric, Employee_Number varchar,profile_photo bytea)";
            String sql2 = " create table IF NOT EXISTS Customers (Account_No int primary key,Customer_Name varchar,Customer_Number varchar,Customer_Balance numeric,Customer_Age int,Customer_Address varchar,Customer_email varchar,customer_photo bytea)";
            PreparedStatement pst = con.prepareStatement(sql);
            PreparedStatement pst2 = con.prepareStatement(sql2);
            pst.execute();
            pst2.execute();



            System.out.println("welcome to the bank management program ");
            System.out.println("If you are Bank employee please login with the Employee Id else enter no in the" +
                    " response or if you are customer press any key");

            do {

                String response = sc.next();

                if (response.equalsIgnoreCase("yes")) {
                    System.out.println("Please enter the  email to login");
                    String Emp_email = sc.next();
                    System.out.println("Please enter the  password to login");
                    String Emp_password = sc.next();

                    int Empchoice;
                    do {
                        if (!bank.checkpassword(Emp_password)) {
                            String[] s = Emp_email.split("@");           // for diffrent type of employee

                            if (s[1].equalsIgnoreCase("Manager.gmail")) {
                                do {

                                    System.out.println("********************************************");
                                    System.out.println(" Select the task ");
                                    System.out.println(" 1. To recruit  the new  employees\n" +
                                            " 2. To see the number of  the employees working\n" +
                                            " 3. Change or update the data of employees\n" +
                                            " 4. to get the data of customers\n" +
                                            " 5. To get break time");
                                    System.out.println("********************************************");
                                    System.out.println();
                                    Empchoice = sc.nextInt();
                                    boolean gotoouter = false;
                                    int outercheck = 1;
                                    int innercheck = 2;


                                    outer:
                                    switch (Empchoice) {
                                        case 1:
                                            bank.recurit_new_employee();
                                            System.out.println("Employee added succesfully");
                                            break;
                                        case 2:
                                            System.out.println("The List of Employees working is  ");

                                            bank.displaying_Employess();

                                            break;
                                        case 3:
                                            System.out.println("Please enter the Employee_E_id :- ");
                                            int Employee_E_id = sc.nextInt();
                                            int choices;
                                            do {
                                                if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                    System.out.println("Enter what you want to change or update");
                                                    System.out.println("********************************************");
                                                    System.out.println(" 1. To update in salary\n" +
                                                            " 2. To update in name\n" +
                                                            " 3. To update in designation\n" +
                                                            " 4. To update the Phone number\n" +
                                                            " 5. To Add profile_pic in employee\n" +
                                                            " 6. To get back  menu \n");
                                                    System.out.println("Enter the choice");
                                                    choices = sc.nextInt();
                                                    System.out.println("********************************************");
                                                    switch (choices) {

                                                        case 1:                /*updating salary */

                                                            if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                                Employee Empdetails = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance
                                                                Employee Empdetails1 = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance

                                                                System.out.println("Enter new Salary:");
                                                                double newsalary = sc.nextDouble();

                                                                Empdetails.setEmployee_salary(newsalary); // Modify the salary variable within the employee instance

                                                                // Put the modified instance back into the HashMap
                                                                Bank.Employeedata.replace(Employee_E_id, Empdetails1, Empdetails);
                                                                updatesalaryindatabase(con, Employee_E_id, newsalary);
                                                                System.out.println("Salary updated successfully!");
                                                            }
                                                            break;
                                                        case 2:                        /*update the name  */

                                                            if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                                Employee Empdetails1 = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance

                                                                Employee Empdetails = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance
                                                                System.out.println("Enter new name:");
                                                                String newName = sc.next();
                                                                Empdetails.setEmployee_Name(newName); // Modify the salary variable within the employee instance


                                                                updatenameindatabaseofEmployee(con, Employee_E_id, newName);
                                                                // Put the modified instance back into the HashMap
                                                                Bank.Employeedata.replace(Employee_E_id, Empdetails1, Empdetails);
                                                                System.out.println("Name updated successfully!");
                                                            }
                                                            break;
                                                        case 3:                        /*update designation*/
                                                            if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                                Employee Empdetails1 = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance

                                                                Employee Empdetails = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance
                                                                System.out.println("Enter new designation:");
                                                                String newdesignation = sc.next();

                                                                Empdetails.setEmployee_designation(newdesignation); // Modify the salary variable within the employee instance

                                                                // Put the modified instance back into the HashMap
                                                                Bank.Employeedata.replace(Employee_E_id, Empdetails1, Empdetails);
                                                                updatedesignationindatabase(con, Employee_E_id, newdesignation);
                                                                System.out.println("Designation updated successfully!");
                                                            }
                                                            break;
                                                        case 4:                           /*update number*/
                                                            if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                                Employee Empdetails = Bank.Employeedata.get(Employee_E_id);
                                                                Employee Empdetails1 = Bank.Employeedata.get(Employee_E_id); // Retrieve the employee instance
                                                                // Retrieve the employee instance
                                                                System.out.println("Enter new Number:");
                                                                String newNumber = sc.next();
                                                                if (Bank.check_PhoneNumber(newNumber)) {

                                                                    Empdetails.setEmployee_Number(newNumber); // Modify the salary variable within the employee instance

                                                                    // Put the modified instance back into the HashMap
                                                                    Bank.Employeedata.replace(Employee_E_id, Empdetails1, Empdetails);
                                                                    updatenumberindatabaseofemployee(con, Employee_E_id, newNumber);
                                                                    System.out.println("Number updated successfully!");
                                                                }
                                                            }
                                                            break;
                                                        case 5:

                                                            System.out.println("Enter the Employee_E_id");
                                                            int Employee_E_id1 = sc.nextInt();
                                                            if (Bank.Employeedata.containsKey(Employee_E_id)) {
                                                                System.out.println("Enter the path of the image");
                                                                String path = sc.next();
                                                                File f = new File(path);
                                                                bank.addprofilepicofemployee(Employee_E_id1, f);
                                                            }
                                                            break;
                                                        case 6:
                                                            gotoouter = true;
                                                            break;
                                                        default:
                                                            System.out.println("Enter again given input is incorrect");
                                                    }

                                                    if (gotoouter) {
                                                        outercheck = 1; // Set the outerValue to trigger the outer case 1
                                                        gotoouter = false; // Reset the flag
                                                        break outer; // Exit the outer switch
                                                    }
                                                    break;

                                                } else {
                                                    System.out.println("Given acc number is invalid enter again");
                                                    int i = 3;
                                                    while (i > 0) {
                                                        System.out.println("Enter again ," + "Chances left = " + i);
                                                        Employee_E_id = sc.nextInt();
                                                        i--;
                                                    }
                                                }

                                            } while (true);
                                            break;

                                        case 4:
                                            System.out.println("The list of the customers having Account ");
                                            bank.displaying_Customers();
                                            break;
                                        case 5:
                                            System.out.println("Thank you for serving!!.");
                                            System.exit(0);
                                            break;
                                        default:
                                            System.out.println("Wrong choice try again");

                                    }
                                }
                                while (true);

                            } else if (s[1].equalsIgnoreCase("employee.gmail")) {               // for employess mail check
                                do {
                                    System.out.println("********************************************");
                                    System.out.println(" Select to help the  customer ");
                                    System.out.println(" 1. To add Balance to customer Acccount\n" +
                                            " 2. To change or update the customers data\n" +
                                            " 3. To create account of new customer\n" +
                                            " 4. to display the details of customers\n" +
                                            " 5. To close the tab ");
                                    System.out.println("********************************************");
                                    Empchoice = sc.nextInt();
                                    long TempAccount;
                                    boolean gotoouter = false;
                                    int outercheck = 1;
                                    int innercheck = 2;

                                    outer:
                                    switch (Empchoice) {

                                        case 1:
                                            System.out.println("Enter the Account number ");
                                            TempAccount = sc.nextLong();
                                            bank.add_BalanceToAccout(TempAccount);
                                            break;

                                        case 2:
                                            System.out.println("Enter the Account number");
                                            TempAccount = sc.nextLong();
                                            int choices;
                                            do {
                                                if (Bank.Customer_Data.containsKey(TempAccount)) {
                                                    System.out.println("Enter what you want to change or update");
                                                    System.out.println("********************************************");
                                                    System.out.println(" 1. To update in address\n" +
                                                            " 2. To update in number\n" +
                                                            " 3. To update in name\n" +
                                                            " 4. To exit\n" +
                                                            " 5. To Add profile_pic in customers" +
                                                            " 6. To do other works\n");
                                                    System.out.println("Enter the choice");
                                                    choices = sc.nextInt();
                                                    System.out.println("********************************************");

                                                    switch (choices) {

                                                        case 1:            //updating address of customer

                                                            if (Bank.Customer_Data.containsKey(TempAccount)) {
                                                                Bank customerBank = Bank.Customer_Data.get(TempAccount);
                                                                Bank customerBank1 = Bank.Customer_Data.get(TempAccount); // Retrieve the Bank instance
                                                                // Retrieve the Bank instance
                                                                System.out.println("Enter new address:");
                                                                sc.nextLine();
                                                                String newAddress = sc.nextLine();

                                                                customerBank.setCustomer_Address(newAddress); // Modify the address variable within the Bank instance

                                                                // Put the modified instance back into the HashMap
                                                                Bank.Customer_Data.replace(TempAccount, customerBank1, customerBank);
                                                                updateaddressindatabase(con, TempAccount, newAddress);
                                                                System.out.println("Address updated successfully!");
                                                            }
                                                            break;
                                                        case 2:                // update number of customer

                                                            if (Bank.Customer_Data.containsKey(TempAccount)) {

                                                                Bank customerBank1 = Bank.Customer_Data.get(TempAccount); // Retrieve the Bank instance
                                                                Bank customerBank = Bank.Customer_Data.get(TempAccount); // Retrieve the Bank instance
                                                                System.out.println("Enter new number:");
                                                                sc.nextLine();
                                                                String newNumber = sc.nextLine();
                                                                if (Bank.check_PhoneNumber(newNumber)) {

                                                                    customerBank.setCustomer_Number(newNumber); // Modify the address variable within the Bank instance

                                                                    // Put the modified instance back into the HashMap
                                                                    Bank.Customer_Data.replace(TempAccount, customerBank1, customerBank);
                                                                    updatenumberindatabase(con, TempAccount, newNumber);
                                                                    System.out.println("Number updated successfully!");
                                                                } else {
                                                                    System.out.println("error");
                                                                }
                                                            }
                                                            break;
                                                        case 3:                   //update name

                                                            if (Bank.Customer_Data.containsKey(TempAccount)) {
                                                                Bank customerBank = Bank.Customer_Data.get(TempAccount);
                                                                Bank customerBank1 = Bank.Customer_Data.get(TempAccount); // Retrieve the Bank instance
                                                                // Retrieve the Bank instance
                                                                System.out.println("Enter new name:");
                                                                sc.nextLine();
                                                                String newName = sc.nextLine();
                                                                customerBank.setCustomer_Name(newName); // Modify the address variable within the Bank instance

                                                                // Put the modified instance back into the HashMap
                                                                Bank.Customer_Data.replace(TempAccount, customerBank1, customerBank);
                                                                CallableStatement cst = con.prepareCall("{call updatecustomername(?,?)}");
                                                                cst.setLong(1, TempAccount);
                                                                cst.setString(2, newName);
                                                                cst.execute();
                                                                System.out.println("Name updated successfully!");
                                                            }
                                                            break;
                                                        case 4:              //exit
                                                            System.out.println("Thank you visit again!!");
                                                            System.exit(0);
                                                            break;

                                                        case 5:
                                                            System.out.println("Enter the account number");
                                                            TempAccount = sc.nextLong();
                                                            if(Bank.Customer_Data.containsKey(TempAccount)){
                                                                System.out.println("Enter the path of the image");
                                                                String path = sc.next();
                                                                File f = new File(path);
                                                                bank.insertprofilephoto(TempAccount,f);
                                                            }

                                                        case 6:             // to return back to main menu
                                                            gotoouter = true;
                                                            break;
                                                        default:
                                                            System.out.println("Enter again given input is incorrect");
                                                    }
                                                    if (gotoouter) {
                                                        outercheck = 1; // Set the outerValue to trigger the outer case 1
                                                        gotoouter = false; // Reset the flag
                                                        break outer; // Exit the outer switch
                                                    }
                                                    break;

                                                } else {
                                                    int i = 3;
                                                    System.out.println("Given acc number is invalid enter again ");
                                                    while (i > 0) {
                                                        System.out.println("Enter again " + " and your chances left are " + i);
                                                        TempAccount = sc.nextLong();
                                                        i--;
                                                    }
                                                }
                                            } while (true);
                                            break;
                                        case 3:
                                            bank.createNew_BankAccount();
                                            Bank.Customer_Data.put(bank.Account_No, bank);
                                            break;
                                        case 4:
                                            System.out.println("The details of customers are below ");
                                            bank.displaying_Customers();
                                            break;
                                        case 5:
                                            System.exit(0);
                                            break;
                                        default:
                                            System.out.println("Wrong choice try again");

                                    }
                                } while (true);
                            } else {

                                System.out.println("Your credentials are wrong please try again ");
                                System.out.println("Please enter the  email to login");
                                Emp_email = sc.next();
                                System.out.println("Please enter the  password to login");
                                Emp_password = sc.next();

                            }
                        }
                    } while (true);
                }

                // for customer use
                int choices;
                System.out.println("********************************************");
                System.out.println(
                        " 1. To see Account balance\n" +
                                " 2. To Withdraw funds from account\n" +
                                " 3. To add fund\n" +
                                " 4. To return back\n" +
                                " 5. to Transfer or withdraw Funds\n" +
                                " 6. To exit bank");
                System.out.println("Enter the choice");
                choices = sc.nextInt();
                System.out.println("********************************************");

                long TempAccount;
                outer:
                switch (choices) {

                    case 1:
                        System.out.println("Enter the Account number");
                        TempAccount = sc.nextLong();
                        bank.displayBalance(TempAccount);
                        break;
                    case 2:
                        System.out.println("Enter the Account number");
                        TempAccount = sc.nextLong();
                        bank.withdraw_Amount(TempAccount);
                        break;
                    case 3:
                        System.out.println("Enter the Account number");
                        TempAccount = sc.nextLong();
                        bank.add_BalanceToAccout(TempAccount);
                        break;
                    case 4:
                        System.out.println("Return back to previous");
                        break;
                    case 5:
                        try {
                            // Create tables if not exist
                            createTables(con);

                            // Menu-driven program
                            while (true) {
                                System.out.println("1. Transfer Funds");
                                System.out.println("2. Exit");
                                System.out.print("Enter your choice: ");
                                Scanner scanner = new Scanner(System.in);
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        transferFunds(con);
                                        break;
                                    case 2:
                                        con.close();
                                        System.out.println("Exiting...");
                                        return;
                                    default:
                                        System.out.println("Invalid choice. Please select again.");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choice try again");

                }
            } while (true);
        }
    }

    public static void createTables(Connection con) throws SQLException {
        String createTransactionTableSQL = "CREATE TABLE IF NOT EXISTS transaction (trans_id SERIAL  PRIMARY KEY, src_acc_no INT, dest_acc_no INT, trans_amt DECIMAL(10, 2), creation_date TIMESTAMP DEFAULT current_timestamp)";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(createTransactionTableSQL);
        }
    }

    /*update the name of employee in table*/
    public static void updatenameindatabaseofEmployee(Connection con, int id, String name) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update Employee set Employee_Name=? where Employee_E_id=?");
        pst.setString(1, name);
        pst.setInt(2, id);
        pst.execute();
    }

    /*update the designation of employee*/
    public static void updatedesignationindatabase(Connection con, int id, String designation) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update Employee set Employee_designation=? where Employee_E_id=?");
        pst.setString(1, designation);
        pst.setInt(2, id);
        pst.execute();
    }

    /*update the salary of employee*/
    public static void updatesalaryindatabase(Connection con, int id, double salary) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update Employee set Employee_salary=? where Employee_E_id=?");
        pst.setDouble(1, salary);
        pst.setInt(2, id);
        pst.execute();
    }

    /*update the number of employee*/
    public static void updatenumberindatabaseofemployee(Connection con, int id, String number) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update Employee set Employee_Number=? where Employee_E_id=?");
        pst.setString(1, number);
        pst.setInt(2, id);
        pst.execute();
    }

    /*update in address of customers*/
    public static void updateaddressindatabase(Connection con, long id, String address) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update customers set customer_address=? where account_no=?");
        pst.setString(1, address);
        pst.setLong(2, id);
        pst.execute();
    }

    /*update in number of customers table*/

    public static void updatenumberindatabase(Connection con, long id, String number) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update customers set customer_number=? where account_no=?");
        pst.setString(1, number);
        pst.setLong(2, id);
        pst.execute();
    }


    public static void transferFunds(Connection con) throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter source account number: ");
            int srcAccNo = scanner.nextInt();
            System.out.print("Enter destination account number: ");
            int destAccNo = scanner.nextInt();
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            con.setAutoCommit(false);

            try (PreparedStatement updateSrcAccount = con.prepareStatement("UPDATE customers SET customer_balance = customer_balance - ? WHERE account_no = ?");
                 PreparedStatement updateDestAccount = con.prepareStatement("UPDATE customers SET customer_balance = customer_balance + ? WHERE account_no = ?");
                 PreparedStatement insertTransaction = con.prepareStatement("INSERT INTO transaction(src_Acc_No,dest_Acc_No,trans_amt)  VALUES ( ?, ?, ?)")) {

                //changes due to the transactions
                updateSrcAccount.setDouble(1, amount);
                updateSrcAccount.setInt(2, srcAccNo);
                updateDestAccount.setDouble(1, amount);
                updateDestAccount.setInt(2, destAccNo);


                //calling the DatesAndTime object for getting the current time and date

                //Adding the deatils of transaction in transaction table
                insertTransaction.setInt(1, srcAccNo);
                insertTransaction.setInt(2, destAccNo);
                insertTransaction.setDouble(3, amount);


                //Final step to excecute all the things
                updateSrcAccount.executeUpdate();
                updateDestAccount.executeUpdate();
                insertTransaction.executeUpdate();

                //To commit our transaction
                con.commit();
                System.out.println("Transaction successful.");
            } catch (SQLException e) {
                //if error occurs it will rollback
                con.rollback();
                System.out.println("Transaction failed. Rolling back changes.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}