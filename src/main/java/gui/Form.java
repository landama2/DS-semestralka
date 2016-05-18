package gui;

import dao.InstancePredmetDAO;
import dao.PredmetDAO;
import dao.StudentDAO;
import dao.VyucujiciDAO;
import entities.InstancePredmet;
import entities.Predmet;
import entities.Student;
import entities.Vyucujici;
import service.InstancePredmetService;
import service.PredmetService;
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
import java.util.ArrayList;
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
    private JPanel vyhledavani;
    private JCheckBox predmet_pridat_zkouska_checkbox;
    private JCheckBox predmet_edit_zkouska_checkbox;
    private JPanel predmet_edit_panel;
    private JRadioButton predmet_pridat_letni;
    private JRadioButton predmet_pridat_zimni;
    private JRadioButton predmet_edit_letni;
    private JRadioButton predmet_edit_zimni;
    private JPanel predmet_pridat_panel;
    private JPanel zapsat_predmet;
    private JList zapsat_predmet_seznam_list;
    private JList zapsat_student_seznam_list;
    private JSpinner zapsat_predmet_rok_spinner;
    private JCheckBox zapsat_predmet_zimni;
    private JCheckBox zapsat_predmet_letni;
    private JButton zapsat_zobrazit_predmety;
    private JLabel zapsat_predmet_student;
    private JLabel zapsat_predmet_predmet;
    private JButton zapsatPredmetButton;
    private JTable student_results_table;
    private JList student_found_list;

    //DAOs
    StudentDAO studentDAO = new StudentDAO(Student.class);
    VyucujiciDAO vyucujiciDAO = new VyucujiciDAO(Vyucujici.class);
    PredmetDAO predmetDAO = new PredmetDAO(Predmet.class);
    InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);

    //Services
    StudentService studentService = new StudentService();
    VyucujiciService vyucujiciService = new VyucujiciService();
    PredmetService predmetService = new PredmetService();
    InstancePredmetService instancePredmetService = new InstancePredmetService();

    //objects
    private Student newStudent;
    private Student updatedStudent;
    private Vyucujici newVyucujici;
    private Vyucujici updatedVyucujici;
    private Predmet newPredmet;
    private Predmet updatedPredmet;

    private List<Student> students;
    private List<Vyucujici> vyucujici;
    private List<Predmet> predmety;

    DefaultTableModel studentTableModel;
    DefaultListModel allStudentModel;
    DefaultListModel allVyucujiciModel;
    DefaultListModel allPredmetModel;
    SpinnerNumberModel spinnerNumberModelKredity;
    SpinnerNumberModel spinnerNumberModelRok;

    JPopupMenu menu;

    public Form() throws HeadlessException {
        super("Form");
        setContentPane(Panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //PREPARE FORM
        prepareStudentSearch();

        updateStudentList();
        updateVyucujiciList();
        updatePredmetList();
        student_seznam_list.setModel(allStudentModel);
        vyucujici_seznam_list.setModel(allVyucujiciModel);
        predmet_seznam_list.setModel(allPredmetModel);
        spinnerNumberModelKredity = new SpinnerNumberModel(0, 0, 30, 1);
        spinnerNumberModelRok = new SpinnerNumberModel(2016,2016,3000,1);
        zapsat_predmet_zimni.setSelected(false);
        zapsat_predmet_letni.setSelected(false);
        predmet_pridat_kredity_spinner.setModel(spinnerNumberModelKredity);
        predmet_edit_kredity_spinner.setModel(spinnerNumberModelKredity);
        zapsat_predmet_rok_spinner.setModel(spinnerNumberModelRok);
        predmet_pridat_letni.setSelected(true);
        predmet_pridat_zimni.setSelected(false);
        zapsat_predmet_student.setVisible(false);
        zapsat_predmet_predmet.setVisible(false);


        //===================================
        //===================================
        //VYHLEDAVANI TAB
        //===================================
        //===================================
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

        //===================================
        //===================================
        //SPRAVA STUDENTU TAB
        //===================================
        //===================================

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

        //edit student button listener
        editovatStudentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check if all parameters are filled
                if (!student_edit_login.getText().equals("") && !student_edit_jmeno.getText().equals("") &&
                        !student_edit_prijmeni.getText().equals("") && student_edit_combobox.getSelectedIndex() != 0) {
                    //get student on desired index - they are ordered
                    updatedStudent = students.get(student_seznam_list.getSelectedIndex());
                    updatedStudent.setLogin(student_edit_login.getText());
                    updatedStudent.setJmeno(student_edit_jmeno.getText());
                    updatedStudent.setPrijmeni(student_edit_prijmeni.getText());
                    updatedStudent.setRocnik(student_edit_combobox.getSelectedIndex());
                    if (studentService.updateStudent(updatedStudent)) {
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
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
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
                        !student_edit_prijmeni.getText().equals("") && student_edit_combobox.getSelectedIndex() != 0) {
                    List<Student> found = studentDAO.findBy(student_edit_login.getText(), null, null, 0);
                    System.out.println("hledany login:" + student_edit_login.getText());
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

        //===================================
        //===================================
        //SPRAVA VYUCUJICICH TAB
        //===================================
        //===================================

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
                    List<Vyucujici> foundVyucujici = vyucujiciDAO.findBy(Utils.extractString(vyucujici_edit_login), null, null);
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
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
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
                        updatedVyucujici = vyucujici.get(vyucujici_seznam_list.getSelectedIndex());
                        updatedVyucujici.setLogin(Utils.extractString(vyucujici_edit_login));
                        updatedVyucujici.setJmeno(Utils.extractString(vyucujici_edit_jmeno));
                        updatedVyucujici.setPrijmeni(Utils.extractString(vyucujici_edit_prijmeni));
                        updatedVyucujici.setCeleJmeno(updatedVyucujici.getJmeno() + " " + updatedVyucujici.getPrijmeni());
                        if (vyucujiciService.updateVyucujici(updatedVyucujici)) {
                            System.out.println("Vyucujici successfuly updated.");
                        } else {
                            JOptionPane.showMessageDialog(vyucujici_sprava_panel, "Vyucujiciho nebylo mozno updatovat.", "Update se nepodaril", JOptionPane.ERROR_MESSAGE);
                        }
                        updateVyucujiciList();
                        cleanVyucujiciEdit();
                }
            }
        });

        //===================================
        //===================================
        //SPRAVA PREDMETU TAB
        //===================================
        //===================================

        //pridat predmet
        pridatPredmetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPredmet = new Predmet();
                newPredmet.setKod(Utils.extractString(predmet_pridat_kod));
                newPredmet.setNazev(Utils.extractString(predmet_pridat_nazev));
                newPredmet.setRozsah(Utils.extractString(predmet_pridat_rozsah));
                newPredmet.setPocetKreditu((Integer) predmet_pridat_kredity_spinner.getValue());
                newPredmet.setJeZkouska(predmet_pridat_zkouska_checkbox.isSelected());
                if (predmet_pridat_letni.isSelected()) {
                    newPredmet.setSemestr('l');
                } else if (predmet_pridat_zimni.isSelected()) {
                    newPredmet.setSemestr('z');
                }
                if (predmetService.addPredmet(newPredmet)) {
                    System.out.println("Podarilo se pridat novy predmet.");
                } else {
                    JOptionPane.showMessageDialog(predmet_pridat_panel, "Nebylo mozno pridat predmet, prosim zkontrolujte zadane udaje.", "Pridani se nezdarilo", JOptionPane.ERROR_MESSAGE);
                }
                cleanPredmetAdd();
                updatePredmetList();
            }
        });

        //listener na double click over list
        predmet_seznam_list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int index = predmet_seznam_list.locationToIndex(e.getPoint());
                    Vector selectedData = (Vector) allPredmetModel.getElementAt(index);
                    predmet_edit_kod.setText((String) selectedData.get(0));
                    predmet_edit_nazev.setText((String) selectedData.get(1));
                    predmet_edit_rozsah.setText((String) selectedData.get(2));
                    predmet_edit_kredity_spinner.setValue(selectedData.get(3));
                    if (selectedData.get(4) != "") {
                        predmet_edit_zkouska_checkbox.setSelected(true);
                    } else {
                        predmet_edit_zkouska_checkbox.setSelected(false);
                    }
                    if (selectedData.get(5).equals('l')) {
                        predmet_edit_letni.setSelected(true);
                        predmet_edit_zimni.setSelected(false);
                    } else {
                        predmet_edit_letni.setSelected(false);
                        predmet_edit_zimni.setSelected(true);
                    }
                }
            }
        });

        //update predmet
        editovatPredmetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((predmet_edit_kod.getText() != "") && (predmet_edit_nazev.getText() != "") && (predmet_edit_rozsah.getText() != "") &&
                        (Integer) predmet_edit_kredity_spinner.getValue() != 0) {
                    updatedPredmet = predmety.get(predmet_seznam_list.getSelectedIndex());
                    updatedPredmet.setKod(Utils.extractString(predmet_edit_kod));
                    updatedPredmet.setNazev(Utils.extractString(predmet_edit_nazev));
                    updatedPredmet.setRozsah(Utils.extractString(predmet_edit_rozsah));
                    updatedPredmet.setPocetKreditu((Integer) predmet_edit_kredity_spinner.getValue());
                    updatedPredmet.setJeZkouska(predmet_edit_zkouska_checkbox.isSelected());
                    if (predmet_edit_letni.isSelected()) {
                        updatedPredmet.setSemestr('l');
                    } else {
                        updatedPredmet.setSemestr('z');
                    }
                    if (predmetService.updatePredmet(updatedPredmet)) {
                        System.out.println("Predmet uspesne updatovan.");
                    } else {
                        JOptionPane.showMessageDialog(predmet_edit_panel, "Predmet nebylo mozno updatovat.", "Update se nepodaril", JOptionPane.ERROR_MESSAGE);
                    }
                    updatePredmetList();
                    cleanPredmetEdit();
                }
            }
        });

        //smazat predmet
        smazatPredmetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!predmet_edit_kod.getText().equals("") && !predmet_edit_nazev.getText().equals("") &&
                        !predmet_edit_rozsah.getText().equals("") && (Integer) predmet_edit_kredity_spinner.getValue() != 0) {
                    List<Predmet> foundPredmet = predmetDAO.findBy(Utils.extractString(predmet_edit_kod), null, null, 0, null, null);
                    if (foundPredmet.size() == 1) {
                        updatedPredmet = foundPredmet.get(0);
                        predmetDAO.delete(updatedPredmet);
                        updatePredmetList();
                        cleanPredmetEdit();
                    }
                }
            }
        });

        //=========================
        //DIALOG NA VYPSANI PREDMETU
        //=========================
        //vypsat predmet na semestr
        predmet_seznam_list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popUpMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popUpMenu(e);
                }
            }

            private void popUpMenu(MouseEvent e) {
                menu = cretaePopupMenuForVypsat();
                menu.show(e.getComponent(), e.getX(), e.getY());
                int index = predmet_seznam_list.locationToIndex(e.getPoint());
                predmet_seznam_list.setSelectedIndex(index);
            }
        });

        //=========================
        //DIALOG NA PRIRAZENI VYUCUJICIHO
        //=========================
        vyucujici_seznam_list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popUpMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popUpMenu(e);
                }
            }

            private void popUpMenu(MouseEvent e) {
                menu = createPopupMenuForPrirazeni();
                menu.show(e.getComponent(), e.getX(), e.getY());
                int index = vyucujici_seznam_list.locationToIndex(e.getPoint());
                vyucujici_seznam_list.setSelectedIndex(index);
            }
        });

        //=====================
        //ZAPIS PREDMETU
        //=====================

        //zobrazit
        zapsat_zobrazit_predmety.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((!zapsat_predmet_zimni.isSelected() && !zapsat_predmet_letni.isSelected())
                        || (zapsat_predmet_letni.isSelected() && zapsat_predmet_zimni.isSelected())) {
                //rozbrazit vsechny predmety bez ohledu na semestr
                } else {
                    char semestr;
                    if (zapsat_predmet_letni.isSelected()) {
                        semestr = 'l';
                    } else {
                        semestr = 'z';
                    }
                }
                List<InstancePredmet> found = new ArrayList<InstancePredmet>();
            }
        });
    }

    //==========================
    //STUDENT SEARCH - MAY BE DELETED
    //==========================

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

    //==============================
    //SPRAVA STUDENTU
    //==============================

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
        vyucujici = foundVyucujici;
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

    //=================================
    //SPRAVA PREDMETU
    //=================================

    private void cleanPredmetAdd() {
        predmet_pridat_kod.setText("");
        predmet_pridat_nazev.setText("");
        predmet_pridat_rozsah.setText("");
        predmet_pridat_kredity_spinner.setValue(0);
        predmet_pridat_zkouska_checkbox.setSelected(false);
        predmet_pridat_letni.setSelected(true);
        predmet_pridat_zimni.setSelected(false);
    }

    private void cleanPredmetEdit() {
        predmet_edit_kod.setText("");
        predmet_edit_nazev.setText("");
        predmet_edit_rozsah.setText("");
        predmet_edit_kredity_spinner.setValue(0);
        predmet_edit_zkouska_checkbox.setSelected(false);
        predmet_edit_letni.setSelected(true);
        predmet_edit_zimni.setSelected(false);
        predmet_seznam_list.clearSelection();
    }

    private void updatePredmetList() {
        allPredmetModel = new DefaultListModel();
        predmety = predmetDAO.findAll();
        for (Predmet p : predmety) {
            Vector predmetData = new Vector();
            predmetData.add(p.getKod());
            predmetData.add(p.getNazev());
            predmetData.add(p.getRozsah());
            predmetData.add(p.getPocetKreditu());
            if (p.isJeZkouska()) {
                predmetData.add("zkouska vyzadovana");
            } else {
                predmetData.add("");
            }
            predmetData.add(p.getSemestr());
            allPredmetModel.addElement(predmetData);
        }
        predmet_seznam_list.setModel(allPredmetModel);
    }

    //============================
    //POPUP MENUS
    //============================

    //create popup menu for "Vypsat predmet"
    private JPopupMenu cretaePopupMenuForVypsat() {
        JMenuItem item = new JMenuItem("Vypsat predmet");
        JPopupMenu menu = new JPopupMenu();
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(predmet_seznam_list.getSelectedIndex() + " selected index");
//                System.out.println(predmet_seznam_list.getSelectedValue() + " selected value");
//                System.out.println(predmety.get(predmet_seznam_list.getSelectedIndex()).getNazev() + " selected predmet");
                VypsaniPredmetu.create(predmety.get(predmet_seznam_list.getSelectedIndex()),zalozkovy_panel);
            }
        });
        return menu;
    }

    private JPopupMenu createPopupMenuForPrirazeni() {
        JMenuItem item = new JMenuItem("Priradit predmet");
        JPopupMenu menu = new JPopupMenu();
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PriraditVyucujici.create(vyucujici.get(vyucujici_seznam_list.getSelectedIndex()));
            }
        });
        return menu;
    }
}
