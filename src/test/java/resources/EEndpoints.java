package resources;

public enum EEndpoints {
	addNewPet("/pet"),
	getPetByStatus("/pet/findByStatus"),
	getPetById("/pet/{id}"),
	updatePet("/pet");
	
	private String resource;
	//Constructor of the enum
	EEndpoints(String resource) {
		
		this.resource = resource;
	}
	
	public String getResource() {
		
		return this.resource;
	}
	
	

}
