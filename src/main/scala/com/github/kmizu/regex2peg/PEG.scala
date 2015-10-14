package com.github.kmizu.regex2peg

import scala.collection.mutable

object PEG {
  case class Grammar(rules: List[Rule])
  case class Rule(name: String, body: Exp)
  sealed abstract class Exp
  case object Eps extends Exp
  case class Alt(lhs: Exp, rhs: Exp) extends Exp
  case class Cat(lhs: Exp, rhs: Exp) extends Exp
  case class Chr(value: Char) extends Exp
  case class Rep0(body: Exp) extends Exp
  case class NonTerminal(name: String) extends Exp

  def pretty(rules: mutable.Map[NonTerminal, Exp]): String = {
    def p(exp: Exp): String = exp match {
      case Eps => "epsilon"
      case Chr(c) => s"'${c}'"
      case Alt(e1, e2) => s"(${p(e1)}/${p(e2)})"
      case Cat(e1, e2) => s"${p(e1)} ${p(e2)}"
      case Rep0(e) => s"(${p(e)})*"
      case NonTerminal(name) => name
    }
    rules.map{case (rule, exp) =>
       s"${rule.name} <- ${p(exp)}\n"
    }.mkString("\n")
  }
}
