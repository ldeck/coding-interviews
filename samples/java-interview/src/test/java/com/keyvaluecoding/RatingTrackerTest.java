package com.keyvaluecoding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RatingTrackerTest {

    private RatingTracker tracker;

    @BeforeEach
    void init() {
        tracker = new RatingTracker();
    }

    @Test
    public void shouldBeEmptyByDefault() {
        assertThat(tracker.top()).isEqualTo(-1);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2",
            "3,1",
            "7,2"
    })
    public void shouldReturnSingleRatingProductId(int productId, int rating) {
        tracker.add(new Vote(productId, rating));
        assertThat(tracker.top()).isEqualTo(productId);
    }

    @Test
    public void shouldReturnHighestRatingProductId() {
        tracker.add(new Vote(1, 3));
        tracker.add(new Vote(2, 3));
        tracker.add(new Vote(3, 1));
        assertThat(tracker.top()).isEqualTo(2);
    }
}
