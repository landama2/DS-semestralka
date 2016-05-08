package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Usually: getContentPane() to then add components to it.

        student_pridat_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(Form.this, "You clicked the button");
            }
        });
        setVisible(true);
    }
}
