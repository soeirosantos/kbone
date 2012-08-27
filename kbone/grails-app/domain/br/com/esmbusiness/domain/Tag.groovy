package br.com.esmbusiness.domain

class Tag {

    String name
    String description
	
    static constraints = {
        name(blank:false, nullable:false, unique:true)
	description(blank:true, nullable:true, maxSize:1000)
    }

    def String toString(){
        name
    }

}
