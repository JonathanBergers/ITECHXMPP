package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 12-10-15.
 */
public class User {

    private final String email;


    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return getEmail();
    }


    private List<User> contacts = new ArrayList<>();

    public void addContact(User u){
        contacts.add(u);
    }

    public List<User> getContacts(){
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);

    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }
}
