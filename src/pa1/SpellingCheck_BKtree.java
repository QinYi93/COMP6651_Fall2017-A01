package pa1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a simple spelling corrector algorithms (for linear search and BK-trees)
 *
 * @author Yi Qin
 * @email yi93qin@gmail.com
 * @date 2017-10-02
 */
public class SpellingCheck_BKtree {
    private static String[] dictionary = new String[400000];
    private static String[] sentence = new String[50];
    //Max distance
    private static int K;
    static FileReader dicReader;
    static FileReader senReader;
    static FileReader distanceReader;
    static HashMap<String, ArrayList<String>> resultMap = new HashMap<>();
    static Node root;

    public static void main(String[] args) {
        System.out.println("BK-tree");
        long startTime = System.currentTimeMillis();
        dicReader = new FileReader();
        senReader = new FileReader();
        distanceReader = new FileReader();
        File dic = new File("vocab.txt");
        File sen = new File("sentence.txt");
        File dis = new File("MaxDistance.txt");
        dicReader.setFile(dic);
        senReader.setFile(sen);
        distanceReader.setFile(dis);
        // initial count num of word in dictionary
        int numOfWord = 0;
        //save the content of dictionary in the array named dictionary[]
        while (dicReader.hasNextLine()) {
            dictionary[numOfWord++] = dicReader.nextLine().trim();
        }
        System.out.println("Num of Word in dictionary :" + numOfWord);
        // initial count num of word in sentence
        int numOfSenten;
        String[] input = senReader.nextLine().trim().split(" +");
        for (numOfSenten = 0; numOfSenten < input.length && input[numOfSenten] != null; numOfSenten++) {
            sentence[numOfSenten] = input[numOfSenten];
        }
        System.out.println("Num of sentence:" + numOfSenten);
        //K is maximinal LD distance
        K = Integer.parseInt(distanceReader.nextLine().trim());
        System.out.println("Num of LD distance: " + K);
        int i = 0;
        bkTree(dictionary);
        while (sentence[i] != null) {
            boolean flag = false;
            ArrayList<String> possibleWord = new ArrayList<>();
            //BK-tree method
            bkVisit(sentence[i], root, K, possibleWord);
            for (String s : possibleWord) {
                if (s.equals(sentence[i])) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                resultMap.put(sentence[i], possibleWord);
            }
            i++;
        }
        printResult(resultMap);
        long endTime = System.currentTimeMillis();
        System.out.println("during time:" + (endTime - startTime)/1000 + "s");
    }


    public static void linearSearch(String word, String[] dic, int min) {
        ArrayList<String> wordList = new ArrayList<>();
        wordList.clear();
        boolean flag = false;
        for (String s : dic) {
            if (s != null) {
                int n = ld(word, s);
                if (n == 0) {
                    flag = true;
                }
                if (n <= min && n != 0) {
                    wordList.add(s);
                }
            }
        }

        if (!flag) {
            resultMap.put(word, wordList);
        }
    }

    public static void bkTree(String[] dictionary) {
        int i = 1;
        root = new Node(dictionary[0], 0);
        root.setParent(null);
        while (dictionary[i] != null) {
            int d = ld(dictionary[0], dictionary[i]);
            Node node = new Node(dictionary[i++], d);
            buildTree(root, node);
        }
    }

    public static void bkVisit(String q, Node node, int r, ArrayList<String> result) {
        int d = ld(q, node.getName());
        if (d <= r) {
            result.add(node.getName());
        }
        for (Map.Entry<Integer, Node> integerNodeEntry : node.getChilds().entrySet()) {
            if (integerNodeEntry.getKey() <= d + r
                    && integerNodeEntry.getKey() >= d - r) {
                bkVisit(q, integerNodeEntry.getValue(), r, result);
            }
        }
    }

    public static void buildTree(Node root, Node node) {
        int d = node.getdistanceOfParent();
        if (!root.hasChild(d)) {
            root.setChilds(d, node);
            node.setParent(root);
            return;
        } else {
            int k = ld(root.getChilds(d).getName(), node.getName());
            node.setdistanceOfParent(k);
            buildTree(root.getChilds(d), node);
        }
    }


    // get minimal distance of adj neighborhood of current point
    private static int min(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    public static int ld(String str1, String str2) {
        int d[][];
        int n = str1.length();
        int m = str2.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = str1.charAt(i - 1);

            for (j = 1; j <= m; j++) {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    public static void printResult(HashMap<String, ArrayList<String>> resultMap) {
        if (resultMap.isEmpty()){
            System.out.println("0");
        }else {
        System.out.println("Spelling:");
        if (resultMap != null) {
            File file = new File("output.txt");
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ArrayList<String>> result : resultMap.entrySet()) {
                sb.append(result.getKey() + " : ");
                for (String output : resultMap.get(result.getKey())) {
                    sb.append(output + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            fw.write(sb.toString(), file);

        } else System.out.println("All words in sentence is right");
        }
    }

}
