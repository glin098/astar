package graph;

public interface IGraph {
    
	public int getVertices();
	public int getCost(int u, int v);
	public void setCost(int u, int v, int cost);
	public String toString(String s);

}