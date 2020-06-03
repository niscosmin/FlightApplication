package view;

import controller.UserController;
import dao.UserDao;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends JFrame {

    JPanel panel;
    JLabel usernameLabel, passwordLabel, emailLabel, passwordConfirmation;
    JTextField usernameText, emailText;
    JPasswordField passwordField, passwordConfirmationField;
    JButton addButton, backButton;

    public RegisterPage() {
        setTitle("Register");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10,20,10));
        this.add(panel);

        initCompRegisterPage();
        initBackButton();
        initAddButton();

        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initCompRegisterPage(){
        usernameLabel = new JLabel("Username:");
        usernameText = new JTextField("");

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField("");

        passwordConfirmation = new JLabel("Password confirmation:");
        passwordConfirmationField = new JPasswordField("");

        emailLabel = new JLabel("E-mail address");
        emailText = new JTextField("");

        panel.setLayout(new GridLayout(5,2));
        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(passwordConfirmation);
        panel.add(passwordConfirmationField);
        panel.add(emailLabel);
        panel.add(emailText);
    }

    private void initBackButton() {
        backButton = new JButton("Back");
        panel.add(backButton);

        backButton.addActionListener(e -> {
            StartPage startPage = new StartPage();
            dispose();
        });
    }
    private void initAddButton() {
        addButton = new JButton("Add");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            if (securityPassword()) {
                if (checkPassword()) {
                    if(checkEmail()){
                        register();
                    }else{
                        JOptionPane.showMessageDialog(null,"Mail Invalid");
                        emailText.setText("");
                        emailText.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Parolele nu sunt identice!");
                    passwordConfirmationField.setText("");
                    passwordConfirmationField.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Parola nu corespunde securitatii: minim 6 caractere, minim o cifra, o litera mica și o litera mare");
                passwordField.setText("");
            }
        });
    }

    public void register(){

        String username = usernameText.getText();
        String password = new String(passwordField.getPassword());
        String email = emailText.getText();

        UserModel userModel = new UserModel(username, password, email);
      if(UserController.getInstance().adaugaUser(userModel)){
          JOptionPane.showMessageDialog(null, "User Inregistrat!");
          LoginPage loginPage = new LoginPage();
          dispose();
      }else {
          JOptionPane.showMessageDialog(null, "User exista deja!");
          usernameText.setText("");
          usernameText.requestFocus();
      }
    }

    public boolean checkEmail() {
        String pattern ="^(.+)@(.+)+\\.[A-Za-z0-9].$";
        String email = emailText.getText();
        if( email.matches(pattern)){
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

    public boolean checkPassword(){
        String password = new String(passwordField.getPassword());
        String passwordConfirmation = new String(passwordConfirmationField.getPassword());
        if(password.equals(passwordConfirmation)){
            return true;
        }
        return false;
    }
}
