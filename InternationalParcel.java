// ---------------- InternationalParcel Class ----------------
public class InternationalParcel extends Parcel {
    private String country;
    private boolean customs;

    public InternationalParcel(String id, Person sender, Person receiver,
                               String country, boolean customs) {
        super(id, sender, receiver);
        setCountry(country);
        this.customs = customs;
    }

    public void setCountry(String country) {
        if (country != null && !country.trim().isEmpty())
            this.country = country;
        else
            System.out.println("Invalid Country!");
    }

    @Override
    public String calculateDeliveryTime() {
        return customs ? "10–15 days (with customs)" : "7–10 days";
    }
}
