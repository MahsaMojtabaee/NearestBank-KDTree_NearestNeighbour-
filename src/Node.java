class Node {
    int mod;
    String name;
    double x, y;
    Point point;
    Node left, right;



    Node(String name, double x, double y, int mod) {
        this.name = name;
        this.x = x;
        this.y = y;
        point = new Point(x,y);
        mod = mod;
        left = right = null;
    }
}

