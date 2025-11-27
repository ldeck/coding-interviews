package com.keyvaluecoding

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class RatingsTrackerSpec extends AnyFlatSpec with should.Matchers:

  trait Tester:
    val tracker = RatingsTracker()

    def ingest(s: Rating*): Unit =
      s.foreach(r => tracker.addRating(productId = r.productId, rating = r.rating))

    def top = tracker.top


  "The tracker" should "be empty by default" in new Tester:
    top shouldBe -1

  it should "handle a single product rating" in new Tester:
    ingest(Rating(productId = 1, rating = 2))
    top shouldBe 1

  it should "handle multiple product ratings" in new Tester:
    ingest(Rating(productId = 1, rating = 2),Rating(productId = 2, rating = 1))
    top shouldBe 1
