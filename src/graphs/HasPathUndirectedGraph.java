package graphs;

import java.util.*;


//Undirected and Cyclic Graph
public class HasPathUndirectedGraph {

    public boolean hasPath(Map<Character, List<Character>> graph, Character src, Character dst, Set<Character> visited) {
        if (src == dst) return true;
        Stack<Character> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            Character current = stack.pop();
            if (current == dst) return true;
            if (visited.contains(current)) continue;
            visited.add(current);
            for (Character neighbor: graph.get(current)) {
                stack.push(neighbor);
            }
        }
        return false;
    }

    public boolean hasPathRecursive(Map<Character, List<Character>> graph, Character src, Character dst, Set<Character> visited) {
        if (src == dst) return true;
        if (visited.contains(src)) return false;
        visited.add(src);

        for (Character neighbor: graph.get(src)) {
            if (hasPathRecursive(graph, neighbor, dst, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPathBFS(Map<Character, List<Character>> graph, Character src, Character dst, Set<Character> visited) {
        Queue<Character> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            Character current = queue.poll();
            if (current == dst) return true;
            if (visited.contains(current)) continue;
            visited.add(current);

            for (Character neighbor: graph.get(current)) {
                queue.add(neighbor);
            }
        }
        return false;
    }
    public Map<Character, List<Character>> buildAdjacencyList(List<List<Character>> edges) {
        Map<Character, List<Character>> graph = new HashMap<>();

        for (List<Character> edge: edges) {
            Character a = edge.get(0);
            Character b = edge.get(1);
            if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
            if (!graph.containsKey(b)) graph.put(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return graph;
    }

    public static void main(String[] args) {
        List<List<Character>> edges = List.of(
                List.of('i','j'),
                List.of('k','i'),
                List.of('m','k'),
                List.of('k','l'),
                List.of('o','n')
        );



        HasPathUndirectedGraph hasPath = new HasPathUndirectedGraph();
        Map<Character, List<Character>> graph = hasPath.buildAdjacencyList(edges);
        System.out.println(hasPath.hasPath(graph, 'i', 'l', new HashSet<>()));
        System.out.println(hasPath.hasPath(graph, 'j', 'm', new HashSet<>()));
        System.out.println(hasPath.hasPath(graph, 'm', 'j', new HashSet<>()));
        System.out.println(hasPath.hasPath(graph, 'k', 'o', new HashSet<>()));
        System.out.println(hasPath.hasPath(graph, 'i', 'o', new HashSet<>()));
        System.out.println("****************************");
        System.out.println(hasPath.hasPathRecursive(graph, 'i', 'l', new HashSet<>()));
        System.out.println(hasPath.hasPathRecursive(graph, 'j', 'm', new HashSet<>()));
        System.out.println(hasPath.hasPathRecursive(graph, 'm', 'j', new HashSet<>()));
        System.out.println(hasPath.hasPathRecursive(graph, 'k', 'o', new HashSet<>()));
        System.out.println(hasPath.hasPathRecursive(graph, 'i', 'o', new HashSet<>()));
        System.out.println("****************************");
        System.out.println(hasPath.hasPathBFS(graph, 'i', 'l', new HashSet<>()));
        System.out.println(hasPath.hasPathBFS(graph, 'j', 'm', new HashSet<>()));
        System.out.println(hasPath.hasPathBFS(graph, 'm', 'j', new HashSet<>()));
        System.out.println(hasPath.hasPathBFS(graph, 'k', 'o', new HashSet<>()));
        System.out.println(hasPath.hasPathBFS(graph, 'i', 'o', new HashSet<>()));
    }
}
