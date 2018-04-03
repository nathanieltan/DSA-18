import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>();
        Collections.sort(values);
        addToTree(myTree, values, 0, values.size());
        return myTree;
    }

    private static void addToTree(BinarySearchTree<Integer> tree, List<Integer> values, int low, int hi){
        if(low >= hi){
            return;
        }

        int mid = (low + hi)/2;
        tree.add(values.get(mid));
        addToTree(tree, values, low, mid);
        addToTree(tree, values, mid+1, hi);
    }
    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
