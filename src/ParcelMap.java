import java.util.TreeSet;

public class ParcelMap {

    private final TreeSet<Parcel> parcels;

    public ParcelMap() {parcels = new TreeSet<>();}

    public void addParcel(Parcel parcel) {parcels.add(parcel);}

    public void removeParcel(Parcel parcel) {if (parcel != null) {parcels.remove(parcel);}}

    public int getNumberOfParcels() {return parcels.size();}

    public Parcel searchParcelByParcelId(String parcelId) {
        for (Parcel parcel : parcels) {

            if (parcel.getParcelId().equals(parcelId)) {

                return parcel;
            }
        }
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
        else { allCollectedParcels.append("\n").append("There are no customers in the queue to collect parcels"); }

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
        else { allParcelsForCollection.append("\n").append("There are no parcels for collection"); }

        return allParcelsForCollection;
    }

    public String printAllParcels() {
        String allParcels = "";

        if(!parcels.isEmpty()) {

            for (Parcel parcel : parcels) { allParcels = allParcels + parcel.toString() + "\n"; } }

        else { allParcels = allParcels +"\n"+"There are no customers in the queue"; }

        return allParcels;
    }

    public TreeSet<Parcel> getParcels() {return parcels;}








}

