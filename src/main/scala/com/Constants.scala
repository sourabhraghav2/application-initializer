package com

object Constants {

  var NEW_LINE: String = "\n"
  var SPACE_SEPARATOR: String = " "
  var FIRST_INDENT_TAB: String = "\t"
  var JSON_PROPERTY: String = "{jsonProperty}"
  var ACCESS_TYPE: String = "{accessType}"
  var DATA_TYPE: String = "{dataType}"
  var DATA_NAME: String = "{dataName}"
  var CLASS_NAME: String = "{className}"
  var CLASS_DEFINITION: String = "{classDefinition}"
  var TYPE_LIST: String = "{typeList}"
  var ENDPOINT_LIST: String = "{endPointList}"
  var METHOD_NAME: String = "{methodName}"
  var URI_NAME: String = "{uriName}"
  var ENDPOINT_RESPONSE: String = "{endpointResponse}"
  var ENDPOINT_REQUEST: String = "{endpointRequest}"
  var SERVICE_NAME: String = "{serviceName}"

  object ApiMethod extends Enumeration {
    type ApiMethod = Value
    val GET = Value("GET")
    val POST = Value("POST")
  }
  object HardCode extends Enumeration {
    type HardCode = Value
    val JSON_PROPERTY_TEMPLATE = Value(
      FIRST_INDENT_TAB + "@JsonProperty(\"" + JSON_PROPERTY + "\")\n"
    )
    val TYPE_DECLARATION_TEMPLATE = Value(
      FIRST_INDENT_TAB + ACCESS_TYPE + " " + DATA_TYPE + " " + DATA_NAME + ";\n"
    )
    val CLASS_STRUCTURE_TEMPLATE = Value(
      NEW_LINE + "class " + CLASS_NAME + "{" + NEW_LINE + CLASS_DEFINITION + NEW_LINE + " }"
    )
    val CONTROLLER_STRUCTURE_TEMPLATE = Value(
      "@Api(tags = \"Controller\")" + NEW_LINE +
        "@RestController" + NEW_LINE +
        "@RequestMapping" + NEW_LINE +
        "@Slf4j" + NEW_LINE +
        "@AllArgsConstructor" + NEW_LINE +
        "@AllArgsConstructor" + NEW_LINE +
        "public class " + CLASS_NAME + " {" + NEW_LINE +
        TYPE_LIST + NEW_LINE +
        ENDPOINT_LIST + NEW_LINE +
        "}" + NEW_LINE
    )
    val CONTROLLER_ENDPOINT_TEMPLATE = Value(
      "@" + METHOD_NAME + "(value = \"" + URI_NAME + "\", produces = MediaType.APPLICATION_JSON_VALUE)" + NEW_LINE +
        "public " + ENDPOINT_RESPONSE + " " + METHOD_NAME + "(@RequestBody @Validated " + ENDPOINT_REQUEST + " request) {" + NEW_LINE +
        "log.info(\"Inside " + URI_NAME + "\");" + NEW_LINE +
        "return " + SERVICE_NAME + ".execute(request);" + NEW_LINE +
        "}"
    )
  }

  def main(args: Array[String]): Unit = {
    print(HardCode.CONTROLLER_STRUCTURE_TEMPLATE.toString)
  }

}
