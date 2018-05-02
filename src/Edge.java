public class Edge{
    Vertex v1;
    Vertex v2;
    boolean drawn = false;

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    Vertex partner(Vertex v){
        if (v == v1){
            return v2;
        } else if (v == v2){
            return v1;
        } else {
            return null;
        }
    }
}