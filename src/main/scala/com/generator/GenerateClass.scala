package com.generator

import com.Constants.HardCode.{
  CLASS_STRUCTURE_TEMPLATE,
  JSON_PROPERTY_TEMPLATE,
  TYPE_DECLARATION_TEMPLATE
}
import com.Constants._
import com.Util
import com.fasterxml.jackson.databind.node.JsonNodeType._
import com.fasterxml.jackson.databind.node.{JsonNodeFactory, ObjectNode}
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.springframework.stereotype.Component
import io.vavr.control.Option
@Component
class GenerateClass {
  private val om = new ObjectMapper

  def jsonToJavaClass(className: String, str: String): String = {
    val node = om.readTree(str)

    val ss = node.fieldNames
    val classOuter = new StringBuilder
    val classContent = new StringBuilder
    while (ss.hasNext()) {
      val key = ss.next
      val jsonProperty = Option
        .of(key)
        .filter(
          each =>
            each.toCharArray.toList
              .sliding(2)
              .count(
                a =>
                  Character.isUpperCase(a(0)) == true && Character
                    .isUpperCase(a(1)) == true
              ) > 0 || each.contains("_")
        )
        .map(
          each => JSON_PROPERTY_TEMPLATE.toString.replace(JSON_PROPERTY, each)
        )
        .getOrNull() match {
        case null => ""
        case o    => o
      }
      val newKey = Util.snakeToCamel(key.toLowerCase(), false)

      val dataType = node.get(key) match {
        case obj if STRING.toString == obj.getNodeType.toString => "String"
        case obj if NUMBER.toString == obj.getNodeType.toString => "Integer"
        case obj
            if NUMBER.toString == obj.getNodeType.toString && obj.toString
              .contains(".") =>
          "Double"
        case obj
            if NUMBER.toString == obj.getNodeType.toString && obj.toString.length > 4 =>
          "Long"
        case obj if BOOLEAN.toString == obj.getNodeType.toString => "Boolean"
        case obj if ARRAY.toString == obj.getNodeType.toString =>
          "List<" + Util.snakeToCamel(key, true) + ">"
        case obj if OBJECT.toString == obj.getNodeType.toString =>
          key.substring(0, 1).toUpperCase + key.substring(1, key.length)
        case _ => "String"

      }
      classContent
        .append(jsonProperty)
        .append(
          TYPE_DECLARATION_TEMPLATE.toString
            .replace(ACCESS_TYPE, "private")
            .replace(DATA_TYPE, dataType)
            .replace(DATA_NAME, newKey)
        )
      if (OBJECT.toString == node.get(key).getNodeType.toString) {
        classOuter.append(jsonToJavaClass(key, node.get(key).toString))
      } else if (ARRAY.toString == node.get(key).getNodeType.toString) {
        val newNode = JsonNodeFactory.instance.objectNode
        node
          .get(key)
          .forEach(
            (eachNode: JsonNode) =>
              newNode.setAll(eachNode.asInstanceOf[ObjectNode])
          )
        classOuter.append(jsonToJavaClass(key, newNode.toString))

      }
    }
    classOuter.append(
      CLASS_STRUCTURE_TEMPLATE.toString
        .replace(CLASS_NAME, Util.snakeToCamel(className, true))
        .replace(CLASS_DEFINITION, classContent.toString)
    )
    print(classOuter.toString)
    classOuter.toString
  }

}

