package com.generator

import com.Constants.{FIRST_INDENT_TAB, JSON_PROPERTY}

case class JsonPropertyDeclaration(propertyName: String) {
  val JSON_PROPERTY_TEMPLATE = FIRST_INDENT_TAB + "@JsonProperty(\"" + JSON_PROPERTY + "\")\n"
  def get(): String = {
    JSON_PROPERTY_TEMPLATE
      .replace(JSON_PROPERTY, propertyName)
  }
}

//object JsonProperty {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + JsonProperty("sourabh").get)
//  }
//}
