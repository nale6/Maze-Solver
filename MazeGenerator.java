import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
 
public class MazeGenerator {
    private int width, height;
    private int[][] maze;
    private Random random = new Random();
 
    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
 
        // Initialize the maze with all walls (1)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }
    }
 
    public int[][] generateMaze() {
        // Start carving paths from a random point
        int startX = random.nextInt(width / 2) * 2;
        int startY = random.nextInt(height / 2) * 2;
 
        carvePath(startX, startY);
 
        // Ensure start and end points are open
        maze[0][0] = 0; // Start
        maze[height - 1][width - 1] = 0; // End
 
        return maze;
    }
 
    private void carvePath(int x, int y) {
        // Directions: Up, Down, Left, Right
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        ArrayList<int[]> shuffledDirections = new ArrayList<>();
 
        // Shuffle the directions for random path generation
        Collections.addAll(shuffledDirections, directions);
        Collections.shuffle(shuffledDirections);
 
        for (int[] direction : shuffledDirections) {
            int dx = direction[0], dy = direction[1];
            int nx = x + dx * 2, ny = y + dy * 2;
 
            if (isInBounds(nx, ny) && maze[ny][nx] == 1) {
                // Remove the wall between the current cell and the next cell
                maze[y + dy][x + dx] = 0;
                maze[ny][nx] = 0;
 
                // Recursively carve paths from the new cell
                carvePath(nx, ny);
            }
        }
    }
 
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
 
    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "#" : ".");
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) {
        // Example usage
        MazeGenerator generator = new MazeGenerator(10, 10);
        int[][] maze = generator.generateMaze();
        generator.printMaze();
    }
}