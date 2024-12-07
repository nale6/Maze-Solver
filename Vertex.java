import java.util.List;

public class Vertex {

  public class Edge{
    private Vertex originVertex, destinationVertex;
    private Integer weight = 0;

    public Edge(Vertex originVertex, Vertex destinationVertex){
      this.originVertex = originVertex;
      this.destinationVertex = destinationVertex;
    }

    public Edge(Vertex originVertex, Vertex destinationVertex, Integer weight){
      this.originVertex = originVertex;
      this.destinationVertex = destinationVertex;
      this.weight = weight;
    }

    public Vertex getOriginVertex(){
      return originVertex;
    }

    public void setOriginVertex(Vertex vertex){
      originVertex = vertex;
    }

    public Vertex getDestinationVertex(){
      return destinationVertex;
    }

    public void setDestinationVertex(Vertex vertex){
      destinationVertex = vertex;
    }

    public Integer getWeight(){
      return weight;
    }

    public void setWeight(Integer number){
      weight = number;
    }

    public void setOrigin(Vertex vertex){
      originVertex = vertex;
    }

    public String displayOrigin(){
      return originVertex.toString();
    }

    public void changeDestination(Vertex vertex){
      destinationVertex = vertex;
    }

    public String displayDestination(){
      return destinationVertex.toString();
    }

    public void setWeight(int weight){
      this.weight = weight;
    }

    public int displayWeight(){
      return weight;
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
  private int distance = Integer.MAX_VALUE;

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

  public void setCoordinates(int x, int y){
    this.x = x;
    this.y = y;
  }

  public String coordinates(){
    return "(" + x + "," + y + ")";
  }

  public void addEdge(Vertex originVertex, Vertex destinationVertex){
    Vertex.Edge edge = new Vertex.Edge(originVertex, destinationVertex);
    edges.add(edge);
  }

  public void removeEdge(Edge edge){
    edges.remove(edge);
  }

  public void addWeight(Edge edge, int weight){
    edge.setWeight(weight);
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

  public void setDistance(int distance){
    this.distance = distance;
  }

  public int getDistance(){
    return distance;
  }
}
