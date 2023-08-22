package Controlador;

public class userClass {
    private String rut, name, lastName, password, access;

    public userClass() {
    }

    public userClass(String rut, String name, String lastName, String password, String access) {
        this.rut = rut;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.access = access;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
