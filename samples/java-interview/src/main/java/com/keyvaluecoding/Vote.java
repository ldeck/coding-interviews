package com.keyvaluecoding;

import java.time.Instant;

public record Vote(
        int productId,
        int vote,
        int count,
        Instant createdAt,
        float average
    ) implements Comparable<Vote> {

    public Vote(int productId, int vote) {
        this(productId, vote, 1, Instant.now(), vote);
    }

    public Vote addTo(Vote other) {
        if (other == null)
            return this;
        else if (productId != other.productId)
            throw new IllegalArgumentException(
                    String.format("Cannot different productId votes together: %i != %i", productId,
                            other.productId));

        int votesSum = vote + other.vote;
        int totalVotes = count + other.count;
        float average = (float) votesSum / totalVotes;

        return new Vote(
            productId,
            vote + other.vote,
            count + other.count,
            Instant.now(),
            average
        );
    }

    @Override
    public int compareTo(Vote other) {
        if (vote == other.vote && count == other.count) {
            return createdAt.compareTo(other.createdAt);
        }
        return Integer.compare(vote, other.vote);
    }

}
