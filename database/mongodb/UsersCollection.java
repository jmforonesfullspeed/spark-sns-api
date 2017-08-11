package app.database.mongodb;
import app.database.neo4j.User.Exceptions.UserExceptions;
import org.apache.commons.validator.routines.EmailValidator;
import org.mongodb.morphia.annotations.*;
import org.bson.types.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity("UserController")
public class UsersCollection {
    @Id
    @Property("id")
    private ObjectId id;

    @Property("firstName")
    private String firstName;

    @Property("lastName")
    private String lastName;

    @Property("email")
    private String email;

    @Property("password")
    private String password;

    public void setId(ObjectId id) { this.id = id; }

    public ObjectId getId() { return this.id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getFirstName() { return firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLastName() { return lastName; }

    public void setEmail(String email) throws UserExceptions {
        this.email = validateEmail(email);
    }

    public String getEmail() throws UserExceptions {
        return validateEmail(this.email);
    }

    private String validateEmail(String email) throws UserExceptions {
        if(EmailValidator.getInstance(true).isValid(email)) {
            return email;
        } else {
            throw new UserExceptions("Invalid email format!");
        }
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public String getPassword() { return password; }

}