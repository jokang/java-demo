package cn.jokang.demos.java8.stream;

import cn.jokang.demos.java8.TestObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {
    @Test
    public void testFilterAndCount() {
        List<TestObject> lst = new ArrayList<>();
        lst.add(new TestObject(1));
        lst.add(new TestObject(2));
        lst.add(new TestObject(2));
        lst.add(new TestObject(3));
        lst.add(new TestObject(3));
        lst.add(new TestObject(3));
        Assert.assertEquals(0, lst.stream().filter(a -> a.getValue() == 0).count());
        Assert.assertEquals(1, lst.stream().filter(a -> a.getValue() == 1).count());
        Assert.assertEquals(2, lst.stream().filter(a -> a.getValue() == 2).count());
        Assert.assertEquals(3, lst.stream().filter(a -> a.getValue() == 3).count());

    }

    @Test
    public void testCollection() {
        ArrayList<String> myList = new ArrayList<String>();

        myList.add("String 1");
        myList.add("String 2");
        myList.add("String 3");

        for (String s : myList) {
            if (s.equals("String 2")) {
                myList.remove(s);
            }
        }

        for (String s : myList) {
            System.out.println(s);
        }
    }

    @Test
    public void testMap() {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(3);
        l.add(3);

        for (Integer i : l) {
            if (i == 2) {
                l.remove(i);
                //break;
            }
        }

        for (Integer i : l) System.out.println(i);
    }

    @Test
    public void testFilter() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> blackList = Arrays.asList(2, 3, 5);

        lst = lst.stream().filter(a -> !blackList.contains(a)).collect(Collectors.toList());
        System.out.println(lst);
    }

    @Test
    public void testMapper() {
        String ss = "444444-4,333333-3,111111-1,222222-2";
        Map<Integer, Integer> r = Arrays.stream(ss.split(",")).collect(Collectors.toMap(s -> Integer.valueOf(s.split(
            "-")[0]),
            s -> Integer.valueOf(s.split("-")[1])));
        System.out.println(r);

        String sss = "444444-4,333333-3,111111-1,222222-2,555555";
        List<Integer> lst =
            Arrays.stream(sss.split(",")).map(this::toPair).filter(Objects::nonNull).sorted(Comparator.comparingInt(Pair::getLeft)).map(s -> s.getLeft()).collect(Collectors.toList());
        System.out.println(lst);
    }

    private Pair<Integer, Integer> toPair(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String[] ss = s.split("-");
        if (ss.length < 2) {
            return Pair.of(Integer.valueOf(ss[0]), Integer.MAX_VALUE);
        } else {
            return Pair.of(Integer.valueOf(ss[0]), Integer.valueOf(ss[1]));
        }
    }

    @Test
    public void testDistinct() {
        List<String> d = Lists.newArrayList("1", "2", "3", "3");
        List<String> dates = d.stream().distinct().collect(Collectors.toList());
        System.out.println(dates);
    }

    @Test
    public void testDiv() {
        int a = 8;
        int b = 9;
//        System.out.println(a / b * 100D);
        DecimalFormat df = new DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.format(a * 100D / b));
    }

    @Test
    public void test() {
        List<Integer> lst = Lists.newArrayList(1, 2, 3);
        Stream<Integer> intStream = lst.stream();
        List<Integer> evens = intStream.filter(x -> x % 2 == 0).collect(Collectors.toList());
        List<Integer> odds = intStream.filter(x -> x % 2 != 0).collect(Collectors.toList());
        System.out.println(evens);
        System.out.println(odds);
    }
}
