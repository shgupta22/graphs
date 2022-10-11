package graphs;

import java.util.*;

public class CourseSchedule {

    public static boolean canComplete(int numCourses, List<List<Integer>> preRequisite) {
        Map<Integer, List<Integer>> graph = buildAdjacencyList(preRequisite);
        Set<Integer> visited = new HashSet<>();

        for (int i=0; i < numCourses; i++) {
            if (!dfs(graph, i, visited)) return false;
        }
        return true;
    }

    public static boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) return false;

        if (graph.getOrDefault(node, new ArrayList<>()).isEmpty()) return true;

        visited.add(node);

        for (int neighbor: graph.get(node)) {
            if (!dfs(graph, neighbor, visited)) return false;
        }
        visited.remove(node);
        graph.put(node, new ArrayList<>());
        return true;
    }

    public static Map<Integer, List<Integer>> buildAdjacencyList(List<List<Integer>> preRequisite) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> edge: preRequisite) {
            int a = edge.get(0);
            int b = edge.get(1);
            if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
            if (!graph.containsKey(b)) graph.put(b, new ArrayList<>());
            graph.get(a).add(b);
        }
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(canComplete(5, List.of(List.of(0,1),
                List.of(0,2),
                List.of(1,3),
                List.of(1,4),
                List.of(3,4))));


        System.out.println(canComplete(2, List.of(List.of(0,1),
                List.of(1,0))));

        System.out.println(canComplete(4, List.of(List.of(0,1),
                List.of(2,3))));

        System.out.println(canComplete(4, List.of(List.of(0,1),
                List.of(1,2),
                List.of(2,0))));
    }
}
