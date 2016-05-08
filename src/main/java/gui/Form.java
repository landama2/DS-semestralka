package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by urban on 5/4/2016.
 */
public class Form extends JFrame {
    private JTabbedPane TabPanel;
    private JPanel Panel;
    private JTextField textField1;
    private JButton pridatStudentaButton;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JList list1;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox2;
    private JButton zapsatPredmetButton;
    private JTextField textField6;
    private JComboBox comboBox3;
    private JTree seznam_terminu;
    private JTextArea coJaVimTextArea;
    private JTextField podleKoduTextField;
    private JTextField textField8;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public Form() throws HeadlessException {
        super("Form");
        setContentPane(Panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usually: getContentPane() to then add components to it.

        pridatStudentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(Form.this, "You clicked the button");
            }
        });
        setVisible(true);
    }
}
