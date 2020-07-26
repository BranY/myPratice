package src.java.lintcode;

import java.util.*;

public class A {
    private static A instance;

    private A() {}

    public static A getInstance() {
        if (instance == null) {
            instance = new A();
        }
        return instance;
    }




    private static class SingletonHolder {
        private static final A INSTANCE = new A();
    }

    public static final A getInstance1() {
        return SingletonHolder.INSTANCE;
    }

    Queue<Integer> queue = new LinkedList<>();
}
