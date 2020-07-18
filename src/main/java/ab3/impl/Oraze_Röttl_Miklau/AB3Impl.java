package ab3.impl.Oraze_Röttl_Miklau;

import ab3.AB3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AB3Impl implements AB3 {
    static int NO_OF_CHAR = 400;



    @Override
    public SearchInfoKMP findPatternKMP(String text, String pattern) {
        int indexText = 0;
        int indexPattern = 0;
        int[] rand = randTable(pattern);

        LinkedList<Integer> positionsOut = new LinkedList<>();
        LinkedList<Integer> jumpsOut = new LinkedList<>();

        for (int k = 0; k < rand.length; k++) {
            System.out.print(rand[k]);
        }

        System.out.println();

        while (indexText < text.length()) {
            if (text.charAt(indexText) == pattern.charAt(indexPattern)) {
                indexText++;
                indexPattern++;
            }

            if (indexPattern == pattern.length()) {
                int startIndex = indexText - pattern.length();
                System.out.println("Pattern found at index " + startIndex);
                positionsOut.push(startIndex);

                indexPattern = rand[indexPattern - 1];
                jumpsOut.push(indexPattern);
                System.out.println(indexPattern);

            } else if (indexText < text.length() && pattern.charAt(indexPattern) != text.charAt(indexText)) {
                if (indexPattern != 0) {
                    indexPattern = rand[indexPattern - 1];
                    jumpsOut.push(indexPattern);
                } else {
                    indexText++;
                }
            }
        }

        SearchInfoKMP out = new SearchInfoKMP(positionsOut, jumpsOut);

        return out;
    }


    private int[] randTable(String pattern) {
        int[] randTable = new int[pattern.length()];
        randTable[0] = 0;

        int j = 0;

        for (int i = 1; i < pattern.length(); ) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                randTable[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = randTable[j - 1];
                } else {
                    randTable[i] = 0;
                    i++;
                }
            }
        }
        return randTable;


    }

    @Override
    public List<Integer> findPatternEndlAutomat(String text, String pattern) {
        char[] pat = pattern.toCharArray();
        char[] txt = text.toCharArray();

        return search(pat, txt);
    }

    public List<Integer> search(char pat[], char txt[]) {
        int M = pat.length;
        int N = txt.length;

        int TF[][] = new int[M + 1][NO_OF_CHAR];
        List<Integer> lsg = new ArrayList();
        computeTransFunction(pat, M, TF);

        int j = 0;
        for (int i = 0; i < N; i++) {
            j = TF[j][txt[i]];
            if (j == M) {
                lsg.add((i - M + 1));
            }
        }
        return lsg;
    }

    public static void computeTransFunction(char[] pat, int M, int TF[][]) {
        int i, lps = 0, x;

        // Fill entries in first row
        for (x = 0; x < NO_OF_CHAR; x++) {
            TF[0][x] = 0;
        }
        TF[0][pat[0]] = 1;

        // Fill entries in other rows
        for (i = 1; i < M; i++) {
            // Copy values from row at index lps
            for (x = 0; x < NO_OF_CHAR; x++) {
                TF[i][x] = TF[lps][x];
            }

            // Update the entry corresponding to this character
            TF[i][pat[i]] = i + 1;

            // Update lps for next row to be filled
            if (i < M) {
                lps = TF[lps][pat[i]];
            }
        }
    }

    @Override
    public Set<SymbolCode> huffmanCoding(List<Character> symbols, List<Integer> frequency) {
        //sort symbols according to frequency
        sortFrequency(symbols, frequency);

        MinHeap tree = new MinHeap(frequency.size());

        //add nodes to tree
        for (int i = 0; i < symbols.size(); i++) {
            tree.add(new HuffmanNode(frequency.get(i), symbols.get(i)));
        }

        //call which generates the huffman tree
        tree.generateCodeTree();

        //generate code set with the given huffman tree
        Set<SymbolCode> codeSet = getCodeFromTree(tree);

        return codeSet;
    }

    /**
     * function which computes a set containing SymbolCode objects
     *
     * @param tree {@link MinHeap} object that already should have been transformed into a huffman tree
     * @return set of SymbolCode objects which represent one char and its binary code each
     */
    public Set<SymbolCode> getCodeFromTree(MinHeap tree) {
        Set<SymbolCode> codeSet = new HashSet<>();
        try {
            generateCodeSet(codeSet, tree.getNode(0), "");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return codeSet;
    }

    /**
     * recursive function which fills the set with objects
     *
     * @param codeSet set which should be filled with SymbolCode objects
     * @param node    node for each recursive call (note: first call with root node from tree)
     * @param code    String which represents the binary code for a single char (initial call empty -> "")
     * @throws IllegalAccessException
     */
    public void generateCodeSet(Set codeSet, HuffmanNode node, String code) throws IllegalAccessException {
        // if node is leaf node then exit recursive function
        if (node.right == null && node.left == null) {
            codeSet.add(new SymbolCode(node.symbol, code));
            return;
        }
        //recursive function calls for left and right child node
        generateCodeSet(codeSet, node.left, code + "0");
        generateCodeSet(codeSet, node.right, code + "1");
    }


    /**
     * function which sorts the lists according to the frequency with shellSort algorithm
     *
     * @param symbols   list of char symbols
     * @param frequency integer list of frequencies of each symbol
     */
    public void sortFrequency(List<Character> symbols, List<Integer> frequency) {

        //dividing our length each time by 2 will decrease the gap distance every iteration and eventually end in a gap of only 1
        for (int gap = frequency.size() / 2; gap > 0; gap /= 2) {

            //iterate over the elements on the right half of the gap
            for (int right = gap; right < frequency.size(); right++) {

                //to store the current value of the element which will be later compared and
                //the index of the current gap value
                int compare = frequency.get(right);
                int temp = right;
                char compareChar = symbols.get(right);

                while (temp >= gap && frequency.get(temp - gap) > compare) {

                    //while is only executed if the two elements separated by the gap are in the wrong order
                    //then it switches the lower one to the right place
                    frequency.set(temp, frequency.get(temp - gap));
                    symbols.set(temp, symbols.get(temp - gap));

                    //reducing temp by the gap amount ensures that while loop will break after temp gets lower than gap
                    temp -= gap;
                }

                //now the lesser number has two switch to the left
                frequency.set(temp, compare);
                symbols.set(temp, compareChar);
            }
        }
    }

    /**
     * data type that can be used to traverse tree generated by huffman algorithm to determine code
     */
    public class HuffmanNode {

        public int frequency;
        public char symbol;

        public HuffmanNode left;
        public HuffmanNode right;

        public HuffmanNode(int frequency, char symbol) {
            this.frequency = frequency;
            this.symbol = symbol;
        }

        public HuffmanNode(int frequency) {
            this.frequency = frequency;
        }
    }

    /**
     * data structure which holds nodes of type {@link HuffmanNode}
     * modified min heap for huffman algorithm which can generate a binary tree
     */
    public class MinHeap {

        private HuffmanNode[] heap;
        private int size;
        private int pointer;

        public MinHeap(int size) {
            this.size = size;
            heap = new HuffmanNode[size];
            pointer = 0;
        }

        public void add(HuffmanNode node) {
            if (pointer >= size) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                heap[pointer++] = node;
            }
        }

        public void generateCodeTree() {
            while (size > 1) {
                createInternalNode();
            }
        }

        public void createInternalNode() {
            int frequencyNew = heap[0].frequency + heap[1].frequency;
            HuffmanNode node = new HuffmanNode(frequencyNew);
            node.left = heap[0];
            node.right = heap[1];
            heap[0] = node;
            for (int i = 1; i < size - 1; i++) {
                heap[i] = heap[i + 1];
            }
            size--;
            heap[size] = null;
            //restore heap
            makeHeap();
        }

        public void makeHeap() {
            int temp = heap[0].frequency;
            for (int i = 0; i < size - 1; i++) {
                if (temp > heap[i + 1].frequency) {
                    swap(i, i + 1);
                } else {
                    break;
                }
            }
        }

        private void swap(int fPos, int sPos) {
            HuffmanNode tmp;
            tmp = heap[fPos];
            heap[fPos] = heap[sPos];
            heap[sPos] = tmp;
        }

        public HuffmanNode getNode(int pos) throws IllegalAccessException {
            if (pos >= size) throw new IllegalAccessException();
            else return heap[pos];
        }

        public int getSize() {
            return this.size;
        }
    }

}
