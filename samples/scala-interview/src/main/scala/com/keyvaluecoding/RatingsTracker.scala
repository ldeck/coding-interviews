package com.keyvaluecoding

class RatingsTracker(
  private var productId: Int = -1
):

  def addRating(productId: Int, rating: Int): Unit =
    this.productId = productId

  def top: Int =
    productId
