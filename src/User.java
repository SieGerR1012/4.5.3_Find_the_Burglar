public class User {
    private String id;
    private String fio;
    private String address;

    public User(String id, String fio, String address) {
        this.id = id;
        this.fio = fio;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getAddress() {
        return address;
    }
}
