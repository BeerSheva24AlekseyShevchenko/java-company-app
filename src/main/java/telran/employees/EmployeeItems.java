package telran.employees;

import java.util.Arrays;
import java.util.HashSet;

import telran.view.InputOutput;
import telran.view.Item;

public class EmployeeItems {
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = { "QA", "Audit", "Development", "Management" };
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_FACTOR = 1;
    final static int MAX_FACTOR = 4;
    final static int MIN_WAGE = 50;
    final static int MAX_WAGE = 1000;
    final static int MIN_HOURS = 1;
    final static int MAX_HOURS = 160;
    final static float MIN_PERCENT = 5;
    final static float MAX_PERCENT = 80;
    final static long MIN_SALES = 0;
    final static long MAX_SALES = 1000000;

    private static Company company;

    public static Item[] getItems(Company company) {
        EmployeeItems.company = company;

        Item[] items = {
                Item.of("Hire Employee", EmployeeItems::readEmployee),
                Item.of("Hire Wage Employee", EmployeeItems::readWageEmployee),
                Item.of("Hire Sales Person", EmployeeItems::readSalesPerson),
                Item.of("Hire Manager", EmployeeItems::readManager),
                Item.ofExit()
        };

        return items;
    }

    public static void readEmployee(InputOutput io) {
        long id = readId(io);
        int salary = readSalary(io);
        String department = readDepartment(io);

        company.addEmployee(new Employee(id, salary, department));
    }

    public static void readWageEmployee(InputOutput io) {
        long id = readId(io);
        int salary = readSalary(io);
        String department = readDepartment(io);
        int wage = readWage(io);
        int hours = readHours(io);

        company.addEmployee(new WageEmployee(id, salary, department, wage, hours));
    }

    public static void readSalesPerson(InputOutput io) {
        long id = readId(io);
        int salary = readSalary(io);
        String department = readDepartment(io);
        int wage = readWage(io);
        int hours = readHours(io);
        float percent = readPercent(io);
        long sales = readSales(io);

        company.addEmployee(new SalesPerson(id, salary, department, wage, hours, percent, sales));
    }

    public static void readManager(InputOutput io) {
        long id = readId(io);
        int salary = readSalary(io);
        String department = readDepartment(io);
        float factor = readFactor(io);

        company.addEmployee(new Manager(id, salary, department, factor));
    }

    private static long readId(InputOutput io) {
        return io.readNumberRange(
                "Enter ID:",
                "Wrong format for ID",
                MIN_ID,
                MAX_ID).longValue();
    }

    private static int readSalary(InputOutput io) {
        return io.readNumberRange(
                "Enter salary:",
                "Wrong format for salary",
                MIN_SALARY,
                MAX_SALARY).intValue();
    }

    private static String readDepartment(InputOutput io) {
        return io.readStringOptions(
                "Enter department:",
                "Wrong format for department",
                new HashSet<String>(Arrays.asList(DEPARTMENTS)));
    }

    private static float readFactor(InputOutput io) {
        return io.readNumberRange(
                "Enter factor:",
                "Wrong format for factor",
                MIN_FACTOR,
                MAX_FACTOR).floatValue();
    }

    private static int readWage(InputOutput io) {
        return io.readNumberRange(
                "Enter wage:",
                "Wrong format for wage",
                MIN_WAGE,
                MAX_WAGE).intValue();
    }

    private static int readHours(InputOutput io) {
        return io.readNumberRange(
                "Enter hours:",
                "Wrong format for hours",
                MIN_HOURS,
                MAX_HOURS).intValue();
    }

    private static float readPercent(InputOutput io) {
        return io.readNumberRange(
                "Enter percent:",
                "Wrong format for percent",
                MIN_PERCENT,
                MAX_PERCENT).intValue();
    }

    private static long readSales(InputOutput io) {
        return io.readNumberRange(
                "Enter sales:",
                "Wrong format for sales",
                MIN_SALES,
                MAX_SALES).longValue();
    }

}
