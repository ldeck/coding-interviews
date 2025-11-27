package com.keyvaluecoding

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class RatingsTrackerSpec extends AnyFlatSpec with should.Matchers:
  "The tracker" should "be empty by default" in:
    val tracker = RatingsTracker()
    tracker.top shouldBe -1

  it should "handle a single product rating" in:
    val tracker = RatingsTracker()
    tracker.addRating(productId = 1, rating = 2)
    tracker.top shouldBe 1

  it should "handle multiple product ratings" in:
    val tracker = RatingsTracker()
    tracker.addRating(productId = 1, rating = 2)
    tracker.addRating(productId = 2, rating = 1)
    tracker.top shouldBe 1
