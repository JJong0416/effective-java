package lecture04.Item24.gc_test;

import java.io.IOException;

public class StaticInnerMain {
    public static void main(String[] args) throws IOException {
        Outer.StaticInner staticInner = getStaticInner();

        System.gc();
        System.out.println("GC 동작 완료");

        System.in.read(); // VisualVM HeapDump 시점

        System.out.println(staticInner);
    }

    private static Outer.StaticInner getStaticInner() {
        return new Outer.StaticInner();
    }
}
