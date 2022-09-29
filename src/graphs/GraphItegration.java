package graphs;

import java.util.*;


//Iteration is for Directed Acyclic Graph
public class GraphItegration {
    public void DFS(Map<Character, List<Character>> graph, Character src) {
        Stack<Character> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            Character current = stack.pop();
            System.out.print(current);

            for (Character neighbor : graph.get(current)) {
                stack.push(neighbor);
            }
        }
    }

    public void DFS_recursive(Map<Character, List<Character>> graph, Character src) {
        System.out.print(src);
        for(Character neighbor: graph.get(src)) {
            DFS_recursive(graph, neighbor);
        }
    }

    public void BFS(Map<Character, List<Character>> graph, Character src) {
        Queue<Character> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            Character current = queue.poll();
            System.out.print(current);
            for(Character neighbor: graph.get(current)) {
                queue.add(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();

        graph.put('a', List.of('b', 'c'));
        graph.put('b', List.of('d'));
        graph.put('c', List.of('e'));
        graph.put('d', List.of('f'));
        graph.put('e', List.of());
        graph.put('f', List.of());

        GraphItegration graphItegration = new GraphItegration();
        graphItegration.DFS(graph, 'a');

        System.out.println("\n*****************");
        graphItegration.DFS_recursive(graph, 'a');

        System.out.println("\n****************");
        graphItegration.BFS(graph, 'a');
    }
}
