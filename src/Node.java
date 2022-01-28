class Node {
    int mod;
    String name;
    double x, y;
    Point point;
    Node left, right;
    boolean isMain;
    int numOfBranches;
    String MainBank;
//    KDTree branches;


    Node(String name, boolean isMain, double x, double y, int mod) {
//        branches = new KDTree();
        numOfBranches = 0;
        this.name = name;
        this.isMain = isMain;
        this.x = x;
        this.y = y;
        point = new Point(x,y);
        this.mod = mod;
        left = right = null;
    }
}

