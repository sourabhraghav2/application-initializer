package com.generator

import com.Constants.{CLASS_DEFINITION, CLASS_NAME, CONTENT, NEW_LINE}
import com.Util

case class InterfaceDefinition(interfaceName: String,
                               content: String,
                               isServiceInterface: Boolean = false) {
  val INTERFACE_STRUCTURE_TEMPLATE = NEW_LINE +
    "public interface " + CLASS_NAME + " {" + CONTENT + "}"

  val INTERFACE_NAME =
    "BaseService<T extends BaseRequest, V extends BaseResponse>"
  val interfaceContent = "V execute(T var1);"

  def get(): String = {
    val res = isServiceInterface match {
      case true =>
        INTERFACE_STRUCTURE_TEMPLATE
          .replace(CLASS_NAME, INTERFACE_NAME)
          .replace(CONTENT, interfaceContent)
      case false =>
        INTERFACE_STRUCTURE_TEMPLATE
          .replace(CLASS_NAME, Util.snakeToCamel(interfaceName, true))
          .replace(CONTENT, content)

    }
    res
  }
}

//object ClassDefinition {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + ClassDefinition("sourabh", "raghav").get)
//  }
//}
