package com.generator

import com.Constants.{
  ACCESS_TYPE,
  CLASS_NAME,
  CONTENT,
  ENDPOINT_REQUEST,
  ENDPOINT_RESPONSE,
  FIRST_INDENT_TAB,
  METHOD_NAME,
  NEW_LINE
}
import com.Util

case class MethodDefinition(methodName: String, content: String = "") {
  val METHOD_STRUCTURE =
    NEW_LINE + FIRST_INDENT_TAB + " " + ACCESS_TYPE + " " + ENDPOINT_RESPONSE + " " + METHOD_NAME + "(" + ENDPOINT_REQUEST + " request)" + CONTENT

  def get(): String = {
    val res = METHOD_STRUCTURE
      .replace(
        ENDPOINT_REQUEST,
        Util.snakeToCamel(methodName + "Request", true)
      )
      .replace(
        ENDPOINT_RESPONSE,
        Util.snakeToCamel(methodName + "Response", true)
      )
      .replace(METHOD_NAME, Util.snakeToCamel(methodName, false))
      .replace(ACCESS_TYPE, "public")
      .replace(CONTENT, content.isBlank match {
        case true  => ";" + NEW_LINE
        case false => content
      })
    res
  }
}
