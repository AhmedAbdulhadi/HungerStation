package entities;

import java.util.ArrayList;

public class Pet {

	private long id;
	private Category category;
	private String name;
	private ArrayList<String> photoUrls;
	private ArrayList<Tags> tags;
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public ArrayList<Tags> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Pet getObjData() {
		 Pet objPet = new Pet();
		objPet.setId(1);
		objPet.setName("Carlos");
		objPet.setStatus("Available");
		
		Category objCategory = new Category();
		objCategory.setId(11);
		objCategory.setName("Dogs");
		
		objPet.setCategory(objCategory);
		
		Tags objTags = new Tags();
		objTags.setId(1);
		objTags.setName("Tag 1");
		
		tags.add(objTags);
		
		objPet.setTags(tags);

		photoUrls.add("https://images.com/image1.png");
		photoUrls.add("https://images.com/image2.png");
		
		objPet.setPhotoUrls(photoUrls);
		
		System.out.println(objPet.toString());
		return objPet;
	}
	
	
	
	
	
}
