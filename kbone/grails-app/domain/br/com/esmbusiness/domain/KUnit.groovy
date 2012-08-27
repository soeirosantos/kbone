package br.com.esmbusiness.domain

import br.com.esmbusiness.auth.User;

class KUnit {
	
    Date dateCreated
    Date lastUpdated
    String content

    User creator

    static hasMany = [tags:Tag]
	
    static constraints = {
         tags(nullable:false)
         content(blank:false, nullable:false, maxSize: 1000)
    }
	
    static mapping = {
    columns {
        tags column: 'kunit_id', joinTable:'kunit_tag', cascade:"none"
        }
    }
	
    def static findAllByTag(Tag tag, String orderBy){
        orderBy = orderBy ?: "desc"
        def c = KUnit.createCriteria()
        def results = c.list {
            tags {
                eq('id', tag.id)
            }
            order("dateCreated", orderBy)
        }
        return results
    }

    def static findAllByTag(Tag tag){
        findAllByTag(tag, null)
    }
}
