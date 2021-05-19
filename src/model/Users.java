package model;

public class Users {
    private int id;
    private int user_type;
    private String name_last_name;
    private String email;
    private String user_name;
    private String password;
    private int phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserType() {
        return user_type;
    }

    public void setUserType(int user_type) {
        this.user_type = user_type;
    }

    public String getNameLastName() {
        return name_last_name;
    }

    public void setNameLastName(String name_last_name) {
        this.name_last_name = name_last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
