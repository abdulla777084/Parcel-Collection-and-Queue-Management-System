package View;

import Model.Customer;
import Model.Parcel;
import Model.Worker;
import Model.ParcelStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class WorkerGUI extends JFrame {
    private final Worker worker;
    private final JTable tblParcel = new JTable();
    private final JTable tblCustomer = new JTable();
    private final JLabel currentParcelLabel = new JLabel();
    private final JButton processCustomerButton = new JButton("Process Parcel");
    private final JButton addParcelButton = new JButton("Add Parcel");
    private final JButton addCustomerButton = new JButton("Add Customer");
    private final JButton saveButton = new JButton("Generate Output Report");

    public WorkerGUI(Worker worker) {
        this.worker = worker;

        setTitle("Parcel Depot Management System");
        setSize(1750, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        JScrollPane customerScroll = new JScrollPane(tblCustomer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        customerPanel.add(customerScroll, BorderLayout.CENTER);

        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcels to be processed (For Collection)"));
        JScrollPane parcelScroll = new JScrollPane(tblParcel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        parcelPanel.add(parcelScroll, BorderLayout.CENTER);

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.add(customerPanel);
        listsPanel.add(parcelPanel);

        JPanel southPanel = new JPanel();

        southPanel.add(currentParcelLabel);
        southPanel.add(processCustomerButton);
        southPanel.add(addCustomerButton);

        /*
            Initially, disable button that worker clicks on to add a new parcel until
            a new customer, who will collect that parcel, is added to the queue
        */
        addParcelButton.setEnabled(false);

        southPanel.add(addParcelButton);
        southPanel.add(saveButton);

        add(listsPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        updatePanels(); //Populates tables

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getProcessCustomerButton() {return processCustomerButton;}

    public JButton getAddCustomerButton() {return addCustomerButton;}

    public JButton getAddParcelButton() {return addParcelButton;}

    public JButton getSaveButton() {return saveButton;}

    public void updatePanels() {

        setCustomerTableModel();
        setParcelTableModel();
        setCurrentParcelLabel();
    }

    private void setCustomerTableModel() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Queue Number");
        model.addColumn("Customer Name");
        model.addColumn("Customer Surname");
        model.addColumn("Parcel Id");

        for (Customer customer : worker.getQueueOfCustomers().getCustomers()) {
            model.addRow(new Object[]{customer.getQueueNumber(),
                                      customer.getCustomerName(),
                                      customer.getCustomerSurname(),
                                      customer.getParcelId()
            });
        }
        tblCustomer.setModel(model);
    }

    private void setParcelTableModel() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Parcel Id");
        model.addColumn("Status");
        model.addColumn("Weight");
        model.addColumn("Dimensions");
        model.addColumn("Days In Depot");
        model.addColumn("Customer Info");

        for (Parcel parcel : worker.getParcelMap().getParcels()) {

            if (parcel.getStatus() == ParcelStatus.FOR_COLLECTION) {

                model.addRow(new Object[]{parcel.getParcelId(),
                                          parcel.getStatus(),
                                          parcel.getWeight() + " kg(s)",
                                          parcel.getLength() + " x " +
                                          parcel.getWidth() + " x " +
                                          parcel.getHeight() + " cm(s)",
                                          parcel.getDaysInDepot(),
                                          parcel.getCustomer().getFullName()
                });

            }

        }

        tblParcel.setModel(model);
    }

    private void setCurrentParcelLabel() {

        Parcel currentParcel = worker.getCurrentParcel();

        if (currentParcel != null && currentParcel.getStatus() == ParcelStatus.FOR_COLLECTION) {
            //Used HTML tags to make the output more readable
            currentParcelLabel.setText("<html><b>Current Parcel Details:</b> " + currentParcel + "</html>");
        }
        else {
            currentParcelLabel.setText("No current parcel to process. All parcels have been collected.");
        }
    }

    public void showProcessCustomerDialog(Parcel currentParcel, double collectionFee) {

        //Used HTML tags to make the output more readable
        JOptionPane.showMessageDialog(this,
                "<html><b>Parcel:</b> " + currentParcel +
                        "<br>" + "<br>" +"<b>Collection fee:</b> " +
                        collectionFee + " £</html>",
                        getTitle(), JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void showNoCurrentParcelDialog() {
        JOptionPane.showMessageDialog(this,
                "No current parcel to process! All parcels have been collected.",
                getTitle(), JOptionPane.WARNING_MESSAGE
        );
    }

    public void showSaveReportDialog() {

        JOptionPane.showMessageDialog(this,
                "Successfully saved the operations to the text file(s) ",
                        getTitle(), JOptionPane.INFORMATION_MESSAGE
        );
    }


}

