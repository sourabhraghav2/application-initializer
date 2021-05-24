package com.generator

import com.Constants.{
  ACCESS_TYPE,
  DATA_NAME,
  DATA_TYPE,
  FIRST_INDENT_TAB,
  NEW_LINE
}
import com.Util

case class TypeDeclaration(dataType: String,
                           newKey: String,
                           isNotNull: Boolean = false) {
  val TYPE_DECLARATION_TEMPLATE = FIRST_INDENT_TAB + ACCESS_TYPE + " " + DATA_TYPE + " " + DATA_NAME + ";\n"
  val NOT_NULL = FIRST_INDENT_TAB + "@NotNull" + NEW_LINE
  def get(): String = {
    val typeDeclaration = TYPE_DECLARATION_TEMPLATE
      .replace(ACCESS_TYPE, "private")
      .replace(DATA_TYPE, Util.snakeToCamel(dataType, true))
      .replace(DATA_NAME, Util.snakeToCamel(newKey, false))
    isNotNull match {
      case true  => NOT_NULL.concat(typeDeclaration)
      case false => typeDeclaration
    }
  }
}

//object TypeDeclaration {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + TypeDeclaration("sourabh", "raghav").get)
//  }
//}
