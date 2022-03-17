package app;

class Organization extends Contact {
    String address;

    Organization(String name, String address, String phoneNumber){
        super(name, phoneNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
