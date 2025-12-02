package com.keyvaluecoding;

import java.util.TreeSet;

public class RatingTracker {

    private final TreeSet<Vote> votes = new TreeSet<>();

    public void add(Vote vote) {
        this.votes.add(vote);
    }

    public int top() {
        return this.votes.isEmpty()
                ? -1
                : votes.last().productId();
    }
}
