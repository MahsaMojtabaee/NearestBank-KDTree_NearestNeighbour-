public class Trie {

    private static final int ALPHA = 26;
    boolean isEnd;
    boolean isB = false;
    public RectangleNode coo;
//    MyStack<String> branches;
    KDTree branches;
    Region reg;
    Trie[] children = new Trie[ALPHA];


    public Trie() {
        isEnd = false;
        for (int i = 0; i < ALPHA; i++) {
            children[i] = null;
        }
    }
}