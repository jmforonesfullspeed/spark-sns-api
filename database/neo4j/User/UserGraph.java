package app.database.neo4j.User;


public interface UserGraph {

    public static final String uid = null;
    public static final String firstName = null;
    public static final String lastName = null;
    public static final String email = null;
    public static final String password = null;

    static String getUid() {
        return uid;
    }

    static String getFirstName() {
        return firstName;
    }

    static String getLastName() {
        return lastName;
    }

    static String getEmail() {
        return email;
    }

    static String getPassword() {
        return password;
    }

}
