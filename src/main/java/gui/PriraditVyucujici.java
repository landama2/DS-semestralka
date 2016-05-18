package gui;

import dao.InstancePredmetDAO;
import entities.InstancePredmet;
import entities.Vyucujici;
import entities.VyucujiciHodina;
import service.VyucujiciHodinaService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class PriraditVyucujici extends JDialog {
    private JPanel contentPane;
    private JButton vyucujici_priradit_predmet;
    private JButton vyucujici_priradit_predmet_zavrit;
    private JLabel vyucujici_atributy;
    private JList vyucujici_priradit_instancepredmetu_list;

    private List<InstancePredmet> listInstanci;
    private DefaultListModel allInstanceModel;
    private InstancePredmet selectedInstance;
    private Vyucujici vyucujici;
    private InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);
    private VyucujiciHodina newVyucujiciHodina;
    private VyucujiciHodinaService vyucujiciHodinaService = new VyucujiciHodinaService();

    public PriraditVyucujici() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(vyucujici_priradit_predmet);

        //zavrit dialog
        vyucujici_priradit_predmet_zavrit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //priradit vyucujiciho instranci predmetu - tvorba vyucovaci hodiny
        vyucujici_priradit_predmet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = vyucujici_priradit_instancepredmetu_list.getSelectedIndex();
                selectedInstance = listInstanci.get(selectedIndex);
                newVyucujiciHodina = new VyucujiciHodina();
                newVyucujiciHodina.setVyucujici(vyucujici);
                newVyucujiciHodina.setInstancePredmet(selectedInstance);
                System.out.println(vyucujici.getJmeno());
                System.out.println(selectedInstance.getSkolniRok());
                if (vyucujiciHodinaService.createVyucujiciHodina(newVyucujiciHodina)) {
                    System.out.println("Predmet uspesne prirazen vyucujicimu.");
                } else {
                    JOptionPane.showMessageDialog(vyucujici_priradit_instancepredmetu_list,"Vyucujici nelze priradit k teto instanci predmetu.","Error",JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });
    }

    //create model for instance list
    private void setListModel() {
        this.listInstanci = instancePredmetDAO.findAll();
        DefaultListModel model = new DefaultListModel();
        System.out.println(listInstanci.size());
        for (InstancePredmet i : listInstanci) {
            Vector instanceData = new Vector();
            instanceData.add(i.getPredmet().getNazev());
            instanceData.add(i.getPredmet().getKod());
            instanceData.add(i.getSkolniRok());
            instanceData.add(i.getPredmet().getSemestr());
            model.addElement(instanceData);
        }
        allInstanceModel = model;
        vyucujici_priradit_instancepredmetu_list.setModel(allInstanceModel);
    }

    private void setVyucujici(Vyucujici v) {
        this.vyucujici = v;
        vyucujici_atributy.setText(v.getCeleJmeno());
    }

    public static void create(Vyucujici vyucujici) {
        PriraditVyucujici dialog = new PriraditVyucujici();
        dialog.setListModel();
        dialog.setVyucujici(vyucujici);
        dialog.pack();
        dialog.setVisible(true);
    }
}
