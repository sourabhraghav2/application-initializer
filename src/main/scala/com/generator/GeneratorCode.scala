package com.generator

import com.Constants._
import com.{
  GenerateCodeRequest,
  NextEndPoint,
  ServiceEndPoint,
  UIController,
  Util
}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GeneratorCode(implicit @Autowired generateClass: GenerateClass) {
  private def generateControllers(name: String, ctrl: UIController): String = {
    val res = name.isEmpty match {
      case false =>
        ControllerDefinition(
          ctrl.name,
          ctrl.endPoints
            .map(_.service)
            .toList,
          ctrl.endPoints.toList
        ).get
      case true => ""
    }
    print("generateControllers: " + res)
    res
  }

  private def generateServices(service: ServiceEndPoint, methodName: String) = {
    val res = SERVICE_INTERFACE.concat(
      NEW_LINE.concat(ServiceAndImplDefinition(service, methodName).get())
    )
    print("generateServices: " + res)
    res
  }

  private def generateAdaptor(name: String,
                              adaptors: List[NextEndPoint]): String = {
    val res = (new StringBuilder)
      .append(
        InterfaceDefinition(
          name,
          adaptors
            .map(each => each.name)
            .map(each => MethodDefinition(each).get)
            .mkString(NEW_LINE)
        ).get
      )
      .toString
    res
  }

  def jsonToCode(request: GenerateCodeRequest): String = {
    val res = (new StringBuilder)
      .append(
        request.controllers
          .flatMap(_.endPoints)
          .map(
            endPoint => generateServices(endPoint.service, endPoint.methodName)
          )
          .mkString(NEW_LINE)
      )
      .append(
        request.controllers
          .flatMap(_.endPoints)
          .map(_.service)
          .flatMap(_.adaptors)
          .map(
            adaptor => generateAdaptor(adaptor.name, adaptor.endPoints.toList)
          )
          .mkString(NEW_LINE)
      )
      .toString()
    res
  }
}
//object GeneratorCode {
//  def main(args: Array[String]): Unit = {
//    print(Util.snakeToCamel("Sourabh-raghav", false))
//
//  }
//}
