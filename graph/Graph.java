package graph;

public class Graph implements IGraph {
    private int vertices;
    private int [][] edges;
    public static final int INFINITE = Integer.MAX_VALUE;
    
    public Graph(int vertices) {
    	this.vertices = vertices;
        this.edges = new int[this.vertices][this.vertices];

        for (int i=0; i<this.vertices; i++) {
        	for (int j=0; j<this.vertices; j++) {
        		this.edges[i][j] = INFINITE;
        	}
        }
    }

    public Graph(int [][] costs) {
    	this.vertices = costs.length;
        this.edges = new int[this.vertices][this.vertices];

        for (int i=0; i<this.vertices; i++) {
        	for (int j=0; j<this.vertices; j++) {
        		this.edges[i][j] = (costs[i][j] == -1) ? INFINITE : costs[i][j];
        	}
        }
    }

    @Override
    public int getVertices() {
    	return this.vertices;
    }

    @Override
    public int getCost(int u, int v) {
    	return (u >= 1 && u <= this.vertices && v >= 1 && v <= this.vertices) ? this.edges[u - 1][v - 1] : INFINITE;
    }

    @Override
    public void setCost(int u, int v, int cost) {
    	if (u >= 1 && u <= this.vertices && v >= 1 && v <= this.vertices) {
    		this.edges[u - 1][v - 1] = cost;
    	}
    }

    @Override
    public String toString(String s) {
    	s = s + "\n";

    	for (int i=0; i<this.vertices; i++) {
    		s = s + "\n" + (((i+1) < 10) ? " " : "") + "[" + (i + 1) + "] ";

    		for (int j=0; j<this.vertices; j++)
    			if (this.edges[i][j] == INFINITE)
    				s = s + " ---- ";
    			else
    				if (this.edges[i][j] < 10)
    					s = s + "    " + this.edges[i][j] + " ";
    				else
    					if (this.edges[i][j] < 100)
    						s = s + "   " + this.edges[i][j] + " ";
    					else
    						if (this.edges[i][j] < 1000)
    							s = s + "  " + this.edges[i][j] + " ";
    						else
    							s = s + " " + this.edges[i][j] + " ";
    	}

    	s = s + "\n";

    	return s;
    }
}
