package br.com.esmbusiness.domain

import br.com.esmbusiness.auth.User;

class Event {

    static mapWith = "mongo"
    static transients = ["user", "tag", "action"]

    Long userId
    Long kUnitId
    Long tagId
    Integer typeCode
    Date dateCreated

    User getUser() {
        return User.get(userId)
    }
	
    Tag getTag() {
        return Tag.get(tagId)
    }
	
    String getAction(){
        EventType.fromCode(typeCode).toString()
    }

    static mapping = {
	userId index:true
	kUnitId index:true
	tagId index:true
    }
	
    static constraints = {
	userId(blank:false, nullable:false)
	tagId(blank:false, nullable:false)
	kUnitId(blank:true, nullable:true)
	typeCode(blank:false, nullable:false)
    }
}
