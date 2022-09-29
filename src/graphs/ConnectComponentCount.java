package graphs;

import java.util.*;


//Undirected Cyclic Graph
public class ConnectComponentCount {

    public int componentCount(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (Integer node: graph.keySet()) {
            if (exploreDfs(graph, node, visited)) count++;
        }
        return count;
    }

    private boolean exploreDfs(Map<Integer, List<Integer>> graph, Integer current, Set<Integer> visited) {
        if (visited.contains(current)) return false;
        visited.add(current);

        for (Integer neighbor: graph.get(current)) {
            exploreDfs(graph, neighbor, visited);
        }
        return true;
    }

    private int componentCountDFSIterative(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (Integer node: graph.keySet()) {
            if (exploreDFSIterative(graph, node, visited)) {
                count++;
            }
        }
        return count;
    }


    private boolean exploreDFSIterative(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
        if (visited.contains(node)) return false;
        Stack<Integer> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (visited.contains(current)) continue;
            visited.add(current);

            for (Integer neighbor: graph.get(current)) {
                stack.push(neighbor);
            }
        }
        return true;
    }

    private int componentCountBFS(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (Integer node: graph.keySet()) {
            if (exploreBfs(graph, node, visited)) {
                count++;
            }
        }
        return count;
    }

    public boolean exploreBfs(Map<Integer, List<Integer>> graph, Integer node, Set<Integer> visited) {
        if (visited.contains(node)) return false;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            for(int neighbor: graph.get(current)) {
                queue.add(neighbor);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(3, List.of());
        graph.put(4, List.of(6));
        graph.put(6, List.of(4,5,7,8));
        graph.put(8, List.of(6));
        graph.put(7, List.of(6));
        graph.put(5, List.of(6));
        graph.put(1, List.of(2));
        graph.put(2, List.of(1));

        ConnectComponentCount componentCount = new ConnectComponentCount();
        System.out.println(componentCount.componentCount(graph));
        System.out.println(componentCount.componentCountDFSIterative(graph));
        System.out.println(componentCount.componentCountBFS(graph));
        System.out.println("*******************");
        System.out.println(componentCount.componentCount(Map.of()));
        System.out.println(componentCount.componentCountDFSIterative(Map.of()));
        System.out.println(componentCount.componentCountBFS(Map.of()));
        System.out.println("*******************");
        System.out.println(componentCount.componentCount(Map.of(
                0, List.of(4, 7),
                1, List.of(),
                2, List.of(),
                3, List.of(6),
                4, List.of(0),
                6, List.of(3),
                7, List.of(0),
                8, List.of()
        )));
        System.out.println(componentCount.componentCountDFSIterative(Map.of(
                0, List.of(4, 7),
                1, List.of(),
                2, List.of(),
                3, List.of(6),
                4, List.of(0),
                6, List.of(3),
                7, List.of(0),
                8, List.of()
        )));
        System.out.println(componentCount.componentCountBFS(Map.of(
                0, List.of(4, 7),
                1, List.of(),
                2, List.of(),
                3, List.of(6),
                4, List.of(0),
                6, List.of(3),
                7, List.of(0),
                8, List.of()
        )));
    }
}
