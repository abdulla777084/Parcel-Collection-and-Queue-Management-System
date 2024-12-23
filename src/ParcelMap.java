import java.util.HashSet;
import java.util.TreeSet;

public class ParcelMap {

    private final TreeSet<Parcel> parcels;

    public ParcelMap() {parcels = new TreeSet<>();}
    public void addParcel(Parcel parcel) {parcels.add(parcel);
        //System.out.println("Added parcel: " + parcel);
        //System.out.println("get parcel that was added: " + searchParcelByParcelId(parcel.getParcelID()) + "\n");
        }
    public void removeParcel(Parcel parcel) {if (parcel != null) {parcels.remove(parcel);}}

    public int getNumberOfParcels() {
        return parcels.size();
    }

    public Parcel searchParcelByParcelId(String parcelId) {

        for (Parcel parcel : parcels) {
            //System.out.println("Checking parcel with ID: " + parcel.getParcelID());
            if (parcel.getParcelID().equals(parcelId)) {
                //System.out.println("Parcel found: " + parcel);
                return parcel;
            }
        }
        //System.out.println("No parcel found with ID: " + parcelId);
        return null;
    }

    public StringBuilder printAllCollectedParcels() {
        StringBuilder allCollectedParcels = new StringBuilder();

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) {
                if (parcel.getStatus() == ParcelStatus.COLLECTED) {
                    allCollectedParcels.append(parcel).append("\n");
                }
            }
        }
        else { allCollectedParcels.append("\n").append("There are no customers in the queue"); }

        return allCollectedParcels;
    }

    public StringBuilder printAllParcelsForCollection() {
        StringBuilder allParcelsForCollection = new StringBuilder();

        boolean foundForCollection = false;

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) {
                if (parcel.getStatus() == ParcelStatus.FOR_COLLECTION) {
                    allParcelsForCollection.append(parcel).append("\n");
                    foundForCollection = true;

                }
            }
            if (!foundForCollection) {
                allParcelsForCollection.append("There are no parcels for collection.\n");
            }
        }
        else { allParcelsForCollection.append("\n").append("There are no customers in the queue"); }

        return allParcelsForCollection;
    }

    public StringBuilder printAllParcels() {
        StringBuilder allParcels = new StringBuilder();

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) { allParcels.append(parcel.toString()).append("\n"); } }

        else { allParcels.append("\n").append("There are no customers in the queue"); }

        return allParcels;
    }

    public TreeSet<Parcel> getParcels() {return parcels;}








}

