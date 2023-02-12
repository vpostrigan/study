package ch03.fig03_05;

// Fig. 3.5: Account.java
// Account class with a constructor that initializes the name.
public class Account {
    private String name; // instance variable

    // constructor initializes name with parameter name
    public Account(String name) {
        this.name = name;
    }

    // method to set the name
    public void setName(String name) {
        this.name = name;
    }

    // method to retrieve the name
    public String getName() {
        return name;
    }

}
