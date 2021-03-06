package br.com.esmbusiness.controller

import org.springframework.dao.DataIntegrityViolationException

import br.com.esmbusiness.auth.User;
import br.com.esmbusiness.domain.Event
import br.com.esmbusiness.domain.EventType;
import br.com.esmbusiness.domain.Tag
import br.com.esmbusiness.domain.KUnit
import grails.converters.*
import grails.plugins.springsecurity.Secured;

@Secured('IS_AUTHENTICATED_REMEMBERED')
class KBaseController {

	def springSecurityService
	
	def eventService
	
	def index() {
		redirect(action: "tags", params: params)
	}
		
	def tags() {
		def tagsView = []
		def totalUnits = KUnit.list().size()
		Tag.list().each {
			def link = "/study_session_of/${it.id}"
			def weight = ( KUnit.findAllByTag(it).size() / totalUnits ) * 100
			tagsView << new TagView(text:it.name, weight:weight, link:"study_session_of/${it.id}")	
		}
		[allTagsJSON:tagsView as JSON]
	}
	
	//XXX: do url mapping and fix method name
	def new_study_session(){
		def tag = Tag.findOrSaveByName(params.name)
		redirect(action: "study_session_of", id: tag.id)
	}

	//XXX: do url mapping and fix method name
	def study_session_of() {
		def tag = Tag.get(params['id'])
		def reading_mode = new Boolean(params['reading_mode'])
		def orderBy = reading_mode ? "asc" : "desc"
		if(tag) {
			def units = KUnit.findAllByTag(tag, orderBy)
			def isUpdatingKUnit = params['unit.id'] != null
			def unit = isUpdatingKUnit ? prepareToChangeKUnit(params['unit.id']) : null
			[units:units, tag:tag, kUnit:unit, reading_mode:reading_mode]
		} else {
			redirect(action:'index')
		}
	}
		
	//XXX: do url mapping and fix method name	
	def remove_unit_of_knowledge(){
		def unit = prepareToChangeKUnit(params['unit.id'])
		def tag = Tag.get(params['id'])
		try {
            unit.delete(flush: true)
            eventService.save(getLoggedUserId(), unit.id, tag.id, EventType.DELETE.code)
        }catch (DataIntegrityViolationException e) {
			flash.message = "quaaaaaaaaaack"
        } 
		redirect(action: "study_session_of", id: params.id)
	}
	
	
	//XXX: do url mapping and fix method name
	def insert_unit_of_knowledge() {
		def tag = Tag.get(params['id'])
		if(tag){
			def unit = new KUnit(content:params.content)
			unit.addToTags(tag)
			unit.creator = getLoggedUser()
			
			if (!unit.save(flush: true)) {
				flash.message = "qaaack!"
			} else {
				eventService.save(getLoggedUserId(), unit.id, tag.id, EventType.CREATE.code)
			}
			redirect(action: "study_session_of", id: params.id)
		} else {
			redirect(action: "tags")
		}
	}

	//XXX: do url mapping and fix method name	
	def update_unit_of_knowledge() {
		def unit = KUnit.get(params['unit.id'])
		def tag = Tag.get(params['id'])
		if(!unit){
			flash.message = "was not possible to find unit of knowledge of id ${id}"
			redirect(action: "study_session_of", id: params.id)
			return
		}
		unit.content=params.content
		if (!unit.save(flush: true)) {
			flash.message = "qaaack!"
		} else {
			eventService.save(getLoggedUserId(), unit.id, tag.id, EventType.UPDATE.code)
		}
		redirect(action: "study_session_of", id: params.id)
	}
	
	def prepareToChangeKUnit(id){
		def kUnit = KUnit.get(id)
		if (!kUnit) {
			flash.message = "was not possible to find unit of knowledge of id ${id}"
			return
		}
		kUnit
	}
	
	private getLoggedUser(){
		return User.get(getLoggedUserId())
	}

	private getLoggedUserId(){
		return springSecurityService.principal.id
	}
}
