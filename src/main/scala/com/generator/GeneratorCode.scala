package com.generator

import com.Constants._
import com.{GenerateCodeRequest, Util}
import org.springframework.stereotype.Component

@Component
class GeneratorCode {
  private def generateControllers(className: String, str: String) = ""
  private def generateServices(className: String, str: String) = ""
  private def generateAdaptor(className: String, str: String) = ""
  def jsonToCode(request: GenerateCodeRequest): String = {
    request.controllers
      .map(each => {
        val services = each.endPoints
          .map(_.service)
          .map(each => {
            HardCode.TYPE_DECLARATION_TEMPLATE.toString
              .replace(ACCESS_TYPE, "private")
              .replace(DATA_TYPE, Util.snakeToCamel(each.name, true))
              .replace(DATA_NAME, Util.snakeToCamel(each.name, false))
          })
          .mkString("")
        val endPoints = each.endPoints
          .map(each => {
            HardCode.CONTROLLER_ENDPOINT_TEMPLATE.toString
              .replace(METHOD_NAME, each.apiMethod match {
                case method if method == ApiMethod.GET.toString => "GetMapping"
                case method if method == ApiMethod.POST.toString =>
                  "PostMapping"
              })
              .replace(URI_NAME, each.uri)
              .replace(
                ENDPOINT_REQUEST,
                Util.snakeToCamel(each.methodName + "Request", true)
              )
              .replace(
                ENDPOINT_RESPONSE,
                Util.snakeToCamel(each.methodName + "Response", true)
              )
              .replace(URI_NAME, Util.snakeToCamel(each.methodName, true))
              .replace(
                SERVICE_NAME,
                Util.snakeToCamel(each.service.name, false)
              )

          })
          .mkString("")

        each.name.isEmpty match {
          case false =>
            HardCode.CONTROLLER_STRUCTURE_TEMPLATE.toString
              .replace(CLASS_NAME, each.name)
              .replace(TYPE_LIST, services)
              .replace(ENDPOINT_LIST, endPoints)
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
