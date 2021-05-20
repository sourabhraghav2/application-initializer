package com.generator

import com.Constants._
import com.Util
import com.fasterxml.jackson.databind.node.JsonNodeType._
import com.fasterxml.jackson.databind.node.{JsonNodeFactory, ObjectNode}
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.springframework.stereotype.Component
import io.vavr.control.Option
@Component
class GenerateClass {

  private val om = new ObjectMapper

  def jsonToJavaClass(className: String, str: String): String = {
    val node = om.readTree(str)

    val ss = node.fieldNames
    val classOuter = new StringBuilder
    val classContent = new StringBuilder
    while (ss.hasNext()) {
      val key = ss.next
      val jsonProperty = Option
        .of(key)
        .filter(
          each =>
            each.toCharArray.toList
              .sliding(2)
              .count(
                a =>
                  Character.isUpperCase(a(0)) == true && Character
                    .isUpperCase(a(1)) == true
              ) > 0 || each.contains("_")
        )
        .map(each => JsonPropertyDeclaration(each).get)
        .getOrNull() match {
        case null => ""
        case o    => o
      }
      val newKey = Util.snakeToCamel(key.toLowerCase(), false)

      val dataType = node.get(key) match {
        case obj if STRING.toString == obj.getNodeType.toString => "String"
        case obj if NUMBER.toString == obj.getNodeType.toString => "Integer"
        case obj
            if NUMBER.toString == obj.getNodeType.toString && obj.toString
              .contains(".") =>
          "Double"
        case obj
            if NUMBER.toString == obj.getNodeType.toString && obj.toString.length > 4 =>
          "Long"
        case obj if BOOLEAN.toString == obj.getNodeType.toString => "Boolean"
        case obj if ARRAY.toString == obj.getNodeType.toString =>
          "List<" + Util.snakeToCamel(key, true) + ">"
        case obj if OBJECT.toString == obj.getNodeType.toString =>
          key.substring(0, 1).toUpperCase + key.substring(1, key.length)
        case _ => "String"

      }
      classContent
        .append(jsonProperty)
        .append(TypeDeclaration(dataType, newKey).get)
      if (OBJECT.toString == node.get(key).getNodeType.toString) {
        classOuter.append(jsonToJavaClass(key, node.get(key).toString))
      } else if (ARRAY.toString == node.get(key).getNodeType.toString) {
        val newNode = JsonNodeFactory.instance.objectNode
        node
          .get(key)
          .forEach(
            (eachNode: JsonNode) =>
              newNode.setAll(eachNode.asInstanceOf[ObjectNode])
          )
        classOuter.append(jsonToJavaClass(key, newNode.toString))

      }
    }
    classOuter.append(ClassDefinition(className, classContent.toString).get())
    print(classOuter.toString)
    classOuter.toString
  }

}
