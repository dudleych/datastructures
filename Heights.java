import java.util.Scanner;

public class Heights {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int buildingNum = scanner.nextInt();
        int requestNum = scanner.nextInt();
        scanner.nextLine();

        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < buildingNum; i++) {
            bst.insert(scanner.nextInt());
        }
        scanner.nextLine();

        for (int i = 0; i < requestNum; i++) {
            int requestHeight = scanner.nextInt();
            int nextLargestHeight = bst.findNextLargest(requestHeight);
            System.out.println(requestHeight + " " + nextLargestHeight);
        }
        scanner.close();
    }

    private static class Node {
        public Node left, right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private static class BinarySearchTree {
        private Node root;

        public void insert(int value) {
            root = privInsert(this.root, value);
        }

        private Node privInsert(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }
            if (value < node.value) {
                node.left = privInsert(node.left, value);
            } else if (value > node.value) {
                node.right = privInsert(node.right, value);
            }
            return node;
        }

        public int findNextLargest(int target) {
            Node tracker = root;
            int nextLargest = 0; // Default to 0 if no smaller value exists
            while (tracker != null) {
                if (tracker.value < target) {
                    nextLargest = tracker.value; // Update to the closest smaller value
                    tracker = tracker.right; // Move right to look for closer values
                } else {
                    tracker = tracker.left; // Move left if tracker.value >= target
                }
            }
            return nextLargest;
        }
    }
}