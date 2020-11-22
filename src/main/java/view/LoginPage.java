package view;
import auditService.AuditService;
import controller.UserController;
import model.UserModel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class LoginPage extends JFrame {

    private JPanel panel;
    private static JTextField usernameText;
    private JPasswordField passwordField;
    private static String nume;

    public LoginPage(){
        setTitle("Login");
        initCompLoginPage();
        setSize(400, 170);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initCompLoginPage(){
        JLabel username = new JLabel("Usermane:");
        this.usernameText = new JTextField("");

        JLabel password = new JLabel("Password:");
        this.passwordField = new JPasswordField("");

        JButton loginButton = new JButton("Login");
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
        loginButton.addActionListener(e-> {
                login();
            AuditService.getInstance().saveAudit(rememberUsername(),"login", localDate());
        });}

    private void login() {
        String username = usernameText.getText();
        String password = new String(passwordField.getPassword());
        Optional <UserModel> userModel = UserController.getInstance().loginUser(username, password);
        if(userModel.isPresent()){
            new DashboardPage();
            dispose();
        }else {
            JOptionPane.showMessageDialog(null, "Username / parola gresite!");
        }
    }

    public static String rememberUsername() {
        return nume = usernameText.getText();
    }

    public static String localDate(){
        String data;
        LocalDateTime today = LocalDateTime.now();
        return  data = today.toString();
    }
}


