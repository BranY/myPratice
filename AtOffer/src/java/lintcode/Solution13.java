package lintcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution13 {

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> cnt.get(a).equals(cnt.get(b)) ? b.compareTo(a) : cnt.get(a) - cnt.get(b));


        for (String word : cnt.keySet()) {
            pq.add(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        LinkedList<String> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.addFirst(pq.poll());
        }
        return list;
    }

}
