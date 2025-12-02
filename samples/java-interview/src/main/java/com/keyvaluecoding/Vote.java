package com.keyvaluecoding;

import java.time.Instant;

public record Vote(
        int productId,
        int vote,
        Instant createdAt) implements Comparable<Vote> {

    public Vote(int productId, int vote) {
        this(productId, vote, Instant.now());
    }

    @Override
    public int compareTo(Vote other) {
        if (vote == other.vote) {
            return createdAt.compareTo(other.createdAt);
        }
        return Integer.compare(vote, other.vote);
    }

}
