import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerDialog extends JDialog {
    private final ManagerMain managerMain;
    private final JTextField txtCustomerName = new JTextField();
    private final JTextField txtCustomerSurname = new JTextField();
    private final JTextField txtParcelId = new JTextField();

    public AddCustomerDialog(WorkerGUI workerGui, ManagerMain managerMain) {

        super(workerGui, "Add Customer Dialog", true);
        this.managerMain = managerMain;

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

        if (!txtCustomerName.getText().isEmpty() && !txtCustomerSurname.getText().isEmpty() && !txtParcelId.getText().isEmpty()) {
            Customer customer = new Customer(txtCustomerName.getText(),
                                             txtCustomerSurname.getText(),
                                             txtParcelId.getText());
            managerMain.getWorker().addNewCustomer(customer);

            JOptionPane.showMessageDialog(this,
                    "Customer added successfully!", getTitle(), JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "All fields must be filled!", getTitle(), JOptionPane.WARNING_MESSAGE);
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

