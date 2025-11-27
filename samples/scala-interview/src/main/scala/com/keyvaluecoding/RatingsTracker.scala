package com.keyvaluecoding

import java.time.Instant
import scala.collection.*

case class Rating(productId: Int, rating: Int, when: Instant = Instant.now())

case class AccumulatedRating(productId: Int, private val votes: Int = 0, private val accum: Int = 0, when: Instant = Instant.now())
    extends Ordered[AccumulatedRating]:

  val rating = accum.toDouble / votes

  def append(rating: Rating): AccumulatedRating =
    copy(votes = votes + 1, accum = accum + rating.rating)

  override def compare(that: AccumulatedRating): Int =
    if (rating == that.rating)
      when.compareTo(that.when)
    else
      rating.compare(that.rating)


class RatingsTracker(
  private val ratings: mutable.Set[AccumulatedRating] = mutable.SortedSet(),
  private val keyed: mutable.Map[Int, AccumulatedRating] = mutable.HashMap()
):

  def addRating(productId: Int, rating: Int): Unit =
    val input = Rating(productId = productId, rating = rating)
    val existing = keyed.get(productId) match
      case Some(prev) =>
        ratings -= prev
        prev

      case _ =>
        AccumulatedRating(productId = productId)

    val next = existing.append(rating = input)
    ratings += next
    keyed.put(productId, next)

  def top: Int =
    this.ratings.lastOption
      .map(_.productId)
      .getOrElse(default = -1)
