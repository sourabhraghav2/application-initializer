package com.generator

import com.Constants.{
  CLASS_DEFINITION,
  CLASS_NAME,
  ENDPOINT_REQUEST,
  ENDPOINT_RESPONSE,
  FIRST_INDENT_TAB,
  NEW_LINE,
  SERVICE_NAME
}
import com.{ServiceEndPoint, Util}

case class ServiceAndImplDefinition(service: ServiceEndPoint,
                                    methodName: String) {
  val SERVICE_IMPL_TEMPLATE =
    "@Slf4j" + NEW_LINE + "@Service" + NEW_LINE + "@AllArgsConstructor" + NEW_LINE + "@RefreshScope" + NEW_LINE +
      "public class " + SERVICE_NAME + " implements BaseService<" + ENDPOINT_REQUEST + ", " + ENDPOINT_RESPONSE + "> {" + NEW_LINE +
      CLASS_DEFINITION + NEW_LINE +
      "}"
  val METHOD_DECLARATION =
    FIRST_INDENT_TAB + "public " + ENDPOINT_RESPONSE + " execute(" + ENDPOINT_REQUEST + " baseRequest) {" + NEW_LINE +
      FIRST_INDENT_TAB + FIRST_INDENT_TAB + "return null;" + NEW_LINE +
      FIRST_INDENT_TAB + "}"
  def get(): String = {
    val serviceContent = (new StringBuilder)
      .append(
        service.adaptors
          .map(_.name)
          .map(each => TypeDeclaration(each, each).get())
          .mkString(NEW_LINE)
      )
      .append(
        METHOD_DECLARATION
          .replace(
            ENDPOINT_REQUEST,
            Util.snakeToCamel(methodName + "Request", true)
          )
          .replace(
            ENDPOINT_RESPONSE,
            Util.snakeToCamel(methodName + "Response", true)
          )
      )
      .toString

    SERVICE_IMPL_TEMPLATE
      .replace(SERVICE_NAME, Util.snakeToCamel(service.name, true))
      .replace(
        ENDPOINT_REQUEST,
        Util.snakeToCamel(methodName + "Request", true)
      )
      .replace(
        ENDPOINT_RESPONSE,
        Util.snakeToCamel(methodName + "Response", true)
      )
      .replace(CLASS_DEFINITION, serviceContent)
  }
}
