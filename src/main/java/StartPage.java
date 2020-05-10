import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {

    JButton buttonLogin;
    JButton buttonRegister;

    StartPage(){
        setTitle("FlightApplication - START");

        initComp();

        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initComp(){
        buttonLogin = new JButton("Login");
        buttonRegister = new JButton("Register");
        JPanel panou = new JPanel();
        panou.setBorder(BorderFactory.createEmptyBorder(100,20,100, 20));
        panou.setLayout(new GridLayout(1,2));
        panou.add(buttonLogin);
        panou.add(buttonRegister);
        this.add(panou);
    }


}
