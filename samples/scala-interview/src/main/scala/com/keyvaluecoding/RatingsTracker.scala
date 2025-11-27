package com.keyvaluecoding

import scala.collection.*

case class Rating(productId: Int, rating: Int) extends Ordered[Rating]:
  override def compare(that: Rating): Int =
    rating.compare(that.rating)


class RatingsTracker(
  private val ratings: mutable.Set[Rating] = mutable.SortedSet()
):

  def addRating(productId: Int, rating: Int): Unit =
    this.ratings.add(Rating(productId = productId, rating = rating))

  def top: Int =
    this.ratings.lastOption.map(_.productId).getOrElse(default = -1)
