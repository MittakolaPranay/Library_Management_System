package models;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(int id,String name,String email,String password,String role) {
        this.email = email;
        this.id = id;
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public User(String name,String email,String password,String role) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.password = password;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
