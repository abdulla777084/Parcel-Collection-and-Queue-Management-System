import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class WorkerGUI extends JFrame {
    private final ManagerMain managerMain; //Log
    private final JTable tblParcel = new JTable(), tblCustomer = new JTable();
    private final JLabel currentParcelLabel = new JLabel();
    private final JButton processCustomerButton = new JButton("Process Parcel");
    private final JButton addParcelButton = new JButton("Add Parcel");
    private final JButton addCustomerButton = new JButton("Add Customer");
    private final JButton saveButton = new JButton("Generate Output Report");

    public WorkerGUI(ManagerMain managerMain) {
        this.managerMain = managerMain;

        setTitle("Parcel Depot Management System");
        setSize(1800, 620);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new GUIWindowListener());

        setLayout(new BorderLayout());

        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        JScrollPane customerScroll = new JScrollPane(tblCustomer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        customerPanel.add(customerScroll, BorderLayout.CENTER);

        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcels still to be processed (For Collection)"));

        JScrollPane parcelScroll = new JScrollPane(tblParcel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        parcelPanel.add(parcelScroll, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.add(customerPanel);
        mainPanel.add(parcelPanel);

        JPanel southPanel = new JPanel();

        processCustomerButton.addActionListener(new ProcessParcelButtonListener());
        addCustomerButton.addActionListener(new AddCustomerButtonListener());
        addParcelButton.addActionListener(new AddParcelButtonListener());
        saveButton.addActionListener(new SaveButtonListener());

        southPanel.add(currentParcelLabel);
        southPanel.add(processCustomerButton);
        southPanel.add(addCustomerButton);
        addParcelButton.setEnabled(false); /* disable button that worker clicks on to add a new parcel
                                              until a new customer who is supposed to collect that parcel is added to the queue */
        southPanel.add(addParcelButton);
        southPanel.add(saveButton);

        add(mainPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        updatePanels();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updatePanels() {

        getUpdatedCustomerTableModel();
        getUpdatedParcelTableModel();

        //Used HTML tags to make the output more readable
        currentParcelLabel.setText("<html><b>Current Parcel Details:</b> " + managerMain.getCurrentParcel() + "</html>");
    }

    private void getUpdatedCustomerTableModel() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Queue Number");
        model.addColumn("Customer Name");
        model.addColumn("Customer Surname");
        model.addColumn("Parcel Id");

        for (Customer customer : managerMain.getWorker().getQueueOfCustomers().getCustomers()) {
            model.addRow(new Object[]{customer.getQueueNumber(),
                                      customer.getCustomerName(),
                                      customer.getCustomerSurname(),
                                      customer.getParcelId()
            });
        }
        tblCustomer.setModel(model);
    }

    private void getUpdatedParcelTableModel() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Parcel Id");
        model.addColumn("Status");
        model.addColumn("Weight");
        model.addColumn("Dimensions");
        model.addColumn("Days In Depot");
        model.addColumn("Customer Info");

        for (Parcel parcel : managerMain.getWorker().getParcelMap().getParcels()) {
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
        tblParcel.setModel(model);
    }



    private void processCustomer() {

        //All the validations for customer and parcel are implemented within the processCustomer method
        managerMain.processCustomer();

        //Used HTML tags to make the output more readable
        JOptionPane.showMessageDialog(this,
                "<html><b>Parcel:</b> " + managerMain.getCurrentParcel()
                        + "<br>" + "<br>" +"<b>Collection fee:</b> "
                        + managerMain.getWorker().calculateFee(managerMain.getCurrentParcel())
                        + " £</html>",
                getTitle(),JOptionPane.INFORMATION_MESSAGE
        );

    }

    private void addCustomer() {new AddCustomerDialog(this,managerMain);addParcelButton.setEnabled(true);}

    private void addParcel() {new AddParcelDialog(this,managerMain);addParcelButton.setEnabled(false);}


    private void saveReport() {

        JOptionPane.showMessageDialog(this,
                "Successfully saved the operations.", getTitle(), JOptionPane.INFORMATION_MESSAGE
        );

        managerMain.getWorker().generateReport("OutputReport.txt");

        //managerMain.saveLog();  //Save the log

    }

    private class ProcessParcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processCustomer();
            updatePanels();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveReport();
        }
    }

    private class AddCustomerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addCustomer();
            updatePanels();
        }
    }

    private class AddParcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addParcel();
            updatePanels();
        }
    }

    //calls saveReport before closing the window
    private class GUIWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {saveReport(); }
    }





}

