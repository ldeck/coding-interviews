package com.keyvaluecoding;

public record Statistic(int productId, int count, float average) implements  Comparable<Statistic> {

    private static float toFloat(int count, int total) {
        return (float) total / count;
    }

    public Statistic(Vote vote) {
        this(vote.productId(), vote.count(), toFloat(vote.count(), vote.vote()));
    }

    @Override
    public int compareTo(Statistic o) {
        if (this.average == o.average) {
            if (this.count == o.count) {
                return Integer.compare(this.productId, o.productId);
            } else {
                return Integer.compare(this.count, o.count);
            }
        }
        return Float.compare(this.average, o.average);
    }
}
