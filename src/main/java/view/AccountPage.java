package view;
import auditService.AuditService;
import controller.UserController;
import model.UserModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccountPage extends JFrame {

    private JButton changeUserNamerButton, changePasswordButton, changeEmailButton, backButton;
    private JTextField changeUserNameField, changeEmailField;
    private JList list;
    private DefaultListModel<UserModel> model;

    AccountPage(){
        setTitle("Contul meu");

        initUserInfo();
        initComp();
        initSouthComp();

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initUserInfo(){
        model = new DefaultListModel<>();
        list = new JList();
        list.setModel(model);
        afisareDetaliiCont();

       this.add(list, BorderLayout.NORTH);
    }

    private void initComp(){
        JPanel panel2 = new JPanel(new GridLayout(2,2));
        changeUserNamerButton = new JButton("Schimbare Username");
        changeUserNameField = new JTextField("");
        changeEmailButton = new JButton("Schimbare Email");
        changeEmailField = new JTextField("");
        panel2.add(changeUserNameField);
        panel2.add(changeUserNamerButton);
        panel2.add(changeEmailField);
        panel2.add(changeEmailButton);

        changeUserNamerButton.addActionListener(e->{
            if(changeUserNameField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Campul Username este gol");
            }
           else if(setareUsernameNou()){
                AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"A schimbat username-ul", LoginPage.localDate());
               JOptionPane.showMessageDialog(null, "Nume utilizator schimbat cu succes!");
           }
        });

        changeEmailButton.addActionListener( e-> {
            if(checkSecurityEmail()){
                if(setNewEmail()){
                    AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"A schimbat email-ul", LoginPage.localDate());
                    JOptionPane.showMessageDialog(null,"Email-ul a fost schimbat in baza de date.");
                }else{
                    JOptionPane.showMessageDialog(null,"Email-ul nu a fost schimbat in baza de date.");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Email invalid.");
                changeEmailField.setText("");
                changeEmailField.requestFocus();
            }
        });
        this.add(panel2, BorderLayout.CENTER);
    }

    private void initSouthComp() {
        JPanel panel3 = new JPanel();
        backButton = new JButton("Back");
        changePasswordButton = new JButton("Schimba Parola");
        panel3.add(backButton);
        panel3.add(changePasswordButton);
        this.add(panel3, BorderLayout.SOUTH);

        changePasswordButton.addActionListener(e->{
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat pagina schimbare parola", LoginPage.localDate());
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            dispose();
        });

        backButton.addActionListener(e-> {
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesare redirectionare Dashboard", LoginPage.localDate());
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });
    }

    private void afisareDetaliiCont(){
      List<UserModel> detalii = UserController.getInstance().getUsernameEmailList(LoginPage.rememberUsername()) ;
        detalii.forEach(model::addElement);
    }

    private boolean setareUsernameNou(){
        String newName = changeUserNameField.getText();
        if(UserController.getInstance().setNewUsername(newName)){
            return false;
        }
        return true;
    }

    private boolean checkSecurityEmail(){
        String pattern = "^(.+)@(.+)+\\.[A-Za-z0-9].$";
        String password = new String(changeEmailField.getText());
        if( password.matches(pattern) ){
            return true;
        }
        return false;
    }

    private boolean setNewEmail(){
        String email = changeEmailField.getText();

        if(UserController.getInstance().setNewEmail(email)){
            return false;
        }
        return true;
    }
}
