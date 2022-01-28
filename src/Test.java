import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.HashMap;
import java.util.Map;

public class Test {
    static KDTree banks = new KDTree();
    static TrieTree bBanks = new TrieTree(new Trie());
    static TrieTree regions = new TrieTree(new Trie());
    static void addN(String name, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        regions.addRec(name, x1, y1, x2, y2, x3, y3, x4, y4);
    }
    static void addB(String name, double x, double y){
        banks.insert(name,true, x, y);
        bBanks.add(name, x, y);
        regions.addBankToRegion(x, y, name, true);
    }
    static void addBr(String mainBankName, String branchName, double x, double y){
        banks.insertBranch(branchName,false, x, y, mainBankName);
//        banks.searchByName(mainBankName)..insert(branchName, false, x, y);
        bBanks.addToBranches(mainBankName, branchName, x, y);
        regions.addBankToRegion(x, y, branchName, false);
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
    }
    static void listB(String name){
        regions.printRegionalBanks(name);
    }
    static void delBr(double x, double y){
        System.out.println(banks.delete(x, y));
        bBanks.delete(x, y);
    }
    static void mostBrs(){
        banks.FindMaxBranches();
    }
    public static void main(String[] args) {
        addN("state", -1,-1,-1,1, 1, -1, 1, -1);
        addB("a", 0, 1);
        addBr("a", "A1", 0, 2);
        addBr("a", "A2", 0, 3);
        System.out.println();
        banks.inorder();
        System.out.println();
//        listBrs("a");
//        delBr(0, 2);
//        listBrs("a");

//        addB("b", 1, 1);
//        addB("c", 1, -1);/////////////////////////////////// nazdik tarin agar 2 ta bood chi?????
//        nearB(1,0);
//        banks.inorder();
//        System.out.println(banks.searchByName("a") != null);
//        nearBr("a", 0, 5);
//        listBrs("a");
//        System.out.println("Here are the banks of state");
//        listB("state");
//        System.out.println();
//        listBrs("a");
//        delBr(0,6);
//        System.out.println();
//        listBrs("a");
//        System.out.println();
//        mostBrs();


//        regions.put("AAA",new Region( 1, 2,1,2,0,3,0,3));
//        KDTree kdt = new KDTree();
//        Trie R = new Trie();
//        TrieTree banks = new TrieTree(R);
//        Trie X = new Trie();
//        TrieTree Rectangles = new TrieTree(X);
//        //banks.initRoot(R);
//        kdt.addB("a", 2, 2);
//        kdt.addB("A1", 1, 4);
//        kdt.addB("A2", 1, 3);
//
//        banks.add("a");
//        banks.addToBranches("a", "A1", 1, 4);
//        banks.addToBranches("a", "A2", 1, 3);
//        banks.PrintBranchesCoordinates("a");
//        System.out.println("Nearest is "+banks.NearestNeighbourBranches("a", 1,1).name);
//
//
////        banks.delete("a");
//
//
//
//
//
//        System.out.println(banks.listBranches("a").root.name);
//
//        System.out.println(kdt.root.name);
//        kdt.addB("b", 1, 2);
//        System.out.println(kdt.root.left.name);
//        kdt.addB("c", 0, 1);
//        System.out.println(kdt.root.left.left.name);
//
//
//        kdt.inorder();

    }
}
