package com.generator

import com.Constants.HardCode.Value
import com.Constants._
import com.{GenerateCodeRequest, Util}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GeneratorCode(implicit @Autowired generateClass: GenerateClass) {
  private def generateControllers(className: String, str: String) = ""
  private def generateServices(className: String, str: String) = ""
  private def generateAdaptor(className: String, str: String) = ""
  def jsonToCode(request: GenerateCodeRequest): String = {
    request.controllers
      .map(each => {
        each.name.isEmpty match {
          case false =>
            ControllerDefinition(
              each.name,
              each.endPoints
                .map(_.service)
                .toList,
              each.endPoints.toList
            ).get
          case true => ""
        }
      })
      .mkString("")
  }
}
//object GeneratorCode {
//  def main(args: Array[String]): Unit = {
//    print(Util.snakeToCamel("Sourabh-raghav", false))
//
//  }
//}
