package comp2402a5;



public class Tester {
    public static void main(String[] args) {
        AVLTree<Integer> T = new AVLTree<Integer>();
        T.add(0);
        T.add(2);
        T.add(4);
        T.add(6);
        T.add(3);
        T.add(4);

        //System.out.println("height = " + T.height());
    }
}
