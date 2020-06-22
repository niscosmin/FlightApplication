package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private JButton jButton;

    private String label;

//    public ButtonEditor(JCheckBox checkBox) {
//        super(checkBox);
//        button = new JButton();
////        button.setOpaque(true);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped();
//            }
//        });

        public ButtonEditor(JTextField jTextField) {
        super(jTextField);
        button = new JButton();
//        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingCanceled();
            }
        });

    }




    
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}