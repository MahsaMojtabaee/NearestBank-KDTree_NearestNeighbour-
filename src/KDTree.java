import java.util.Scanner;


public class KDTree {
    public  Node root ;

    KDTree(){
        root = null;
    }

    Node insert(Node root, String name, boolean isMain, double x, double y,int mod) {
        if (root == null) {
            return new Node(name, isMain, x, y, mod);
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
        root.numOfBranches += 1;
        return root;
    }

    void insert(String name, boolean isMain, double x, double y) {
        root = insert(root, name,isMain,  x, y, 0);
    }


    Node insertBranch(Node root, String name, boolean isMain, double x, double y,int mod, String MainBank) {

        if (root == null) {
            Node node = new Node(name, isMain, x, y, mod);
            node.MainBank = MainBank;
//            System.out.println("main bank is "+node.MainBank);
            return node;
        }
        System.out.println("1:");
        System.out.println(root.name);
        if (root.x == x && root.y == y) {
            System.out.println("A bank  with these coordinates already exists.");
            return root;
        }
        System.out.println("2:");
        System.out.println(root.name);
        if ((root.mod==0 && x < root.x) || (root.mod==1 && y < root.y)) {
            root.left = insert(root.left, name, isMain, x, y, (root.mod == 0 ? 1 : 0));
        }
        else {
            root.right = insert(root.right, name, isMain, x, y, (root.mod == 0 ? 1 : 0));
        }
        System.out.println("3:");
        System.out.println(root.name);

        root.numOfBranches += 1;
        return root;
    }

    void insertBranch(String name, boolean isMain, double x, double y, String MainBank) {
        root = insertBranch(root, name,isMain,  x, y, 0, MainBank);
    }



    private Node delete(Node root, double x, double y, int mod) {
        if (root == null) {
            return null;
        }
        if (root.x == x && root.y == y) {



//            System.out.println(root.MainBank);



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
        Node node = delete(root, x, y, 0);
        if (node != null) {
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
        System.out.println(FindMaxBranches(root, root.numOfBranches, root).name);
    }

    private Node FindMaxBranches(Node root, int Max, Node MaxBranches){
        if (root != null )
        {
            FindMaxBranches(root.left, Max, MaxBranches);
            if(root.numOfBranches > Max)
            Max = root.numOfBranches;
            MaxBranches =root;
            FindMaxBranches(root.right, Max, MaxBranches);
        }
        return root;
    }

    public Node searchByName(String name) {
        return searchByName(root, name);
    }

    private Node searchByName(Node root, String name) {
        if(root == null){
            return null;
        }
        inorder(root.left);
        if(root.name == name){
            return root;
        }
        inorder(root.right);
        return null;
    }

    static Node search(Node root, double x, double y) {
        if (root == null)
            return null;

        if (root.x == x && root.y == y)
            return root;
        if (root.mod == 0 && x < root.x || root.mod == 1 && y < root.y)
            return search(root.left, x, y);
        else
            return search(root.right, x, y);
    }

    Node search(double x, double y) {
        return search(root, x, y);
    }

    void printNodesInArea(Node node,Region area, int mod) {
        if (node == null)
            return;
        int containsResult = area.containsPoint(node.x, node.y, mod);
        if (containsResult == 0) {
            // check both subtrees
            printNodesInArea(node.right, area, (mod == 0 ? 1 : 0));
            printNodesInArea(node.left, area, (mod == 0 ? 1 : 0));
        } else if (containsResult > 0) {
            // check only left subtree
            printNodesInArea(node.left, area, (mod == 0 ? 1 : 0));
        } else {
            // check only right subtree
            printNodesInArea(node.right, area, (mod == 0 ? 1 : 0));
        }
        if (containsResult == 0 && area.containsPoint(node.x, node.y, (mod == 0 ? 1 : 0)) == 0) {
//            System.out.println("There  " at point "+node->branch->point.x << " " << node->branch->point.y << endl);
        }
    }

    void printNodesInArea(Region area){
        printNodesInArea(root, area, 0);
    }


    //////////////////////////////////////////////////////////////////////////////////// NEAREST NEIGHBOUR
    private static double distance(Point p1, Point p2) {
        return (p1.x() - p2.x()) * (p1.x() - p2.x()) + (p1.y() - p2.y()) * (p1.y() - p2.y());
    }
    static Node closerDistance(double X, double Y, Node P1, Node P2){
        if(P1 == null){
            return P2;
        }
        if(P2 == null){
            return P1;
        }

        if( distance(new Point(P1.x, P1.y), new Point(X, Y)) < distance(new Point(P2.x, P2.y), new Point(X, Y))){
            return P1;
        }
        return P2;

    }

    public Node NearestNeighbour(Node Root, double X, double Y, int mod){
        if(Root == null){
            return null;
        }
        Node nextNode;
        Node oppositeNode;
        if((mod == 0 && X < Root.x) || (mod == 1 && Y < Root.y)){
            nextNode = Root.left;
            oppositeNode = Root.right;
        }
        else {
            nextNode = Root.right;
            oppositeNode = Root.left;
        }

        Node best = closerDistance(X, Y, NearestNeighbour(nextNode, X, Y, (mod == 0 ? 1 : 0)), Root);

        if (distance(new Point(X, Y), new Point(best.x, best.y)) > (mod == 0 ? ((X - Root.x) * (X - Root.x)) : ((Y - Root.y) * (Y - Root.y))) ){
            best = closerDistance(X, Y, NearestNeighbour(oppositeNode, X, Y, (mod == 0 ? 1 : 0)), best);
        }

        return best;
    }
    public Node NearestNeighbour(double x, double y){
        return NearestNeighbour(root, x, y, 0);
    }
    private static void options() {
        System.out.println("1. Add Neighbourhood");
        System.out.println("2. Add Bank");
        System.out.println("3. Add Branch");
        System.out.println("4. Delete Branch");
        System.out.println("5. Banks of the Neighbourhood");
        System.out.println("6. Branches of the Bank");
        System.out.println("7. Nearest Bank");
        System.out.println("8. Nearest Branch");
        System.out.println("9. Available Banks");
        System.out.println("10. Bank with Most Branches");
        System.out.println("11. Most Famous Bank");
        System.out.println("12. Undo Time");
    }

    void MainMenu() {
        Scanner input = new Scanner(System.in);
                System.out.println("Enter Name of the Bank:");
                String bankName = input.nextLine();
                System.out.println("Enter Banks's Coordinate:");
                double x = input.nextDouble();
                double y = input.nextDouble();
    }

}
