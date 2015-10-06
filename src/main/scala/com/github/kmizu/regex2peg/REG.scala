package com.github.kmizu.regex2peg

object REG {
  case class Grammar(body: Exp)
  sealed abstract class Exp
  case class Alt(lhs: Exp, rhs: Exp) extends Exp
  case class Cat(lhs: Exp, rhs: Exp) extends Exp
  case class Chr(value: Char) extends Exp
  case class Rep0(body: Exp) extends Exp
  case class Rep1(body: Exp) extends Exp
}
