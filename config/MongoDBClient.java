package app.config;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;

import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.UuidCodec;
import org.mongodb.morphia.*;

public class MongoDBClient {

    private MongoClient mongoClient = new MongoClient("localhost" , 27017);

    protected MongoDatabase initialize() {

        MongoDatabase database;

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
                        MongoClient.getDefaultCodecRegistry());

        database = this.mongoClient.getDatabase("sns").withCodecRegistry(codecRegistry);
        return database;
    }

    public Datastore morphiaOrm() {
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        // morphia.mapPackage("org.mongodb.morphia.example");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(this.mongoClient, "sns");
        datastore.ensureIndexes();
        return datastore;
    }

}
