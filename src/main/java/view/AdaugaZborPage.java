package view;

import controller.ZborController;
import model.ZborModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class AdaugaZborPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel sursa, destinatie, oraSosire, pret, oraPlecare;
    private JTextField sursaField, destinatieField, oraSosireField, pretField, oraPlecareField;
    private List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    private JCheckBox jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5, jCheckBox6, jCheckBox7;
    private String jCheckBoxSelected;

    AdaugaZborPage(){
        setTitle("Dashboard Page");

        initCompZbor();
        initButtons();


        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initCompZbor(){
        JPanel panelNorth1 = new JPanel(new GridLayout(9,2));
        panelNorth1.setBorder(BorderFactory.createEmptyBorder(10,15,10,10));

        sursa = new JLabel("Sursa");
        sursaField = new JTextField("");
        destinatie = new JLabel("Destinatie");
        destinatieField = new JTextField("");
        oraPlecare = new JLabel("Ora de plecare");
        oraPlecareField = new JTextField("");
        oraSosire = new JLabel("Ora sosire");
        oraSosireField = new JTextField("");
        pret = new JLabel("Pret");
        pretField = new JTextField("");

        panelNorth1.add(sursa);
        panelNorth1.add(sursaField);
        panelNorth1.add(destinatie);
        panelNorth1.add(destinatieField);
        panelNorth1.add(oraPlecare);
        panelNorth1.add(oraPlecareField);
        panelNorth1.add(oraSosire);
        panelNorth1.add(oraSosireField);
        panelNorth1.add(pret);
        panelNorth1.add(pretField);

        jCheckBox1 = new JCheckBox("luni");
        jCheckBox2 = new JCheckBox("marti");
        jCheckBox3 = new JCheckBox("miercuri");
        jCheckBox4 = new JCheckBox("joi");
        jCheckBox5 = new JCheckBox("vineri");
        jCheckBox6 = new JCheckBox("sambata");
        jCheckBox7 = new JCheckBox("duminica");

        panelNorth1.add(jCheckBox1);
        panelNorth1.add(jCheckBox2);
        panelNorth1.add(jCheckBox3);
        panelNorth1.add(jCheckBox4);
        panelNorth1.add(jCheckBox5);
        panelNorth1.add(jCheckBox6);
        panelNorth1.add(jCheckBox7);

        this.add(panelNorth1);
    }

    public void initButtons(){
        JPanel panel = new JPanel();

        addButton = new JButton("Adauga zbor");
        cancelButton = new JButton("Anuleaza");

        panel.add(cancelButton);
        panel.add(addButton);

        this.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e-> {
            if(checkSursa()){
                if(checkDestinatie()){
                    if(checkOraPlecare()){

                    }else{
                        JOptionPane.showMessageDialog(null, "Format orar incorect!");
                        oraPlecareField.setText("");
                        requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Destinatia trebuie sa contina cel putin 3 litere si sa fie diferita de Sursa");
                    destinatieField.setText("");
                    requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Sursa trebuie sa contina cel putin 3 litere");
                sursaField.setText("");
                requestFocus();
            }


        });

        cancelButton.addActionListener(e->{
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });
    }

    public static void main(String[] args) {
        AdaugaZborPage adaugaZborPage = new AdaugaZborPage();
    }

//    public void selectedCheckBox() {
//        if(jCheckBox1.isSelected())
//        {
//            if(jCheckBox2.isSelected())
//            {
//                jCheckBoxSelected = "Marti";
//            }
//            jCheckBoxSelected = "Luni";
//        }
//
//    }

    public void addZbor(){
        String sursa = sursaField.getText();
        String destinatie = destinatieField.getText();
        String oraPlecare = oraPlecareField.getText();
        String oraSosire = oraSosireField.getText();
        String zile = jCheckBoxSelected = "";
        int pret = Integer.parseInt(pretField.getText());

        ZborModel zborModel = new ZborModel(sursa, destinatie, oraPlecare, oraSosire, zile, pret);
        if(ZborController.getInstance().adaugaZbor(zborModel)){
            JOptionPane.showMessageDialog(null, "Zborul a fost adaugat!");
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Zboul deja exista!");
            AdaugaZborPage adaugaZborPage = new AdaugaZborPage();
            dispose();
        }
    }

    public boolean checkSursa(){
        String pattern = "().{3,}";
        String sursa = new String(sursaField.getText());
        if(sursa.matches(pattern)){
            return true;
        }
        return false;
    }

    public boolean checkDestinatie(){
        String pattern = "().{3,}";
        String destinatie = new String( destinatieField.getText());
        String sursa = new String(sursaField.getText());
        if (destinatie.matches(pattern) & !destinatie.equals(sursa)){
            return true;
            }
        return false;
    }

    public boolean checkPret(){
        int pret = Integer.parseInt(pretField.getText());
        if( pret > 0 ){
            return true;
        }
        return false;
    }

    public boolean checkOraPlecare(){
        Format timeFormat = new SimpleDateFormat("HH:mm");
        String str = new String( oraPlecareField.getText());
        if( str.equals(timeFormat)){
            return true;
        }
        return false;
    }





}
