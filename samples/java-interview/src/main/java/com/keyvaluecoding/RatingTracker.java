package com.keyvaluecoding;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RatingTracker {

    private final Map<Integer, Vote> cache = new HashMap<>();
    private final TreeSet<Vote> votes = new TreeSet<>();

    public void add(Vote vote) {
        var current = cache.get(vote.productId());
        if (current != null) {
            votes.remove(current);
            current = new Vote(vote.productId(), vote.vote() + current.vote());
        } else {
            current = vote;
        }
        this.votes.add(current);
        this.cache.put(current.productId(), current);
    }

    public int top() {
        return this.votes.isEmpty()
                ? -1
                : votes.last().productId();
    }
}
