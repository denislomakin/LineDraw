import java.util.ArrayList;
import java.util.List;

public class Vertex{
    List<Edge> adjacencyList;
    boolean odd;
    int y;
    int x;
    int index;

    public Vertex(){
        this.adjacencyList = new ArrayList<>();
        odd = false;
    }
    public Vertex(int index){
        this.adjacencyList = new ArrayList<>();
        odd = false;
        this.index=index;
    }

    public void connect(Vertex v2){
        Edge e = new Edge(this, v2);
        this.adjacencyList.add(e);
  
        v2.adjacencyList.add(e);
    }
}