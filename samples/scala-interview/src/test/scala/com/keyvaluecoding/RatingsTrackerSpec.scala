package com.keyvaluecoding

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class RatingsTrackerSpec extends AnyFlatSpec with should.Matchers:

  trait Tester:
    val tracker = RatingsTracker()

    def ingest(s: Vote*): Unit =
      s.foreach(r => tracker.addRating(productId = r.productId, rating = r.rating))

    def top = tracker.top


  "The epic 1 tracker" should "be empty by default" in new Tester:
    top shouldBe -1

  it should "handle a single product rating" in new Tester:
    ingest(Vote(productId = 1, rating = 2))
    top shouldBe 1

  it should "handle multiple ratings for unique products" in new Tester:
    ingest(Vote(productId = 1, rating = 2),Vote(productId = 2, rating = 1))
    top shouldBe 1

  it should "handle multiple ratings per product" in new Tester:
    ingest(
      Vote(productId = 1, rating = 2),
      Vote(productId = 2, rating = 1),
      Vote(productId = 2, rating = 3),
    )
    top shouldBe 2

  it should "handle multiple combined ratings per product" in new Tester:
    ingest(
      Vote(productId = 1, rating = 1),
      Vote(productId = 1, rating = 2),
      Vote(productId = 1, rating = 3),
      Vote(productId = 1, rating = 4),
      Vote(productId = 1, rating = 5),
      Vote(productId = 2, rating = 1),
      Vote(productId = 2, rating = 2),
      Vote(productId = 2, rating = 2),
      Vote(productId = 2, rating = 4),
      Vote(productId = 2, rating = 5),
    )
    top shouldBe 1

  "The epic 2 tracker" should "provide the list of average rating per product" in new Tester:
    ingest(
      Vote(productId = 1, rating = 1),
      Vote(productId = 1, rating = 2),
      Vote(productId = 1, rating = 3),
      Vote(productId = 1, rating = 4),
      Vote(productId = 1, rating = 5),
      Vote(productId = 2, rating = 1),
      Vote(productId = 2, rating = 2),
      Vote(productId = 2, rating = 2),
      Vote(productId = 2, rating = 4),
      Vote(productId = 2, rating = 5),
      Vote(productId = 3, rating = 4),
      Vote(productId = 3, rating = 3),
    )
    tracker.stats should contain theSameElementsInOrderAs Seq(
      Stat(productId = 2, rating = 3.0),
      Stat(productId = 1, rating = 3.0),
      Stat(productId = 3, rating = 3.5))
