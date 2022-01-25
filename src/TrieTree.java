public class TrieTree {

    static Trie TrieRoot;

    TrieTree(Trie R) {
         TrieRoot = R;
    }

    void add(String s) {

        int l = s.length();
        Trie temp = TrieRoot;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new Trie();
            }
            temp = temp.children[index];
        }
        temp.isEnd = true;
        temp.branches = new KDTree();
    }

    Trie search(String s) {

        int l = s.length();
        int index;
        Trie temp = TrieRoot;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (temp.children[index] == null) {
                return null;
            }
            temp = temp.children[index];
        }
        if (temp != null && temp.isEnd) {
            return temp;
        }
        return null;
    }

    boolean delete(String s) {
        int l = s.length();
        int index;
        Trie t = TrieRoot;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.children[index] == null) {
                return false;
            }
            t = t.children[index];
        }
        if (t != null && t.isEnd) {
            t.isEnd = false;
            return true;
        }
        return false;
    }

    public void PrintBranchesCoordinates(String MainBankName){
        Trie node = search(MainBankName);
        if(node == null){
            System.out.println("There is no bank with this name.");
            return;
        }
        node.branches.inorder();
    }

    Node NearestNeighbourBranches(String MainBankName, double X, double Y){
        return search(MainBankName).branches.NearestNeighbour(search(MainBankName).branches.root, X, Y, 0);
    }
    public void addToBranches(String s, String name, double X, double Y) {
        int l = s.length();
        Trie t = TrieRoot;
        int index;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.children[index] == null) {
                t.children[index] = new Trie();
            }
            t = t.children[index];
        }
        t.branches.addB(name, X, Y);
//        t.branches.push(name);
    }
    KDTree listBranches(String s) {

        int l = s.length();
        int index;
        Trie t = TrieRoot;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.children[index] == null) {
                return null;
            }
            t = t.children[index];
        }
        if (t != null && t.isEnd) {
            return t.branches;
        }
        return null;
    }
    boolean ismain(String s) {

        int l = s.length();
        int index;
        Trie t = TrieRoot;
        for (int i = 0; i < l; i++) {
            index = s.charAt(i) - 'a';
            if (t.children[index] == null) {
                return false;
            }
            t = t.children[index];
        }
        if (t != null && t.isEnd && t.isB) {
            return true;
        }
        return false;
    }

//    boolean removeFromBranches(String[] ss) {
//        String s = ss[1];
//        int l = s.length();
//        Trie t = root;
//        int index;
//        for (int i = 0; i < l; i++) {
//            index = s.charAt(i) - 'a';
//            if (t.children[index] == null) {
//                t.children[index] = new Trie();
//            }
//            t = t.children[index];
//        }
//        MyStack<String> temp = new MyStack<>();
//        while (){
//            if (t.branches.peek().equals(ss[0])) {
//                t.branches.pop();
//            } else {
//                temp.push(t.branches.pop());
//            }
//        }
//        while (!temp.isEmpty()) {
//            t.branches.push(temp.pop());
//        }
//        return true;
//    }
}
