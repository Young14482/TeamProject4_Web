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
	@JsonProperty(value = "username")
	private String Id;
	private String Pw;
	private String Phone;
	private String Birth; // int 
	private String Gender; // int 
	private String Grade;
}
