package dk.miracleas.scala.commons

import org.scalatest.FunSuite


class SeqWithGroupedTest extends FunSuite {

  test("An empty string list should produce en empty grouped while listlist") {
    val groupedWhile: List[List[String]] = List.empty[String].groupedWhile {lst: String => true}
    assert(groupedWhile.isEmpty)
  }


  test("Some lines separated at each empty line") {

    val input = List("p1l1","p1l2", "", "p2l1","","p3l1","p3l2","p3l3","","p4")

    val groupedWhile: List[List[String]] = input.groupedWhile(!_.isEmpty)
    // Note blank lines are also in the inner lists..
    assert(groupedWhile.size==4)
    assert(groupedWhile(0).size==2)
    assert(groupedWhile(1).size==2)
    assert(groupedWhile(2).size==4)
    assert(groupedWhile(3).size==2)
  }


}
