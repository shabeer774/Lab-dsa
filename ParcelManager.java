// ---------------- ParcelManager Class ----------------
public class ParcelManager {
    private Parcel[] parcels;
    private int count;

    public ParcelManager(int initialSize) {
        parcels = new Parcel[initialSize];
        count = 0;
    }

    public void addParcel(Parcel p) {
        if (count == parcels.length) {
            Parcel[] temp = new Parcel[parcels.length * 2];
            for (int i = 0; i < parcels.length; i++)
                temp[i] = parcels[i];
            parcels = temp;
        }
        parcels[count++] = p;
    }

    public void sortParcelsByID() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (parcels[j].getParcelID()
                        .compareTo(parcels[j + 1].getParcelID()) > 0) {
                    Parcel temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }
        }
    }

    public Parcel binarySearchByID(String id) {
        sortParcelsByID();
        int low = 0, high = count - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = parcels[mid].getParcelID().compareTo(id);

            if (cmp == 0)
                return parcels[mid];
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;
    }

    public void displayAll() {
        for (int i = 0; i < count; i++)
            System.out.println(parcels[i]);
    }

    public int getCount() {
        return count;
    }
}
