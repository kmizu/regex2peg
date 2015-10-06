package com.github.kmizu.regex2peg

object PEG {
  case class Grammar(rules: List[Rule])
  case class Rule(name: String, body: Exp)
  sealed abstract class Exp
  case object Eps extends Exp
  case class Alt(lhs: Exp, rhs: Exp) extends Exp
  case class Cat(lhs: Exp, rhs: Exp) extends Exp
  case class Chr(value: Char) extends Exp
  case class Rep0(body: Exp) extends Exp
  case class Rep1(body: Exp) extends Exp
}
