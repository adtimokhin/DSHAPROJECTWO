package adtimokhin.lesson7;

import adtimokhin.lesson3.Queue;
import adtimokhin.lesson3.Stack;

import java.util.ArrayList;
import java.util.List;

public class Graph {




    private class Vertex {
        public char label;
        public boolean wasVisited;

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
                             //дз
    public void matrixShortestPath(int startingPoint, int start, int temp, int finish) {
        for (int i = temp; i < vertices.length; i++) {
                System.out.printf("\n %s -> %s", vertices[start], vertices[i]);
            if (start == finish && i == finish) {
                System.out.println("lol");
            }
            if (i == finish && adjMatrix[start][i] != 0) {
                System.out.println("path found");
//                System.out.println(start);
//                System.out.println(vertices[i]);
            } else if (i == finish && adjMatrix[start][i] == 0 && finish != 0) {
                System.out.println("no path");
//                System.out.println(start);
            } else if (adjMatrix[start][i] == 1 && start != i) {
//                System.out.printf("\nfrom %s to %s", vertices[start], vertices[i]);
                matrixShortestPath(startingPoint+1, i, i, finish);
                    System.out.println(start);
                if(startingPoint == start) System.out.println(vertices[start]);
//                System.out.println(vertices[i]);
            }
        }

    }


















    // ПРОШЛЫЕ ПОПЫТКИ(НЕВАЖНО)
//    public void matrixShortestPath(int starting, int begin, int temp, int finish){
//        for (int i = temp; i < vertices.length; i++) {
//            if(adjMatrix[begin][i] == finish)
//
//        }
//    }
}

   /* public void findShortestPath(int start, int finish) {
        if (!(start > -1 || start < size || finish < size || finish > -1)) throw new IndexOutOfBoundsException();
        if (start == finish) System.out.println("start = finish");// todo: вернуть массив из finish'a
        // todo: добавить еще один ArrayList в который класть закончиные маршруты из метода  insertNewPath(paths, orderPaths.peek(), v);
        Queue queue = new Queue(MAX_VERTICES);
        vertices[start].wasVisited = true;
        queue.insert(start);

        Queue orderPaths = new Queue(MAX_VERTICES);
        int i = 0;
        orderPaths.insert(i);
        // TODO: придумать и реализовать возможность добавлять в ArrayList такие же динамические массивы, чтобы не приходилось создавать каждый раз новый массив
        List<int[]> paths = new ArrayList<>();
        paths.add(new int[]{queue.peek()});

        List<int[]> finishedPaths = new ArrayList<>();
        // поиск всех возможных путей
        while (!queue.isEmpty()) {
            int v = getUnvisitedVertex(queue.peek());
            if (v == -1) {
                queue.remove();
                orderPaths.remove();
            } else {
                // FIXME: НАЙТИ И ИСПАРВИТЬ ОШИБКУ СОСТАВЛЕНИЯ НЕ ВЕРНЫХ ПУТЕЙ!!!!!!!!!!!!!!!
                insertNewPath(paths, orderPaths.peek(), v), finish;
                orderPaths.insert(i++);
                if (v != finish)
                    vertices[v].wasVisited = true;
                queue.insert(v);
            }
            if (v == finish) {
                queue.remove();
                orderPaths.remove();
            }
        }
        *//*for (int j = 0; j < paths.size(); j++) {
            System.out.print("[");
            for (int k = 0; k < paths.get(j).length; k++) {
                System.out.print(paths.get(j)[k]+",");
            }
            System.out.println("]");
        }*//*
        update(paths, finish);
        findShortest(paths);

    }

    private void findShortest(List<int[]> paths) {
        int max = MAX_VERTICES, index = 0;
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).length < max) {
//                System.out.println("yes");
                max = paths.get(i).length;
                index = i;
            }
        }
//        System.out.println(index);
//        System.out.println(max);
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < max; i++) {
            stringBuilder.append(paths.get(index)[i]);
            stringBuilder.append(((i== max-1)? "]": ","));
        }
        System.out.println(stringBuilder);
    }

    private void update(List<int[]> paths, int finish) {
        for (int i = 0; i < paths.size(); ) {
            if (paths.get(i)[paths.get(i).length - 1] != finish) {
                paths.remove(i);
//                System.out.println("delete");
            }
            else i++;
        }
    }

    private void insertNewPath(List<int[]> arr, int index, int next,int finish) {
        int[] ints = new int[arr.get(index).length+1];
//        System.out.println(ints.length+" : "+arr.get(index).length);
        for (int i = 0; i < arr.get(index).length; i++) {
            ints[i] = arr.get(index)[i];
        }
//        System.out.println(next);
        ints[arr.get(index).length] = next;
        if(next == finish)
        arr.add(ints);
    }*/

/*    public void findShortestPath(int start, int finish) {
        if (start == finish) {
            System.out.println(start + " = " + finish);
        } else if (start < 0 || start >= MAX_VERTICES || finish < 0 || finish >= MAX_VERTICES)
            throw new IndexOutOfBoundsException("starting/ending element is out of graph's bounds");
        else {
            Queue elements = new Queue(MAX_VERTICES);
            Queue numberOfPaths = new Queue(MAX_VERTICES);
            List<int[]> unfinishedPaths = new ArrayList<>();
            List<int[]> finishedPaths = new ArrayList<>();
            vertices[start].wasVisited = true;
            elements.insert(start);
            unfinishedPaths.add(new int[]{elements.peek()});
            int i = 0;
            numberOfPaths.insert(i);
            while (!elements.isEmpty()) {
                int v = getUnvisitedVertex(elements.peek());
                if (v == -1) {
                    numberOfPaths.remove();
                    resetFlags(elements.remove());
                } else {
                    if (addPath(unfinishedPaths, numberOfPaths.peek(), v, finishedPaths, finish))
                        numberOfPaths.insert(i++);
                    if (v == finish) {
                        vertices[v].wasVisited = false;

                    numberOfPaths.remove();
                    resetFlags(elements.remove());
                    }else {
                        elements.insert(v);
                        vertices[v].wasVisited = true;

                    }
                }

//                System.out.println(elements.size());
            }
        }
    }

    private void resetFlags(int remove) {
        for (int i = 0; i < vertices.length; i++) {
            if (adjMatrix[remove][i] == 1 && !vertices[i].wasVisited && adjMatrix[remove][i]!=remove)vertices[i].wasVisited = false;
        }
    }


    private boolean addPath(List<int[]> paths, int index, int next, List<int[]> dest, int finish) {
        int arr[] = new int[(paths.get(index).length) + 1];
        for (int i = 0; i < paths.get(index).length; i++) {
            arr[i] = paths.get(index)[i];
        }
        arr[arr.length - 1] = next;
        if (next == finish) {
            dest.add(arr);
            print(arr);
            return false;
        } else {
            paths.add(arr);
            print(arr);
        }
        return true;
    }

    private void print(int[] arr) {
        System.out.print("\n" + arr.length + "\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(vertices[arr[i]].label);
        }
    }
}*/
