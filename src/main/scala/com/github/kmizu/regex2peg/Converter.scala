package com.github.kmizu.regex2peg

import com.github.kmizu.regex2peg.PEG.NonTerminal

import scala.collection.mutable

object Converter {
  def convert(exp: Regexp.Exp): (NonTerminal, mutable.Map[NonTerminal, PEG.Exp]) = {
    val start = NonTerminal("S")
    val rules = mutable.Map[NonTerminal, PEG.Exp]()
    val generator = Generator("A")
    def C(exp: Regexp.Exp, k: PEG.Exp): PEG.Exp = exp match {
      case Regexp.Eps => PEG.Eps ~ k
      case Regexp.Chr(ch) => PEG.Chr(ch) ~ k
      case Regexp.Cat(e1, e2) => C(e1, C(e2, k))
      case Regexp.Alt(e1, e2) => C(e1, k) / C(e2, k)
      case Regexp.Rep0(e) =>
        val nonterminal = NonTerminal(generator.symbol())
        rules(nonterminal) = C(e, nonterminal) / k
        nonterminal
    }
    rules(start) = C(exp, PEG.Eps)
    (start, rules)
  }
}
