import de.gordo.{BoardCreating, RuleApplying}

object App extends BoardCreating with RuleApplying {

  def main(args: Array[String]): Unit = {

    var board = createBoard(20, 90, 0.4)
    println(board.toString)
    Thread.sleep(1000)

    while (true) {
      board = applyConvayRules(board)
      println(board.toString)
      Thread.sleep(1000)
    }


  }
}
