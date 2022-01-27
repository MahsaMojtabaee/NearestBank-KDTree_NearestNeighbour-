import java.util.GregorianCalendar;
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
//    Node findMin(Node root, int d, int mod) {
//        if (root == null)
//            return null;
//
//        if (mod == 0){
//            if (root.left == null)
//                return root;
//            return findMin(root.left, d, (mod == 0 ? 1 : 0));
//        }
//        return least(root, findMin(root.left, d, (mod == 0 ? 1 : 0)), findMin(root.right, d, (mod == 0 ? 1 : 0)), d);
//    }
//
//    Node findMin(Node root) {
//        return findMin(root, 0, 0);
//    }
//
//    Node least(Node root1, Node root2, Node root3, int mod) {
//        Node res = root1;
//        if (root2 != null && (mod == 0 && root2.x < res.x || mod == 1 && root2.y < res.y))
//            res = root2;
//        if (root3 != null && (mod == 0 && root3.x < res.x || mod == 1 && root3.y < res.y))
//            res = root3;
//        return res;
//    }
//
//    Node deleteNode(Node root, double x, double y, int mod) {
//        if (root == null)
//            return null;
//
//        if (root.x == x && root.y == y) {
//            if (root.right != null) {
//                Node min = findMin(root.right);
//                root.x = min.x;
//                root.y = min.y;
//                root.right = deleteNode(root.right, min.x, min.y, (mod == 0 ? 1 : 0));
//            } else if (root.left != null) {
//                Node min = findMin(root.left);
//                root.x = min.x;
//                root.y = min.y;
//                root.right = deleteNode(root.left, min.x, min.y, (mod == 0 ? 1 : 0));
//            } else {  // If node is leaf
//                root = null;
//                return null;
//            }
//            return root;
//        }
//
//        if (root.mod == 0 && x < root.x || root.mod == 1 && y < root.y)
//            root.left = deleteNode(root.left, x, y, (mod == 0 ? 1 : 0));
//        else
//            root.right = deleteNode(root.right, x, y, (mod == 0 ? 1 : 0));
//        return root;
//    }
//
//    void deleteNode(double x, double y) {
//        deleteNode(root, x, y, 0);
//    }


    Node findMin(Node root, int e, int d) {

        if (root == null) {
            return null;
        }

        if (d == e) {
            if (root.left == null) {
                return root;
            }
            return findMin(root.left, e, d + 1);
        }
        return Min(root, findMin(root.left, e, d + 1), findMin(root.right, e, d + 1), d);
    }

    Node Min(Node a, Node b, Node c, int d) {

        Node temp = a;
        if (b != null && ((d == 0 && b.x < temp.x) || (d == 1 && b.y < temp.y))) {
            temp = b;
        }
        if (c != null && ((d == 0 && c.x < temp.x) || (d == 1 && c.y < temp.y))) {
            temp = c;
        }
        return temp;

    }

    Node deleteNode(Node root, double x, double y, int d) {

        if (root == null) {
            return null;
        }

        if (root.x == x && root.y == y) {
            if (root.right != null) {
                Node min = findMin(root.right, d, 0);
                root.x = min.x;
                root.y = min.y;
//                root.isB = min.isB;
                if (min.name != null)
                    root.name = min.name;
//                if (min.mainB != null)
//                    root.mainB = min.mainB;
                root.right = deleteNode(root.right, min.x, min.y, (d == 0 ? 1 : 0));

            } else if (root.left != null) {
                Node min = findMin(root.left, d, 0);
                root.x = min.x;
                root.y = min.y;
//                root.isB = min.isB;
                if (min.name != null)
                    root.name = min.name;
//                if (min.mainB != null)
//                    root.mainB = min.mainB;
                root.right = deleteNode(root.left, min.x, min.y, (d == 0 ? 1 : 0));
            } else {
                root = null; //??
                return root;
            }
            return root;
        }

        if ((d == 0 && x < root.x) || (d == 1 && y < root.y)) {
            root.left = deleteNode(root.left, x, y, d + 1);
        } else {
            root.right = deleteNode(root.right, x, y, d + 1);
        }
        return root;
    }

    void deleteNode(double x, double y){
        root = deleteNode(root, x, y, 0);
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
        System.out.println("mod is "+mod+" and root is "+ Root.name);
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
