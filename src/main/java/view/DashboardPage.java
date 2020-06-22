package view;

import auditService.AuditService;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import javax.swing.table.*;
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

        setSize(970,400);
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
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Adauga zbor", LoginPage.localDate());
            AdaugaZborPage zborPage = new AdaugaZborPage();
            dispose();
        });
        myAccountButton.addActionListener(e -> {
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Contul meu", LoginPage.localDate());
            AccountPage accountPage = new AccountPage();
            dispose();
        });

        this.add(panou, BorderLayout.SOUTH);
    }

    public void initAdaugaZborButton() {
        adaugaZborButton.addActionListener(e -> {
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Adauga zbor", LoginPage.localDate());
            AdaugaZborPage zborPage = new AdaugaZborPage();
            dispose();
        });
    }

    public void showDate() {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        dateLabel.setText("Data : " + s.format(date));
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
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Dashboard Page (meniu)", LoginPage.localDate());
            DashboardPage dashboardPage = new DashboardPage();
            dispose();
        });

        logOutMenu.addActionListener(e -> {
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Log Out (meniu)", LoginPage.localDate());
            LoginPage loginPage = new LoginPage();
            dispose();
        });

        accountMenu.addActionListener(e -> {
            AuditService.getInstance().saveAudit(LoginPage.rememberUsername(),"Accesat Contul meu (meniu)", LoginPage.localDate());
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
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        JTable table = new JTable(defaultTableModel);

        JScrollPane scroll = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());


        defaultTableModel.addColumn("Sursa");
        defaultTableModel.addColumn("Destinatie");
        defaultTableModel.addColumn("Ora de plecare");
        defaultTableModel.addColumn("Ora de Sosire");
        defaultTableModel.addColumn("Zile");
        defaultTableModel.addColumn("Pret");
        defaultTableModel.addColumn("Delete row");
        defaultTableModel.addColumn("Id");

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
                        rs.getInt("pret"),
                        rs.getInt("id")
                });
            }

            table.setAutoResizeMode(0);

            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(400);
            table.getColumnModel().getColumn(5).setPreferredWidth(70);
            table.getColumnModel().getColumn(6).setPreferredWidth(80);

            table.getColumn("Delete row").setCellRenderer( new ButtonRenderer() );
            table.getColumn("Delete row").setCellEditor( new ButtonEditor(new JTextField()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.add(scroll);
    }

    public void closeDashboard(){
        dispose();
    }

    public static void main(String[] args) {
        DashboardPage dashboardPage = new DashboardPage();
    }
}
