package app.database.neo4j.User;

import org.mindrot.jbcrypt.BCrypt;

public class UserClassEntities implements UserGraph {

    private String uid = UserGraph.uid;
    private String firstName = UserGraph.firstName;
    private String lastName = UserGraph.lastName;
    private String email = UserGraph.email;
    private String password = UserGraph.password;

    public String getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() { return BCrypt.hashpw(password, BCrypt.gensalt(12)); }

}
