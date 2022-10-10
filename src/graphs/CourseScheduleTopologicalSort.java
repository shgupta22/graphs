package graphs;

import java.util.*;

public class CourseScheduleTopologicalSort {

    public static List<Integer> courseOrder(int numCourse, List<List<Integer>> preRequisite) {
        Map<Integer, List<Integer>> graph = buildAdjacencyList(preRequisite);
        Set<Integer> visited = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int i=0; i< numCourse; i++) {
            if (!dfs(graph, i, visited, cycle, result)) return new ArrayList<>();
        }
        return result;
    }

    public static boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> cycle, Set<Integer> visited, List<Integer> result) {
        // a course has 3 possible states
        //1) visited -> crs has been added to output
        //2) visiting -> crs not added to output, but added to cycle
        //3) unvisited -> crs not added to output or cycle
        if (cycle.contains(node)) return false;
        if (visited.contains(node)) return true;

        cycle.add(node);
        for (int neighbor: graph.getOrDefault(node, List.of())) {
            if (!dfs(graph, neighbor, cycle, visited, result)) return false;
        }
        cycle.remove(node);
        visited.add(node);
        result.add(node);
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
        System.out.println(courseOrder(6, List.of(List.of(0,1),
                List.of(0,2),
                List.of(1,3),
                List.of(3,2),
                List.of(5,0),
                List.of(4,0))));

        System.out.println(courseOrder(2, List.of()));
    }
}
