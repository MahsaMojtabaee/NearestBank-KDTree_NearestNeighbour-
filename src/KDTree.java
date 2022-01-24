import java.util.Map;
import java.util.Scanner;


public class KDTree {
    static Node root ;
    KDTree(){
        this.root = null;
    }

    private static Node insert(Node root, String name, double x, double y,int mod) {
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

    private static void insert(String name, double x, double y) {
        root = insert(root, name, x, y, 0);
    }

    //////////////////////////////////////////////////////////////////////////////////// SEARCH


    //////////////////////////////////////////////////////////////////////////////////// NEAREST NEIGHBOUR
    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x() - p2.x()),2) + Math.pow((p1.y() - p2.y()),2));
    }









    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    void addB(double x, double y, String name) {
        insert(name ,x, y);
//        str = (x + "," + y);
//        types.put(str, "Bank");
//        nameOfTheCoordinate.put(str, bankName);
        Node newNode = new Node(name, x, y, 0);
        Point point = new Point(x, y);
        bank_main.put(bankName, point);
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
                while (search(x, y)) {
                    System.out.println("This Coordinate Is Taken, Enter Another One:");
                    x = input.nextDouble();
                    y = input.nextDouble();

        }
    }

    public static void main(String[] args) {

    }
}
