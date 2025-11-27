package com.keyvaluecoding

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class RatingsTrackerSpec extends AnyFlatSpec with should.Matchers:
  "The tracker" should "be empty by default" in:
    val tracker = RatingsTracker()
    tracker.top shouldBe -1
