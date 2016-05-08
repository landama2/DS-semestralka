package gui;

import dao.StudentDAO;
import entities.Student;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextField textField4;
    private JTextField textField5;
    private JButton zapsatPredmetButton;
    private JLabel student_login_label;
    private JLabel student_jmeno_label;
    private JLabel student_prijmeni_label;
    private JLabel student_rocnik_label;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JTextField textField3;
    private JList list1;
    private JTextField student_vyhledat_login;
    private JComboBox student_vyhledat_rocnik;
    private JTextField student_vyhledat_prijmeni;
    private JTextField student_vyhledat_jmeno;
    private JButton student_vyhledat_button;
    private JTable student_results_table;
    private JList student_found_list;
    //DAOs
    StudentDAO studentDAO = new StudentDAO(Student.class);

    //objects
    private Student newStudent;

    private void createUIComponents() {
        // TODO: place custom component creation code here
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

        student_vyhledat_rocnik.addItem("");
        for (int i = 1; i < 8; i++) {
            student_vyhledat_rocnik.addItem(i + ". rocnik");
        }

        //adding student
        student_pridat_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newStudent = new Student();
                newStudent.setLogin(Utils.extractString(student_login_textfield));
                newStudent.setJmeno(Utils.extractString(student_jmeno_textfield));
                newStudent.setPrijmeni(Utils.extractString(student_prijmeni_textfield));
                newStudent.setRocnik(student_rocnik_combobox.getSelectedIndex());
                studentDAO.create(newStudent);
            }
        });
        setVisible(true);

        //searching for student by specifics
        student_vyhledat_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Searchng for some students...");
                List<Student> results = studentDAO.findBy(Utils.extractString(student_vyhledat_login),Utils.extractString(student_vyhledat_jmeno),
                        Utils.extractString(student_vyhledat_prijmeni),student_vyhledat_rocnik.getSelectedIndex());
                DefaultListModel<Student> model = new DefaultListModel<Student>();
                DefaultTableModel tableModel = new DefaultTableModel();

                tableModel.addColumn("Login");
                tableModel.addColumn("Jmeno");
                tableModel.addColumn("Prijmeni");
                tableModel.addColumn("Rocnik");
                if (results!=null) {
                    for (Student s : results) {
                        Vector data = new Vector();
                        data.add(s.getLogin());
                        data.add(s.getJmeno());
                        data.add(s.getPrijmeni());
                        data.add(s.getRocnik());

                        tableModel.addRow(data);
                    }
                    //nefunguje protoze tabulka
                    student_results_table.setModel(tableModel);
                    student_results_table.repaint();
                    student_results_table.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(student_results_table,"Student podle zadanych pozadavku nebyl nalezen.");
                    student_vyhledat_login.setText("");
                    student_vyhledat_jmeno.setText("");
                    student_vyhledat_prijmeni.setText("");
                    student_vyhledat_rocnik.setSelectedIndex(0);
                }
            }
        });
    }
}
