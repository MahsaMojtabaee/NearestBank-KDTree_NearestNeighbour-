public class TrieTree {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // trie node
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;
        String branches;
        KDTree kdBranches;
        TrieNode(){
            isEndOfWord = false;
//            branches = "";
//            kdBranches = new KDTree();
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    static class NeighbourNode extends Trie.TrieNode{
        double X_min;
        double X_max;
        double Y_min;
        double Y_max;
        NeighbourNode [] children;
        NeighbourNode () {
            this.Y_min = Double.parseDouble(null);
            this.X_min = Double.parseDouble(null);
            this.Y_max = Double.parseDouble(null);
            this.X_max = Double.parseDouble(null);
        }
        NeighbourNode (double X_min, double Y_min, double X_max, double Y_max){
            this.X_max = X_max;
            this.Y_max = Y_max;
            this.X_min = X_min;
            this.Y_min = Y_min;
        }
    }

    static TrieNode root;
    static NeighbourNode Root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    static void insert(String key)
    {
        root = new TrieNode();
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
        pCrawl.branches = "";
        pCrawl.kdBranches = new KDTree();
    }

//    static void insertNeighbour(String name, double X_min, double Y_min, double X_max, double Y_max){
//        int level;
//        int length = name.length();
//        int index;
//
//        NeighbourNode pCrawl = Root;
//
//        for (level = 0; level < length; level++)
//        {
//            index = name.charAt(level) - 'a';
//            if (pCrawl.children[index] == null)
//                pCrawl.children[index] = new NeighbourNode(X_min, Y_min, X_max, Y_max);
//
//            pCrawl = pCrawl.children[index];
//        }
//
//        // mark last node as leaf
//        pCrawl.isEndOfWord = true;
//        pCrawl.branches = "";
//    }

    // Returns true if key presents in trie, else false
    static TrieNode search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null) {
                return null;
            }
            pCrawl = pCrawl.children[index];
        }

        if(pCrawl.isEndOfWord){
            return pCrawl;
        }
        return null;
    }

    static boolean addBranches(String MainBank, String branchName, double X, double Y){
        int level;
        int length = MainBank.length();
        int index;
        TrieNode pCrawl = root;
        if(pCrawl == null){
            System.out.println("There is no such main bank");
            return false;
        }
        for (level = 0; level < length; level++)
        {
            index =  MainBank.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        if(pCrawl.isEndOfWord) {
            pCrawl.branches += "(" + X + "," + Y + ")";
            pCrawl.kdBranches.insert(branchName,X,Y);
            return true;
        }
        return false;
    }

}

