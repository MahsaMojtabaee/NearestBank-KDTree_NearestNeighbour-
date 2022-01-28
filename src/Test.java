import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.HashMap;
import java.util.Map;

public class Test {

    // A KDTree to store all the banks including main banks and branches
    static KDTree banks = new KDTree();
    // A TrieTree to store banks and their branches
    static TrieTree bBanks = new TrieTree(new Trie());
    // A TrieTree to store the regions
    static TrieTree regions = new TrieTree(new Trie());
    int Max;
    Node BankWithMost;

    static void addN(String name, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        regions.addRec(name, x1, y1, x2, y2, x3, y3, x4, y4);
    }

    static void addB(String name, double x, double y){
        banks.insert(name,true, x, y);
        bBanks.add(name, x, y);
//        regions.addBankToRegion(x, y, name, true);
    }

    static void addBr(String mainBankName, String branchName, double x, double y){
        banks.insert(branchName,false, x, y);
        banks.searchByName(mainBankName).branches.insert(branchName, false, x, y);
//        banks.searchByName(mainBankName)..insert(branchName, false, x, y);
        bBanks.addToBranches(mainBankName, branchName, x, y);
//        regions.addBankToRegion(x, y, branchName, false);
    }

    static void nearB(double x, double y){
        Node bank = banks.NearestNeighbour(x, y);
        if(bank == null){
            System.out.println("Banks is empty");
            return;
        }
        System.out.println("name : "+ bank.name);
        System.out.println("coordinates : ("+bank.x+", "+bank.y+")");
        KDTree branches = bBanks.getBranches(bank.name);
        if(branches == null){
            System.out.println("This bank has no branch.");
        }
        else {
            System.out.println("branches are ");
            branches.inorder();
        }
    }

    static void nearBr(String name, double x, double y){
        Node bank = bBanks.NearestNeighbourBranches(name, x, y);
        if(bank == null){
            System.out.println("banks is empty");
            return;
        }
//        System.out.println("name : "+bank.name);
        System.out.println("coordinates : ("+bank.x+", "+bank.y+")");
    }

    static void listBrs(String name){
        bBanks.PrintBranchesCoordinates(name);
        System.out.println();
    }

    static void listB(String name){
//        regions.printRegionalBanks(name);
        System.out.println("Banks in region "+name+" are:");
        banks.printNodesInArea(regions.search(name).reg);
    }

    static void delBr(double x, double y){
        banks.delete(x, y);
        bBanks.delete(x, y);
//        bBanks.deleteBranchRegion(x, y);
    }

    static void availB(double R, double x, double y){
        System.out.println("Banks in the circular area with center ("+x+", "+y+") and radius "+R+" are:");
        banks.printNodesInCircularArea(new Region("", x-R, y-R, x+R,y+R, x-R, y+R, x+R, y-R), x, y, R);
    }

    static void mostBrs(){
        banks.FindMaxBranches();
    }

    void PrintMenu(){
        System.out.println("Welcome");
        System.out.println("Here are your options. print the number of option to start the command.");
        System.out.println("1. add a region");
        System.out.println("2. add main bank");
        System.out.println("3. add branches to a bank");
        System.out.println("4. delete a bank branch");
        System.out.println("5. The names of banks in a region");
        System.out.println("6. Coordinates of all branches of a bank");
        System.out.println("7. Nearest bank");
        System.out.println("8. Nearest branch of a bank");
        System.out.println("9. Available banks in a range");

    }
    public static void main(String[] args) {
        addN("state", -1,-1,-1,3, 1, -1, 1, 3);
        addB("a", 0, 1);
        addBr("a", "A1", -1, 1);
        addBr("a", "A2", 0, 3);
        System.out.println();
        banks.inorder();
        System.out.println();
        listB("state");
        delBr(-1,1);
        listB("state");
        availB(1,0, 0);


    }
}
