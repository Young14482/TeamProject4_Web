package user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	private String id;
	private String pw;
	private String phone;
	private int birth;
	private int gender;
	private String grade;
}
