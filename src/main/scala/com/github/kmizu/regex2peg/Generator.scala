package com.github.kmizu.regex2peg

case class Generator(prefix: String, var index: Int) {
  def symbol(): String = {
    index += 1
    prefix + index
  }
}
