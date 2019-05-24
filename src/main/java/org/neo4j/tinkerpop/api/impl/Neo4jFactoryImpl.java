package org.neo4j.tinkerpop.api.impl;

import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tinkerpop.api.Neo4jFactory;
import org.neo4j.tinkerpop.api.Neo4jGraphAPI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Neo4jFactoryImpl implements Neo4jFactory {

    @Override
    public Neo4jGraphAPI newGraphDatabase(String path, Map<String, String> config) {
        try {
            if (path.startsWith("file:")) {
                path = new URL(path).getPath();
            }
            GraphDatabaseBuilder builder = createGraphDatabaseFactory(config).newEmbeddedDatabaseBuilder(new File(path));
            if (config != null) builder = builder.setConfig(config);
            return new Neo4jGraphAPIImpl(builder.newGraphDatabase());
        } catch(MalformedURLException e) {
            throw new RuntimeException("Error handling path "+path,e);
        }
    }

    protected GraphDatabaseFactory createGraphDatabaseFactory(Map<String, String> config) {
        return new GraphDatabaseFactory();
    }
}
