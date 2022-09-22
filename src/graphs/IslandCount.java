package graphs;

import java.util.List;

public class IslandCount {

    private int countIsLand(List<List<Character>> grid) {
        int[][] visited = new int[grid.size()][grid.get(0).size()];

        int count = 0;
        for (int i = 0; i < grid.size(); i++){
            for (int j=0; j < grid.get(0).size(); j++) {
                if (grid.get(i).get(j) == 'L' && visited[i][j] != 1) {
                    if (explore(grid, i, j, visited)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean explore(List<List<Character>> grid, int r, int c, int[][] visited) {
        if (r < 0 || r > grid.size() -1 || c<0 || c> grid.get(0).size() -1) return false;

        if (grid.get(r).get(c) == 'W') return false;

        if (visited[r][c] == 1) return false;
        visited[r][c] = 1;

        explore(grid, r -1, c, visited);
        explore(grid, r + 1, c, visited);
        explore(grid, r, c - 1, visited);
        explore(grid, r, c + 1, visited);
        return true;
    }

    public static void main(String[] args) {
        List<List<Character>> grid = List.of(
                List.of('W', 'L', 'W', 'W', 'W'),
                List.of('W', 'L', 'W', 'W', 'W'),
                List.of('W', 'W', 'W', 'L', 'W'),
                List.of('W', 'W', 'L', 'L', 'W'),
                List.of('L', 'W', 'W', 'L', 'L'),
                List.of('L', 'L', 'W', 'W', 'W'));

        IslandCount islandCount = new IslandCount();

        System.out.println(islandCount.countIsLand(grid));

        List<List<Character>> grid2 = List.of(
                List.of('L', 'W', 'W', 'L', 'W'),
                List.of('L', 'W', 'W', 'L', 'L'),
                List.of('W', 'L', 'W', 'L', 'W'),
                List.of('W', 'W', 'W', 'W', 'W'),
                List.of('W', 'W', 'L', 'L', 'L')
        );
        System.out.println(islandCount.countIsLand(grid2));
    }
}
