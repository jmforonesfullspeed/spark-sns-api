package app.database.neo4j.User;


import app.database.neo4j.User.Exceptions.UserExceptions;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserResource {

    StatementResult byEmail(String email, ArrayList<HashMap<String, String>>...filters) throws UserExceptions;

    List<UserGraph> list();

}