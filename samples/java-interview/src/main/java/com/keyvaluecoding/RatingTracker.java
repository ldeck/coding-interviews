package com.keyvaluecoding;

import java.util.*;
import java.util.stream.Collectors;

public class RatingTracker {

    private final Map<Integer, Vote> cache = new HashMap<>();
    private final TreeSet<Vote> votes = new TreeSet<>();

    public void add(Vote vote) {
        var current = cache.get(vote.productId());
        if (current != null) {
            votes.remove(current);
            current = current.addTo(vote);
        } else {
            current = vote;
        }
        this.votes.add(current);
        this.cache.put(current.productId(), current);
    }

    public List<Statistic> averages() {
        return votes
            .stream()
            .map(Statistic::new)
            .distinct()
            .sorted()
            .toList();
    }

    public int top() {
        return this.votes.isEmpty()
                ? -1
                : votes.last().productId();
    }
}
