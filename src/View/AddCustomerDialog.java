package View;

import javax.swing.*;
import java.awt.*;

public class AddCustomerDialog extends JDialog {
    private final JTextField txtCustomerName = new JTextField();
    private final JTextField txtCustomerSurname = new JTextField();
    private final JTextField txtParcelId = new JTextField();
    private final JButton addButton = new JButton("Add");
    private final JButton resetButton = new JButton("Reset");

    public AddCustomerDialog(JFrame frame) {

        super(frame, "Add Customer Dialog", true);

        JPanel pnlCenter = new JPanel(new GridLayout(3, 2));
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pnlCenter.add(new JLabel("Customer Name:"));
        pnlCenter.add(txtCustomerName);
        pnlCenter.add(new JLabel("Customer Surname:"));
        pnlCenter.add(txtCustomerSurname);
        pnlCenter.add(new JLabel("Parcel Id:"));
        pnlCenter.add(txtParcelId);

        pnlSouth.add(addButton);
        pnlSouth.add(resetButton);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        setSize(320, 200);
        setResizable(false);

        setLocationRelativeTo(null);

    }

    public JTextField getTxtCustomerName() { return txtCustomerName; }

    public JTextField getTxtCustomerSurname() { return txtCustomerSurname; }

    public JTextField getTxtParcelId() { return txtParcelId; }

    public JButton getAddButton() { return addButton; }

    public JButton getResetButton() { return resetButton; }

    public void resetAddCustomerDialog() {

        txtCustomerName.setText("");
        txtCustomerSurname.setText("");
        txtParcelId.setText("");
    }

    public void showCustomerAddedDialog() {

        JOptionPane.showMessageDialog(this,
                "Customer added successfully!",
                getTitle(), JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }

    public void showWrongCustomerDetailsDialog() {
        JOptionPane.showMessageDialog(this,
                "All fields must be filled properly!",
                getTitle(), JOptionPane.WARNING_MESSAGE
        );
    }






}

