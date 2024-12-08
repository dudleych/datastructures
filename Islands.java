import java.util.Scanner;

public class Islands {
    private char[][] graph;
    //private boolean[][] visited;
    private int gridSize;
    private int islandCount;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Islands(scanner);
        scanner.close();
    }

    private Islands(Scanner scanner) {
        this.gridSize = scanner.nextInt();
        scanner.nextLine();
        this.graph = new char[gridSize][gridSize];
        //this.visited = new boolean[gridSize][gridSize];
        initializeGraph(scanner);
        findIslands();
        System.out.print(this.islandCount);
    }

    private void findIslands() {
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if (graph[r][c] == '*') {
                    islandCount++;
                    depthFirstSearch(r, c);
                }
            }
        }
    }

    private void depthFirstSearch(int row, int col) {
        graph[row][col] = '-';
    
        // this should cover all eight possible direction (top left --> bottom right) and exclused the middle for obvious reasons
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };
    
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            // I'm trying to save memory by manipulating current graph[][] array to mark visitations instead of using secondary boolean[][] visited array
            if (validIndex(newRow, newCol) && graph[newRow][newCol] == '*') {
                depthFirstSearch(newRow, newCol);
            }
        }
    }

    private void initializeGraph(Scanner scanner) {
        for (int i = 0; i < gridSize; i++) {
            graph[i] = scanner.nextLine().toCharArray();
        }
    }

    private boolean validIndex(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }
}