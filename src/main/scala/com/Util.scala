package com

object Util {
  def snakeToCamel(str: String, isFirstCharUpper: Boolean): String =
    Option(str).isDefined match {
      case true =>
        var lowerCamelCase = str.toList
          .foldLeft(List.empty[Char]) {
            case ('_' :: xs, c) => c.toUpper :: xs
            case ('-' :: xs, c) => c.toUpper :: xs
            case (xs, c)        => c :: xs
          }
          .reverse
          .mkString
        isFirstCharUpper match {
          case false =>
            lowerCamelCase.substring(0, 1).toLowerCase + lowerCamelCase
              .substring(1, lowerCamelCase.length)
          case true =>
            lowerCamelCase.substring(0, 1).toUpperCase + lowerCamelCase
              .substring(1, lowerCamelCase.length)
        }
      case false => ""
    }

}
