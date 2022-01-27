public class TrieTree {

    Trie TrieRoot;

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
    void addRec(String s, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
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
        temp.reg = new Region(s, x1,y1,x2,y2,x3,y3,x4,y4);
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

    void printRegionalBanks(String s){
        Trie node = search(s);
        if(node == null){
            System.out.println("There is no bank with this name.");
            return;
        }
        System.out.println(node.reg.name);
        node.reg.banks.inorder();
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
        if(AlreadyExistsByCoordinates(s , X, Y)){
            System.out.println("These coordinates was taken before");
            return;
        }
        if(AlreadyExistsByName(s, name)){
            System.out.println("This name is already taken");
            return;
        }
        t.branches.insert(name,false, X, Y);
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
    boolean AlreadyExistsByCoordinates(String s, double x, double y){
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
        return (t.branches.search(x, y) != null);
    }
    boolean AlreadyExistsByName(String s, String name){
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
        return (t.branches.searchByName(name) != null);
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
    void addBankToRegion(Trie itr, double x, double y, String bankName, boolean isMain) {
        // Base condition
        if (itr == null)
            return;
        // Loop for printing t a value of character
        for (int i = 0; i < 26; i++) {

            if(itr.reg != null && itr.reg.isInRegion(x, y)){

                itr.reg.banks.insert(bankName, isMain, x, y);
            }
            // Recursive call for print pattern
            if (itr.children[i] != null) {
                addBankToRegion(itr.children[i], x, y, bankName, isMain);
            }
        }
    }
    void addBankToRegion(double x, double y, String name, boolean isMain){
        addBankToRegion(TrieRoot, x, y, name, isMain);
    }
    KDTree getBranches(String s){
        if(search(s) == null){
            return null;
        }
        return search(s).branches;
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
