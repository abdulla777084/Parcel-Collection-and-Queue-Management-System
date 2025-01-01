package View;

import Model.Customer;
import Controller.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerDialog extends JDialog {
    private final Worker worker;
    private final JTextField txtCustomerName = new JTextField();
    private final JTextField txtCustomerSurname = new JTextField();
    private final JTextField txtParcelId = new JTextField();

    public AddCustomerDialog(JFrame frame, Worker worker) {

        super(frame, "Add Customer Dialog", true);
        this.worker = worker;

        JPanel pnlCenter = new JPanel(new GridLayout(3, 2));
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pnlCenter.add(new JLabel("Customer Name:"));
        pnlCenter.add(txtCustomerName);
        pnlCenter.add(new JLabel("Customer Surname:"));
        pnlCenter.add(txtCustomerSurname);
        pnlCenter.add(new JLabel("Parcel Id:"));
        pnlCenter.add(txtParcelId);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new AddListener());
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ResetListener());

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnReset);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        setSize(320, 200);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCustomer() {

        String name = txtCustomerName.getText().trim();
        String surname = txtCustomerSurname.getText().trim();
        String parcelId = txtParcelId.getText().trim();

        if (!name.isEmpty() && !surname.isEmpty() && !parcelId.isEmpty()) {

            Customer customer = new Customer(name, surname, parcelId);

            worker.addNewCustomer(customer);

            JOptionPane.showMessageDialog(this,
                    "Customer added successfully!",
                            getTitle(), JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "All fields must be filled properly!",
                            getTitle(), JOptionPane.WARNING_MESSAGE
            );
        }


    }

    private void reset() {
        txtCustomerName.setText("");
        txtCustomerSurname.setText("");
        txtParcelId.setText("");
    }

    private class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            addCustomer();
        }
    }

    private class ResetListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            reset();
        }
    }
}

