package com

import com.fasterxml.jackson.databind.node.ObjectNode

case class GenerateCodeRequest(controllers: Array[UIController]) {
  def this() = this(null)
}
case class UIController(name: String, endPoints: Array[UIEndPoint]) {
  def this() = this(null, null)
}
case class UIEndPoint(uri: String,
                      methodName: String,
                      apiMethod: String,
                      request: ObjectNode,
                      response: ObjectNode,
                      requestMandatory: Array[String],
                      responseMandatory: Array[String],
                      service: ServiceEndPoint) {
  def this() = this(null, null, null, null, null, null, null, null)
}
case class ServiceEndPoint(name: String, adaptors: Array[AdaptorEndPoint]) {
  def this() = this(null, null)
}
case class AdaptorEndPoint(name: String, endPoints: Array[NextEndPoint]) {
  def this() = this(null, null)
}
case class NextEndPoint(name: String,
                        uri: String,
                        request: Object,
                        response: Object) {
  def this() = this(null, null, null, null)
}
