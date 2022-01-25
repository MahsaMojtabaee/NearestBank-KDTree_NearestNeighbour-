public class Test {
    public static void main(String[] args) {
        KDTree kdt = new KDTree();
        Trie R = new Trie();
        TrieTree banks = new TrieTree(R);

        //banks.initRoot(R);
        kdt.addB("a", 2, 2);
        banks.add("a");
        banks.addToBranches("a", "A1", 1, 2);
        banks.addToBranches("a", "A2", 1, 3);
        banks.PrintBranchesCoordinates("a");
        System.out.println("Nearest is "+banks.NearestNeighbourBranches("a", 1,1).name);


//        banks.delete("a");





        System.out.println(banks.listBranches("a").root.name);
        System.out.println(kdt.root.name);
        kdt.addB("b", 1, 2);
        System.out.println(kdt.root.left.name);
//        t.banks.addToBranches("a", "A.1", 1, 6);
//        t.banks.addToBranches("a", "A.2", 1, 5);
        kdt.addB("c", 0, 1);
        System.out.println(kdt.root.left.left.name);
        kdt.inorder();

    }
}
