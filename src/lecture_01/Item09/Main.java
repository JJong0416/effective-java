package lecture_01.Item09;

import java.io.*;

public class Main {

    public static String inputString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    public static void inputAndWriteString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            try {
                String line = br.readLine();
                bw.write(line);
            } finally {
                bw.close();
            }
        } finally {
            br.close();
        }
    }
}
