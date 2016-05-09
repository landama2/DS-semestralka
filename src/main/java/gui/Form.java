package gui;

import dao.StudentDAO;
import entities.Student;
import utils.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by urban on 5/4/2016.
 */
public class Form extends JFrame {
    private JTabbedPane zalozkovy_panel;
    private JPanel Panel;
    private JTextField student_login_textfield;
    private JButton student_pridat_button;
    private JTextField student_jmeno_textfield;
    private JTextField student_prijmeni_textfield;
    private JComboBox student_rocnik_combobox;
    private JList student_seznam_list;
    private JLabel student_login_label;
    private JLabel student_jmeno_label;
    private JLabel student_prijmeni_label;
    private JLabel student_rocnik_label;
    private JTextField student_vyhledat_login;
    private JComboBox student_vyhledat_rocnik;
    private JTextField student_vyhledat_prijmeni;
    private JTextField student_vyhledat_jmeno;
    private JButton student_vyhledat_button;
    private JTable student_result_table;
    private JPanel student_result_panel;
    private JPanel seznam_studentu;
    private JTextField student_edit_login;
    private JTextField student_edit_jmeno;
    private JTextField student_edit_prijmeni;
    private JButton editovatStudentaButton;
    private JComboBox student_edit_combobox;
    private JTextField textField6;
    private JButton smazatStudentaButton;
    private JPanel student_edit_panel;
    private JPanel sprava_studentu;
    private JPanel sprava_vyucujicich;
    private JTable student_results_table;
    private JList student_found_list;
    //DAOs
    StudentDAO studentDAO = new StudentDAO(Student.class);

    //objects
    private Student newStudent;
    private Student updatedStudent;

    DefaultTableModel studentTableModel;
    DefaultListModel allStudentModel;

    private void createUIComponents() {

    }

