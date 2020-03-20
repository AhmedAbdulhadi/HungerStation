package resources;

public enum HttpMethod {

	POST("POST"),
	GET("GET"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private String method;
	//Constructor of the enum
	HttpMethod(String method) {
		
		this.method = method;
	}
	
	public String getHttpMethod() {
		
		return this.method;
	}
}
