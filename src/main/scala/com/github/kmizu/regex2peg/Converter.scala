package com.github.kmizu.regex2peg

import com.github.kmizu.regex2peg.PEG.NonTerminal

import scala.collection.mutable

object Converter {
  def convert(exp: REG.Exp): (NonTerminal, mutable.Map[NonTerminal, PEG.Exp]) = {
    val start = NonTerminal("S")
    val rules = mutable.Map[NonTerminal, PEG.Exp]()
    val generator = Generator("A")
    def C(exp: REG.Exp, k: PEG.Exp): PEG.Exp = exp match {
      case REG.Eps => PEG.Cat(PEG.Eps, k)
      case REG.Chr(ch) => PEG.Cat(PEG.Chr(ch), k)
      case REG.Cat(e1, e2) => C(e1, C(e2, k))
      case REG.Alt(e1, e2) => PEG.Alt(C(e1, k), C(e2, k))
      case REG.Rep0(e) =>
        val nonterminal = NonTerminal(generator.symbol())
        rules(nonterminal) = PEG.Alt(C(e, nonterminal), k)
        nonterminal
    }
    rules(start) = C(exp, PEG.Eps)
    (start, rules)
  }
}
