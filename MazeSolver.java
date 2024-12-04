import java.util.ArrayList;
import java.util.List;
 
public class MazeSolver {
    private int[][] maze;
    private int width, height;
    private int[][] distances;
    private Vertex[][] vertices;
    private PriorityQueue<Vertex> queue;
 
    // Directions: Up, Down, Left, Right
    private final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
 
    public MazeSolver(int[][] maze) {
        this.maze = maze;
        this.height = maze.length;
        this.width = maze[0].length;
 
        this.distances = new int[height][width];
        this.vertices = new Vertex[height][width];
        this.queue = new PriorityQueue<>();
 
        // Initialize vertices and distances
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                vertices[y][x] = new Vertex(x, y, "(" + x + "," + y + ")");
                vertices[y][x].setDistance(Integer.MAX_VALUE);
                if (maze[y][x] == 1) {
                    vertices[y][x].setObstacle();
                }
                distances[y][x] = Integer.MAX_VALUE;
            }
        }
    }
 
    public List<int[]> solve() {
        // Initialize start vertex
        Vertex start = vertices[0][0];
        start.setDistance(0);
        queue.addVertex(start);
 
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.isVisited() || current.isObstacle()) continue;
 
            current.visited();
            int x = current.getLabel().charAt(1) - '0';
            int y = current.getLabel().charAt(3) - '0';
 
            // Stop if we reach the end
            if (x == width - 1 && y == height - 1) break;
 
            // Explore neighbors
            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];
 
                if (isInBounds(nx, ny) && !vertices[ny][nx].isVisited() && !vertices[ny][nx].isObstacle()) {
                    Vertex neighbor = vertices[ny][nx];
                    int newDistance = current.getDistance() + 1;
 
                    if (newDistance < neighbor.getDistance()) {
                        neighbor.setDistance(newDistance);
                        queue.addVertex(neighbor);
                        neighbor.addEdge(current, neighbor);
                    }
                }
            }
        }
 
        return reconstructPath();
    }
 
    private List<int[]> reconstructPath() {
        List<int[]> path = new ArrayList<>();
 
        Vertex end = vertices[height - 1][width - 1];
        if (end.getDistance() == Integer.MAX_VALUE) {
            System.out.println("No path found!");
            return path;
        }
 
        Vertex current = end;
        while (current != null) {
            path.add(new int[]{current.getLabel().charAt(1) - '0', current.getLabel().charAt(3) - '0'});
            if (current.getLabel().equals("(0,0)")) break;
            for (Vertex.Edge edge : current.edges) {
                if (edge.destinationVertex == current) {
                    current = edge.originVertex;
                    break;
                }
            }
        }
 
        return path;
    }
 
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
 
    public static void main(String[] args) {
        // Example usage
        int[][] maze = {
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
        };
 
        MazeSolver solver = new MazeSolver(maze);
        List<int[]> path = solver.solve();
 
        if (!path.isEmpty()) {
            for (int[] position : path) {
                System.out.println("(" + position[0] + "," + position[1] + ")");
            }
        }
    }
}