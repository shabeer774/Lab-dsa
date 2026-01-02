import java.util.Scanner;

// ---------------- Person Class ----------------
class Person {
    private String name;
    private String contact;

    public Person(String name, String contact) {
        setName(name);
        setContact(contact);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty())
            this.name = name;
        else
            System.out.println("Invalid Name!");
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (contact != null && !contact.trim().isEmpty())
            this.contact = contact;
        else
            System.out.println("Invalid Contact!");
    }

    @Override
    public String toString() {
        return name + " (" + contact + ")";
    }
}

// ---------------- Parcel Abstract Class ----------------
abstract class Parcel {
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

// ---------------- DomesticParcel Class ----------------
class DomesticParcel extends Parcel {
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

// ---------------- InternationalParcel Class ----------------
class InternationalParcel extends Parcel {
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

// ---------------- ParcelManager Class ----------------
class ParcelManager {
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

// ---------------- Main Class ----------------
 class CourierSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParcelManager manager = new ParcelManager(5);

        while (true) {
            System.out.println("\n--- Courier Management Menu ---");
            System.out.println("1. Add Parcel");
            System.out.println("2. Display All Parcels");
            System.out.println("3. Search Parcel by ID");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Parcel ID: ");
                    String id = sc.nextLine();

                    System.out.print("Sender Name: ");
                    String sName = sc.nextLine();
                    System.out.print("Sender Contact: ");
                    String sContact = sc.nextLine();

                    System.out.print("Receiver Name: ");
                    String rName = sc.nextLine();
                    System.out.print("Receiver Contact: ");
                    String rContact = sc.nextLine();

                    Person sender = new Person(sName, sContact);
                    Person receiver = new Person(rName, rContact);

                    System.out.print("Parcel Type (1 = Domestic, 2 = International): ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    Parcel parcel;

                    if (type == 1) {
                        System.out.print("Distance (km): ");
                        int distance = sc.nextInt();
                        sc.nextLine();
                        parcel = new DomesticParcel(id, sender, receiver, distance);
                    } else {
                        System.out.print("Country: ");
                        String country = sc.nextLine();
                        System.out.print("Customs Required? (true/false): ");
                        boolean customs = sc.nextBoolean();
                        sc.nextLine();
                        parcel = new InternationalParcel(id, sender, receiver, country, customs);
                    }

                    System.out.print("Status: ");
                    parcel.setStatus(sc.nextLine());

                    manager.addParcel(parcel);
                    System.out.println("Parcel Added Successfully!");
                    break;

                case 2:
                    manager.sortParcelsByID();
                    manager.displayAll();
                    System.out.println("Total Parcels: " + manager.getCount());
                    break;

                case 3:
                    System.out.print("Enter Parcel ID to search: ");
                    Parcel found = manager.binarySearchByID(sc.nextLine());
                    if (found != null)
                        System.out.println("Parcel Found:\n" + found);
                    else
                        System.out.println("Parcel Not Found");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
