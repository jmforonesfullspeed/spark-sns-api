package app.database.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class NeoInitializer {

    private static final String URI = "bolt://localhost:7687";
    private static final String USERNAME = "neo4j";
    private static final String PASSWORD = "fullspeed";
    protected static final Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USERNAME, PASSWORD));
}
