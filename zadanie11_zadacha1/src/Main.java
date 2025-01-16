import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable{
    public String Name;
    public int Age;
    public String Gender;

    //обычный конструктор
    //public Student(){
        //this.Name = "Алиса";
        //this.Age = 19;
        //this.Gender = "женский";
    //}

    //с параметром
    public Student(String Name, int Age, String Gender){
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
    }

    //билдер-конструктор
    //public Student(StudentBuilder builder){
        //this.Name = builder.Name;
        //this.Age = builder.Age;
        //this.Gender = builder.Gender;
    //}

    //private class StudentBuilder {
        //public String Name;
        //public int Age;
        //public String Gender;

        //public StudentBuilder setName(String Name){
            //this.Name = Name;
            //return this;
        //}

        //public StudentBuilder setAge(int Age){
            //this.Age = Age;
            //return this;
        //}

        //public StudentBuilder setGender(String Gender){
            //this.Gender = Gender;
            //return this;
        //}

        //public Student build(){
            //return new Student(this);
        //}
    //}

    public String getGender() {
        return Gender;
    }

    @Override
    public String toString() {
        return "Студент " + Name + ", " + Age + " лет, пол: " + Gender;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Алиса", 19, "женский"));
        students.add(new Student("Света", 19, "женский"));
        students.add(new Student("Кирилл", 19, "мужской"));
        students.add(new Student("Руслан", 19, "мужской"));
        students.add(new Student("Игорь", 19, "мужской"));
        students.add(new Student("Даша", 19, "женский"));
        students.add(new Student("Рамиль", 19, "мужской"));
        students.add(new Student("Веня", 19, "мужской"));
        students.add(new Student("Илья", 19, "мужской"));
        students.add(new Student("Вика", 19, "женский"));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt"))){
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> Students = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.txt"))) {
            Students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Student student : Students){
            if (student.getGender().equalsIgnoreCase("женский")){
                System.out.println(student);
            }
        }
    }
}