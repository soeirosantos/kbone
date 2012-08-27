package br.com.esmbusiness.controller

import br.com.esmbusiness.domain.Event
import grails.plugins.springsecurity.Secured;

@Secured('IS_AUTHENTICATED_REMEMBERED')
class EventController {

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
		params.sort = "dateCreated"
		params.order = "desc"
        [eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
    }

}