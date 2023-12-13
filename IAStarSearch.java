import graph.IGraph;
import java.util.Stack;

public interface IAStarSearch {
    public int getCost();
    public int getExploredNodes();
    public IGraph getGraph();
    public Stack<INode> getPath();
    boolean solve();
}
