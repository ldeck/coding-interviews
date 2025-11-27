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

  it should "handle multiple ratings for unique products" in new Tester:
    ingest(Rating(productId = 1, rating = 2),Rating(productId = 2, rating = 1))
    top shouldBe 1

  it should "handle multiple ratings per product" in new Tester:
    ingest(
      Rating(productId = 1, rating = 2),
      Rating(productId = 2, rating = 1),
      Rating(productId = 2, rating = 3),
    )
    top shouldBe 2

  it should "handle multiple combined ratings per product" in new Tester:
    ingest(
      Rating(productId = 1, rating = 1),
      Rating(productId = 1, rating = 2),
      Rating(productId = 1, rating = 3),
      Rating(productId = 1, rating = 4),
      Rating(productId = 1, rating = 5),
      Rating(productId = 2, rating = 1),
      Rating(productId = 2, rating = 2),
      Rating(productId = 2, rating = 2),
      Rating(productId = 2, rating = 4),
      Rating(productId = 2, rating = 5),
    )
    top shouldBe 1
