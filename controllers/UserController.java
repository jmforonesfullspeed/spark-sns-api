package app.controllers;

import app.database.neo4j.User.Exceptions.UserExceptions;
import app.config.MongoDBClient;
import app.database.neo4j.models.UserResource;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

import app.database.neo4j.UserGraph;
import org.neo4j.driver.v1.exceptions.*;

import java.util.Objects;

public class UserController extends MongoDBClient {

    private static final String exceptionMessage = "Authentication failed!";

    /**
     * Returns collection resource from mongodb after validation on some entities
     * @return :: Object of the collection
     * **/
    public static UserResource insert(UserResource userResource) throws UserExceptions {
        if (UserGraph.Userlist(userResource.getEmail()).hasNext()) {
            throw new UserExceptions("User already exist!");
        } else {
            Gson gson = new Gson();
            return gson.fromJson(gson.toJson(UserGraph.add(userResource).single().asMap()), UserResource.class);
        }
    }

    private static UserResource getOneByEmail(String email, String... UserExceptionMessage) throws UserExceptions {
        try {
            final Gson gson = new Gson();
            Objects.requireNonNull(email, "Email is required!");
            return gson.fromJson(gson.toJson(UserGraph.Userlist(email, UserExceptionMessage.length > 0 ? UserExceptionMessage[0] : "User not found!").next()), UserResource.class);
        } catch (NoSuchRecordException exception) {
            throw new UserExceptions("No records found!");
        }
    }

    public static UserResource authentication(UserResource userResource) throws UserExceptions {
        UserResource userResourceData = getOneByEmail(userResource.getEmail());
        if (BCrypt.checkpw(userResource.getPassword(), userResourceData.getHashedPassword())) return userResourceData;
        else throw new UserExceptions(exceptionMessage);
    }
}