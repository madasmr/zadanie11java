import java.io.*;
import java.util.*;

class Employee{
    String name;
    String surname;
    int age;
    String gender;
    Position position;
    Department department;

    public Employee(String name, String surname, int age, String gender, Position position, Department department){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.department = department;
    }

    public String getFullName(){
        return name + " " + surname;
    }

    public Position getPosition(){
        return position;
    }

    public Department getDepartment(){
        return department;
    }

    public void actionLog (String action) {
        System.out.println(getFullName() + " (" + position.getName() + "): " + action);
    }

    public void performDuties() {
        actionLog("Начало рабочего дня");
        if (position.getName().equals("Бухгалтер")) {
            actionLog("Расчет зарплаты");
            actionLog("Подписание документов");
            actionLog("Подготовка отчета");
            actionLog("Отправка документов в банк");
        } else if (position.getName().equals("Менеджер")) {
            actionLog("Консультация с клиентами");
            actionLog("Ведение переговоров о покупке с клиентами");
            actionLog("Подготовка отчета по продажам");
            actionLog("Выдача покупки");
        } else if (position.getName().equals("Менеджер по персоналу")) {
            actionLog("Обучение нового персонала");
            actionLog("Проведение собеседования");
            actionLog("Подготовка отчета");
        } else if (position.getName().equals("Генеральный директор")) {
            actionLog("Проведение собрания директоров");
            actionLog("Подписание документов");
        }
        actionLog("Рабочий день завершен :)");
    }
}

class Department{
    String name;
    List<Employee> employees = new ArrayList<>();
    List<Position> positions = new ArrayList<>();

    public Department(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}

class Position{
    String name;
    double salary;

    public Position(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }
}

class Company{
    public static void generateEmployeeList(List<Employee> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
        for (Employee employee : employees) {
            writer.write(employee.getFullName() + ", " + employee.getPosition().getName() + ", " + employee.department.getName() + ", " + employee.position.getSalary());
            writer.newLine();
        }
        writer.close();
    }

    public static void generateUniquePositions(List<Employee> employees) throws IOException {
        Map<String, Integer> positionCount = new HashMap<>();
        for (Employee employee : employees) {
            String positionName = employee.getPosition().getName();
            positionCount.put(positionName, positionCount.getOrDefault(positionName, 0) + 1);
        }

        List<Employee> uniquePositionEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (positionCount.get(employee.getPosition().getName()) == 1) {
                uniquePositionEmployees.add(employee);
            }
        }

        uniquePositionEmployees.sort(Comparator.comparing(Employee::getFullName));

        BufferedWriter writer = new BufferedWriter(new FileWriter("unique_employees.txt"));

        for (Employee employee : uniquePositionEmployees) {
            writer.write(employee.getFullName() + ", " + employee.getPosition().getName());
            writer.newLine();
        }

        writer.close();
    }

    public static void generateSortedDepartments(List<Department> departments) throws IOException {
        departments.sort(Comparator.comparingInt(Department::getEmployeeCount));

        BufferedWriter writer = new BufferedWriter(new FileWriter("departments.txt"));

        for (Department department : departments) {
            writer.write(department.getName() + ", количество сотрудников: " + department.getEmployeeCount());
            writer.newLine();
        }
        writer.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Position manager = new Position("Менеджер", 50000);
        Position accountant = new Position("Бухгалтер", 55000);
        Position HRmanager = new Position("Менеджер по персоналу", 45000);
        Position CEO = new Position("Генеральный директор", 165000);

        Department sales = new Department("Отдел продаж");
        Department finance = new Department("Финансовый отдел");
        Department HR = new Department("Отдел кадров");
        Department boardOfDirectors = new Department("Совет директоров");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Алиса", "Бахтина", 30, "женский", manager, sales));
        employees.add(new Employee("Светлана", "Попова", 29, "женский", accountant, finance));
        employees.add(new Employee("Кирилл", "Новиков", 35, "мужской", manager, sales));
        employees.add(new Employee("Руслан", "Абиджанов", 40, "мужской", accountant, finance));
        employees.add(new Employee("Дарья", "Исаева", 47, "женский", CEO, boardOfDirectors));
        employees.add(new Employee("Анастасия", "Никитенко", 33, "женский", HRmanager, HR));

        sales.employees.add(employees.get(0));
        sales.employees.add(employees.get(2));
        finance.employees.add(employees.get(1));
        finance.employees.add(employees.get(3));
        HR.employees.add(employees.get(5));
        boardOfDirectors.employees.add(employees.get(4));

        List<Department> departments = Arrays.asList(sales, finance, HR, boardOfDirectors);

        Company.generateEmployeeList(employees);
        Company.generateUniquePositions(employees);
        Company.generateSortedDepartments(departments);

        for (Employee employee : employees) {
            employee.performDuties();
        }
    }
}