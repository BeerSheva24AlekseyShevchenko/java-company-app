package telran.employees;

import java.util.function.Function;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CompanyItems {
    private static Company company;
    public static Item[] getItems(Company company) {
        CompanyItems.company = company;

        Item[] items = {
            Item.of("Add Employee", CompanyItems::addEmployee),
            Item.of("Display Employee Data", CompanyItems::displayEmployee),
            Item.of("Fire Employee", CompanyItems::fireEmployee),
            Item.of("Department Salary Budget", CompanyItems::displayDepartmentSalaryBudget),
            Item.of("List of Departments", CompanyItems::displayDepartments),
            Item.of("Display Managers with Most Factor", CompanyItems::displayManagersMostFactor),
            Item.ofExit()
        };

        return items;
    };


    private static void addEmployee(InputOutput io) {
        Item[] items = {
            createEmployeeItem("Hire Employee", EmployeeReader::readEmployee),
            createEmployeeItem("Hire Wage Employee", EmployeeReader::readWageEmployee),
            createEmployeeItem("Hire Sales Person", EmployeeReader::readSalesPerson),
            createEmployeeItem("Hire Manager", EmployeeReader::readManager),
            Item.ofExit()
        };
    
        new Menu("Select Employee type", items).perform(io);
    }

    private static Item createEmployeeItem(String displayName, Function<InputOutput, Employee> readEmployee) {
        return Item.of(displayName, i -> company.addEmployee(readEmployee.apply(i)));
    }


    private static void displayEmployee(InputOutput io) {
        long id = io.readLong("Enter ID:", null);
        Employee empl = company.getEmployee(id);
        io.writeLine(empl == null ? "Employee not found" : empl);
    }

    private static void fireEmployee(InputOutput io) {
        long id = io.readLong("Enter ID:", null);
        try {
            company.removeEmployee(id);
            io.writeLine("Employee fired");
        } catch (Exception e) {
            io.writeLine("Employee not found");
        }
    }

    private static void displayDepartmentSalaryBudget(InputOutput io) {
        String department = io.readString("Enter department:");
        io.writeLine(company.getDepartmentBudget(department));
    }

    private static void displayDepartments(InputOutput io) {
        io.writeLine(String.join(", ", company.getDepartments()));
    }

    private static void displayManagersMostFactor(InputOutput io) {
        for (Manager manager : company.getManagersWithMostFactor()) {
            io.writeLine(manager);
        }
    }
}
