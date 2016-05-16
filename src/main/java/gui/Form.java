package gui;

import dao.StudentDAO;
import dao.VyucujiciDAO;
import entities.Student;
import entities.Vyucujici;
import service.StudentService;
import service.VyucujiciService;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

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
    private JButton smazatStudentaButton;
    private JPanel student_edit_panel;
    private JPanel sprava_studentu;
    private JPanel sprava_vyucujicich;
    private JList vyucujici_seznam_list;
    private JTextField vyucujici_pridat_login;
    private JTextField vyucujici_pridat_jmeno;
    private JTextField vyucujici_pridat_prijmeni;
    private JTextField vyucujici_edit_login;
    private JTextField vyucujici_edit_jmeno;
    private JTextField vyucujici_edit_prijmeni;
    private JButton pridatVyucujicihoButton;
    private JButton editovatVyucujicihoButton;
    private JButton smazatVyucujicihoButton;
    private JPanel vyucujici_sprava_panel;
    private JList predmet_seznam_list;
    private JTextField predmet_pridat_kod;
    private JTextField predmet_pridat_nazev;
    private JTextField predmet_pridat_rozsah;
    private JSpinner predmet_pridat_kredity_spinner;
    private JTextField predmet_edit_kod;
    private JTextField predmet_edit_nazev;
    private JTextField predmet_edit_rozsah;
    private JButton pridatPredmetButton;
    private JButton editovatPredmetButton;
    private JButton smazatPredmetButton;
    private JSpinner predmet_edit_kredity_spinner;
    private JPanel sprava_predmetu;
    private JTable student_results_table;
    private JList student_found_list;

    //DAOs
    StudentDAO studentDAO = new StudentDAO(Student.class);
    VyucujiciDAO vyucujiciDAO = new VyucujiciDAO(Vyucujici.class);

    //Services
    StudentService studentService = new StudentService();
    VyucujiciService vyucujiciService = new VyucujiciService();

    //objects
    private Student newStudent;
    private Student updatedStudent;
    private Vyucujici newVyucujici;
    private Vyucujici updatedVyucujici;

    private List<Student> students;

    DefaultTableModel studentTableModel;
    DefaultListModel allStudentModel;
    DefaultListModel allVyucujiciModel;

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
        updateVyucujiciList();
        student_seznam_list.setModel(allStudentModel);
        vyucujici_seznam_list.setModel(allVyucujiciModel);

        //adding student
        student_pridat_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newStudent = new Student();
                newStudent.setLogin(Utils.extractString(student_login_textfield));
                newStudent.setJmeno(Utils.extractString(student_jmeno_textfield));
                newStudent.setPrijmeni(Utils.extractString(student_prijmeni_textfield));
                newStudent.setRocnik(student_rocnik_combobox.getSelectedIndex());
                studentService.createStudent(newStudent);
