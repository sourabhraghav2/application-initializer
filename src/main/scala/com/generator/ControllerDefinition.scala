package com.generator

import com.Constants.{CLASS_NAME, ENDPOINT_LIST, NEW_LINE, TYPE_LIST}
import com.fasterxml.jackson.databind.ObjectMapper
import com.{ServiceEndPoint, UIEndPoint, Util}
import org.springframework.beans.factory.annotation.Autowired

case class ControllerDefinition(name: String,
                                services: List[ServiceEndPoint],
                                endPoints: List[UIEndPoint]) {

  val CONTROLLER_STRUCTURE_TEMPLATE =
    NEW_LINE + "@Api(tags = \"Controller\")" + NEW_LINE +
      "@RestController" + NEW_LINE +
      "@RequestMapping" + NEW_LINE +
      "@Slf4j" + NEW_LINE +
      "@AllArgsConstructor" + NEW_LINE +
      "@AllArgsConstructor" + NEW_LINE +
      "public class " + CLASS_NAME + " {" + NEW_LINE +
      TYPE_LIST + NEW_LINE +
      ENDPOINT_LIST + NEW_LINE +
      "}" + NEW_LINE

  def get(implicit generateClass: GenerateClass): String = {

    endPoints
      .map(each => {
        val stringBuilder = new StringBuilder
        stringBuilder.append(
          generateClass.jsonToJavaClass(
            Util.snakeToCamel(each.methodName + "Request", true),
            each.request.toString,
            each.requestMandatory.toList
          )
        )
        stringBuilder.append(
          generateClass.jsonToJavaClass(
            Util.snakeToCamel(each.methodName + "Response", true),
            each.response.toString,
            each.responseMandatory.toList
          )
        )
        stringBuilder.toString
      })
      .mkString(NEW_LINE)
      .concat(
        CONTROLLER_STRUCTURE_TEMPLATE
          .replace(CLASS_NAME, name)
          .replace(
            TYPE_LIST,
            services
              .map(each => TypeDeclaration(each.name, each.name).get)
              .mkString("")
          )
          .replace(
            ENDPOINT_LIST,
            endPoints
              .map(each => {
                ControllerEndPointDefinition(
                  each.apiMethod,
                  each.uri,
                  each.methodName,
                  each.service
                ).get
              })
              .mkString("")
          )
      )

  }
}
