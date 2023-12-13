public interface INode extends Comparable<INode>{
    public int getCost();
    public int getCostFunction();
    public int getHeuristic();
    public int getID();
    public int getPredecessor();
    public int compareTo(INode n);
    
}
