package adtimokhin.lesson7;

/**
 * Class MainClass. Created by adtimokhin. 17.09.2018 (11:14)
 **/
public class MainClass {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('J');
        graph.addVertex('K');
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,9);
        graph.addEdge(1,5);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(4,7);
        graph.addEdge(5,8);
        graph.addEdge(6,8);
        graph.addEdge(7,9);
        graph.addEdge(4,9);
        graph.addEdge(0,9);

//        graph.addVertex('A');
//        graph.addVertex('B');
//        graph.addVertex('C');
//        graph.addVertex('D');
//        graph.addVertex('E');
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(0,3);
//        graph.addEdge(1,3);
//        graph.addEdge(1,2);
//        graph.addEdge(1,4);
//        graph.addEdge(0,4);
        graph.printMatrix();
//        graph.matrixShortestPath(0,0,0,9);
        graph.shortestPath(5,4);

    }
}
