package br.com.esmbusiness.domain

enum EventType {

	CREATE(1), UPDATE(2), DELETE(3);

	Integer code
	
	private EventType(Integer code){
		this.code = code
	}
	
	def static EventType fromCode(Integer code){
		switch (code) {
			case 1:
				return CREATE
				break;
			case 2:
				return UPDATE
				break;
			case 3:
				return DELETE
				break;
			default:
				return null
			break;
		}
		
	}
	
	def String toString(){
		switch (this) {
			case CREATE:
				return "created"
				break;
			case UPDATE:
				return "updated"
				break;
			case DELETE:
				return "removed"
				break;
			default:
				return null
			break;
		}
	}
}
