// ---------------- Person Class ----------------
public  class Person {
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
