package app.database.neo4j.models;

import org.mindrot.jbcrypt.BCrypt;

public class UserResource {
    private String uid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getUid() { return uid; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getHashedPassword() { return BCrypt.hashpw(password, BCrypt.gensalt(12)); }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
