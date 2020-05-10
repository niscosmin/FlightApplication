import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JPanel panel;

    public LoginPage(){
        setTitle("Login");

        initCompLoginPage();


        setSize(400, 170);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initCompLoginPage(){
        JLabel username = new JLabel("Usermane:");
        JTextField usernameText = new JTextField("");
        JLabel password = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField("");
        JButton loginButton = new JButton("Add");
        JButton backButton = new JButton("Back");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10,20,0));
        panel.setLayout(new GridLayout(3,2));

        panel.add(username);
        panel.add(usernameText);
        panel.add(password);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);
        this.add(panel);

        backButton.addActionListener( e -> {
            StartPage startPage = new StartPage();
            dispose();
        });

    }
}


