package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ButtonRenderer extends JButton implements TableCellRenderer {


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        if (isSelected) {
            setBackground(Color.green);
            int raspuns = JOptionPane.showConfirmDialog(null, "Sunteti sigur ca stergeti randul?", String.valueOf(JOptionPane.QUESTION_MESSAGE), JOptionPane.YES_NO_OPTION);
            if ( raspuns == 0){
                JOptionPane.showMessageDialog(null, "ar trebui sa stearga din baza de date.....");

            }else {
                JOptionPane.showMessageDialog(null, "nu se sterge");

            }
        } else {
            setBackground(Color.red);
            setText("Delete row");
        }
        return this;
    }
}