package view;

import auditService.AuditService;
import controller.ZborController;
import model.ZborModel;

import javax.swing.*;
import java.awt.*;

public class AdaugaZborPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel sursa, destinatie, oraSosire, pret, oraPlecare;
    private JTextField sursaField, destinatieField, oraSosireField, pretField, oraPlecareField;
    private JCheckBox jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5, jCheckBox6, jCheckBox7;
    private String checkBoxValues = "";

    AdaugaZborPage(){
        setTitle("Pagina Adauga Zbor");

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
        cancelButton = new JButton("Cancel");

        panel.add(cancelButton);
        panel.add(addButton);

        this.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e-> {
            if(checkSursa()){
                if(checkDestinatie()){
                    if(checkOraPlecare()){
                        if (checkOraSosire()){
                            if (checkPret()){
                                selectCheckBox();
                                addZbor();
                                AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Adaugare zbor", LoginPage.localDate());
                            }else{
                                JOptionPane.showMessageDialog(null,"Pret incorect!");
                                pretField.setText("");
                                requestFocus();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Ora de sosire incorecta! Format necesar: ORA:Minut");
                            oraSosireField.setText("");
                            requestFocus();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ora de plecare incorecta! Format necesar: ORA:Minut");
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
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Anulare adaugare zbor", LoginPage.localDate());
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });
    }

    public void selectCheckBox() {
        if(jCheckBox1.isSelected()){
           checkBoxValues += jCheckBox1.getText()+", ";
        }
        if (jCheckBox2.isSelected()){
            checkBoxValues += jCheckBox2.getText()+", ";
        }
        if (jCheckBox3.isSelected()){
            checkBoxValues += jCheckBox3.getText()+", ";
        }
        if(jCheckBox4.isSelected()){
           checkBoxValues += jCheckBox4.getText()+", ";
        }
        if (jCheckBox5.isSelected()){
            checkBoxValues += jCheckBox5.getText()+", ";
        }
        if (jCheckBox6.isSelected()){
            checkBoxValues += jCheckBox6.getText()+", ";
        }
        if (jCheckBox7.isSelected()){
            checkBoxValues += jCheckBox7.getText()+", ";
        }

    }

    public void addZbor(){
        String sursa = sursaField.getText();
        String destinatie = destinatieField.getText();
        String oraPlecare = oraPlecareField.getText();
        String oraSosire = oraSosireField.getText();
        String zile = checkBoxValues;
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
        String pattern = "().{4,}";
        String sursa = sursaField.getText();
        if(sursa.matches(pattern)){
            return true;
        }
        return false;
    }

    public boolean checkDestinatie(){
        String pattern = "().{4,}";
        String destinatie = destinatieField.getText();
        String sursa = sursaField.getText();
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

        String time24Pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String sursa = oraPlecareField.getText();

        if(sursa.matches(time24Pattern)){
            return true;
        }
        return false;
    }

    public boolean checkOraSosire(){
        String time24Pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String sursa = oraSosireField.getText();

        if(sursa.matches(time24Pattern)){
            return true;
        }
        return false;
    }

}
