package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Registration extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, allStudents, errorMessage;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    private JButton btn1, btn2, btn3;
    ArrayList<Student> students = getStudents();
    JLabel[] totalStudents = new JLabel[students.size()];
    JLabel[] totalIDs = new JLabel[students.size()];



    Registration(){
        setSize(600, 700);
        setMinimumSize(new Dimension(600, 550));
        setMaximumSize(new Dimension(600, 800));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration");

        l1= new JLabel("Registration Form");

        l2= new JLabel("Name:");
        l3=new JLabel("Surname");
        l4=new JLabel("Department");
        l5=new JLabel("Date of Birth (DD/MM/YYYY)");
        l6=new JLabel("Email");
        l7=new JLabel("Student ID");
        l8=new JLabel("Phone Number");
        errorMessage=new JLabel("");
        allStudents=new JLabel("Students that already have been registered:");

        tf1=new JTextField();
        tf2=new JTextField();
        tf3=new JTextField();
        tf4=new JTextField();
        tf5=new JTextField();
        tf6=new JTextField();
        tf7=new JTextField();


        btn1=new JButton("Submit");
        btn1.addActionListener(this);
        btn2=new JButton("Clear");
        btn2.addActionListener(this);
        btn3=new JButton("Show all info");
        btn3.addActionListener(this);

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        l4.setBounds(80, 150, 200, 30);
        l5.setBounds(80, 190, 200, 30);
        l6.setBounds(80, 230, 200, 30);
        l7.setBounds(80, 270, 200, 30);
        l8.setBounds(80, 310, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        tf2.setBounds(300, 110, 200, 30);
        tf3.setBounds(300, 150, 200, 30);
        tf4.setBounds(300, 190, 200, 30);
        tf5.setBounds(300, 230, 200, 30);
        tf6.setBounds(300, 270, 200, 30);
        tf7.setBounds(300, 310, 200, 30);
        btn1.setBounds(80, 350, 100, 30);
        btn2.setBounds(200, 350, 100, 30);
        errorMessage.setBounds(100, 395, 600, 30);
        allStudents.setBounds(80, 440, 400, 30);
        btn3.setBounds(400, 440, 150, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l4);
        add(tf3);
        add(l5);
        add(tf4);
        add(l6);
        add(tf5);
        add(l7);
        add(tf6);
        add(l8);
        add(tf7);
        add(btn1);
        add(btn2);
        add(errorMessage);
        add(allStudents);
        add(btn3);

        for (int i = 0; i < students.size(); i++) {
            totalStudents[i] = new JLabel(students.get(i).getName());
            totalStudents[i].setBounds(110, 480 + (i*20), 500, 30);
            add(totalStudents[i]);

        }

        for (int i = 0; i < students.size(); i++) {
            totalIDs[i] = new JLabel("ID: " + students.get(i).getId());
            totalIDs[i].setBounds(300, 480 + (i*20), 500, 30);
            add(totalIDs[i]);

        }

        setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        String get= e.getActionCommand();
        if(get.equals("Submit")){
            try {
                insert();
            } catch (InvalidInputException invalidInputException) {
                invalidInputException.printStackTrace();
            }

        } else if(get.equals("Clear")){
            clearTextField();

        } else if (get.equals("Show all info")) {
            new StudentsWindow();
        }
    }

    public void insert() throws InvalidInputException {


        if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()
                && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty()
                && !tf7.getText().isEmpty()) {
            if (tf5.getText().contains("@") && tf5.getText().contains(".")) {
                if (tf4.getText().contains("/")) {

                    try {
                        File database = new File("data_base.txt");
                        if (database.length() == 0) {
                            FileWriter myWriter = new FileWriter("data_base.txt");
                            myWriter.write(tf1.getText() + '\n' + tf2.getText() + '\n' + tf3.getText() + '\n' +
                                    tf4.getText() + '\n' + tf5.getText() + '\n' + tf6.getText() + '\n' +
                                    tf7.getText() + '\n');
                            myWriter.close();

                            Registration registrationWindow = new Registration();
                            registrationWindow.repaint();
                            dispose();


                        } else {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("data_base.txt",
                                    true));
                            writer.write("");
                            writer.write(tf1.getText() + '\n' + tf2.getText() + '\n' + tf3.getText() + '\n' +
                                    tf4.getText() + '\n' + tf5.getText() + '\n' + tf6.getText() + '\n' +
                                    tf7.getText() + '\n');
                            writer.close();

                            Registration registrationWindow = new Registration();
                            registrationWindow.repaint();
                            dispose();
                        }
                        clearTextField();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    errorMessage.setText("Field of Date of birth is not correctly written");
                    throw new InvalidInputException("Field of Date of birth is not correctly written");
                }
            } else {
                errorMessage.setText("Email field is not correctly written");
                throw new InvalidInputException("Email field is not correctly written");
            }
        } else {
            errorMessage.setText("No inputs must be empty");
            throw new InvalidInputException("No inputs must be empty");
        }

    }

    public void clearTextField(){

        tf1.setText(null);
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
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






