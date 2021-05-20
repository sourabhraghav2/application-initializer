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
  var API_METHOD_NAME: String = "{apiMethodName}"
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

  }

}
