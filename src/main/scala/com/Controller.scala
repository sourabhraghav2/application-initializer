package com

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{
  GetMapping,
  PostMapping,
  RequestBody,
  RestController
}

@RestController
class Controller(@Autowired service: CodeGeneratorService) {

  @GetMapping(Array("/test"))
  def test(): String = " working!"

  @PostMapping(Array("/generate-code"))
  def generator(@RequestBody request: GenerateCodeRequest): String = {
    val om = new ObjectMapper();
    print("controller list : " + om.writeValueAsString(request))
    service.generateCode(request)
  }

  @PostMapping(Array("/generate-classes"))
  def generateClasses(@RequestBody request: String): String =
    service.generateClasses(request)

}
