package payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class empDto {
	
	@Max(value = 8,message = "Employee Id must be numeric and max 8 digit")
	private long empId;
	
	@NotBlank(message="First name is required")
	private String firstName;
	private String lastName;
	
	@Email(message="Invalid email address")
	private String email;
	
	@Pattern(regexp="^(\\+91[\\-\\s]?)?[0]?[789]\\d{9}$",message="Invalid Phone Number")
	private String phone;
	
	
	private String address;
	private long salary;
	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public empDto(int empId, String firstName, String lastName, String email, String phone, String address, long salary) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "empDto [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", salary=" + salary + "]";
	}
	public empDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
