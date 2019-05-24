package pub.sha0w.NeoRDF;

import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jVertex;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import sparql.SparqlToGremlinCompiler;

import java.util.HashMap;

public class Neo4JCommunityTest {
    public static void main(String[] args) {
        String dbPath = "C:\\Users\\coco1\\IdeaProjects\\testNeoRDF\\src\\neo4jDB\\graph.db";
        String sparql = "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o\n" +
                "}";
        Neo4jGraph graph = Neo4jGraph.open(dbPath);
        GraphTraversal cypherTraversal = graph.cypher("match (p) return p");
        while (cypherTraversal.hasNext()) {
            HashMap<String, Neo4jVertex> node =  (HashMap<String, Neo4jVertex>)cypherTraversal.next();
            System.out.println(node.values().iterator().next().label());
        }

        GraphTraversal sparqlTraversal = SparqlToGremlinCompiler.compile(graph, sparql);

        while (sparqlTraversal.hasNext()) {
            HashMap<String, Neo4jVertex> node =  (HashMap<String, Neo4jVertex>)sparqlTraversal.next();
            System.out.println(node.values().iterator().next().label());
        }
    }
}
