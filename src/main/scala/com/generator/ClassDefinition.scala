package com.generator

import com.Constants.{CLASS_DEFINITION, CLASS_NAME, NEW_LINE}
import com.Util

case class ClassDefinition(className: String, classContent: String) {
  val CLASS_STRUCTURE_TEMPLATE =
    NEW_LINE + "class " + CLASS_NAME + "{" + NEW_LINE + CLASS_DEFINITION + NEW_LINE + " }"

  def get(): String = {
    CLASS_STRUCTURE_TEMPLATE
      .replace(CLASS_NAME, Util.snakeToCamel(className, true))
      .replace(CLASS_DEFINITION, classContent)
  }
}

object ClassDefinition {
  def main(args: Array[String]): Unit = {
    print("Prop : " + ClassDefinition("sourabh", "raghav").get)
  }
}
