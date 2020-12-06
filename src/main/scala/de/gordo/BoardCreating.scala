package de.gordo

import de.gordo.ConvaysBoard.Board

trait BoardCreating {

  def createBoard(x: Int, y: Int, populationDense: Double) = {
    assert(populationDense <= 1.0)
    val board: Array[Array[Boolean]] = Array.ofDim[Boolean](x, y)
    val populate: Array[Boolean] => Unit = (arr: Array[Boolean]) => {
      for (i <- arr.indices) {
        arr(i) = if (Math.random() >= populationDense) false else true
      }
    }
    board.foreach(populate)
    Board(board)
  }
}
