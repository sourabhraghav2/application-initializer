package com

object Constants {

  val NEW_LINE: String = "\n"
  val SPACE_SEPARATOR: String = " "
  val FIRST_INDENT_TAB: String = "\t"
  val JSON_PROPERTY: String = "{jsonProperty}"
  val ACCESS_TYPE: String = "{accessType}"
  val DATA_TYPE: String = "{dataType}"
  val DATA_NAME: String = "{dataName}"
  val CLASS_NAME: String = "{className}"
  val CLASS_DEFINITION: String = "{classDefinition}"
  val TYPE_LIST: String = "{typeList}"
  val ENDPOINT_LIST: String = "{endPointList}"
  val METHOD_NAME: String = "{methodName}"
  val API_METHOD_NAME: String = "{apiMethodName}"
  val URI_NAME: String = "{uriName}"
  val ENDPOINT_RESPONSE: String = "{endpointResponse}"
  val ENDPOINT_REQUEST: String = "{endpointRequest}"
  val SERVICE_NAME: String = "{serviceName}"
  val CONTENT: String = "{content}"
  val SERVICE_INTERFACE: String =
    "public interface BaseService<T extends BaseRequest, V extends BaseResponse> {V execute(T var1);}"

  object ApiMethod extends Enumeration {
    type ApiMethod = Value
    val GET = Value("GET")
    val POST = Value("POST")
  }
}
