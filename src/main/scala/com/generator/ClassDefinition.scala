package com.generator

import com.Constants.{CLASS_DEFINITION, CLASS_NAME, NEW_LINE}
import com.Util

case class ClassDefinition(className: String,
                           classContent: String,
                           isValidationActive: Boolean = false) {
  val CLASS_STRUCTURE_TEMPLATE =
    NEW_LINE + "class " + CLASS_NAME + "{" + NEW_LINE + CLASS_DEFINITION + NEW_LINE + " }"
  val VALIDATE = "@Validated"
  def get(): String = {

    val classStructure = CLASS_STRUCTURE_TEMPLATE
      .replace(CLASS_NAME, Util.snakeToCamel(className, true))
      .replace(CLASS_DEFINITION, classContent)
    isValidationActive match {
      case true  => VALIDATE.concat(classStructure)
      case false => classStructure
    }
  }
}

//object ClassDefinition {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + ClassDefinition("sourabh", "raghav").get)
//  }
//}
