import java.util.TreeSet;

public class ParcelMap {

    private final TreeSet<Parcel> parcels;



    public ParcelMap() {parcels = new TreeSet<>();}
    public void addParcel(Parcel parcel) {parcels.add(parcel);}
    public void removeParcel(Parcel parcel) {parcels.removeIf(p -> p.getParcelID().equals(parcel.getParcelID()));}

    public int getNumberOfParcels() {
        return parcels.size();
    }

    public Parcel searchByParcelId(String parcelId) {
        for (Parcel parcel : parcels) {
            if (parcel.getParcelID().equals(parcelId)) {
                return parcel;
            }
        }
        return null;
    }

    public String printParcels() {
        String allParcels = "";

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) { allParcels = allParcels + parcel.toString() + "\n"; } }

        else { allParcels = allParcels + "\n" + "There are no customers in the queue"; }

        return allParcels;
    }

}

