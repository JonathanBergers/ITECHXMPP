package model;


/**
 * Created by jonathan on 26-10-15.
 */
public class RegisteredUser extends User {

    private final String password;

    public RegisteredUser(String email, String password) {
        super(email);
        this.password = password;
    }


    public boolean authenticate(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "password='" + password + '\'' +
                '}';
    }
}
