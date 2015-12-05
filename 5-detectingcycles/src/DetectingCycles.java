import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DetectingCycles {

    static class Node {
        Node next;
        String val;

        Node(String val) {
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException
    {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        boolean firstLine = true;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] numSeq = line.split(" ");

            /*
             * Floyd's cycle-detection algorithm (a.k.a. tortoise and hare).
             */
            Map<String, Node> nodeMap = new HashMap<>();
            Node startNode = new Node(numSeq[0]);
            Node prevNode = startNode;
            nodeMap.put(numSeq[0], startNode);

            for (int index = 1; index < numSeq.length; index++) {
                Node node = nodeMap.get(numSeq[index]);

                if (node == null) {
                    node = new Node(numSeq[index]);
                    nodeMap.put(numSeq[index], node);
                }

                prevNode.next = node;
                prevNode = node;
            }

            Node slowRef = startNode;
            Node fastRef = startNode;

            while (fastRef != null) {
                fastRef = fastRef.next;

                if (fastRef != null) {
                    fastRef = fastRef.next;
                    slowRef = slowRef.next;
                }

                if (fastRef == slowRef)
                    break;
            }

            if (fastRef != slowRef) {
                System.out.println();
                continue;
            }

            slowRef = startNode;

            while (slowRef != fastRef) {
                slowRef = slowRef.next;
                fastRef = fastRef.next;
            }

            if (!firstLine)
                System.out.println();

            firstLine = false;

            boolean firstNum = true;

            do {
                if (!firstNum)
                    System.out.print(" ");
                firstNum = false;

                System.out.print(slowRef.val);
                slowRef = slowRef.next;
            } while (slowRef != fastRef);
        }

        // required for CodeEval
        System.exit(0);
    }
}
