package com.target.dealbrowserpoc.dealbrowser.utils;

import java.util.Comparator;

/**
 * Created by yashwant on 5/22/18.
 */

public class CompoundComparator<T> implements Comparator<T> {

    public final Comparator<T> c1;
    public final Comparator<T> c2;

    public CompoundComparator() {
        this.c1 = null;
        this.c2 = null;
    }

    public CompoundComparator(Comparator<T> c1) {
        this.c1 = c1;
        this.c2 = null;
    }

    public CompoundComparator(Comparator<T> c1, Comparator<T> c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public static <T> Comparator<T> create(Comparator<T>... comparators) {
        Comparator<T> comparator = comparators.length == 0
                ? new CompoundComparator<T>()
                : comparators[0];
        for (int i = 1; i < comparators.length; i++) {
            comparator = new CompoundComparator<T>(comparator, comparators[i]);
        }

        return comparator;
    }

    @Override
    public int compare(T t1, T t2) {
        int v = 0;
        if (v == 0 && c1 != null) v = c1.compare(t1, t2);
        if (v == 0 && c2 != null) v = c2.compare(t1, t2);
        return v;
    }
}
