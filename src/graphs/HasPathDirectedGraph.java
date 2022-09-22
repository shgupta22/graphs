package graphs;

import java.util.*;

public class HasPathDirectedGraph {

    public boolean hasPath(Map<Character, List<Character>> graph, Character src, Character dst) {
        if (src == dst) return true;
        Stack<Character> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            Character current = stack.pop();
            if (current == dst) return true;
            for(Character neighbor: graph.get(current)) {
                stack.push(neighbor);
            }
        }
        return false;
    }

    public boolean hasPathRecursive(Map<Character, List<Character>> graph, Character src, Character dst) {
        if (src == dst) return true;
        for (Character neighbor: graph.get(src)) {
            if (hasPathRecursive(graph, neighbor, dst)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPathBFS(Map<Character, List<Character>> graph, Character src, Character dst) {
        if (src == dst) return true;
        Queue<Character> queue = new LinkedList<>();
        queue.add(src);

        while(!queue.isEmpty()) {
            Character current = queue.poll();
            if (current == dst) return true;
            for(Character neighbor: graph.get(current)) {
                queue.add(neighbor);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();

        graph.put('f', List.of('g', 'i'));
        graph.put('g', List.of('h'));
        graph.put('h', List.of());
        graph.put('i', List.of('g', 'k'));
        graph.put('j', List.of('i'));
        graph.put('k', List.of());

        HasPathDirectedGraph hasPath = new HasPathDirectedGraph();
        System.out.println(hasPath.hasPath(graph, 'f', 'k'));
        System.out.println(hasPath.hasPath(graph, 'f', 'j'));
        System.out.println(hasPath.hasPath(graph, 'i', 'h'));


        System.out.println("******************");
        System.out.println(hasPath.hasPathRecursive(graph, 'f', 'k'));
        System.out.println(hasPath.hasPathRecursive(graph, 'f', 'j'));
        System.out.println(hasPath.hasPathRecursive(graph, 'i', 'h'));

        System.out.println("******************");
        System.out.println(hasPath.hasPathBFS(graph, 'f', 'k'));
        System.out.println(hasPath.hasPathBFS(graph, 'f', 'j'));
        System.out.println(hasPath.hasPathBFS(graph, 'i', 'h'));
    }
}
