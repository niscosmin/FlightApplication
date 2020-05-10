import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {

    JPanel panel;
    JLabel usernameLabel, passwordLabel, emailLabel, passwordConfirmation;
    JTextField usernameText, emailText;
    JPasswordField passwordField, passwordConfirmationField;
    JButton addButton, backButton;

    public RegisterPage() {
        setTitle("Register");

        initCompRegisterPage();

        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initCompRegisterPage(){
        addButton = new JButton("Add");
        backButton = new JButton("Back");
        panel = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        passwordConfirmation = new JLabel("Password confirmation:");
        emailLabel = new JLabel("E-mail address");
        usernameText = new JTextField("");
        passwordField = new JPasswordField("");
        passwordConfirmationField = new JPasswordField("");
        emailText = new JTextField("");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 10,20,10));
        panel.setLayout(new GridLayout(5,2));
        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(passwordConfirmation);
        panel.add(passwordConfirmationField);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(addButton);
        panel.add(backButton);
        this.add(panel);


        backButton.addActionListener( e -> {
            StartPage startPage = new StartPage();
            dispose();
        });


    }


}
