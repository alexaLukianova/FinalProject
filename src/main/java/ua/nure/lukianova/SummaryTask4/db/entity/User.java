package ua.nure.lukianova.SummaryTask4.db.entity;

public class User extends Entity {

    private static final long serialVersionUID = -7318397511827886287L;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean locked;
    private int roleId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User [login=" + username
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", roleId=" + roleId + "]";
    }
}
