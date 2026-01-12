
import java.util.Scanner;

// ---------------- Main Class ----------------
 public class CourierSystem {
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
