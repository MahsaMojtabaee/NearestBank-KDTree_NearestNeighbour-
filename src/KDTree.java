public class KDTree {
    public  Node root;
    boolean flag = false;
    public boolean checkIfBanksInArea = false;

    KDTree(){
        root = null;
    }


    Node insert(Node root, String name, boolean isMain, double x, double y,int mod) {
        if (root == null) {
            Node node = new Node(name, isMain, x, y, mod);
            node.branches = new KDTree();
//            node.numOfBranches += 1;
//            System.out.println(node.numOfBranches);
            return node;
        }

        if (root.x == x && root.y == y) {
            System.out.println("A bank  with these coordinates already exists.");
            return root;
        }

        if ((root.mod==0 && x < root.x) || (root.mod==1 && y < root.y)) {
            root.left = insert(root.left, name, isMain, x, y, (root.mod == 0 ? 1 : 0));
        }
        else {
            root.right = insert(root.right, name, isMain, x, y, (root.mod == 0 ? 1 : 0));
        }

        return root;
    }

    void insert(String name, boolean isMain, double x, double y) {
        root = insert(root, name,isMain,  x, y, 0);
    }


    private Node delete(Node root, double x, double y, int mod) {
        if (root == null) {
            return null;
        }
        if (root.x == x && root.y == y) {
            if (root.isMain)
                System.out.println("This is a main bank.You cannot delete it.");
            else {
//                deleted_Bank = root.getBranch();
                if (root.right != null) {
                    Node min = findMin(root.right, mod, mod);
                    root = min;
                    root.right = delete(root.right, min.x, min.y, (mod == 0 ? 1 : 0));
                } else if (root.left != null) {
                    Node min = findMin(root.left, mod, mod);
                    root = min;
                    root.right = delete(root.left, min.x, min.y, (mod == 0 ? 1 : 0));
                } else {
                    flag = true;
                    root = null;
                }
            }
            return root;
        }
        if ( (mod == 0 && root.x > x) || (mod == 1 && root.y > y)) {
            root.left = delete(root.left, x, y, (mod == 0 ? 1 : 0));
        } else {
            root.right = delete(root.right, x, y, (mod == 0 ? 1 : 0));
        }
        return root;
    }

    private Node findMin(Node root, int targetDim, int mod) {
        if (root == null)
            return null;
        if (targetDim == mod) {
            if (root.left == null)
                return root;
            return findMin(root.left, targetDim, (mod == 0 ? 1 : 0));
        }
        Node rightMin = findMin(root.right, targetDim, (mod == 0 ? 1 : 0));
        Node leftMin = findMin(root.left, targetDim, (mod == 0 ? 1 : 0));
        Node res = root;
        if (rightMin != null && ((targetDim == 0 && rightMin.x < res.x) || (targetDim == 1 && rightMin.y < res.y)))
            res = rightMin;
        if (leftMin != null && ((targetDim == 0 && leftMin.x < res.x) || (targetDim == 1 && leftMin.y < res.y)))
            res = leftMin;
        return res;
    }


    boolean delete(double x, double y){
        flag = false;
        Node node = delete(root, x, y, 0);
        if (flag) {
            root = node;
            return true;
        }
        return false;
    }


    public void inorder()
    {
        inorder(root);
    }

    private void inorder(Node root){
        if (root != null)
        {
            inorder(root.left);
            System.out.print("(" + root.x + ", " + root.y + ")  ");
            inorder(root.right);
        }
    }
    public void FindMaxBranches()
    {
        if(root == null){
            System.out.println("There is no bank");
            return;
        }
        System.out.println(FindMaxBranches(root, root.numOfBranches, root).name);
    }

    private Node FindMaxBranches(Node root, int Max, Node MaxBranches){
        if (root != null )
        {
            FindMaxBranches(root.left, Max, MaxBranches);
            if(root.numOfBranches > Max) {
                Max = root.numOfBranches;
                MaxBranches = root;
            }
            FindMaxBranches(root.right, Max, MaxBranches);
        }
        return root;
    }

    void printNodesInCircularArea(Node node,Region area, int mod, double x, double y, double R) {
        if (node == null)
            return;
        if ((mod == 0 && node.x >= area.x_min && node.x <= area.x_max) || (mod == 1 && node.y >= area.y_min && node.y <= area.y_max)) {

            printNodesInCircularArea(node.right, area, (mod == 0 ? 1 : 0), x, y, R);
            printNodesInCircularArea(node.left, area, (mod == 0 ? 1 : 0), x, y, R);


        } else if ((mod == 0 && node.x > area.x_max) || (mod == 1 && node.y > area.y_max)) {

            printNodesInCircularArea(node.left, area, (mod == 0 ? 1 : 0), x, y, R);
        } else {

            printNodesInCircularArea(node.right, area, (mod == 0 ? 1 : 0), x, y, R);
        }
        if (((mod == 0 && node.x >= area.x_min && node.x <= area.x_max) || (mod == 1 && node.y >= area.y_min && node.y <= area.y_max)) ) {
            mod = mod == 0 ? 1 : 0;
            if (((mod == 0 && node.x >= area.x_min && node.x <= area.x_max) || (mod == 1 && node.y >= area.y_min && node.y <= area.y_max)) && (node.x - x) * (node.x - x) + (node.y - y) * (node.y - y) <= R * R) {
//         area.IfContainsPoint(node.x, node.y, (mod == 0 ? 1 : 0)) == 0){
                System.out.println("|*|=> " + node.name);
            }
        }
    }

    void printNodesInCircularArea(Region area, double x, double y, double R){
        printNodesInCircularArea(root, area, 0, x, y, R);
    }

    void printNodesInArea(Node node,Region region, int mod) {
        if (node == null)
            return;
//        int PointPosition = region.IfContainsPoint(node.x, node.y, mod);
        if ((mod == 0 && node.x >= region.x_min && node.x <= region.x_max) || (mod == 1 && node.y >= region.y_min && node.y <= region.y_max)) {

            printNodesInArea(node.right, region, (mod == 0 ? 1 : 0));
            printNodesInArea(node.left, region, (mod == 0 ? 1 : 0));


        } else if ((mod == 0 && node.x > region.x_max) || (mod == 1 && node.y > region.y_max)) {

            printNodesInArea(node.left, region, (mod == 0 ? 1 : 0));
        } else {

            printNodesInArea(node.right, region, (mod == 0 ? 1 : 0));
        }
        if (((mod == 0 && node.x >= region.x_min && node.x <= region.x_max) || (mod == 1 && node.y >= region.y_min && node.y <= region.y_max)) ) {
            mod = mod == 0 ? 1 : 0;
            if (((mod == 0 && node.x >= region.x_min && node.x <= region.x_max) || (mod == 1 && node.y >= region.y_min && node.y <= region.y_max))) {
//         region.IfContainsPoint(node.x, node.y, (mod == 0 ? 1 : 0)) == 0){
                System.out.println("|*|=> " + node.name);
            }
        }
    }

    void printNodesInArea(Region region){
        printNodesInArea(root, region, 0);
    }


    //////////////////////////////////////////////////////////////////////////////////// NEAREST NEIGHBOUR
    private static double distance(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    static Node closer(double X, double Y, Node Point1, Node Point2){
        // check if the first point is null then return second point
        if(Point1 == null){
            return Point2;
        }
        // check if the second point is null then first point
        if(Point2 == null){
            return Point1;
        }
        // check if the first point is closer to the point (x, y)
        if( distance(Point1.x, Point1.y, X, Y) < distance(Point2.x, Point2.y, X, Y)){
            return Point1;
        }
        // check if the second point is closer to the point (x, y)
        return Point2;

    }

    public Node NearestNeighbour(Node Root, double X, double Y, int mod){
        if(Root == null){
            return null;
        }
        // First we get close to the coordinates of the point (x, y)
        Node morePossible;
        // We store this to compare the best distance in the closest subtree to the point with the best distance in the other subtree
        Node lessPossible;
        // check if we are in x level or y level
        // then check if the parameter based on the x or y in the current node is less than the given point then best possible distance is in left
        // otherwise in right
        if((mod == 0 && X < Root.x) || (mod == 1 && Y < Root.y)){
            morePossible = Root.left;
            lessPossible = Root.right;
        }
        else {
            morePossible = Root.right;
            lessPossible = Root.left;
        }
        // find the best to compare
        Node best = closer(X, Y, NearestNeighbour(morePossible, X, Y, (mod == 0 ? 1 : 0)), Root);
        // if the best distance is not the best based on the x or y level then go to the other subtree
        double x =  (mod == 0 ? ((X - Root.x) * (X - Root.x)) : ((Y - Root.y) * (Y - Root.y)));
        if (distance(X, Y , best.x, best.y) > x) {
            best = closer(X, Y, NearestNeighbour(lessPossible, X, Y, (mod == 0 ? 1 : 0)), best);
        }

        return best;
    }

    public Node NearestNeighbour(double x, double y){
        return NearestNeighbour(root, x, y, 0);
    }


}
