import java.util.ArrayList;
import java.util.Scanner;

class Startup {

    private ArrayList<String> names;
    private ArrayList<Double> salaries;

    public Startup() {
        names = new ArrayList<>();
        salaries = new ArrayList<>();
    }

    public Startup(String[] nam, double[] sal) {
        names = new ArrayList<>();
        salaries = new ArrayList<>();
        for (int i = 0; i < nam.length; i++) {
            salaries.add(sal[i]);
            names.add(nam[i]);
        }
        sortSalaries();
    }

    public void sortSalaries() {
        int n = salaries.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (salaries.get(j) > salaries.get(j + 1)) {
                    // Swap salaries
                    double tempSalary = salaries.get(j);
                    salaries.set(j, salaries.get(j + 1));
                    salaries.set(j + 1, tempSalary);

                    // Swap names
                    String tempName = names.get(j);
                    names.set(j, names.get(j + 1));
                    names.set(j + 1, tempName);
                }
            }
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void removeEmployee(String nam) {
        for (int i = 0; i < names.size(); i++) {
            if (nam.equals(names.get(i))) {
                names.remove(i);
                salaries.remove(i);
                return;
            }
        }
    }

    public void addEmployee(String nam, double sal) {
        if (!names.contains(nam)) {
            names.add(nam);
            salaries.add(sal);
            sortSalaries();
        }
    }

    public void updateSalary(String nam, double sal) {
        for (int i = 0; i < names.size(); i++) {
            if (nam.equals(names.get(i))) {
                if (sal > 5000) {
                    salaries.set(i, 5000.0);
                } else {
                    salaries.set(i, sal);
                }
            }
        }
    }

    public void sortNames() {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + ": " + salaries.get(i));
        }
    }

    public void printStats() {
        double sum = 0;
        for (double salary : salaries) {
            sum += salary;
        }
        System.out.println("Total sum of salaries: " + sum);

        double median;
        int n = salaries.size();
        if (n % 2 == 0) {
            median = (salaries.get(n / 2 - 1) + salaries.get(n / 2)) / 2.0;
        } else {
            median = salaries.get(n / 2);
        }
        System.out.println("Median salary: " + median);
    }

    public boolean findSpy(String nam, Startup otherCompany) {
        if (otherCompany.getNames().contains(nam)) {
            removeEmployee(nam);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Startup startup = new Startup();

        while (true) {
            System.out.print("Enter employee name (or 'quit' to stop): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.print("Enter employee salary: ");
            double salary;
            try {
                salary = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Please enter a valid number.");
                continue;
            }

            startup.addEmployee(name, salary);
        }

        // After quitting, print sorted employee list and stats
        System.out.println("\nEmployees and Salaries:");
        startup.sortNames();

        // Print stats (sum and median salary)
        startup.printStats();

        scanner.close();
    }
}
