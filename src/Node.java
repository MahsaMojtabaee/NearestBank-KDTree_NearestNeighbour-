class Node { // nodes as banks
    int mod;
    String name;
    double x, y;
    Node left, right;
    Branch branch;

    Node(String name, double x, double y, int mod) {
        this.name = name;
        this.x = x;
        this.y = y;
        mod = mod;
        left = right = null;
    }
}

