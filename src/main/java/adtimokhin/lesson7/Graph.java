package adtimokhin.lesson7;

import adtimokhin.lesson3.Queue;
import adtimokhin.lesson3.Stack;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Graph {


    private class Vertex {
        public char label;
        public boolean wasVisited;
        private List<Integer> neighbors = new ArrayList<>();
        private int distFromStart = -1;

        public Vertex(char label) {
            this.label = label;
            wasVisited = false;
        }

        @Override
        public String toString() {
            return "V:" + label;
        }
    }

    private final int MAX_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;
    private List<Integer> paths = new ArrayList<>();

    public Graph(int size) {
        this.MAX_VERTICES = size;
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
        vertices[start].neighbors.add(end);
        vertices[end].neighbors.add(start);
    }

    public void showVertex(int vertex) {
        System.out.println(vertices[vertex]);
    }

    private int getUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] == 1 && !vertices[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void depthTravers() {
        Stack stack = new Stack(MAX_VERTICES);
        vertices[0].wasVisited = true;
        showVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex(stack.peek());
            if (v == -1)
                stack.pop();
            else {
                vertices[v].wasVisited = true;
                showVertex(v);
                stack.push(v);
            }
        }
        resetFlags();
    }

    public void widthTravers() {
        Queue queue = new Queue(MAX_VERTICES);
        vertices[0].wasVisited = true;
        showVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int vCurr = queue.remove();
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurr)) != -1) {
                vertices[vNext].wasVisited = true;
                showVertex(vNext);
                queue.insert(vNext);
            }
        }
        resetFlags();
    }

    public void printMatrix() {
        System.out.print(" ");
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(" " + vertices[i].label);
        }
        for (int i = 0; i < vertices.length; i++) {
            System.out.print("\n" + vertices[i].label + " ");
            for (int j = 0; j < MAX_VERTICES; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    public void matrixShortestPath(int startingPoint, int start, int temp, int finish) {
        for (int i = temp; i < vertices.length; i++) {
            System.out.printf("\n %s -> %s", vertices[start], vertices[i]);
            if (start == finish && i == finish) {
                System.out.println("lol");
            }
            if (i == finish && adjMatrix[start][i] != 0) {
                System.out.println("path found");
            } else if (i == finish && adjMatrix[start][i] == 0 && finish != 0) {
                System.out.println("no path");

            } else if (adjMatrix[start][i] == 1 && start != i) {

                matrixShortestPath(startingPoint + 1, i, i, finish);
                System.out.println(start);
                if (startingPoint == start) System.out.println(vertices[start]);

            }
        }

    }

    public void shortestPath(int start, int finish) {
        vertices[start].distFromStart = 0;
        initNeighbors(vertices[start], 1);

        System.out.printf("The shortest path from (%s) to (%s):\n",vertices[start], vertices[finish]);
        findNeighbor(vertices[finish], null);
        System.out.printf("%s.",vertices[finish].label);
    }

    private void findNeighbor(Vertex vertex, Vertex smallest) {
        for (int i = 0; i < vertex.neighbors.size(); i++) {
            if (null == smallest) {
                smallest = vertices[vertex.neighbors.get(i)];
            } else if (smallest.distFromStart > vertices[vertex.neighbors.get(i)].distFromStart) {
                smallest = vertices[vertex.neighbors.get(i)];
            }
        }

        if (smallest.distFromStart != 0)
            findNeighbor(smallest, null);
        System.out.printf("%s -> ",smallest.label );
    }

    private void initNeighbors(Vertex vertex, int dist) {
        List<Vertex> needCheck = new ArrayList<>();
        for (int i = 0; i < vertex.neighbors.size(); i++) {
            if(vertices[vertex.neighbors.get(i)].distFromStart ==-1){
                vertices[vertex.neighbors.get(i)].distFromStart = dist;
                needCheck.add(vertices[vertex.neighbors.get(i)]);
            }
        }
        for (int i = 0; i < needCheck.size(); i++) {
            initNeighbors(needCheck.get(i), dist+1);
        }

    }
}

