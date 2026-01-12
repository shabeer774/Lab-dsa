// ---------------- DomesticParcel Class ----------------
public class DomesticParcel extends Parcel {
    private int distance;

    public DomesticParcel(String id, Person sender, Person receiver, int distance) {
        super(id, sender, receiver);
        setDistance(distance);
    }

    public void setDistance(int distance) {
        if (distance > 0)
            this.distance = distance;
        else
            System.out.println("Invalid Distance!");
    }

    @Override
    public String calculateDeliveryTime() {
        if (distance <= 50)
            return "1 day";
        else if (distance <= 200)
            return "2 days";
        else
            return "3 days";
    }
}
