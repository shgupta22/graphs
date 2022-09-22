package graphs;

import java.util.*;

public class LargestComponent {

    private int largestSizeDFS(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int maxSize = -1;
        for (int node: graph.keySet()) {
            int size = exploreSizeDFS(graph, node, visited);
            System.out.print(size);
            maxSize = Math.max(maxSize, size);
        }
        return maxSize;
    }

    private int exploreSizeDFS(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) return 0;
        visited.add(node);
        int size = 1;
        for(int neighbor: graph.get(node)) {
            size += exploreSizeDFS(graph, neighbor, visited);
        }
        return size;
    }

    private int largestSizeDFSIterative(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int maxSize = -1;
        for (int node : graph.keySet()) {
            int size = exploreSizeDFSItr(graph, node, visited);
            System.out.print(size);
            maxSize = Math.max(size, maxSize);
        }
        return maxSize;
    }

    private int exploreSizeDFSItr(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
        if (visited.contains(node)) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        int size = 0;
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (visited.contains(current)) continue;
            visited.add(current);
            size++;
            for(int neighbor: graph.get(current)) {
                stack.push(neighbor);
            }
        }
        return size;
    }

    private int largestSizeBFS(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int maxSize = -1;
        for (int node: graph.keySet()) {
            int size = exploreSizeBFS(graph, node, visited);
            System.out.print(size);
            maxSize = Math.max(maxSize, size);
        }
        return maxSize;
    }

    private int exploreSizeBFS(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        int size = 0;
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);
            size++;
            for(int neighbor: graph.get(current)) {
                queue.add(neighbor);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        /**
         * {
         *   0: ['8', '1', '5'],
         *   1: ['0'],
         *   5: ['0', '8'],
         *   8: ['0', '5'],
         *   2: ['3', '4'],
         *   3: ['2', '4'],
         *   4: ['3', '2']
         * }
         */

        Map<Integer, List<Integer>> graph = Map.of(
                0, List.of(8, 1, 5),
                1, List.of(0),
                5, List.of(0, 8),
                8, List.of(0, 5),
                2, List.of(3, 4),
                3, List.of(2, 4),
                4, List.of(3, 2)
        );

        LargestComponent component = new LargestComponent();

        System.out.println("\n"+component.largestSizeDFS(graph));

        System.out.println("*********************");
        System.out.println("\n"+component.largestSizeDFSIterative(graph));

        System.out.println("*********************");
        System.out.println("\n"+component.largestSizeBFS(graph));
    }
}
