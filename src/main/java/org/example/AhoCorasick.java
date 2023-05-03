package org.example;

import java.util.*;
class AhoCorasick {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail;
        List<String> patterns = new ArrayList<>();
    }

    private Node root;

    public AhoCorasick(List<String> patterns) {
        root = new Node();

        // Build trie
        for (String pattern : patterns) {
            Node curr = root;
            for (char c : pattern.toCharArray()) {
                curr.children.putIfAbsent(c, new Node());
                curr = curr.children.get(c);
            }
            curr.patterns.add(pattern);
        }

        // Build failure links using BFS
        Queue<Node> queue = new LinkedList<>();
        for (Node child : root.children.values()) {
            child.fail = root;
            queue.add(child);
        }
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Map.Entry<Character, Node> entry : curr.children.entrySet()) {
                char c = entry.getKey();
                Node child = entry.getValue();
                queue.add(child);
                Node fail = curr.fail;
                while (fail != null && !fail.children.containsKey(c)) {
                    fail = fail.fail;
                }
                child.fail = fail == null ? root : fail.children.get(c);
                child.patterns.addAll(child.fail.patterns);
            }
        }
    }

    public List<String> search(String text) {
        text = text.toLowerCase();
        List<String> result = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            while (curr != null && !curr.children.containsKey(c)) {
                curr = curr.fail;
            }
            if (curr == null) {
                curr = root;
                continue;
            }
            curr = curr.children.get(c);
            result.addAll(curr.patterns);
        }
        return result;
    }
}

