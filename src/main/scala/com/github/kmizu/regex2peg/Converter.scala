package com.github.kmizu.regex2peg

object Converter {
  private def convert(exp: REG.Exp, k: PEG.Exp): PEG.Exp = exp match {
    case REG.Chr(ch) => PEG.Cat(PEG.Chr(ch), k)
    case REG.Cat(e1, e2) => convert(e1, convert(e2, k))
    case REG.Alt(e1, e2) => PEG.Alt(convert(e1, k), convert(e2, k))
  }
}
