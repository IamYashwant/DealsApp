package dealbrowser.utils;

import com.target.dealbrowserpoc.dealbrowser.utils.ListUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.target.dealbrowserpoc.dealbrowser.utils.ListUtils.DESCENDING;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Created by yashwant on 5/22/18.
 */

@RunWith(JUnit4.class)
public class ListUtilsTest {

    private List<String> list = Arrays.asList("ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN");
    private List<String> emptyList = new ArrayList<>();
    private List<String> nullList = null;
    private Comparator<String> stringComparator = (lhs, rhs) -> lhs.compareTo(rhs);

    @Test
    public void coerce_whenNull_whenNull() {
        assertThat(ListUtils.coerce((String)null, (String)null), is(nullValue()));
    }

    @Test
    public void coerce_whenNull() {
        assertThat(ListUtils.coerce(null, "ONE"), is(equalTo("ONE")));
    }

    @Test
    public void coerce_whenNonNull() {
        assertThat(ListUtils.coerce("ONE", "TWO"), is(equalTo("ONE")));
    }

    @Test
    public void coerceNonEmpty_whenNonNull() {
        assertThat(ListUtils.coerceNonEmpty("ONE", "TWO"), is(equalTo("ONE")));
    }

    @Test
    public void coerceNonEmpty_whenNull() {
        assertThat(ListUtils.coerceNonEmpty(null, "TWO"), is(equalTo("TWO")));
    }

    @Test
    public void coerceNonEmpty_whenEmpty() {
        assertThat(ListUtils.coerceNonEmpty("", "TWO"), is(equalTo("TWO")));
    }

    @Test
    public void coerceNonEmpty_whenSpaces() {
        assertThat(ListUtils.coerceNonEmpty("   ", "TWO"), is(equalTo("   ")));
    }

    @Test
    public void collect() {
        List<Integer> results = ListUtils.collect(list, s -> s.length());
        assertThat(results, contains(new Integer(3), new Integer(3), new Integer(5), new Integer(4), new Integer(4), new Integer(3), new Integer(5), new Integer(5), new Integer(4), new Integer(3)));
    }

    @Test
    public void collect_whenEmpty() {
        List<Integer> results = ListUtils.collect(emptyList, s -> s.length());
        assertThat(results, hasSize(0));
    }

    @Test
    public void collect_whenNull() {
        List<Integer> results = ListUtils.collect(nullList, s -> s.length());
        assertThat(results, hasSize(0));
    }


    @Test
    public void copy() {
        assertThat(ListUtils.copy(list), is(not(sameInstance(list))));
        assertThat(ListUtils.copy(list), contains("ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN"));
    }

    @Test
    public void copy_whenEmpty() {
        assertThat(ListUtils.copy(emptyList), is(not(sameInstance(emptyList))));
        assertThat(ListUtils.copy(emptyList), is(empty()));
    }

    @Test
    public void copy_whenNull() {
        assertThat(ListUtils.copy(nullList), is(not(sameInstance(nullList))));
        assertThat(ListUtils.copy(nullList), is(notNullValue()));
    }

    @Test
    public void reverse() {
        assertThat(ListUtils.reverse(list), contains("TEN", "NINE", "EIGHT", "SEVEN", "SIX", "FIVE", "FOUR", "THREE", "TWO", "ONE"));
    }

    @Test
    public void reverse_whenEmpty() {
        assertThat(ListUtils.reverse(emptyList), is(not(sameInstance(emptyList))));
        assertThat(ListUtils.reverse(emptyList), is(empty()));
    }

    @Test
    public void reverse_whenNull() {
        assertThat(ListUtils.reverse(nullList), is(not(sameInstance(nullList))));
        assertThat(ListUtils.reverse(nullList), is(notNullValue()));
    }

    @Test
    public void sort() {
        assertThat(ListUtils.sort(list, stringComparator), contains("EIGHT", "FIVE", "FOUR", "NINE", "ONE", "SEVEN", "SIX", "TEN", "THREE", "TWO" ));
    }

    @Test
    public void sort_whenDescending() {
        assertThat(ListUtils.sort(list, stringComparator, DESCENDING), contains("TWO", "THREE", "TEN", "SIX", "SEVEN", "ONE", "NINE", "FOUR", "FIVE", "EIGHT"));
    }

    @Test
    public void sort_whenEmpty() {
        assertThat(ListUtils.sort(emptyList, stringComparator), is(not(sameInstance(emptyList))));
        assertThat(ListUtils.sort(emptyList, stringComparator), is(empty()));
    }

    @Test
    public void sort_whenNull() {
        assertThat(ListUtils.sort(nullList, stringComparator), is(not(sameInstance(nullList))));
        assertThat(ListUtils.sort(nullList, stringComparator), is(empty()));
    }

