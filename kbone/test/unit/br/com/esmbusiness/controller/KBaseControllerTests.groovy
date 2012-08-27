package br.com.esmbusiness.controller



import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import br.com.esmbusiness.domain.KUnit;
import br.com.esmbusiness.domain.Tag;

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(KBaseController)
//@Mock([Tag, KUnit])
@TestMixin(DomainClassUnitTestMixin)
class KBaseControllerTests {

	def populateValidParams(params) {
		assert params != null
		params["id"] = '1'
		params["content"] = "um novo conteúdo na sessao de estudo" 
	}
	
	void testIndex() {
		controller.index()
		assert "/KBase/tags" == response.redirectedUrl
	}

	void testList() {
		mockDomain(Tag)
		def model = controller.tags()
		assert model.allTags.size() == 0
		assert model.allTagsTotal == 0
	}

	void testSessionStudyFor(){
		mockDomain(Tag,[[name:"python"], [name:"java"]])
		mockDomain(KUnit, [[content:"abcd", tags:[[id:1L, name:"python"]]],[content:"abcde", tags:[[id:1L, name:"python"]]]])
		
		populateValidParams(params)
		
		def model = controller.study_session_of()
		
		assert model.subject.name == "python" 
		
	}
	
	void testInsertUnitOfKnowledge(){
		mockDomain(Tag,[[id:1L, name:"python"], [id:2L, name:"java"]])
		mockDomain(KUnit)
		
		populateValidParams(params)

		params.content = ""
		
		def model = controller.insert_unit_of_knowledge()

		assert controller.flash.message == "qaaack!"
		
		controller.response.reset()
		
		populateValidParams(params)

		model = controller.insert_unit_of_knowledge()
		
		assert response.redirectedUrl == '/KBase/study_session_of/1'
		assert KUnit.count() == 1
	}
		
	void testPrepareToChange(){
		//XXX: this test is not working. 
		//For some reason the mockDomain method is not putting a KUnit available
		mockDomain(KUnit, [[content:"abcd"],[content:"abcd"],[content:"abcd"]])

		controller.prepareToChangeKUnit(2L)		
		
		assert controller.flash.message == "was not possible to find unit of knowledge of id 2"
		
//		controller.response.reset()
//		
//		def kUnit = controller.prepareToChangeKUnit(1)
//		
//		assert controller.flash.message == null
//		assert kUnit != null
	}
}
