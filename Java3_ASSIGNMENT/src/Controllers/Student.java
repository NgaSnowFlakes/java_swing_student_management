package Controllers;

public class Student {
	private String id;
	private String name;
	private String email;
	private String contact;
	private String gender;
	private String address;
	private String path;
	public Student(String id, String name, String email, String contact, String gender, String address, String path) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.gender = gender;
		this.address = address;
		this.path = path;
	}
	public Student(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
