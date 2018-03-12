class Contact {

    private String name, number;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
