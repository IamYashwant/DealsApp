package com.target.dealbrowserpoc.dealbrowser.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.functions.Func0;
import rx.functions.Func1;


/**
 * Created by yashwant on 5/22/18.
 */

public class ListUtils {

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = !ASCENDING;

    public static class Tuple1<T> {
        public final T e1;

        public Tuple1(T e1) {
            this.e1 = e1;
        }
    }

    public static class Tuple2<T, U> {
        public final T e1;
        public final U e2;

        public Tuple2(T e1, U e2) {
            this.e1 = e1;
            this.e2 = e2;
        }
    }

    public static class Tuple3<T, U, V> {
        public final T e1;
        public final U e2;
        public final V e3;

        public Tuple3(T e1, U e2, V e3) {
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
        }
    }

    public static class Tuple4<T, U, V, W> {
        public final T e1;
        public final U e2;
        public final V e3;
        public final W e4;

        public Tuple4(T e1, U e2, V e3, W e4) {
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
            this.e4 = e4;
        }
    }

    public static <T> T coerce(T t1, T t2) {
        return t1 != null ? t1 : t2;
    }

    public static <T> T coerce(T t1, T t2, T t3) {
        return t1 != null ? t1 : coerce(t2, t3);
    }

    public static <T> T coerce(T t1, T t2, T t3, T t4) {
        return t1 != null ? t1 : coerce(t2, t3, t4);
    }

    public static <T> T coerce(T t1, T t2, T t3, T t4, T t5) {
        return t1 != null ? t1 : coerce(t2, t3, t4, t5);
    }

    public static <T> T coerce(T t1, Func0<T> func) {
        return t1 != null ? t1 : func.call();
    }

    public static String coerceNonEmpty(String s1, String s2) {
        return (s1 != null && !s1.equals(Constants.EMPTY_STRING)) ? s1 : s2;
    }

    public static <T, R> List<R> collect(T[] array, Func1<T, R> func) {
        List<R> l = new ArrayList<>();
        if (array != null) {
            for (T t : array) {
                l.add(func.call(t));
            }
        }
        return l;
    }

    public static <T, R> List<R> collect(List<T> list, Func1<T, R> func) {
        List<R> l = new ArrayList<>();
        if (list != null) {
            for (T t : list) {
                l.add(func.call(t));
            }
        }
        return l;
    }


    public static <T> List<T> copy(List<T> list) {
        return collect(list, t -> t);
    }

    public static <T> List<T> reverse(List<T> list) {
        List<T> copy = copy(list);
        Collections.reverse(copy);
        return copy;
    }

    public static <T> List<T> sort(List<T> list, Comparator<T> comparator, boolean ascending) {
        List<T> copy = copy(list);
        Collections.sort(copy, comparator);
        if (!ascending) {
            Collections.reverse(copy);
        }
        return copy;
    }

    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        return sort(list, comparator, ASCENDING);
    }

    public static <T> List<T> sort(List<T> list, Comparator<T>... comparators) {
        return sort(list, join(comparators));
    }

    public static <T> Comparator<T> join(Comparator<T>... comparators) {
        return CompoundComparator.create(comparators);
    }

    public static String join(List list, String delim) {
        return join(list, null, delim, null);
    }

    public static String join(List list, String prefix, String delim, String postfix) {
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
        }
        if (list != null) {
            for (int i = 0, n = list.size(); i < n; i++) {
                if (i > 0 && delim != null) {
                    sb.append(delim);
                }
                sb.append(list.get(i));
            }
        }
        if (postfix != null) {
            sb.append(postfix);
        }
        return sb.toString();
    }

    /**
     * equality
     */

    public static boolean areEqual(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        } else if (o1 == null || o2 == null) {
            return false;
        } else {
            return o1.equals(o2);
        }
    }

    public static <T, R> boolean areEqual(T o1, T o2, Func1<T, R> func) {
        if (o1 == o2) {
            return true;
        } else if (o1 == null || o2 == null) {
            return false;
        } else {
            return areEqual(func.call(o1), func.call(o2));
        }
    }


    public static List<String> split(String s, String delim) {
        List<String> list = new ArrayList<>();
        if (s != null && s.length() > 0)
            if (delim != null && !delim.equals("")) {
                int l = delim.length();
                int i0 = 0;
                for (; ; ) {
                    int i1 = s.indexOf(delim, i0);
                    if (i1 > 0) {
                        list.add(s.substring(i0, i1));
                    } else {
                        list.add(s.substring(i0));
                        break;
                    }
                    i0 = i1 + l;
                }
            } else {
                list.add(s);
            }
        return list;
    }
}