//                studentDAO.create(newStudent);
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

                students = results;
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
                    System.out.println("hledany login:"+student_edit_login.getText());
                    if (found.size() == 1) {
                        updatedStudent = found.get(0);
                        updatedStudent.setLogin(student_edit_login.getText());
                        updatedStudent.setJmeno(student_edit_jmeno.getText());
                        updatedStudent.setPrijmeni(student_edit_prijmeni.getText());
                        updatedStudent.setRocnik(student_edit_combobox.getSelectedIndex());
                        studentService.updateStudent(updatedStudent);
                        updateStudentList();
                        cleanStudentEdit();
                    } else {
                        for (Student s : found){
                            System.out.println(s.getJmeno());
                        }
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
                if (!student_edit_login.getText().equals("") && !student_edit_jmeno.getText().equals("") &&
                        !student_edit_prijmeni.getText().equals("") && student_edit_combobox.getSelectedIndex()!=0) {
                    List<Student> found = studentDAO.findBy(student_edit_login.getText(),null,null,0);
                    System.out.println("hledany login:"+student_edit_login.getText());
                    if (found.size() == 1) {
                        updatedStudent = found.get(0);
                        studentDAO.delete(updatedStudent);
                        updateStudentList();
                        cleanStudentEdit();
                    } else {
                        JOptionPane.showMessageDialog(student_edit_panel, "Student podle zadanych pozadavku nebyl nalezen.");
                        cleanStudentEdit();
                    }
                }
            }
        });

        //add vyucujici button listener
        pridatVyucujicihoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newVyucujici = new Vyucujici();
                newVyucujici.setLogin(Utils.extractString(vyucujici_pridat_login));
                newVyucujici.setJmeno(Utils.extractString(vyucujici_pridat_jmeno));
                newVyucujici.setPrijmeni(Utils.extractString(vyucujici_pridat_prijmeni));
                newVyucujici.setCeleJmeno(newVyucujici.getJmeno() + " " + newVyucujici.getPrijmeni());
                vyucujiciService.addVyucujici(newVyucujici);
                cleanVyucujiciAdd();
                updateVyucujiciList();
            }
        });

        //smazat vyucujiciho
        smazatVyucujicihoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!vyucujici_edit_login.getText().equals("") && !vyucujici_edit_jmeno.getText().equals("") &&
                        !vyucujici_edit_prijmeni.getText().equals("")) {
                    List<Vyucujici> foundVyucujici = vyucujiciDAO.findBy(Utils.extractString(vyucujici_edit_login),null,null);
                    if (foundVyucujici.size() == 1) {
                        updatedVyucujici = foundVyucujici.get(0);
                        vyucujiciDAO.delete(updatedVyucujici);
                        updateVyucujiciList();
                        cleanVyucujiciEdit();
                    }
                }
            }
        });

        //double click choose vyucujici
        vyucujici_seznam_list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = vyucujici_seznam_list.locationToIndex(e.getPoint());
                    Vector selectedData = (Vector) allVyucujiciModel.getElementAt(index);
                    vyucujici_edit_login.setText((String) selectedData.get(0));
                    vyucujici_edit_jmeno.setText((String) selectedData.get(1));
                    vyucujici_edit_prijmeni.setText((String) selectedData.get(2));
                }
            }
        });

        //update vyucujiciho
        editovatVyucujicihoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!vyucujici_edit_login.getText().equals("") && !vyucujici_edit_jmeno.getText().equals("") &&
                        !vyucujici_edit_prijmeni.getText().equals("")) {
                    List<Vyucujici> found = vyucujiciDAO.findBy(Utils.extractString(vyucujici_edit_login),null,null);
                    if (found.size() == 1) {
                        updatedVyucujici = found.get(0);
                        updatedVyucujici.setLogin(Utils.extractString(vyucujici_edit_login));
                        updatedVyucujici.setJmeno(Utils.extractString(vyucujici_edit_jmeno));
                        updatedVyucujici.setPrijmeni(Utils.extractString(vyucujici_edit_prijmeni));
                        updatedVyucujici.setCeleJmeno(updatedVyucujici.getJmeno() + " " + updatedVyucujici.getPrijmeni());
                        if (vyucujiciService.updateVyucujici(updatedVyucujici)) {
                            System.out.println("Vyucujici successfuly updated.");
                        } else {
                            JOptionPane.showMessageDialog(vyucujici_sprava_panel,"Vyucujiciho nebylo mozno updatovat.","Update se nepodaril",JOptionPane.ERROR_MESSAGE);
                        }
                        updateVyucujiciList();
                        cleanVyucujiciEdit();
                    }
                }
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
        students = foundStudents;
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

    //=============================================
    //SPRAVA VYUCUJICICH
    //=============================================

    private void cleanVyucujiciAdd() {
        vyucujici_pridat_login.setText("");
        vyucujici_pridat_jmeno.setText("");
        vyucujici_pridat_prijmeni.setText("");
    }

    private void updateVyucujiciList() {
        allVyucujiciModel = new DefaultListModel();
        List<Vyucujici> foundVyucujici = vyucujiciDAO.findAll();
        for (int i = 0; i < foundVyucujici.size(); i++) {
            Vector vyucujiciData = new Vector();
            vyucujiciData.add(foundVyucujici.get(i).getLogin());
            vyucujiciData.add(foundVyucujici.get(i).getJmeno());
            vyucujiciData.add(foundVyucujici.get(i).getPrijmeni());
            allVyucujiciModel.addElement(vyucujiciData);
        }
        vyucujici_seznam_list.setModel(allVyucujiciModel);
    }

    private void cleanVyucujiciEdit() {
        vyucujici_edit_login.setText("");
        vyucujici_edit_jmeno.setText("");
        vyucujici_edit_prijmeni.setText("");
        vyucujici_seznam_list.clearSelection();
    }

}
