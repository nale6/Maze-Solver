import java.util.Comparator;
import java.util.LinkedList;

public class PriorityQueue<Node extends Vertex> {
  private LinkedList<Vertex> queue;
  private Comparator<Vertex> comparator;

  public static class Sort implements Comparator<Vertex>{
    @Override
    public int compare (Vertex vertex1, Vertex vertex2){
      return vertex1.getDistance() - vertex2.getDistance();
    }
  }

  public PriorityQueue(Comparator<Vertex> comparator){
    this.queue = new LinkedList<Vertex>();
    this.comparator = comparator;
  }

  public PriorityQueue(){
    this(new Sort());
  }

  public void sort(){
    queue.sort(comparator);
  }

  public void addVertex(Vertex vertex){
    for(int i = 0; i < queue.size(); i++){
      if(comparator.compare(vertex, queue.get(i)) < 0){
        queue.add(i, vertex);
        return;
      }
    }
    queue.addLast(vertex);
  }

  public Vertex poll(){
    return queue.isEmpty() ? null : queue.remove(0);
  }

  public boolean isEmpty(){
    return queue.isEmpty();
  }
}
