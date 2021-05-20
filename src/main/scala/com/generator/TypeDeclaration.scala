package com.generator

import com.Constants.{ACCESS_TYPE, DATA_NAME, DATA_TYPE, FIRST_INDENT_TAB}
import com.Util

case class TypeDeclaration(dataType: String, newKey: String) {
  val TYPE_DECLARATION_TEMPLATE = FIRST_INDENT_TAB + ACCESS_TYPE + " " + DATA_TYPE + " " + DATA_NAME + ";\n"
  def get(): String = {
    TYPE_DECLARATION_TEMPLATE
      .replace(ACCESS_TYPE, "private")
      .replace(DATA_TYPE, Util.snakeToCamel(dataType, true))
      .replace(DATA_NAME, Util.snakeToCamel(newKey, false))
  }
}

//object TypeDeclaration {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + TypeDeclaration("sourabh", "raghav").get)
//  }
//}
