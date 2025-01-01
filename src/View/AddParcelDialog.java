package View;

import Model.Customer;
import Model.Parcel;
import Controller.Worker;
import Model.ParcelStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddParcelDialog  extends JDialog {
    private final Worker worker;
    private final JTextField txtParcelId = new JTextField();
    private final JSpinner spnStatus = new JSpinner(new SpinnerListModel(ParcelStatus.values()));
    private final JSpinner spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnDaysInDepot = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));

    public AddParcelDialog(WorkerGUI workerGui, Worker worker) {
        super(workerGui, "Add Parcel Dialog", true);
        this.worker = worker;

        JPanel pnlCenter = new JPanel(new GridLayout(7, 2));
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pnlCenter.add(new JLabel("Parcel Id:"));
        pnlCenter.add(txtParcelId);
        pnlCenter.add(new JLabel("Status:"));
        pnlCenter.add(spnStatus);
        pnlCenter.add(new JLabel("Weight:"));
        pnlCenter.add(spnWeight);
        pnlCenter.add(new JLabel("Length:"));
        pnlCenter.add(spnLength);
        pnlCenter.add(new JLabel("Width:"));
        pnlCenter.add(spnWidth);
        pnlCenter.add(new JLabel("Height:"));
        pnlCenter.add(spnHeight);
        pnlCenter.add(new JLabel("Days In Depot:"));
        pnlCenter.add(spnDaysInDepot);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new AddListener());
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ResetListener());

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnReset);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        setSize(320, 400);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addParcel() {

        String parcelId = txtParcelId.getText().trim();
        ParcelStatus status = (ParcelStatus) spnStatus.getValue();
        double weight = (double) spnWeight.getValue();
        double length = (double) spnLength.getValue();
        double width = (double) spnWidth.getValue();
        double height = (double) spnHeight.getValue();
        int daysInDepot = (int) spnDaysInDepot.getValue();

        if (!parcelId.isEmpty() && status != null
                                && weight > 0
                                && length > 0
                                && width > 0
                                && height > 0
                                && daysInDepot > 0) {

            Customer customer = worker.getQueueOfCustomers().searchForCustomerByParcelId(txtParcelId.getText());

            if (customer != null) {
                Parcel parcel = new Parcel(
                        parcelId,
                        status,
                        weight,
                        length,
                        width,
                        height,
                        daysInDepot,
                        customer
                );

                worker.addNewParcel(parcel);

                JOptionPane.showMessageDialog(this,
                        "Parcel added successfully!",
                        getTitle(), JOptionPane.INFORMATION_MESSAGE);

                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Customer with parcel id " +
                                parcelId + " doesn't exist!",
                        getTitle(), JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "All fields must be filled properly!",
                    getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reset() {
        txtParcelId.setText("");
        spnStatus.setValue(ParcelStatus.FOR_COLLECTION);
        spnWeight.setValue(1D);
        spnLength.setValue(1D);
        spnWidth.setValue(1D);
        spnHeight.setValue(1D);
        spnDaysInDepot.setValue(1);
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addParcel();
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

}



