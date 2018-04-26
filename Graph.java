import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Vertex> vertices;

    public Graph(int n){
        vertices = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            vertices.add(new Vertex());
        }
    }

    public class Vertex{
        List<Edge> adjacencyList;

        public Vertex(){
            this.adjacencyList = new ArrayList<>();
        }

        public void connect(Vertex v2){
            Edge e = new Edge(this, v2);
            this.adjacencyList.add(e);
            v2.adjacencyList.add(e);
        }
    }

    public class Edge{
        Vertex v1;
        Vertex v2;

        public Edge(Vertex v1, Vertex v2){
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}