//object Generator {
//  def main(args: Array[String]): Unit = {
//    var ff = new Generator;
//    var res = ff.jsonToJavaClass(
//      "Temp",
//      "{\n                   \"hospital\":{\n                      \"hospital_code\":\"00000\",\n                      \"hospital_name\":\"โรงพยาบาลทดสอบระบบ\",\n                      \"his_identifier\" : \"HIS ABC v1.0\"\n                   },\n                   \"patient\":{\n                      \"CID\":\"0030700084500\",\n                      \"hn\":\"990029982\",\n                      \"patient_guid\":\"{73F287FF-5924-435F-B80A-8993A1349DAE}\",\n                      \"prefix\":\"นาย\",\n                      \"first_name\":\"ทดสอบ\",\n                      \"last_name\":\"ระบบ\",\n                      \"gender\":1,\n                      \"birth_date\":\"1984-10-12\",\n                      \"marital_status_id\":3,\n                      \"address\":\"22\",\n                      \"moo\":\"2\",\n                      \"road\":\"\",\n                      \"chw_code\":\"20\",\n                      \"amp_code\":\"04\",\n                      \"tmb_code\":\"05\",\n                      \"installed_line_connect\":\"Y\",\n                      \"home_phone\":\"0277777777\",\n                      \"mobile_phone\":\"\",\n                      \"ncd\":[\n                         \n                      ]\n                   },\n                   \"lab\":[\n                      \n                   ],\n                   \"immunization_plan\":[\n                      {\n                         \"vaccine_code\":\"C19\",\n                         \"immunization_plan_ref_code\":\"18\",\n                         \"treatment_plan_name\":\"Vaccine COVID-19\",\n                         \"practitioner_license_number\":\"ท.1234567\",\n                         \"practitioner_name\":\"LAB\",\n                         \"practitioner_role\":\"แพทย์\",\n                         \"vaccine_ref_name\":\"วัคซีน Covid 19 - ซิโนแวค\",\n                         \"schedule\":[\n                            {\n                               \"immunization_plan_schedule_ref_code\":\"33\",\n                               \"schedule_date\":\"2021-01-25\",\n                               \"treatment_number\":1,\n                               \"schedule_description\":\"ให้ Vaccine COVID-19 เข็มแรก\",\n                               \"complete\":\"Y\",\n                               \"visit_date\":\"2021-01-25\"\n                            },\n                            {\n                               \"immunization_plan_schedule_ref_code\":\"34\",\n                               \"schedule_date\":\"2021-02-24\",\n                               \"treatment_number\":2,\n                               \"schedule_description\":\"ให้ Vaccine COVID-19 เข็มที่ 2\",\n                               \"complete\":\"N\"\n                            }\n                         ]\n                      }\n                   ],\n                   \"visit\":{\n                      \"visit_guid\":\"{8A4298CF-6E0C-4338-926F-ACDDECF454FF}\",\n                      \"visit_ref_code\":\"640125141902\",\n                      \"visit_datetime\":\"2021-01-25T14:19:02.000\",\n                      \"claim_fund_pcode\":\"A2\",\n                      \"visit_observation\":{\n                         \"systolic_blood_pressure\":120,\n                         \"diastolic_blood_pressure\":80,\n                         \"body_weight_kg\":65,\n                         \"body_height_cm\":165,\n                         \"temperature\":37.5\n                      },\n                      \"visit_immunization\":[\n                         {\n                            \"visit_immunization_ref_code\":\"70\",\n                            \"immunization_datetime\":\"2021-01-26T13:30:29.000\",\n                            \"vaccine_code\":\"C19\",\n                            \"lot_number\":\"TestABCDE\",\n                            \"expiration_date\":\"2024-01-26\",\n                            \"vaccine_note\":\"test\",\n                            \"vaccine_ref_name\":\"วัคซีน Covid 19 - ซิโนแวค\",\n                            \"serial_no\":\"1234567890\",\n                            \"vaccine_manufacturer\" : \"Sinovac Life Sciences\" ,\n                \n                            \"vaccine_plan_no\":1,\n                            \"vaccine_route_name\":\"ฉีดเข้ากล้ามเนื้อ (Intramuscular)\",\n                            \"practitioner\":{\n                               \"license_number\":\"ท.1234567\",\n                               \"name\":\"นพ.ทดสอบ\",\n                               \"role\":\"แพทย์\"\n                            },\n                            \"immunization_plan_ref_code\":\"18\",\n                            \"immunization_plan_schedule_ref_code\":\"33\"\n                         }\n                      ],\n                      \"visit_immunization_reaction\":[\n                         {\n                            \"visit_immunization_reaction_ref_code\":\"17\",\n                            \"visit_immunization_ref_code\":\"70\",\n                            \"report_datetime\":\"2021-01-26T17:13:16.000\",\n                            \"reaction_detail_text\":\"ปวดศีรษะ (Headache) : test\",\n                            \"vaccine_reaction_type_id\":2,\n                            \"reaction_date\":\"2021-01-26\",\n                            \"vaccine_reaction_stage_id\":2,\n                            \"vaccine_reaction_symptom_id\":4\n                         }\n                      ],\n                      \"appointment\":[\n                         {\n                            \"appointment_ref_code\":\"710378\",\n                            \"appointment_datetime\":\"2021-02-24T09:00:00.000\",\n                            \"appointment_note\":\"หมายเหตุ\",\n                            \"appointment_cause\":\"นัดมารับวัคซีนเข็ม 2\",\n                            \"provis_aptype_code\":\"C19\",\n                            \"practitioner\":{\n                               \"license_number\":\"ท.1234567\",\n                               \"name\":\"นพ.ทดสอบ\",\n                               \"role\":\"แพทย์\"\n                            }\n                         }\n                      ]\n                   }\n                }"
//    );
//    print(res)
////    print(ff.snakeToCamel("sourabh-raghav", false))
//
//  }
//
//}
