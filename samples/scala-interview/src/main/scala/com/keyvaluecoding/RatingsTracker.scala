package com.keyvaluecoding

import java.time.Instant
import scala.collection.*

case class Vote(productId: Int, rating: Int)
case class Stat(productId: Int, rating: Float)

case class AccumulatedRating(productId: Int, val votes: Int = 0, private val accum: Int = 0, when: Instant = Instant.now())
    extends Ordered[AccumulatedRating]:

  /**
  * Accumulated ratings are ascendingly ordered as per their rawRating
  * otherwise by when they were most recent change.
  */
  override def compare(that: AccumulatedRating): Int =
    if (rawRating == that.rawRating)
      when.compareTo(that.when)
    else
      rawRating.compare(that.rawRating)

  /**
  * Value of the raw average rating given to this product.
  */
  val rawRating: Double = accum.toDouble / votes
  /**
  * Value of the average public star rating, rounded to the nearest 0.5.
  */
  val rating: Float = math.round(rawRating * 2) / 2.0f

  def amended(vote: Vote): AccumulatedRating =
    copy(votes = votes + 1, accum = accum + vote.rating)

class RatingsTracker(
  private val ratings: mutable.Set[AccumulatedRating] = mutable.SortedSet(),
  private val keyed: mutable.Map[Int, AccumulatedRating] = mutable.HashMap()
):

  def addRating(productId: Int, rating: Int): Unit =
    val input = Vote(productId = productId, rating = rating)
    val existing = keyed.get(productId) match
      case Some(prev) =>
        ratings -= prev
        prev

      case _ =>
        AccumulatedRating(productId = productId)

    val next = existing.amended(vote = input)
    ratings += next
    keyed.put(productId, next)

  def allVotes: Int = ratings.toSeq.map(_.votes).sum

  def stats: Seq[Stat] =
    ratings.toSeq.map(r => Stat(productId = r.productId, rating = r.rating))

  def top: Int =
    this.ratings.lastOption
      .map(_.productId)
      .getOrElse(default = -1)
