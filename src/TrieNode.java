
public class TrieNode{
    static final int ALPHABET_SIZE = 26;
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];


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
}