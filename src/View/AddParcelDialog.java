package View;

import Model.Customer;
import Model.Parcel;
import Model.ParcelStatus;

import javax.swing.*;
import java.awt.*;


public class AddParcelDialog  extends JDialog {
    private final JTextField txtParcelId = new JTextField();
    private final JButton addButton = new JButton("Add");
    private final JButton resetButton = new JButton("Reset");
    private final JSpinner spnStatus = new JSpinner(new SpinnerListModel(ParcelStatus.values()));
    private final JSpinner spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
    private final JSpinner spnDaysInDepot = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));

    public AddParcelDialog(JFrame frame) {
        super(frame, "Add Parcel Dialog", true);

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

        pnlSouth.add(addButton);
        pnlSouth.add(resetButton);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        setSize(320, 400);
        setResizable(false);

        setLocationRelativeTo(null);


    }

    public JTextField getTxtParcelId() {return txtParcelId;}

    public JSpinner getSpnStatus() {return spnStatus;}

    public JSpinner getSpnWeight() {return spnWeight;}

    public JSpinner getSpnLength() {return spnLength;}

    public JSpinner getSpnWidth() {return spnWidth;}

    public JSpinner getSpnHeight() {return spnHeight;}

    public JSpinner getSpnDaysInDepot() {return spnDaysInDepot;}

    public JButton getAddButton() {return addButton;}

    public JButton getResetButton() {return resetButton;}

    public void resetAddParcelDialog() {
        txtParcelId.setText("");
        spnStatus.setValue(ParcelStatus.FOR_COLLECTION);
        spnWeight.setValue(1D);
        spnLength.setValue(1D);
        spnWidth.setValue(1D);
        spnHeight.setValue(1D);
        spnDaysInDepot.setValue(1);
    }

    public void showParcelAddedDialog() {

        JOptionPane.showMessageDialog(this,
                "Parcel added successfully!",
                getTitle(), JOptionPane.INFORMATION_MESSAGE
        );


        dispose();
    }

    public void showCustomerNotFoundDialog() {

        JOptionPane.showMessageDialog(this,
                "Customer with the given parcel Id doesn't exist!",
                getTitle(), JOptionPane.WARNING_MESSAGE
        );

    }

    public void showWrongParcelDetailsDialog() {

        JOptionPane.showMessageDialog(this,
                "All fields must be filled properly!",
                getTitle(), JOptionPane.WARNING_MESSAGE
        );

    }

}



