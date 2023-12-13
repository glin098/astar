public class AStar implements ICostFunction{

    @Override
    public int cost(int g, int h){
        return g + h;
    }
    
}
