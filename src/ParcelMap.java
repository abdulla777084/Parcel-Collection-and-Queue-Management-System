import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ParcelMap {

    private final TreeSet<Parcel> parcels;



    public ParcelMap() {parcels = new TreeSet<>();}
    public void addParcel(Parcel parcel) {parcels.add(parcel);}
    public void removeParcel(Parcel parcel) {parcels.removeIf(p -> p.getParcelID().equals(parcel.getParcelID()));}

    public int getNumberOfParcels() {
        return parcels.size();
    }

    public Parcel searchParcelByParcelId(String parcelId) {
        for (Parcel parcel : parcels) {
            if (parcel.getParcelID().equals(parcelId)) {
                return parcel;
            }
        }
        return null;
    }

    public String printAllParcels() {
        String allParcels = "";

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) { allParcels = allParcels + parcel.toString() + "\n"; } }

        else { allParcels = allParcels + "\n" + "There are no customers in the queue"; }

        return allParcels;
    }

    public TreeSet<Parcel> getParcels() {return parcels;}




}

