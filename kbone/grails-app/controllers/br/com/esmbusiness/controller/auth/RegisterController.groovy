package br.com.esmbusiness.controller.auth

import grails.plugins.springsecurity.ui.RegisterCommand

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {
	
	def register = { RegisterCommand command ->
		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}

		String salt = saltSource instanceof NullSaltSource ? null : command.username
		def user = lookupUserClass().newInstance(email: command.email, username: command.username,
				accountLocked: false, enabled: true)

		RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
	
		redirect(controller: "KBase", action: "tags")
	}
	
}
