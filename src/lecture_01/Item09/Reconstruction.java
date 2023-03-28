package lecture_01.Item09;

import java.io.*;

public class Reconstruction {

    public static String otherInputString(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine();
        } catch (Exception e){
            return "IOException 발생";
        }
    }
    public static String inputString() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine();
        }
    }

    public static void inputAndWriteString() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(br.readLine());
        }
    }

    public static void main(String[] args) {
        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void check() throws Exception {
        try(IllegalArgumentExceptionThrower thrower = new IllegalArgumentExceptionThrower()) {
            throw new NullPointerException();
        }
    }

    static class IllegalArgumentExceptionThrower implements AutoCloseable {

        @Override
        public void close() throws Exception {
            throw new IllegalArgumentException();
        }
    }
}