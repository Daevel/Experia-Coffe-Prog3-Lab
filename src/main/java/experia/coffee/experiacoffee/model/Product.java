package experia.coffee.experiacoffee.model;

public class Product {
    private Integer id;
    private String firstname;
    private String secondname;
    private String lastname;

    public Product(Integer id, String firstname, String secondname, String lastname) {
        this.setId(id);
        this.setFirstname(firstname);
        this.setSecondname(secondname);
        this.setLastname(lastname);
    }
    public Product(String firstname, String secondname, String lastname) {
        this.setFirstname(firstname);
        this.setSecondname(secondname);
        this.setLastname(lastname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
