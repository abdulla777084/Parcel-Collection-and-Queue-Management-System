package Controller;

import Model.Customer;
import Model.Parcel;
import Model.ParcelStatus;
import Model.Worker;
import View.AddCustomerDialog;
import View.AddParcelDialog;
import View.WorkerGUI;
import Log.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WorkerController {
    private final Worker worker;
    private final WorkerGUI workerGui;

    private final AddCustomerDialog customerDialog;
    private final AddParcelDialog parcelDialog;
    private final Log log = Log.getInstance();

    public WorkerController(Worker worker, WorkerGUI workerGui) {
        this.worker = worker;
        this.workerGui = workerGui;

        customerDialog = new AddCustomerDialog(workerGui);
        parcelDialog = new AddParcelDialog(workerGui);

        workerGui.getProcessCustomerButton().addActionListener(new ProcessParcelButtonListener());

        workerGui.getAddCustomerButton().addActionListener(new AddCustomerButtonListener());

        workerGui.getAddParcelButton().addActionListener(new AddParcelButtonListener());

        workerGui.getSaveButton().addActionListener(new SaveButtonListener());

        workerGui.addWindowListener(new GUIWindowListener());

        customerDialog.getAddButton().addActionListener(new AddCustomerDialogListener());

        parcelDialog.getAddButton().addActionListener(new AddButtonParcelDialogListener());

        customerDialog.getResetButton().addActionListener(new ResetCustomerListener());

        parcelDialog.getResetButton().addActionListener(new ResetParcelListener());

    }

    private void processCustomer() {

        //All the validations for customer and parcel are implemented within the processCustomer method
        worker.processCustomer();

        Parcel currentParcel = worker.getCurrentParcel();

        if (currentParcel != null) {

            double collectionFee = worker.calculateFee(currentParcel);

            workerGui.showProcessCustomerDialog(currentParcel, collectionFee);

        }
        else {workerGui.showNoCurrentParcelDialog();}
    }

    /*
        Save reports to text files:
            generate information and save it to a text file when the window is closed or the save button is clicked
            log of events is saved when the window is closed
     */
    private void saveReport() {
        worker.generateReport("OutputReport.txt");
        log.saveLogToFile("LogReport.txt");
        workerGui.showSaveReportDialog();
    }

    private void addCustomer() {

        String name = customerDialog.getTxtCustomerName().getText().trim();
        String surname = customerDialog.getTxtCustomerSurname().getText().trim();
        String parcelId = customerDialog.getTxtParcelId().getText().trim();

        if (!name.isEmpty() && !surname.isEmpty() && !parcelId.isEmpty()) {

            Customer customer = new Customer(name, surname, parcelId);

            worker.addNewCustomer(customer);

            customerDialog.showCustomerAddedDialog();

        }
        else {customerDialog.showWrongCustomerDetailsDialog();}
    }

    private void addParcel() {

        String parcelId = parcelDialog.getTxtParcelId().getText().trim();
        ParcelStatus status = (ParcelStatus) parcelDialog.getSpnStatus().getValue();
        double weight = (double) parcelDialog.getSpnWeight().getValue();
        double length = (double) parcelDialog.getSpnLength().getValue();
        double width = (double) parcelDialog.getSpnWidth().getValue();
        double height = (double) parcelDialog.getSpnHeight().getValue();
        int daysInDepot = (int) parcelDialog.getSpnDaysInDepot().getValue();

        if (!parcelId.isEmpty() && status != null
                && weight > 0
                && length > 0
                && width > 0
                && height > 0
                && daysInDepot > 0) {

            Customer customer = worker.getQueueOfCustomers()
                                      .searchForCustomerByParcelId(
                                              parcelDialog.getTxtParcelId().getText()
                                      );

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

                parcelDialog.showParcelAddedDialog();
            }
            else {parcelDialog.showCustomerNotFoundDialog();}
        }
        else {parcelDialog.showWrongParcelDetailsDialog();}
    }

    private class ProcessParcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processCustomer();
            workerGui.updatePanels();
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
        public void actionPerformed(ActionEvent e) {customerDialog.setVisible(true);}
    }

    private class AddCustomerDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            addCustomer();

            //Enable button that worker clicks on to add a new parcel
            workerGui.getAddParcelButton().setEnabled(true);

            workerGui.updatePanels();
        }
    }

    private class AddParcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {parcelDialog.setVisible(true);}
    }

    private class AddButtonParcelDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            addParcel();

            //Disable button that worker clicks on to add a new parcel
            workerGui.getAddParcelButton().setEnabled(false);

            workerGui.updatePanels();
        }
    }

    private class ResetCustomerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {customerDialog.resetAddCustomerDialog();}
    }

    private class ResetParcelListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            parcelDialog.resetAddParcelDialog();
        }
    }

    //Calls saveReport() when the window closes
    private class GUIWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {saveReport(); log.addEvent("Simulation has ended."); }
    }
}
