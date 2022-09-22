package graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumIsland {

    private int minIsland(List<List<Character>> grid) {
        Set<String> visited = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (int i =0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (grid.get(i).get(j) == 'L') {
                    int size = exploreSize(grid, i, j, visited);
                    if (size != 0)
                        min = Math.min(size, min);
                }
            }
        }
        return min;
    }

    private int exploreSize(List<List<Character>> grid, int r, int c, Set<String> visited) {
        if (r <0 || r>grid.size()-1 || c <0 || c>grid.get(0).size()-1) return 0;

        if (grid.get(r).get(c) == 'W') return 0;

        if (visited.contains(r+","+c)) return 0;
        visited.add(r+","+c);

        int size = 1;
        size += exploreSize(grid, r - 1, c, visited);
        size += exploreSize(grid, r + 1, c, visited);
        size += exploreSize(grid, r, c - 1, visited);
        size += exploreSize(grid, r, c + 1, visited);
        return size;
    }

    public static void main(String[] args) {
        List<List<Character>> grid = List.of(
                List.of('W', 'L', 'W', 'W', 'W'),
                List.of('W', 'L', 'W', 'W', 'W'),
                List.of('W', 'W', 'W', 'L', 'W'),
                List.of('W', 'W', 'L', 'L', 'W'),
                List.of('L', 'W', 'W', 'L', 'L'),
                List.of('L', 'L', 'W', 'W', 'W'));

        MinimumIsland minimumIsland = new MinimumIsland();

        System.out.println(minimumIsland.minIsland(grid));

        List<List<Character>> grid1 = List.of(
                List.of('L', 'L', 'L'),
                List.of('L', 'L', 'L'),
                List.of('L', 'L', 'L'));
        System.out.println(minimumIsland.minIsland(grid1));
    }
}
