package view;

import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.Timer;

public class DashboardPage extends JFrame {

    private JButton adaugaZborButton, myAccountButton;
    private JLabel dateLabel, timeLabel;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem dashboardMenu;
    private JMenuItem accountMenu;
    private JMenuItem logOutMenu;

    public DashboardPage() {
        setTitle("Dashboard Page");

        initMenu();
        initCompNorth();
        showTable();
        initCompSouth();
        initAdaugaZborButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initCompNorth() {

        JPanel panouTimp = new JPanel(new FlowLayout());

        dateLabel = new JLabel("");
        timeLabel = new JLabel("");

        showDate();
        showTme();


        panouTimp.add(dateLabel);
        panouTimp.add(timeLabel);

        this.add(panouTimp, BorderLayout.NORTH);
    }

    public void initCompSouth() {
        JPanel panou = new JPanel();
        panou.setLayout(new GridLayout(1, 2));

        adaugaZborButton = new JButton("Adauga zbor");
        myAccountButton = new JButton("Contul meu");

        panou.add(myAccountButton);
        panou.add(adaugaZborButton);

        adaugaZborButton.addActionListener(e -> {
            AdaugaZborPage zborPage = new AdaugaZborPage();
            dispose();
        });
        myAccountButton.addActionListener(e -> {
            AccountPage accountPage = new AccountPage();
            dispose();
        });

        this.add(panou, BorderLayout.SOUTH);
    }

    public void initAdaugaZborButton() {
        adaugaZborButton.addActionListener(e -> {
            AdaugaZborPage zborPage = new AdaugaZborPage();
            dispose();
        });
    }

    public void showDate() {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        dateLabel.setText("Data este: " + s.format(date));
    }

    public void showTme() {
        new javax.swing.Timer(0, e -> {

            Date timer1 = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
            timeLabel.setText(" si ora curenta: " + s.format(timer1));
        }).start();
    }

    private void initMenu() {
        dashboardMenu = new JMenuItem("Dashboard");
        accountMenu = new JMenuItem("Contul Meu");
        logOutMenu = new JMenuItem("Logout");

        dashboardMenu.addActionListener(e -> {
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });

        logOutMenu.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
            dispose();
        });

        accountMenu.addActionListener(e -> {
            AccountPage accountPage = new AccountPage();
            dispose();
        });

        jMenu = new JMenu("Meniu");

        jMenu.add(dashboardMenu);
        jMenu.add(accountMenu);
        jMenu.add(logOutMenu);

        jMenuBar = new JMenuBar();

        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
    }

    private void showTable() {

//        Object [] columnName = {"Sursa", "Destinatie", "Ora de plecare", "Oa de sosire", "Zile", "Pret"};
        DefaultTableModel defaultTableModel = new DefaultTableModel();
//        defaultTableModel.setColumnIdentifiers(columnName);
        JTable table = new JTable(defaultTableModel);

        defaultTableModel.addColumn("Sursa");
        defaultTableModel.addColumn("Destinatie");
        defaultTableModel.addColumn("Ora de plecare");
        defaultTableModel.addColumn("Ora de Sosire");
        defaultTableModel.addColumn("Zile");
        defaultTableModel.addColumn("Pret");

//        table.getColumnModel().getColumn(0).setHeaderValue("Sursa");
//        table.getColumnModel().getColumn(1).setHeaderValue("Destinatie");
//        table.getColumnModel().getColumn(2).setHeaderValue("Ora de plecare");
//        table.getColumnModel().getColumn(3).setHeaderValue("Ora de Sosire");
//        table.getColumnModel().getColumn(4).setHeaderValue("Zile");
//        table.getColumnModel().getColumn(5).setHeaderValue("Pret");

        table.getTableHeader().resizeAndRepaint();


         String url ="jdbc:mysql://localhost/flightapp";
         String username = "root";
         String pass =   "";
         Connection connection;

        try {
            connection = DriverManager.getConnection(url, username, pass);
            String sql = "SELECT * FROM zboruri";
            PreparedStatement tableQuerry = connection.prepareStatement(sql);
            ResultSet rs = tableQuerry.executeQuery();

            while(rs.next()){
                defaultTableModel.addRow(new Object[]{
                        rs.getString("sursa"),
                        rs.getString("destinatie"),
                        rs.getString("ora_plecare"),
                        rs.getString("ora_sosire"),
                        rs.getString("zile"),
                        rs.getInt("pret")
                });
            }

                table.setAutoResizeMode(0);


            table.getColumnModel().getColumn(0).setPreferredWidth(70);
            table.getColumnModel().getColumn(1).setPreferredWidth(70);
            table.getColumnModel().getColumn(2).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(70);
            table.getColumnModel().getColumn(4).setPreferredWidth(70);
            table.getColumnModel().getColumn(5).setPreferredWidth(70);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.add(table, BorderLayout.CENTER);


    }
        public static void main (String[]args){
            DashboardPage dashboardPage = new DashboardPage();
        }
}
