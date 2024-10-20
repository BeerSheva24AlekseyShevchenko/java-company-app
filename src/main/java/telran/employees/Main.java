package telran.employees;

import telran.io.Persistable;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardIO;

public class Main {
    public static void main(String[] args) {
        Company company = new CompanyImpl();

        if (company instanceof Persistable persistable) {
            try {
                persistable.restoreFromFile("employees.data");
            } catch (Exception e) {
                System.err.println("Failed to load company data");
            }
        }

        Item[] items = CompanyItems.getItems(company);
        Menu menu = new Menu("Company Application", items);
        menu.perform(new StandardIO());
    }
}