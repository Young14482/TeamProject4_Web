package user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	//@JsonProperty(value = "username")
	private String id;
	private String pw;
	private String phone;
	private String name;
	private String birth; // int 
	private String gender; // int 
	private String address;
	private String grade;
	private int useMoney;
	
	public User(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	public static User UserId(String name, String birth) {
		User user = new User();
		user.setName(name);
		user.setBirth(birth);
		return user;
	}
	
	public static User UserPw(String name, String id) {
		User user = new User();
		user.setName(name);
		user.setId(id);
		return user;
	}
	
	
}
