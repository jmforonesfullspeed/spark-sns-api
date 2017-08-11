package app.database.neo4j;

import app.database.neo4j.User.Exceptions.UserExceptions;
import app.database.neo4j.models.UserResource;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.*;

import java.util.UUID;

import static org.neo4j.driver.v1.Values.parameters;

public class UserGraph extends NeoInitializer {

    public static StatementResult Userlist(String email, String...UserExceptionMessage) throws UserExceptions {
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                try {
                    final Value params = parameters("email", email);
                    return tx.run("MATCH (n:Person) WHERE n.email={email} RETURN n.uid as uid, n.firstName as firstName, n.lastName as lastName, n.email as email, n.password as password", params);
                } catch (NoSuchRecordException exception) {
                    throw new UserExceptions(UserExceptionMessage.length > 0 ? UserExceptionMessage[0] : "User not found!");
                }
            }
        }
    }

    public static StatementResult add(UserResource userResource) {

        /* Sessions are lightweight and disposable connection wrappers. */
        try (Session session = driver.session()) {
            /*
                Wrapping Cypher in an explicit transaction provides atomicity
                and makes handling errors much easier.
            */
            try (Transaction tx = session.beginTransaction()) {
                Object uuid = UUID.randomUUID().toString();
                final Value params = parameters("uid", uuid, "firstName", userResource.getFirstName(), "lastName", userResource.getLastName(), "email", userResource.getEmail(), "password", userResource.getHashedPassword());
                StatementResult data = tx.run("CREATE (n:Person {uid: {uid}, name: {firstName}, firstName: {firstName}, lastName: {lastName}, email: {email}, password: {password} }) RETURN n.uid as uid, n.firstName as firstName, n.lastName as lastName, n.email as email, n.password as passwor", params);
                /* Mark this write as successful. */
                tx.success();
                return data;
            }
        }
    }

    public void close()
    {
        /* Closing a driver immediately shuts down all open connections. */
        driver.close();
    }

}
