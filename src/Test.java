import java.util.Scanner;

public class Test {

    // A KDTree to store all the banks including main banks and branches
    static KDTree banks = new KDTree();
    // A TrieTree to store banks and their branches
    static TrieTree bBanks = new TrieTree(new Trie());
    // A TrieTree to store the regions
    static TrieTree regions = new TrieTree(new Trie());


    static void addN(String name, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        regions.addRec(name, x1, y1, x2, y2, x3, y3, x4, y4);
    }

    static void addB(String name, double x, double y){
        banks.insert(name,true, x, y);
        bBanks.add(name, x, y);
    }

    static void addBr(String mainBankName, String branchName, double x, double y){
//        Node node =  banks.searchByName(mainBankName);
        banks.insert(branchName,false, x, y);
        bBanks.addToBranches(mainBankName, branchName, x, y);
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
        if(bBanks.search(name).bank.branches == null ){
            System.out.println("There is no bank with this name.");
            return;
        }
        bBanks.PrintBranchesCoordinates(name);
        System.out.println();
    }

    static void listB(String name){
        if (regions.search(name) == null){
            System.out.println("There is no neighbour with this name.");
            return;
        }
        Region r = regions.search(name).reg;
        if(r == null){
            System.out.println("There is no neighbour with this name.");
            return;
        }
        System.out.println("Banks in region "+name+" are:");
        banks.printNodesInArea(r);
    }

    static void delBr(double x, double y){
        if(!banks.delete(x, y)){
            return;
        }
        else {
            System.out.println("It is deleted.");
        }
        bBanks.delete(x, y);
//        bBanks.deleteBranchRegion(x, y);
    }

    static void availB(double R, double x, double y){
        banks.checkIfBanksInArea = false;
        banks.printNodesInCircularArea(new Region("", x-R, y-R, x+R,y+R, x-R, y+R, x+R, y-R), x, y, R);
        if(!banks.checkIfBanksInArea){
            System.out.println("There is no bank in this area.");
        }

    }

//    static void mostBrs(){
//        banks.FindMaxBranches();
//    }

    static void PrintMenu(){
        System.out.println("Welcome");
        System.out.println("Here are your options. print the number of option to start the command.");
        System.out.println("0. add a region");
        System.out.println("1. add main bank");
        System.out.println("2. add branches to a bank");
        System.out.println("3. delete a bank branch");
        System.out.println("4. The names of banks in a region");
        System.out.println("5. Coordinates of all branches of a bank");
        System.out.println("6. Nearest bank");
        System.out.println("7. Nearest branch of a bank");
        System.out.println("8. Available banks in a range");
        System.out.println("9.Exit");

    }
    public static void main(String[] args) {
        String p = "[a-z]+";
        String p2 = "[0-9]";
        Scanner scr = new Scanner(System.in);
        while (true) {
            PrintMenu();
            System.out.println("Input:");
            String command = scr.next();
            if(!command.matches(p2)){
                System.out.println("Invalid input.Only numbers from 1 to 10");
                continue;
            }
            String pattern = "\\D";
            if (command.contains(pattern)) {
                System.out.println("You should enter a number not anything else.");
            } else {
                switch (Integer.parseInt(command)) {
                    case 0: {
                        System.out.println("To add a new neighbour please enter the essential parameters:");
                        System.out.print("name (A valid name only contains of lower case alphabets): ");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
//                    System.out.println();
                        System.out.print("x1:");
                        double x1 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("y1:");
                        double y1 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("x2:");
                        double x2 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("y2:");
                        double y2 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("x3:");
                        double x3 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("y3:");
                        double y3 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("x4:");
                        double x4 = scr.nextDouble();
//                    System.out.println();
                        System.out.print("y4:");
                        double y4 = scr.nextDouble();
//                    System.out.println();
                        addN(name, x1, y1, x2, y2, x3, y3, x4, y4);
                        break;
                    }
                    case 1: {
                        System.out.println("To add a new bank please enter the essential parameters:");
                        System.out.print("name of the bank:");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        addB(name, x, y);
                        break;
                    }
                    case 2: {
                        System.out.println("To add a branch please enter the essential parameters:");
                        System.out.print("name of the main bank:");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
                        Trie node =  bBanks.search(name);
                        if(node == null){
                            System.out.println("There is no bank with this name.");
                            break;
                        }
                        System.out.print("name of the branch:");
                        String Bname = scr.next();
                        if(!Bname.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
//                        System.out.println();
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        addBr(name, Bname, x, y);

                        break;
                    }
                    case 3: {
                        System.out.println("TO delete a branch please enter the essential parameters:");
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        delBr(x, y);
                        break;
                    }
                    case 4: {
                        System.out.println("To see the list of all banks in a neighbour please enter the essential parameters:: ");
                        System.out.print("name of the neighbour:");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
//                        System.out.println();
                        listB(name);
                        break;
                    }
                    case 5: {
                        System.out.println("To see the coordinates of a main bank branches please enter the essential parameters:");
                        System.out.print("name of the main bank:");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
                        listBrs(name);
//                        System.out.println();
                        break;
                    }
                    case 6: {
                        System.out.println("To see which bank is the nearest one please enter the essential parameters:");
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        nearB(x, y);
                        break;
                    }
                    case 7: {
                        System.out.println("To see which branch of bank is the nearest one please enter the essential parameters:");
                        System.out.print("name of the bank:");
                        String name = scr.next();
                        if(!name.matches(p)){
                            System.out.println("Invalid Input");
                            break;
                        }
                        System.out.println();
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        nearBr(name, x, y);
                        break;
                    }
                    case 8: {
                        System.out.println("To see the all the banks in a particular area please enter the essential parameters:");
                        System.out.println("coordinates:");
                        System.out.print("x : ");
                        double x = scr.nextDouble();
                        System.out.print("y : ");
                        double y = scr.nextDouble();
//                        System.out.println();
                        System.out.print("radius : ");
                        double R = scr.nextDouble();
                        availB(R, x, y);
                        break;
                    }
                    case 9:{
                        return;
                    }
                    default: {
                        System.out.println("Invalid Input.Try again.");
                        break;
                    }
                }
            }
        }
    }
}
