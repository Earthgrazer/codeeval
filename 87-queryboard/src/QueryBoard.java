import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QueryBoard {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        byte[][] board = new byte[256][256];

        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            String[] params = line.split(" ");
            int col;
            int row;
            byte val;
            int sum;

            switch (params[0]) {
                case "SetCol":
                    col = Integer.parseInt(params[1]);
                    val = Byte.parseByte(params[2]);

                    for (row = 0; row < board[col].length; row++) {
                        board[col][row] = val;
                    }

                    break;
                case "SetRow":
                    row = Integer.parseInt(params[1]);
                    val = Byte.parseByte(params[2]);

                    for (col = 0; col < board.length; col++) {
                        board[col][row] = val;
                    }

                    break;
                case "QueryCol":
                    col = Integer.parseInt(params[1]);
                    sum = 0;

                    for (row = 0; row < board[col].length; row++) {
                        sum += board[col][row];
                    }

                    System.out.println(sum);

                    break;
                case "QueryRow":
                    row = Integer.parseInt(params[1]);
                    sum = 0;

                    for (col = 0; col < board.length; col++) {
                        sum += board[col][row];
                    }

                    System.out.println(sum);

                    break;
            }
        }
    }
}
