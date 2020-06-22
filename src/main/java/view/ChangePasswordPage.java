package view;

import auditService.AuditService;
import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordPage extends JFrame {

    JButton backButton, confirmButton;
    JLabel passwordLabel, confirmPasswordLabel;
    JPasswordField passwordField, confirmPasswordField;

     public ChangePasswordPage(){
         setTitle("Schimbare Parola");

         initComp();

         setSize(420,170);
         setVisible(true);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initComp(){
         JPanel panel = new JPanel(new GridLayout(3,2));
         panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;

         passwordLabel = new JLabel("Noua Parola");
         passwordField = new JPasswordField("");

         confirmPasswordLabel = new JLabel("Confirma parola");
         confirmPasswordField = new JPasswordField("");

         backButton = new JButton("Back");
         confirmButton = new JButton("Schimba Parola");

         panel.add(passwordLabel);
         panel.add(passwordField);
         panel.add(confirmPasswordLabel);
         panel.add(confirmPasswordField);
         panel.add(backButton);
         panel.add(confirmButton);

         this.add(panel);

         backButton.addActionListener(e->{
             AccountPage accountPage = new AccountPage();
             dispose();
         });

         confirmButton.addActionListener(e->{
             if(securityPassword()){
                 if(checkPasswords()){
                     if (setareNewPass()){
                         AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Schimbare parola", LoginPage.localDate());
                         JOptionPane.showMessageDialog(null, "Parola a fost modificata!");
                         LoginPage loginPage = new LoginPage();
                         dispose();
                     }else{
                         JOptionPane.showMessageDialog(null, "Parola nu a fost modificata!");
                     }
                 }else{
                     JOptionPane.showMessageDialog(null, "Parolele nu corespund.");
                 }
             }else{
                 JOptionPane.showMessageDialog(null,"Parola nu corespunde normelor de securitate: minim 6 caractere, minim o cifra, o litera mica și o litera mare");
                 passwordField.setText("");
                 passwordField.requestFocus();
             }
         });
    }

    public boolean setareNewPass(){
         String parola = new String(passwordField.getPassword());
         if(UserController.getInstance().setNewPass(parola)){
             return false;
         }
         return true;
    }

    public boolean checkPasswords(){
        String password = new String(passwordField.getPassword());
        String passwordConfirmation = new String(confirmPasswordField.getPassword());
        if(password.equals(passwordConfirmation)){
            return true;
        }
        return false;
    }

    public boolean securityPassword(){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}";
        String password = new String(passwordField.getPassword());
        if( password.matches(pattern) ){
            return true;
        }
        return false;
    }
}
