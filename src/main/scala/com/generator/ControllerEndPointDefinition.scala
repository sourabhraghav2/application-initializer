package com.generator

import com.Constants._
import com.{ServiceEndPoint, Util}
import org.springframework.beans.factory.annotation.Autowired

case class ControllerEndPointDefinition(apiMethod: String,
                                        uri: String,
                                        methodName: String,
                                        service: ServiceEndPoint) {

  val CONTROLLER_ENDPOINT_TEMPLATE =
    FIRST_INDENT_TAB + "@" + API_METHOD_NAME + "(value = \"" + URI_NAME + "\", produces = MediaType.APPLICATION_JSON_VALUE)" + NEW_LINE +
      FIRST_INDENT_TAB + "public " + ENDPOINT_RESPONSE + " " + METHOD_NAME + "(@RequestBody @Validated " + ENDPOINT_REQUEST + " request) {" + NEW_LINE +
      FIRST_INDENT_TAB + FIRST_INDENT_TAB + "log.info(\"Inside " + URI_NAME + "\");" + NEW_LINE +
      FIRST_INDENT_TAB + FIRST_INDENT_TAB + "return " + SERVICE_NAME + ".execute(request);" + NEW_LINE +
      FIRST_INDENT_TAB + "}"

  def get(): String = {
    CONTROLLER_ENDPOINT_TEMPLATE
      .replace(API_METHOD_NAME, apiMethod match {
        case method if method == ApiMethod.GET.toString => "GetMapping"
        case method if method == ApiMethod.POST.toString =>
          "PostMapping"
      })
      .replace(METHOD_NAME, Util.snakeToCamel(methodName, false))
      .replace(URI_NAME, uri)
      .replace(
        ENDPOINT_REQUEST,
        Util.snakeToCamel(methodName + "Request", true)
      )
      .replace(
        ENDPOINT_RESPONSE,
        Util.snakeToCamel(methodName + "Response", true)
      )
      .replace(URI_NAME, Util.snakeToCamel(methodName, true))
      .replace(SERVICE_NAME, Util.snakeToCamel(service.name, false))
  }

}

//object TypeDeclaration {
//  def main(args: Array[String]): Unit = {
//    print("Prop : " + TypeDeclaration("sourabh", "raghav").get)
//  }
//}
