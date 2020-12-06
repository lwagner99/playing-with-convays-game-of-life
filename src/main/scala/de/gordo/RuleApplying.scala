package de.gordo

import de.gordo.ConvaysBoard.Board

import scala.util.Try

trait RuleApplying {


  def applyConvayRules(conveyBoard: Board) = {
    val board = conveyBoard.board
    val newBoard: Array[Array[Boolean]] = board.clone()
    val board1 = applyRules(board, newBoard)
    Board(board1)
  }


  private def applyRules(board: Array[Array[Boolean]], newBoard: Array[Array[Boolean]]) = {
    val firstDimLength = board.length
    val secondDimLength = board(0).length
    val livingNeighboursBoard = countLivingNeighbours(board)

    for (x <- 0 until firstDimLength) {
      for (y <- 0 until secondDimLength) {
        if (livingNeighboursBoard(x)(y) < 2) newBoard(x)(y) = false // Any live cell with fewer than two live neighbours dies, as if by underpopulation
        if (livingNeighboursBoard(x)(y) > 3) newBoard(x)(y) = false // Any live cell with more than three live neighbours dies, as if by overpopulation
        if (livingNeighboursBoard(x)(y) == 3) newBoard(x)(y) = true // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
      }
    }
    newBoard
  }


  private def countLivingNeighbours(board: Array[Array[Boolean]]): Array[Array[Int]] = {
    val firstDimLength = board.length
    val secondDimLength = board(0).length
    val livingNeighboursBoard: Array[Array[Int]] = Array.ofDim[Int](firstDimLength, secondDimLength)

    for (x <- 0 until firstDimLength) {
      for (y <- 0 until secondDimLength) {
        val livingNeighbours = getNumberOfLivingNeighbours(board, x, y)
        livingNeighboursBoard(x)(y) = livingNeighbours
      }
    }
    livingNeighboursBoard
  }

  private def getNumberOfLivingNeighbours(board: Array[Array[Boolean]], x: Int, y: Int) = {
    getCellStatus(board, x - 1, y - 1) +
      getCellStatus(board, x - 1, y) +
      getCellStatus(board, x - 1, y + 1) +
      getCellStatus(board, x, y - 1) +
      getCellStatus(board, x, y + 1) +
      getCellStatus(board, x + 1, y - 1) +
      getCellStatus(board, x + 1, y) +
      getCellStatus(board, x + 1, y + 1)

  }

  private def getCellStatus(board: Array[Array[Boolean]], x: Int, y: Int): Int = {
    val status = Try(board(x)(y))
    val cellLiving: Boolean = status.getOrElse(false)
    if (cellLiving) 1 else 0
  }

  //
  //    x x x
  //    x - x
  //    x - x
  //
} //
