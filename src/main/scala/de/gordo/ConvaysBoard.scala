package de.gordo

object ConvaysBoard {

  case class Board(board: Array[Array[Boolean]]) {
    override def toString: String = {
      val ret = board.map((arr: Array[Boolean]) => {
        val tmp = arr.map((value: Boolean) => if (value) " x" else "  ").mkString("")
        tmp + "\n"
      }
      ).mkString("")
      ret
    }
  }
}
