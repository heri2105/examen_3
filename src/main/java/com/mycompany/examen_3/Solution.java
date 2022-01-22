/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.examen_3;

import java.io.*;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n, k;
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            k = sc.nextInt();
            String[] a = new String[n];
            for (int j = 0; j < n; j++) {
                a[j] = sc.next();
            }
            System.out.printf("Case #%d: %s\n", i + 1, max(a, k));
        }
    }

    private static Object max(String[] a, int k) {
        TrieNode root = new TrieNode();
        for (String word : a) {
            root.insert(word);
        }
        return root.count(k);
    }

    private static class TrieNode {

        private final TrieNode[] child;
        private char cont;
        private int count;

        public TrieNode() {
            count = 0;
            child = new TrieNode[26];
        }

        public TrieNode(char cont) {
            this();
        }

        public void insert(String word) {
            insert(word.toCharArray(), 0);
        }

        private void insert(char[] charArray, int i) {
            if (charArray.length == i) {
                return;
            }
            char letter = charArray[i];
            if (child[letter - 'A'] == null) {
                child[letter - 'A'] = new TrieNode(letter);
            }
            child[letter - 'A'].count++;
            child[letter - 'A'].insert(charArray, i + 1);
        }

        public int count(int k) {
            int counter = this.count / k;
            for (int i = 0; i < 26; i++) {
                if (child[i] != null) {
                    counter += child[i].count(k);
                }
            }
            return counter;
        }
    }
}
