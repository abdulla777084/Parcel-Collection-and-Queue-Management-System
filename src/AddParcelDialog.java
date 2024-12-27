import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddParcelDialog  extends JDialog {
    private final ManagerMain managerMain;
    private final JTextField txtParcelId = new JTextField();
    private final JSpinner spnStatus = new JSpinner(new SpinnerListModel(ParcelStatus.values()));
    private final JSpinner spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnDaysInDepot = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));

    public AddParcelDialog(WorkerGUI workerGui, ManagerMain managerMain) {
        super(workerGui, "Add Parcel Dialog", true);
        this.managerMain = managerMain;

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

        if (!txtParcelId.getText().isEmpty() && spnStatus.getValue() != null
                                             && spnWeight.getValue() != null
                                             && spnLength.getValue() != null
                                             && spnWidth.getValue() != null
                                             && spnHeight.getValue() != null
                                             && spnDaysInDepot.getValue() != null) {

            Customer customer = managerMain.getWorker().getQueueOfCustomers().searchForCustomerByParcelId(txtParcelId.getText());

            if (customer != null) {
                Parcel parcel = new Parcel(
                        txtParcelId.getText(),
                        (ParcelStatus) spnStatus.getValue(),
                        (Double) spnWeight.getValue(),
                        (Double) spnLength.getValue(),
                        (Double) spnWidth.getValue(),
                        (Double) spnHeight.getValue(),
                        (Integer) spnDaysInDepot.getValue(),
                        customer
                );

                managerMain.getWorker().addNewParcel(parcel);

                JOptionPane.showMessageDialog(this,
                        "Parcel added successfully!", getTitle(), JOptionPane.INFORMATION_MESSAGE);

                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Customer doesn't exist!", getTitle(), JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "All fields must be filled!", getTitle(), JOptionPane.WARNING_MESSAGE);
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


