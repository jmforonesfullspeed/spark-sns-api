package app;
import static spark.Spark.*;

import app.database.neo4j.User.Exceptions.UserExceptions;
import app.controllers.UserController;
import app.database.neo4j.User.UserClassResource;
import app.database.neo4j.UserGraph;
import app.database.neo4j.models.UserResource;
import com.google.gson.Gson;
import app.config.Options;
import org.neo4j.driver.v1.StatementResult;


class Routes {

    private Options options;
    private Gson gson;

    Routes (Options options) {
         this.options = options;
        gson = new Gson();
    }

    void initialize() {

        get("/:email", (req, res) -> {
            try {
                res.status(401);
                return gson.toJson(new Response("Results found!", UserGraph.Userlist(req.params("email")).list(
                        x ->  gson.fromJson(gson.toJson(x.asMap()), UserResource.class))));
//                StatementResult userClassResource = new UserClassResource().byEmail(req.params("email"));
//                return gson.toJson(userClassResource.list());
            } catch (UserExceptions exceptions) {
                res.status(404);
                return gson.toJson(new Response(exceptions.getMessage(), ""));
            }
        });

        post("/registration", this.options.getContentType() , (req, res) -> {
            try {
                res.status(401);
                return gson.toJson(new Response("Successfully registered!", UserController.insert(gson.fromJson(req.body(), UserResource.class))));
            } catch (UserExceptions exceptions) {
                res.status(404);
                return gson.toJson(new Response(exceptions.getMessage(), ""));
            }
        });

        post("/authentication", this.options.getContentType() , (req, res) -> {
            try {
                res.status(401);
                return gson.toJson(new Response("Authenticated!", UserController.authentication(gson.fromJson(req.body(), UserResource.class))));
            } catch (UserExceptions exceptions) {
                res.status(404);
                return gson.toJson(new Response(exceptions.getMessage(), ""));
            }
        });

    }
}
