package com.github.kmizu.regex2peg

case class Generator(prefix: String) {
  private var index: Int = 0

  def symbol(): String = {
    index += 1
    prefix + index
  }
}
