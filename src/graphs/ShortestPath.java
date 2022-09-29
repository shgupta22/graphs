package graphs;

import java.util.*;

//For Shortest Path BFS is more efficient Solution as compare to DFS.
public class ShortestPath {

    class Pair{
        int distance;
        Character character;

        public Pair(Character character, int distance) {
            this.distance = distance;
            this.character = character;
        }
    }

    private int shortestPath(Map<Character, List<Character>> graph, Character src, Character dst) {
        Queue<Pair> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(new Pair(src, 1));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            if (visited.contains(current.character)) continue;
            visited.add(current.character);
            for (Character neighbor: graph.get(current.character)) {
                if (neighbor == dst) return current.distance;
                queue.add(new Pair(neighbor, current.distance + 1));
            }
        }
        return -1;
    }

    private Map<Character, List<Character>> buildAdjacencyList(List<List<Character>> edges) {
        Map<Character, List<Character>> graph = new HashMap<>();

        for (List<Character> pair: edges) {
            Character a = pair.get(0);
            Character b = pair.get(1);
            if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
            if (!graph.containsKey(b)) graph.put(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return graph;
    }

    public static void main(String[] args) {
        /**
         * const edges = [
         *   ['w', 'x'],
         *   ['x', 'y'],
         *   ['z', 'y'],
         *   ['z', 'v'],
         *   ['w', 'v']
         * ];
         */

        List<List<Character>> edges = List.of(
                List.of('w', 'x'),
                List.of('x', 'y'),
                List.of('z', 'y'),
                List.of('z', 'v'),
                List.of('w', 'v'));

        ShortestPath shortestPath = new ShortestPath();
        Map<Character, List<Character>> graph = shortestPath.buildAdjacencyList(edges);
        System.out.println(shortestPath.shortestPath(graph, 'w', 'z'));
        System.out.println(shortestPath.shortestPath(graph, 'x', 'y'));

        List<List<Character>> edges1 = List.of(
                List.of('a', 'c'),
                List.of('a', 'b'),
                List.of('c', 'b'),
                List.of('c', 'd'),
                List.of('b', 'd'),
                List.of('e', 'd'),
                List.of('g', 'f')
        );
        Map<Character, List<Character>> graph1 = shortestPath.buildAdjacencyList(edges1);
        System.out.println(shortestPath.shortestPath(graph1, 'e', 'c'));
        System.out.println(shortestPath.shortestPath(graph1, 'a', 'e'));
        System.out.println(shortestPath.shortestPath(graph1, 'b', 'g'));

    }
}
