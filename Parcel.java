// ---------------- Parcel Abstract Class ----------------
public abstract class Parcel {
    private String parcelID;
    private Person sender;
    private Person receiver;
    private String status;

    public Parcel(String id, Person sender, Person receiver) {
        if (id != null && !id.trim().isEmpty())
            this.parcelID = id;
        else
            System.out.println("Invalid Parcel ID!");

        this.sender = sender;
        this.receiver = receiver;
        this.status = "Created";
    }

    public String getParcelID() {
        return parcelID;
    }

    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty())
            this.status = status;
        else
            System.out.println("Invalid Status!");
    }

    public abstract String calculateDeliveryTime();

    @Override
    public String toString() {
        return "Parcel ID: " + parcelID +
                ", Sender: " + sender +
                ", Receiver: " + receiver +
                ", Status: " + status +
                ", ETA: " + calculateDeliveryTime();
    }
}
