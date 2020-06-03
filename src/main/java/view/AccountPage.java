package view;

import controller.UserController;
import dao.UserDao;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public void initUserInfo(){

        model = new DefaultListModel<>();
        list = new JList();
        list.setModel(model);
        afisareDetaliiCont();

       this.add(list, BorderLayout.NORTH);
    }

    public  void initComp(){
        JPanel panel2 = new JPanel(new GridLayout(2,2));



        changeUserNamerButton = new JButton("Schimbare Username");
        changeUserNameField = new JTextField("New Username");

        changeEmailButton = new JButton("Schimbare Email");
        changeEmailField = new JTextField("New E-mail");

        panel2.add(changeUserNameField);
        panel2.add(changeUserNamerButton);
        panel2.add(changeEmailField);
        panel2.add(changeEmailButton);

        this.add(panel2, BorderLayout.CENTER);
    }

    public void initSouthComp() {
        JPanel panel3 = new JPanel();

        backButton = new JButton("Back");
        changePasswordButton = new JButton("Schimba Parola");

        panel3.add(backButton);
        panel3.add(changePasswordButton);


        this.add(panel3, BorderLayout.SOUTH);

        changePasswordButton.addActionListener(e->{
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            dispose();
        });

        backButton.addActionListener(e-> {
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });


    }

    public static void main(String[] args) {
        AccountPage accountPage = new AccountPage();
    }

    public void afisareDetaliiCont(){
      List<UserModel> detalii = UserController.getInstance().getUsernameEmail("laura");
      detalii.forEach(model::addElement);
    }
}
