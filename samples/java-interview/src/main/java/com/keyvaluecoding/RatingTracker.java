package com.keyvaluecoding;

public class RatingTracker {

    private Vote vote;

    public void add(Vote vote) {
        this.vote = vote;
    }

    public int top() {
        return vote == null ? -1 : vote.productId();
    }
}
