import java.util.List;

public class Vertex {

  public class Edge{
    public Vertex originVertex, destinationVertex;
    public Integer weight;

    public Edge(Vertex originVertex, Vertex destinationVertex){
      this.originVertex = originVertex;
      this.destinationVertex = destinationVertex;
    }
  }

  private String label;
  List<Edge> edges;

  public Vertex(String label){
    this.label = label;
  }

  public String getLabel(){
    return label;
  }

  @Override
  public String toString(){
    return this.label;
  }
}
