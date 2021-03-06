package com.deltaqin.code04_tree;

import sun.text.normalizer.Trie;

/**
 * @author deltaqin
 * @date 2021/3/6 5:02 下午
 */
// 前缀树的建立
// 有多少是以当前值为前缀的，可以通过path的值来判断
public class T12_TrieTree {

    public static class Node{
//    如果只有英文字母，那么一个树的节点最多有26个,所以没有必要记录每一个节点的每一个字符是什么，
//    毕竟只是为了查找个数，不是为了查找具体的字符，浪费空间，直接使用26个占位，不为空的就是有
//        public char c;

//    根节点是代表空字符的，每一个节点都有自己的
//      path 值（路过+1） 和end值（结束+1）
//    一个节点就是一个字符，
        public int path;
        public int end ;
        public Node[] nexts;

        public Node() {
            path = 0;
            end = 0;
            // 使用数组来表达一个字符的 路
            // 当前节点到下级的26条路都建立好了，就看有没有下级节点，没有就是null
            // nexts[0] == null 有走向a的路
            // nexts[0] != null 没有走向a的路
            nexts = new Node[26];
        }
    }

    public static class TrieTree {
        private Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] strings = word.toCharArray();
            Node node = this.root;
//            根节点每次都++，可以判断总共加了多少Word
            node.path++;
//            在数组里面索引的位置
            int index = 0;
            for (int i=0; i<strings.length; i++) {
                // 从左到右遍历字符串，先得到对应nexts的索引位置
                index = strings[i] - 'a';
                // index 方向有没有节点，就是看有没有路
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
//                注意下面两句，先移动node为当前节点，再++，不然加到了上一个上面
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }

        public void delete(String word) {
//            存在再删除
            if (search(word) != 0) {
                if (word == null) return;
                char[] strings = word.toCharArray();
                Node node = this.root;
//            根节点每次都++，可以判断总共加了多少Word
                node.path--;
//            在数组里面索引的位置
                int index = 0;
                for (int i=0; i<strings.length; i++) {
                    // 从左到右遍历字符串，先得到对应nexts的索引位置
                    index = strings[i] - 'a';
                    // 沿途--p。不是p--.因为需要判断是否为0
                    // 如果p减去之后是0
                    if (--node.nexts[index].path == 0){
//                        巧妙
//                        path 为0后续直接不要了，说明只有他自己
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        // Word 之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            Node node = this.root;
            int index = 0;
            char[] strs = word.toCharArray();
            for (int i = 0; i < strs.length; i++) {
                index = strs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
//                忘记了更新node的值
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串里面，有几个是以pre这个字符串为前缀的
        public int prefixNum(String pre) {
            if (pre == null) return 0;
            Node node = this.root;
            int index = 0;
            char[] strs = pre.toCharArray();
            for (int i = 0; i < strs.length; i++) {
                index = strs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
//                忘记了更新node的值
                node = node.nexts[index];
            }
            return node.path;
        }
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        System.out.println(trie.search("qin"));
        trie.insert("qin");
        System.out.println(trie.search("qin"));
        trie.delete("qin");
        System.out.println(trie.search("qin"));
        trie.insert("qin");
        trie.insert("qin");
        trie.delete("qin");
        System.out.println(trie.search("qin"));
        trie.delete("qin");
        System.out.println(trie.search("qin"));
        trie.insert("qina");
        trie.insert("qinac");
        trie.insert("qinab");
        trie.insert("qinad");
        trie.delete("qina");
        System.out.println(trie.search("qina"));
        System.out.println(trie.prefixNum("qin"));

    }

}
