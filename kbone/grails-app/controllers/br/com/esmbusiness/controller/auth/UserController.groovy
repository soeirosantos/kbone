package br.com.esmbusiness.controller.auth

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class UserController extends grails.plugins.springsecurity.ui.UserController {
	
}
