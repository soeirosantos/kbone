package br.com.esmbusiness.controller.auth

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class AclEntryController extends grails.plugins.springsecurity.ui.AclEntryController {

}
