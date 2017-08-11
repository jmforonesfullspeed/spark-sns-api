package app.database.neo4j.User;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface UserGraphResult extends Iterator<UserGraph> {
    <T> List<T> list(Function<UserGraph, T> mapFunction );
}
