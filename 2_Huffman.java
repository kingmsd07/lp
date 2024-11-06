import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

// Node class for Huffman Tree

// Comparator for the priority queue

// Main Huffman class
public class Huffman {
    static class Node {
        char character;
        int frequency;
        Node left;
        Node right;

        // Constructor
        public Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
    }

    static class FrequencyComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.frequency - n2.frequency;
        }
    }

    // Generate Huffman codes and store them in a map
    public static void generateCodes(Node root, String code, Map<Character, String> huffmanCodeMap) {
        if (root == null)
            return;

        // If this is a leaf node, store the character and its code
        if (root.left == null && root.right == null) {
            huffmanCodeMap.put(root.character, code);
        }

        // Traverse left and right with added "0" and "1" respectively to the code
        generateCodes(root.left, code + "0", huffmanCodeMap);
        generateCodes(root.right, code + "1", huffmanCodeMap);
    }

    // Build Huffman Tree and return the root
    public static Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new FrequencyComparator());

        // Create leaf nodes for each character and add to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree
        while (priorityQueue.size() > 1) {
            // Remove two nodes with the lowest frequency
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            // Create a new internal node with these two nodes as children
            int combinedFrequency = left.frequency + right.frequency;
            Node internalNode = new Node('\0', combinedFrequency);
            internalNode.left = left;
            internalNode.right = right;

            // Add the internal node to the priority queue
            priorityQueue.add(internalNode);
        }

        // Return the root of the Huffman Tree
        return priorityQueue.poll();
    }

    public static void main(String[] args) {
        // Example frequency map for a sample text
        String text = "huffman";
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Calculate frequency of each character in the text
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman Tree
        Node root = buildHuffmanTree(frequencyMap);

        // Generate Huffman codes
        Map<Character, String> huffmanCodeMap = new HashMap<>();
        generateCodes(root, "", huffmanCodeMap);

        // Display the Huffman codes
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode the text using the generated Huffman codes
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodeMap.get(c));
        }
        System.out.println("\nEncoded Text: " + encodedText.toString());
    }
}
