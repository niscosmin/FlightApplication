package view;

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

         confirmPasswordLabel = new JLabel("Confirma parloca");
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


    }

    public static void main(String[] args) {
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
    }
}
