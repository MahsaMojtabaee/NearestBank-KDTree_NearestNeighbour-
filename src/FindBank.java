public class FindBank {
}

class KDBank{
    private String name;
    private int X,Y;
    private KDNode Banks;
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }
    void setCoordinates(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    int getX(){
        return this.X;
    }
    int getY(){
        return this.Y;
    }

}
class KDNode extends KDBank{
    int x1,y1,x2,y2,x3,y3;
}

class KDTree{
    KDNode root;
}