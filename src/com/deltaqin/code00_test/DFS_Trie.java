package com.deltaqin.code00_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author deltaqin
 * @date 2021/4/12 6:01 下午
 */

class DFS_Trie {
    public static void main(String[] args) {
        char[][] chars= new char[4][4];
        chars[0] = new char[]{'o','a','a','n'};
        chars[1] = new char[]{'e','t','a','e'};
        chars[2] = new char[]{'i','h','k','r'};
        chars[3] = new char[]{'i','f','l','v'};

        String[] str = new String[]{"oath","pea","eat","rain"};
        List<String> words = new DFS_Trie().findWords(chars, str);
        System.out.println(words.toString());
    }


    public List<String> findWords(char[][] board, String[] words) {
        Trie tree = new Trie();

        for(int i = 0; i < words.length; i++) {
            tree.insert(words[i]);
        }

        int h = board.length;
        int w = board[0].length;
        // 保证当前DFS的时候不会自交，每一轮新的ij都会清空
        boolean[][] visited = new boolean[h][w];
        // 也可以是找到之后就减枝，也可以避免重复
        Set<String> res = new HashSet<>();
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                find(board, i, j,  tree, res, visited);
            }
        }
        List<String> list = new ArrayList<>();
        list.addAll(res);
        return list;
    }

    // DFS :要遍历的格子，坐标ij，是否遍历过， 当前的位置，树，结果集，
    public void find(char[][] board, int i, int j, Trie node,
                     Set<String> res, boolean[][] visited ) {

        if ( i < 0 ||  j < 0 || i >= board.length ||  j >= board[0].length
                || visited[i][j]) {
            return;
        }
        int index = board[i][j] - 'a';

        visited[i][j] = true;

        node = node.nexts[index];
        if(node != null) {
            if (node.word != null){
                res.add(node.word);
            }
            find(board, i+1, j, node, res, visited);
            find(board, i-1, j, node, res, visited);
            find(board, i, j+1, node, res, visited);
            find(board, i, j-1, node, res, visited);
        }

        visited[i][j] = false;
    }


    public class Trie {
        String word;
        Trie nexts[];

        public Trie() {
            nexts = new Trie[26];
        }

        public void insert(String word) {
            if (word == null) return;

            char[] str = word.toCharArray();
            int  i = 0;
            Trie node = this;
            while(i < str.length) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Trie();
                }
                node = node.nexts[index];
                i++;
            }
            node.word = word;
        }
    }


}
