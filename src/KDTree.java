import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class KDTree {
    public static Node root ;
    TrieTree banks = new TrieTree();
    TrieTree neighbourBanks = new TrieTree();
//    private static HashMap<String, Rectangle> district = new HashMap<>(); // district name -> Rect
//    //    private static HashMap<String, String> nameOfTheCoordinate = new HashMap<>(); // coordinate -> name of the bank or branch
//    private static HashMap<String, String> bankOfTheBranch = new HashMap<>(); // branch name -> bank name
////    private static HashMap<String, Node> banks = new HashMap<>(); // name -> Node
//    private static HashMap<String, String> types = new HashMap<>(); // coordinate -> type
//        private static HashMap<String, Point> bank_main = new HashMap<>(); // list of the main banks
//    private static HashMap<String, Point> bank_branch = new HashMap<>(); // list of the branches
//    private static HashMap<String, Integer> fame_degree = new HashMap<>(); // most famous bank

    KDTree(){
        this.root = null;
    }

    static Node insert(Node root, String name, double x, double y,int mod) {
        if (root == null) {
            return new Node(name, x, y, mod);
        }

        if (root.x == x && root.y == y) {
            System.out.println("A bank  with these coordinates already exists.");
            return root;
        }

        if ((root.mod==0 && x < root.x) || (root.mod==1 && y < root.y)) {
            root.left = insert(root.left, name, x, y, (root.mod == 0 ? 1 : 0));
        }
        else {
            root.right = insert(root.right, name, x, y, (root.mod == 0 ? 1 : 0));
        }
        return root;
    }
    static void insert(String name, double x, double y) {
        root = insert(root, name, x, y, 0);
    }

    public void inorder()
    {
        inorder(root);
    }

    private void inorder(Node root)
    {
        if (root != null)
        {
            inorder(root.left);
            System.out.print("(" + root.x + ", " + root.y + ")  ");
            inorder(root.right);
        }
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
    private static Node NearestNeighbour(Node Root, double X, double Y, int mod){
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








    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    void addN(String name, double x_min, double x_max, double y_min, double y_max) {
        Rectangle rect = new Rectangle(name, x_min, x_max, y_min, y_max);
//        district.put(name, rect);
    }
    void addB(double x, double y, String name) {
        insert(name ,x, y);
        banks.insert(name);
    }
    void addBr(String MainBankName, String name, double x, double y) {
        insert(name, x, y);
        banks.addBranches(MainBankName, name, x, y);
    }
    void listBrs(String MainBankName){
        System.out.println(banks.search(MainBankName).branches);
    }
    Node nearB(double X, double Y){
        return NearestNeighbour(root, X, Y, 0);
    }
    Node nearBr(String MainBankName, double X, double Y){
        return NearestNeighbour((Node) banks.search(MainBankName).kdBranches.root, X, Y, 0);
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

    public static void main(String[] args) {
        KDTree t = new KDTree();
//        t.addB(1, 2, "a");
        t.insert("a", 2, 2);
        System.out.println(t.root.name);
//        t.addB(0, 2, "b");
        t.insert("b", 1, 2);
        System.out.println(t.root.left.name);
//        t.addBr("a", "A.1", 1, 6);
        t.insert("c", 0, 1);
        System.out.println(t.root.left.left.name);
                t.addBr("a", "A.1", 1, 6);
        t.inorder();
        System.out.println(t.nearB(1,5).name);
    }
}
