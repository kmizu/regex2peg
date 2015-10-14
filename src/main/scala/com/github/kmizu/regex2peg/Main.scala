package com.github.kmizu.regex2peg

import REG._
import Converter._

object Main {
  def main(args: Array[String]): Unit = {
    println(PEG.pretty(convert(Cat(Rep0(Alt(Cat(Chr('b'), Chr('a')), Chr('a'))), Chr('a')))._2))
  }
}
