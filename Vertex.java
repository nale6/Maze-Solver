import java.util.List;

public class Vertex {

  public class Edge{
    public Vertex originVertex, destinationVertex;
    public Integer weight = 0;

    public Edge(Vertex originVertex, Vertex destinationVertex){
      this.originVertex = originVertex;
      this.destinationVertex = destinationVertex;
    }

    public Edge(Vertex originVertex, Vertex destinationVertex, Integer weight){
      this.originVertex = originVertex;
      this.destinationVertex = destinationVertex;
      this.weight = weight;
    }
  }

  private String label;
  List<Edge> edges;
  private boolean visited = false;
  private boolean obstacle = false;
  private boolean start = false;
  private boolean end = false;
  private int x;
  private int y;

  public Vertex(int x, int y, String label){
    this.x = x;
    this.y = y;
    this.label = label;
  }

  public String getLabel(){
    return label;
  }

  @Override
  public String toString(){
    return this.label;
  }

  public String coordinates(){
    return "(" + x + "," + y + ")";
  }

  public void addEdge(Edge edge){
    edges.add(edge);
  }

  public void removeEdge(Edge edge){
    edges.remove(edge);
  }

  public void visited(){
    visited = true;
  }

  public boolean isVisited(){
    return visited;
  }

  public void setObstacle(){
    obstacle = true;
  }

  public boolean isObstacle(){
    return obstacle;
  }

  public void setStart(){
    start = true;
  }

  public boolean isStart(){
    return start;
  }

  public void setEnd(){
    end = true;
  }

  public boolean isEnd(){
    return end;
  }
}
