package com

import com.generator.{GenerateClass, GeneratorCode}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

trait CodeGeneratorService {
  def generateCode(request: GenerateCodeRequest): String
  def generateClasses(request: String): String
}
@Service
class ServiceImpl(@Autowired classGenerator: GenerateClass, @Autowired codeGenerator: GeneratorCode) extends CodeGeneratorService {
  override def generateCode(request: GenerateCodeRequest): String = codeGenerator.jsonToCode(request);
  override def generateClasses(request: String): String           = classGenerator.jsonToJavaClass("Temp", request);
}
