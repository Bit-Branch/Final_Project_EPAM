package by.javatr.finance.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3179565463408300964L;

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private UserType userType;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String firstName,
                String lastName, UserType userType) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public User(Integer id, String login, String password,
                String firstName, String lastName, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return prime * result + ((userType == null) ? 0 : userType.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return (id != null && other.getId() != null) ?
                id.equals(other.getId()) : id == other.getId() &&
                (login != null && other.getLogin() != null) ?
                login.equals(other.getLogin()) : login == other.getLogin() &&
                (password != null && other.getPassword() != null) ?
                password.equals(other.getPassword()) : password == other.getPassword() &&
                (firstName != null && other.getFirstName() != null) ?
                firstName.equals(other.getFirstName()) : firstName == other.getFirstName() &&
                (lastName != null && other.getLastName() != null) ?
                lastName.equals(other.getLastName()) : lastName == other.getLastName() &&
                (userType != null && other.getUserType() != null) ?
                userType.equals(other.getUserType()) : userType == other.getUserType();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + userType + ": " + login + ", " + password + ", " + firstName + ", " + lastName;
    }
}
