// package astar;

public class GreedyBestFirst implements ICostFunction{
    @Override
    public int cost(int g, int h){
        return h;
    }
}
