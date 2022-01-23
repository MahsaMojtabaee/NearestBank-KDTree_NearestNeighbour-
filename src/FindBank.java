public class FindBank {}

class BankNode{
    private String name;
    private int X,Y;
    private int depth = -1;
    private BankNode right;
    private BankNode left;
    private BankNode Banks;
    BankNode(String name, int X, int Y){
        this.name = name;
        this.X = X;
        this.Y = Y;
    }
    void setBanks(BankNode Banks){
        this.Banks = Banks;
    }
    void setRight(BankNode right){
        this.right = right;
    }
    void setLeft(BankNode left){
        this.left = left;
    }
    void addDepth(){
        this.depth += 1;
    }
    String getName(){
        return this.name;
    }
    int getX(){
        return this.X;
    }
    int getY(){
        return this.Y;
    }
    int getDepth(){return this.depth;}
    BankNode getRight(){ return this.right;}
    BankNode getLeft(){ return this.left;}
}
class Bank{
    BankNode root;
    void addBank(String name, int X, int Y){
        BankNode node = new BankNode(name, X, Y);
        if(this.root == null) {
            root = node;
            root.setBanks(null);
            root.setRight(null);
            root.setLeft(null);
            root.addDepth();
        }
        else {
            BankNode temp = this.root;
            while (temp != null){
                if (temp.getX() == node.getX() && temp.getY() == node.getY()){
                    System.out.println("There is already a bank in this coordinates");
                    return;
                }
                if(temp.getDepth()%2 == 0){
                    if (temp.getX() > node.getX()){
                        temp = temp.getLeft();
                    }
                    else {
                        temp = temp.getRight();
                    }
                }
                else {
                    if (temp.getY() > node.getY()){
                        temp = temp.getLeft();
                    }
                    else {
                        temp = temp.getRight();
                    }
                }
            }
            temp = node;
            temp.setRight(null);
            temp.setLeft(null);
            temp.addDepth();
        }
    }


//    int findLocal(){}
}