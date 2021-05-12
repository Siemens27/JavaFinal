package sample;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentsWindow extends JFrame {
    JLabel name, surname, department, dateOfBirth, email, studentId, phoneNumber;
    ArrayList<Student> students = getStudents();
    JLabel[] totalStudents = new JLabel[students.size()];

    StudentsWindow(){
        setSize(1000, 550);
        setMinimumSize(new Dimension(1000, 550));
        setMaximumSize(new Dimension(1000, 550));
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Students");

        name = new JLabel("Name");
        surname = new JLabel("Surname");
        department = new JLabel("Department");
        dateOfBirth = new JLabel("Date of birth");
        email = new JLabel("email");
        studentId = new JLabel("Student ID");
        phoneNumber = new JLabel("Phone Number");

        name.setBounds(10, 10, 400, 30);
        surname.setBounds(130, 10, 400, 30);
        department.setBounds(250, 10, 400, 30);
        dateOfBirth.setBounds(370, 10, 400, 30);
        email.setBounds(520, 10, 400, 30);
        studentId.setBounds(760, 10, 400, 30);
        phoneNumber.setBounds(880, 10, 400, 30);

        add(name);
        add(surname);
        add(department);
        add(dateOfBirth);
        add(email);
        add(studentId);
        add(phoneNumber);

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getName());
            totalStudents[i].setBounds(10, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getSurname());
            totalStudents[i].setBounds(130, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getDepartment());
            totalStudents[i].setBounds(250, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getDateOfBirth());
            totalStudents[i].setBounds(370, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getEmail());
            totalStudents[i].setBounds(520, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getId());
            totalStudents[i].setBounds(760, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getPhoneNum());
            totalStudents[i].setBounds(880, 30 + (i*20), 500, 30);
            add(totalStudents[i]);
        }

        setVisible(true);
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        try {
            File myObj = new File("data_base.txt");
            Scanner myReader = new Scanner(myObj);

            String[] arr = new String[7];

            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arr[i % 7] = data;
                i++;
                if (i % 7 == 0) {
                    Student p = new Student(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
                    arr = new String[7];
                    students.add(p);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return students;
    }


}






