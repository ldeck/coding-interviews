package com.keyvaluecoding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
