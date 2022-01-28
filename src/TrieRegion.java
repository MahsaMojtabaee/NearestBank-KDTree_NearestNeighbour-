//public class TrieRegion {
//    Trie TrieRoot;
//
//    TrieRegion(Trie R) {
//        TrieRoot = R;
//    }
//
//    Trie searchRegion(String s) {
//
//        int l = s.length();
//        int index;
//        Trie temp = TrieRoot;
//        for (int i = 0; i < l; i++) {
//            index = s.charAt(i) - 'a';
//            if (temp.children[index] == null) {
//                return null;
//            }
//            temp = temp.children[index];
//        }
//        if (temp != null && temp.isEnd) {
//            return temp;
//        }
//        return null;
//    }
//}
