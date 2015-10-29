package com.github.kmizu.regex2peg

object Regexp {
  case class Grammar(body: Exp)
  sealed abstract class Exp {
    def |(rhs: Exp): Alt = Alt(this, rhs)
    def ~(rhs: Exp): Cat = Cat(this, rhs)
    def * : Rep0 = Rep0(this)
  }
  case object Eps extends Exp
  case class Alt(lhs: Exp, rhs: Exp) extends Exp
  case class Cat(lhs: Exp, rhs: Exp) extends Exp
  case class Chr(value: Char) extends Exp
  case class Rep0(body: Exp) extends Exp
}
