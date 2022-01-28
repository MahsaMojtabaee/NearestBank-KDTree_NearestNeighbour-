import java.io.OutputStream;

public class TrieTree {

    Trie TrieRoot;

    TrieTree(Trie R) {
         TrieRoot = R;
    }

    void add(String s, double x, double y) {

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
//        temp = new Node(s,true, x, y,0);
        temp.bank = new Node(s, true, x, y, 0);
        temp.bank.branches = new KDTree();
//        temp.branches = new KDTree();
    }
    void PrintBranchesCoordinates(String MainBankName){
        Trie node = search(MainBankName);
        if(node == null){
            System.out.println("There is no bank with this name.");
            return;
        }
        node.bank.branches.inorder();
    }
    void delete(Trie itr, double x, double y) {

        // Base condition
        if (itr == null)
            return;

        // Loop for printing t a value of character
        for (int i = 0; i < 26; i++) {

            // Recursive call for print pattern
            delete(itr.children[i], x, y);
            if (itr.isEnd && itr.bank.branches != null) {
//                System.out.println("deleBr");
               if(itr.bank.branches.delete(x, y)) {
                   return;
               }
            }
        }
//        return itr;
    }
    void delete(double x, double y) {
//        TrieRoot = delete(TrieRoot, x, y);
        delete(TrieRoot , x, y);
    }
    Node NearestNeighbourBranches(String MainBankName, double X, double Y){
        return search(MainBankName).bank.branches.NearestNeighbour(search(MainBankName).bank.branches.root, X, Y, 0);
    }
    void addToBranches(String s, String name, double X, double Y) {
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
        if(t.bank.branches == null){
            System.out.println("There is no bank with this name.");
            return;
        }
        t.bank.branches.insert(name, false, X, Y);
//        t.branches.insert(name,false, X, Y);
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
            return t.bank.branches;
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
        if(t.bank.branches == null){
//            System.out.println("There is no bank with the name "+s);
            return false;
        }
        return (t.bank.branches.searchByCoordinates(x, y) != null);
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
        if(t.bank.branches == null){
//            System.out.println("There is no bank with the name "+s);
            return false;
        }
        return (t.bank.branches.searchByName(name) != null);
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
    KDTree getBranches(String s){
        if(search(s) == null){
            return null;
        }
        return search(s).bank.branches;
    }


    //////////////////////////////////////////////////////////////////////////////// REGIONS

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
}