    public Form() throws HeadlessException {
        super("Form");
        setContentPane(Panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareStudentSearch();
        allStudentModel = new DefaultListModel();
        updateStudentList();
        student_seznam_list.setModel(allStudentModel);

        //adding student
        student_pridat_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newStudent = new Student();
                newStudent.setLogin(Utils.extractString(student_login_textfield));
                newStudent.setJmeno(Utils.extractString(student_jmeno_textfield));
                newStudent.setPrijmeni(Utils.extractString(student_prijmeni_textfield));
                newStudent.setRocnik(student_rocnik_combobox.getSelectedIndex());
                studentDAO.create(newStudent);
                cleanStudentAdd();
                cleanStudentEdit();
                updateStudentList();
            }
        });
        setVisible(true);

        //searching for student by specifics
        student_vyhledat_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Searchng for some students...");
                List<Student> results = studentDAO.findBy(Utils.extractString(student_vyhledat_login), Utils.extractString(student_vyhledat_jmeno),
                        Utils.extractString(student_vyhledat_prijmeni), student_vyhledat_rocnik.getSelectedIndex());

                cleanStudentResults();
                //if there were any results found - put them in the table
                if (results != null && !results.isEmpty()) {
                    for (Student s : results) {
                        Vector data = new Vector();
                        data.add(s.getLogin());
                        data.add(s.getJmeno());
                        data.add(s.getPrijmeni());
                        data.add(s.getRocnik());
                        studentTableModel.addRow(data);
                        cleanStudentSearch();
                    }
                } else {
                    JOptionPane.showMessageDialog(student_result_table, "Student podle zadanych pozadavku nebyl nalezen.");
                    cleanStudentSearch();
                }
            }
        });

        //edit student button listener
        editovatStudentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!student_edit_login.getText().equals("") && !student_edit_jmeno.getText().equals("") &&
                        !student_edit_prijmeni.getText().equals("") && student_edit_combobox.getSelectedIndex()!=0) {
                    List<Student> found = studentDAO.findBy(student_edit_login.getText(),null,null,0);
                    if (found.size() == 1) {
                        updatedStudent = found.get(0);
                        updatedStudent.setLogin(student_edit_login.getText());
                        updatedStudent.setJmeno(student_edit_jmeno.getText());
                        updatedStudent.setPrijmeni(student_edit_prijmeni.getText());
                        updatedStudent.setRocnik(student_edit_combobox.getSelectedIndex());
                        studentDAO.update(updatedStudent);
                        updateStudentList();
                        cleanStudentEdit();
                    } else {
                        JOptionPane.showMessageDialog(student_edit_panel, "Student podle zadanych pozadavku nebyl nalezen.");
                        cleanStudentEdit();
                    }
                }
            }
        });

        //list selection listener on doubleclick
        student_seznam_list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = student_seznam_list.locationToIndex(e.getPoint());
                    Vector selectedData = (Vector) allStudentModel.getElementAt(index);
                    student_edit_login.setText((String) selectedData.get(0));
                    student_edit_jmeno.setText((String) selectedData.get(1));
                    student_edit_prijmeni.setText((String) selectedData.get(2));
                    student_edit_combobox.setSelectedIndex((Integer) selectedData.get(3));
                }
            }
        });

        //delete student
        smazatStudentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudentList();
            }
        });
    }

    private void prepareStudentSearch() {
        student_vyhledat_rocnik.addItem("");
        for (int i = 1; i < 8; i++) {
            student_vyhledat_rocnik.addItem(i + ". rocnik");
        }

        studentTableModel = new DefaultTableModel();
        studentTableModel.addColumn("Login");
        studentTableModel.addColumn("Jmeno");
        studentTableModel.addColumn("Prijmeni");
        studentTableModel.addColumn("Rocnik");

        Vector header = new Vector();
        header.add("Login");
        header.add("Jmeno");
        header.add("Prijmeni");
        header.add("Rocnik");
        studentTableModel.addRow(header);
        student_result_table.setModel(studentTableModel);

        student_vyhledat_rocnik.setSelectedIndex(0);

        student_edit_combobox.addItem("");
        for (int i = 1; i < 8; i++) {
            student_edit_combobox.addItem(i + ". rocnik");
        }
        student_edit_combobox.setSelectedIndex(0);

    }

    private void cleanStudentSearch() {
        student_vyhledat_login.setText("");
        student_vyhledat_jmeno.setText("");
        student_vyhledat_prijmeni.setText("");
        student_vyhledat_rocnik.setSelectedIndex(0);
    }

    private void cleanStudentAdd() {
        student_login_textfield.setText("");
        student_jmeno_textfield.setText("");
        student_prijmeni_textfield.setText("");
        student_rocnik_combobox.setSelectedIndex(0);
    }

    private void cleanStudentEdit() {
        student_edit_login.setText("");
        student_edit_jmeno.setText("");
        student_edit_prijmeni.setText("");
        student_edit_combobox.setSelectedIndex(0);
        student_seznam_list.clearSelection();
    }

    private void cleanStudentResults() {
        for (int i = studentTableModel.getRowCount() - 1; i > 0; i--) {
            studentTableModel.removeRow(i);
        }
    }

    private void updateStudentList() {
        allStudentModel = new DefaultListModel();
        List<Student> foundStudents = studentDAO.findAll();
        for (int i = 0; i < foundStudents.size(); i++) {
            Vector studentData = new Vector();
            studentData.add(foundStudents.get(i).getLogin());
            studentData.add(foundStudents.get(i).getJmeno());
            studentData.add(foundStudents.get(i).getPrijmeni());
            studentData.add(foundStudents.get(i).getRocnik());
            //better looking data
//            String studentData = foundStudents.get(i).getLogin() + " "
//                    + foundStudents.get(i).getJmeno() + " " + foundStudents.get(i).getPrijmeni() + " "
//                    + foundStudents.get(i).getRocnik() + ". rocnik";
            allStudentModel.addElement(studentData);
        }
        student_seznam_list.setModel(allStudentModel);
    }

}
