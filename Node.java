//package astar;

/*
 * This class implements the method compareTo of the interface Comparable<INode>, a cost function is used to classify the nodes in the frontier (Priority Queue)
 *
 * - id is the identifier of the current node
 * - predecessor is the predecessor node of the current node
 * - cost represents the real cost to get to the current node from the initial state
 * - heuristic represents the estimated cost to reach a goal state from the current node
 * - function is the cost function used by Uniform cost search, Greedy Best-first search, and A*
 */

public class Node implements INode {
	private int id;
	private int predecessor;
	private int cost;
	private int heuristic;
	private ICostFunction function;
	
	public Node(int id, int predecessor, int cost, int heuristic, ICostFunction function) {
		this.id = id;
		this.predecessor = predecessor;
		this.cost = cost;
		this.heuristic = heuristic;
		this.function = function;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getHeuristic() {
		return this.heuristic;
	}

	@Override
	public int getID() {
		return this.id;
	}
	
	@Override
	public int getPredecessor() {
		return this.predecessor;
	}	
	
	@Override
	public int getCostFunction() {
		return this.function.cost(this.cost, this.heuristic);
	}
	
	@Override
	public int compareTo(INode n) {
		return (this.getCostFunction() == n.getCostFunction()) ? 0 : ((this.getCostFunction() > n.getCostFunction()) ? 1 : - 1);  // same as this.getCostFunction() - n.getCostFunction()
	}
}
