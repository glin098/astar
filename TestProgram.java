//package astar;

import graph.*;

import java.util.Stack;

public class TestProgram {

	private static void printSearchPath(String search, Stack<INode> path, int cost, String [] nodes, int explored) {
		INode node;
		
		System.out.print(search + " path is ");
		
		while (!path.isEmpty()) {
			node = path.pop();
			
			System.out.print(nodes[node.getID()-1] + (path.isEmpty() ? "" : "-"));						
		}
		
		System.out.println(" with cost " + cost + " after exploring " + explored + " nodes");
	}
	
	public static void main(String[] args) {
		
		//                        S   A   B   C   D   E   G
		
		int [][] costs1    = { { -1,  1,  5,  8, -1, -1, -1 },    // S
				               { -1, -1, -1, -1,  3,  7,  9 },    // A
				               { -1, -1, -1, -1, -1, -1,  4 },    // B
				               { -1, -1, -1, -1, -1, -1,  5 },    // C
				               { -1, -1, -1, -1, -1, -1, -1 },    // D
				               { -1, -1, -1, -1, -1, -1, -1 },    // E
		                       { -1, -1, -1, -1, -1, -1, -1 } };  // G

		String [] nodes1   = { "S", "A", "B", "C", "D", "E", "G" };
		
		int [] heuristics1 = {  8,   8,   4,   3,  99,  99,   0 };

		// Graph
		
		IGraph graph1 = new Graph(costs1);
				
		System.out.println(graph1.toString("\nGraph"));
		
		// Uniform cost search

		IAStarSearch ucs1 = new AStarSearch(graph1, heuristics1, 1, 7, new UniformCost());
		
		if (ucs1.solve()) 
			printSearchPath("Uniform cost", ucs1.getPath(), ucs1.getCost(), nodes1, ucs1.getExploredNodes());
		else 
			System.out.println("Uniform cost did not find a solution!");	
				
		// Greedy Best-first search
		
		IAStarSearch greedy1 = new AStarSearch(graph1, heuristics1, 1, 7, new GreedyBestFirst());
		
		if (greedy1.solve())
			printSearchPath("Greedy Best-first", greedy1.getPath(), greedy1.getCost(), nodes1, greedy1.getExploredNodes());
		else
			System.out.println("Greedy Best-first did not find a solution!");
		
		// A* search
		
		IAStarSearch astar1 = new AStarSearch(graph1, heuristics1, 1, 7, new AStar());
		
		if (astar1.solve())
			printSearchPath("A*", astar1.getPath(), astar1.getCost(), nodes1, astar1.getExploredNodes());
		else
			System.out.println("A* did not find a solution!");			
		
		//                        A   B   C   D   E   F   G   H   I   J
		
		int [][] costs2    = { { -1,  6, -1, -1, -1,  3,  5, -1, -1, -1 },    // A
				               {  6, -1,  3,  2, -1, -1, -1, -1, -1, -1 },    // B
				               { -1,  3, -1,  2,  5, -1, -1, -1, -1, -1 },    // C
				               { -1,  2,  2, -1,  8, -1, -1, -1, -1, -1 },    // D
				               { -1, -1,  5,  8, -1, -1, -1, -1,  5,  5 },    // E
				               {  3, -1, -1, -1, -1, -1,  1,  7, -1, -1 },    // F
				               {  5, -1, -1, -1, -1,  1, -1, -1,  3, -1 },    // G
				               { -1, -1, -1, -1, -1,  7, -1, -1,  2, -1 },    // H
				               { -1, -1, -1, -1,  5, -1,  3,  2, -1,  3 },    // I
				               { -1, -1, -1, -1,  5, -1, -1, -1,  3, -1 } };  // J

		String [] nodes2   = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		
		int [] heuristics2 = {  8,   8,   5,   7,   3,   6,   5,   3,   2,   0 };
		
		// Graph
		
		IGraph graph2 = new Graph(costs2);
				
		System.out.println(graph2.toString("\nGraph"));
		
		// Uniform cost search

		IAStarSearch ucs2 = new AStarSearch(graph2, heuristics2, 1, 10, new UniformCost());
		
		if (ucs2.solve())
			printSearchPath("Uniform cost", ucs2.getPath(), ucs2.getCost(), nodes2, ucs2.getExploredNodes());
		else
			System.out.println("Uniform cost did not find a solution!");			

		// Greedy Best-first search
		
		IAStarSearch greedy2 = new AStarSearch(graph2, heuristics2, 1, 10, new GreedyBestFirst());

		if (greedy2.solve())		
			printSearchPath("Greedy Best-first", greedy2.getPath(), greedy2.getCost(), nodes2, greedy2.getExploredNodes());
		else
			System.out.println("Greedy Best-first did not find a solution!");		
		
		// A* search
		
		IAStarSearch astar2 = new AStarSearch(graph2, heuristics2, 1, 10, new AStar());

		if (astar2.solve())	
			printSearchPath("A*", astar2.getPath(), astar2.getCost(), nodes2, astar2.getExploredNodes());
		else
			System.out.println("A* did not find a solution!");			
						
	}

}
