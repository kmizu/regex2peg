package com.github.kmizu.regex2peg

import Regexp._
import Converter._

object Main {
  def main(args: Array[String]): Unit = {
    println(PEG.pretty(convert(((Chr('b') ~ Chr('a')) | Chr('a')).* ~ Chr('a'))._2))
  }
}
