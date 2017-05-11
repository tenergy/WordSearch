/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package googleappliedcs.week6.wordsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    /**
     * Adding new word to the trie starting from root.
     * 1. repeatedly check each character is in the map.
     * 2. If so, point root to that child
     * 3. If not, create a new node, and add to the map.
     * 3.1. point root to that new child.
     * 4. toggle flag on last trienode as it is a word.
     *
     * NOTE: might have performance issue if totally new word is added.
     * It will keep on checking the map although it won't be found.
     * But performance is minimum since words are quite short.
     * @param s new word to be added.
     */
    public void add(String s) {
        TrieNode current = this;
        for (Character each : s.toCharArray()) {
            if (current.children.containsKey(each.toString())) {
                current = current.children.get(each.toString());
            } else {
                TrieNode tempNode = new TrieNode();
                current.children.put(each.toString(), tempNode);
                current = tempNode;
            }
        }
        current.isWord = true;
    }

    /**
     * Similar to add.
     * 1. Keep checking each word is a child in map.
     * 2. If any character is not in map, return false.
     * 3. If all characters are in map, check last node is a word.
     * @param s word to be searched
     * @return flag
     */
    public boolean isWord(String s) {
        TrieNode current = this;
        for (Character each : s.toCharArray()) {
            if (current.children.containsKey(each.toString())) {
                current = current.children.get(each.toString());
            } else {
                return false;
            }
        }
        return current.isWord;
    }

    /**
     * 1. edge case: if prefix is empty, return random character
     * 2. similar to add and isWord. iterate to get the node holding last character of prefix.
     * 2.1 if there is no such node, return null
     * 2.2 if so, return random child.
     *
     * @param s prefix word
     * @return null OR prefix + 1 more character
     */
    public String getAnyWordStartingWith(String s) {
        if (s == null || s.isEmpty()) {
            return getRandomKey(children.keySet());
        }
        TrieNode current = this;
        for (Character each : s.toCharArray()) {
            if (current.children.containsKey(each.toString())) {
                current = current.children.get(each.toString());
            } else {
                return null;
            }
        }
        return s + getRandomKey(current.children.keySet());
    }

    /**
     * Finding random key from the keyset
     * 1. convert set to list (MIGHT have performance issue for big set).
     * 2. use random to find random
     *
     * @param keySet children map's all keys
     * @return random key
     */
    private String getRandomKey(Set<String> keySet) {
        if (keySet == null || keySet.isEmpty()) {
            return null;
        }
        List<String> temp = new ArrayList<String>();
        temp.addAll(keySet);
        return temp.get(FastDictionary.random.nextInt(temp.size()));
    }

    /**
     * Improvement to getAnyWord
     * 1. same edge case to getAnyWord
     * 2. same search algorithm to get the node.
     * 3. use different algorithm to maximize the chance for computer to win.
     *
     * @param s prefix word
     * @return null OR prefix + 1 more character
     */
    public String getGoodWordStartingWith(String s) {
        if (s == null || s.isEmpty()) {
            return getRandomKey(children.keySet());
        }
        TrieNode current = this;
        for (Character each : s.toCharArray()) {
            if (current.children.containsKey(each.toString())) {
                current = current.children.get(each.toString());
            } else {
                return null;
            }
        }
        String nextWord = findGoodWord(current);
        return nextWord == null ? null : s + nextWord;
    }

    /**
     * Split all children to complete word list and not completed word list.
     * If there are words in not completed word list, choose random from it.
     * If there are not, choose from completed word.
     *
     * @param current Trie Node with children
     * @return good child key
     */
    private String findGoodWord(TrieNode current) {
        if (current.children.isEmpty()) {
            return null;
        }
        List<String> allValidChildren = new ArrayList<>();
        List<String> allInValidChildren = new ArrayList<>();
        for (Map.Entry<String, TrieNode> each : current.children.entrySet()) {
            if (each.getValue().isWord) {
                allInValidChildren.add(each.getKey());
            } else {
                allValidChildren.add(each.getKey());
            }
        }
        if (allValidChildren.isEmpty()) {
            return allInValidChildren.get(FastDictionary.random.nextInt(allInValidChildren.size()));
        } else {
            return allValidChildren.get(FastDictionary.random.nextInt(allValidChildren.size()));
        }
    }
}
