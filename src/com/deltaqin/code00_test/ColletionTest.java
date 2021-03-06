package com.deltaqin.code00_test;

import java.util.*;

//set的方法是add
//map的方法是put
//stack 的方法是pop和peek
/**
 * @author deltaqin
 * @date 2021/3/4
 */
public class ColletionTest {
    public static class Node {
        int value;
        Node next;

        public Node (int val){
            value = val;
        }
    }

    public static class MyComparator implements Comparator<Node> {

//        升序
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

//        HashSet
        HashSet<Node> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        set.remove(node1);
        System.out.println(set.contains(node1));
        System.out.println(set.contains(node2));

//        HashMap
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(node1, 1);
        map.put(node2, 3);
        map.put(node3,2);
        map.remove(node2);
        System.out.println(map.containsKey(node2)); // false
        System.out.println(map.containsValue(1)); // true
        System.out.println(map.get(node1)); // 1
        System.out.println(map.get(node2)); // null

//        TreeSet
        TreeSet<Node> treeSet = new TreeSet();
        try{
            treeSet.add(node1);
            treeSet.add(node2);
            treeSet.add(node3);
        } catch (Exception e) {
            System.out.println("不提供比较器无法加入TreeSet");
        }

        TreeSet<Node> treeSet1 = new TreeSet<>(new MyComparator());
        treeSet1.add(node1);
        treeSet1.add(node2);
        treeSet1.add(node3);
        System.out.println("提供了Node的比较器就会加入成功");

//        有序表
//        TreeMap
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        treeMap1.put(7, "我是7");
        treeMap1.put(5, "我是5");
        treeMap1.put(4, "我是4");
        treeMap1.put(3, "我是3");
        treeMap1.put(9, "我是9");
        treeMap1.put(2, "我是2");
        System.out.println(treeMap1.containsKey(2));
        System.out.println(treeMap1.get(2));
        System.out.println(treeMap1.firstKey() + "  min");
        System.out.println(treeMap1.lastKey() + "  max");
        System.out.println(treeMap1.floorKey(8) + " 离8最近小于等于8");
        System.out.println(treeMap1.ceilingKey(8) + " 离8最近大于等于8");
        treeMap1.remove(8);
    }
}
