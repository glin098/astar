//package astar;

import graph.*;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * The cost function f(n) for A* combines the actual cost g(n) and the estimated cost h(n) to reach a goal state from node n
 *
 * f(n) = g(n) + h(n)
 * 
 * g(n) represents the actual cost to reach node n from the initial state
 * h(n) represents the estimated cost to reach a goal state from node n
 * h(n) is admissible if it doesn't overestimate the actual cost to reach a goal state from node n
 *
 * Uniform cost search      f(n) = g(n)
 * Greedy Best-first search f(n) = h(n)
 * A* search                f(n) = g(n) + h(n)
 *
 */

public class AStarSearch implements IAStarSearch {
	private IGraph graph;
	private int [] heuristic;
	private int initial;
	private int goal;
	private Queue<INode> frontier;
	private List<INode> explored;
	private Stack<INode> path;
	private int cost;
	private ICostFunction function;
	
	public AStarSearch(IGraph graph, int [] heuristic, int initial, int goal, ICostFunction function) {
		this.graph = graph;
		this.heuristic = heuristic.clone();
		this.initial = initial;
		this.goal = goal;
		this.frontier = new PriorityQueue<INode>();
		this.explored = new ArrayList<INode>();
		this.path = new Stack<INode>();
		this.cost = 0;
		this.function = function;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getExploredNodes() {
		return this.explored.size();
	}

	@Override
	public IGraph getGraph() {
		return this.graph;
	}
	
	@Override
	public Stack<INode> getPath() {
		return this.path;
	}
		
	@Override
	public boolean solve() {
		INode node;
		int g, h;
		
		// add the initial node to the frontier with predecessor -1, cost=0, g(n)=0, and h(n)
		
		this.frontier.add(new Node(this.initial, -1, 0, this.heuristic[this.initial - 1], this.function));
		
		while (!this.frontier.isEmpty()) {
			node = this.frontier.poll();
						
			if (!explored(node.getID())) {
				// add the current node to the explored set

				this.explored.add(node);
								
				// if the node is a goal state, get the path backwards until the node predecessor is -1
				
				if (node.getID() == this.goal) {
					
					// initialize the cost of the path with the cost from the predecessor to the current node
					
					this.cost = this.graph.getCost(node.getPredecessor(), node.getID());
										
					while (node.getPredecessor() != -1) {
						this.path.add(node);
						
						for (int i=0; i<this.explored.size(); i++)
							if (this.explored.get(i).getID() == node.getPredecessor()) {
								node = this.explored.get(i);
								
								if (node.getID() != this.initial) {
									this.cost = this.cost + this.graph.getCost(node.getPredecessor(), node.getID());
								}
								
								break;
							}
					}
					
					this.path.add(new Node(this.initial, -1, 0, this.heuristic[this.initial - 1], this.function));
					
					return true;
				}
				
				// otherwise, add the successors of the current node to the frontier with the cost from the current node to their adjacent nodes and f(n)
				
				for (int i=1; i<=this.graph.getVertices(); i++) {
					if (!explored(i) && this.graph.getCost(node.getID(), i) != Graph.INFINITE) {
						
						// g(n) represents the real cost to get to node n (i - 1) from the initial state
						
						g = node.getCost() + this.graph.getCost(node.getID(), i);
						
						// h(n) represents the estimated cost to reach a goal state from node n (i - 1)
						
						h = this.heuristic[i - 1];
						
						this.frontier.add(new Node(i, node.getID(), g, h, this.function));						
					}
				}
			}
		}
		
		// if the frontier is empty, there is no solution
		
		return false;
		
	}
	
	// explored returns true if the node is in the explored nodes list and false otherwise
	
	private boolean explored(int node) {
		for (INode n : this.explored) {
			if (n.getID() == node)
				return true;
		}
		
		return false;
	}
}
