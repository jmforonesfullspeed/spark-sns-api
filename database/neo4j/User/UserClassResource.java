package app.database.neo4j.User;

import app.database.neo4j.NeoInitializer;
import app.database.neo4j.User.Exceptions.UserExceptions;
import com.google.gson.Gson;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

public class UserClassResource extends NeoInitializer implements UserResource {

    private StatementResult statementResults;

    public UserClassResource() {
        Gson gson = new Gson();
    }

    @Override
    public StatementResult byEmail(String email, ArrayList<HashMap<String, String>>...filters) throws UserExceptions {
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                List filtered = new ArrayList<HashMap<String, String>>();
                statementResults = tx.run("MATCH (n:Person) WHERE n.email={email} RETURN n.firstName as firstName", parameters("email", email));
                tx.success();
                return statementResults;
            }
        }
    }

    @Override
    public List<UserGraph> list() {
        return null;
    }

    public List<UserGraph> asList() {
        Gson gson = new Gson();
        return statementResults.list(x -> gson.fromJson(gson.toJson(x.asMap()), UserClassEntities.class));
    }
}
