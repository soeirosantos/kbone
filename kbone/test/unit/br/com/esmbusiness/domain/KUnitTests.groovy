package br.com.esmbusiness.domain



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(KUnit)
class KUnitTests {

    void testConstraints() {
		
		mockForConstraintsTests(KUnit)
		
		def kUnitWithoutTags = new KUnit()
		
		assert !kUnitWithoutTags.validate()
		assert "nullable" == kUnitWithoutTags.errors["tags"]

		def kUnitWithTags = new KUnit()
		
		kUnitWithTags.addToTags(new Tag(name:"Python"))
		kUnitWithTags.content = "lou"
		
		assert kUnitWithTags.validate()

		def kUnitWithoutContent = new KUnit()
		
		kUnitWithoutContent.addToTags(new Tag(name:"Python"))
		
		assert !kUnitWithoutContent.validate()
		assert "nullable" == kUnitWithoutContent.errors["content"]

		kUnitWithoutContent.content = ""
		
		assert !kUnitWithoutContent.validate()
		assert "blank" == kUnitWithoutContent.errors["content"]

		def validKUnit = new KUnit(content:"abcd")
		validKUnit.addToTags(new Tag(name:"Python"))
		
		assert validKUnit.validate()
		
    }
}
