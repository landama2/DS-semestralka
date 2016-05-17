package gui;

import dao.InstancePredmetDAO;
import entities.InstancePredmet;
import entities.Predmet;
import service.InstancePredmetService;
import service.PredmetService;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VypsaniPredmetu extends JDialog {
    private JPanel predmet_vypsat_pane;
    private JButton buttonZavritDialog;
    private JButton buttonCancel;
    private JButton vypsatPredmetButton;
    private JList predmet_vypsat_list;
    private JSpinner predmet_vypsat_rok_spinner;
    private JLabel predmet_vypsat_atributy;

    private SpinnerNumberModel spinnerNumberModel;

    private InstancePredmet newInstancePredmet;
    private Predmet selectedPredmet;

    private InstancePredmetService instancePredmetService = new InstancePredmetService();
    private PredmetService predmetService = new PredmetService();

    private InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);

    public VypsaniPredmetu() {
        setContentPane(predmet_vypsat_pane);
        setModal(true);
        getRootPane().setDefaultButton(buttonZavritDialog);

        buttonZavritDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        //kliknuto na vypsat predmet
        vypsatPredmetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<InstancePredmet> found = instancePredmetDAO.findBy(selectedPredmet, (Integer) predmet_vypsat_rok_spinner.getValue());
                if (found == null || found.size() == 0) {
                    newInstancePredmet = new InstancePredmet();
                    newInstancePredmet.setPredmet(selectedPredmet);
                    newInstancePredmet.setSkolniRok((Integer) predmet_vypsat_rok_spinner.getValue());
                    instancePredmetService.createInstance(newInstancePredmet);
                    if (!predmetService.addInstanceAndUpdate(selectedPredmet, newInstancePredmet)) {
                        instancePredmetService.deleteInstance(newInstancePredmet);
                    }
                } else {
                    JOptionPane.showMessageDialog(predmet_vypsat_pane, "Instance nejde vytvorit, jiz existuje.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setSelectedPredmet(Predmet predmet) {
        selectedPredmet = predmet;
        //TODO: udelat toString metody, aby se na to dalo koukat
        predmet_vypsat_atributy.setText(predmet.getKod() + " " + predmet.getNazev() + " " + predmet.getRozsah() + " " + predmet.getSemestr());
    }

    private void setSpinnerModel() {
        predmet_vypsat_rok_spinner.setModel(new SpinnerNumberModel(2016,2016,2030,1));
    }


    //TODO udelat, aby se nezaviraly vsechny panely a okna, dispose by to sice melo delat ale dela jenom bordel
    public static void create(Predmet predmet) {
        VypsaniPredmetu dialog = new VypsaniPredmetu();
        dialog.setSelectedPredmet(predmet);
        dialog.setSpinnerModel();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