    @Test
    public void compoundSort() {
        Comparator<String> c1 = (lhs, rhs) -> lhs.substring(0, 2).compareTo(rhs.substring(0, 2));
        Comparator<String> c2 = (lhs, rhs) -> lhs.substring(3, 5).compareTo(rhs.substring(3, 5));
        List s0 = Arrays.asList("00:00", "00:10", "02:20", "01:20", "00:30", "02:30", "02:10", "00:20", "02:00");
        List s1 = ListUtils.sort(s0, c1, c2);
        int i = 0;
        assertThat(s1.get(i++), is("00:00"));
        assertThat(s1.get(i++), is("00:10"));
        assertThat(s1.get(i++), is("00:20"));
        assertThat(s1.get(i++), is("00:30"));
        assertThat(s1.get(i++), is("01:20"));
        assertThat(s1.get(i++), is("02:00"));
        assertThat(s1.get(i++), is("02:10"));
        assertThat(s1.get(i++), is("02:20"));
        assertThat(s1.get(i++), is("02:30"));
    }

    @Test
    public void compoundSort_whenEmpty() {
        Comparator<String> c1 = (lhs, rhs) -> lhs.substring(0, 2).compareTo(rhs.substring(0, 2));
        Comparator<String> c2 = (lhs, rhs) -> lhs.substring(3, 5).compareTo(rhs.substring(3, 5));
        List s0 = new ArrayList<>();
        List s1 = ListUtils.sort(s0, c1, c2);
        assertThat(s1.size(), is(0));
    }

    @Test
    public void join() {
        assertThat(ListUtils.join(Arrays.asList("ONE", new Integer(1)), "+|+"), is("ONE+|+1"));
    }

    @Test
    public void join_onNullList() {
        assertThat(ListUtils.join(null, "+|+"), is(""));
    }

    @Test
    public void join_onEmptyList() {
        assertThat(ListUtils.join(Arrays.asList(), "+|+"), is(""));
    }

    @Test
    public void join_onNullDelimiter() {
        assertThat(ListUtils.join(Arrays.asList("ONE", new Integer(1)), null), is("ONE1"));
    }

    @Test
    public void join_onEmptyDelimiter() {
        assertThat(ListUtils.join(Arrays.asList("ONE", new Integer(1)), ""), is("ONE1"));
    }


    @Test
    public void split() {
        List<String> list = ListUtils.split("ONE+|+1", "+|+");
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("ONE"));
        assertThat(list.get(1), is("1"));
    }

    @Test
    public void split_onNullString() {
        List<String> list = ListUtils.split(null, "+|+");
        assertThat(list.size(), is(0));
    }

    @Test
    public void split_onEmptyString() {
        List<String> list = ListUtils.split("", "+|+");
        assertThat(list.size(), is(0));
    }

    @Test
    public void split_onNullDelimiter() {
        List<String> list = ListUtils.split("ONE+|+1", null);
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("ONE+|+1"));
    }

    @Test
    public void split_onEmptyDelimiter() {
        List<String> list = ListUtils.split("ONE+|+1", "");
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("ONE+|+1"));
    }

    @Test
    public void split_onNonMatchingDelimiter() {
        List<String> list = ListUtils.split("ONE+|+1", "=|=");
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("ONE+|+1"));
    }

    @Test
    public void areEqual_whenNull_whenNull() {
        assertThat(ListUtils.areEqual(null, null), is(true));
    }

    @Test
    public void areEqual_whenOne_whenNull() {
        assertThat(ListUtils.areEqual("ONE", null), is(false));
    }

    @Test
    public void areEqual_whenNull_whenTwo() {
        assertThat(ListUtils.areEqual(null, "TWO"), is(false));
    }

    @Test
    public void areEqual_whenOne_whenTWO() {
        assertThat(ListUtils.areEqual("ONE", "TWO"), is(false));
    }

    @Test
    public void areEqual_whenOne_whenOne() {
        assertThat(ListUtils.areEqual("ONE", "ONE"), is(true));
    }

    @Test
    public void areEqual_whenMap_whenNull_whenNull() {
        assertThat(ListUtils.areEqual(null, null, o -> o.hashCode()), is(true));
    }

    @Test
    public void areEqual_whenMap_whenOne_whenNull() {
        assertThat(ListUtils.areEqual("ONE", null, o -> o.hashCode()), is(false));
    }

    @Test
    public void areEqual_whenMap_whenNull_whenTwo() {
        assertThat(ListUtils.areEqual(null, "TWO", o -> o.hashCode()), is(false));
    }

    @Test
    public void areEqual_whenMap_whenOne_whenTWO() {
        assertThat(ListUtils.areEqual("ONE", "TWO", o -> o.hashCode()), is(false));
    }

    @Test
    public void areEqual_whenMap_whenOne_whenOne() {
        assertThat(ListUtils.areEqual("ONE", "ONE", o -> o.hashCode()), is(true));
    }
}
