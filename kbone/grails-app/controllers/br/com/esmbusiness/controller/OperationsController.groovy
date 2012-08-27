package br.com.esmbusiness.controller

import grails.plugins.springsecurity.Secured

import org.hibernate.ObjectNotFoundException;

import br.com.esmbusiness.auth.User;
import br.com.esmbusiness.domain.KUnit;

@Secured('ROLE_ADMIN')
class OperationsController {

    def index() { }
	
	
	/**
	 * Populate admin user where knowledge unit is without 
	 * a user
	 * This situation was created when there was not relationship
	 * KUnit->User.   
	 *
	 */
	def fixUnitsWithoutUser(){
		def allUnits = KUnit.list()
		def i = 0
		allUnits.each {
			try{
				it.creator
			} catch (ObjectNotFoundException e) {
				i++
				it.creator = User.get(1)
				it.save(flush:true)
			}
		}
		render "Total of " + i + " units updated"
	}
}
