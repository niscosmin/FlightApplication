import javax.swing.*;
import java.awt.*;
import java.security.KeyStore;

public class StartPage extends JFrame {

    JButton buttonLogin;
    JButton buttonRegister;

    StartPage(){
        setTitle("FlightApplication - START");

        initCompStartPage();

        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initCompStartPage(){
        buttonLogin = new JButton("Login");
        buttonRegister = new JButton("Register");
        JPanel panou = new JPanel();
        panou.setBorder(BorderFactory.createEmptyBorder(100,20,100, 20));
        panou.setLayout(new GridLayout(1,2));
        panou.add(buttonLogin);
        panou.add(buttonRegister);
        this.add(panou);

        buttonRegister.addActionListener(e-> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });
        buttonLogin.addActionListener(e->{
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
    }


}
