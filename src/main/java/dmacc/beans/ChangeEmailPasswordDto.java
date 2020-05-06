package dmacc.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import dmacc.validation.FieldMatch;
import dmacc.validation.ValidEmail;
import dmacc.validation.ValidPassword;


@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class ChangeEmailPasswordDto { 
	private long userId;
	
    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    
    @NotNull
    @NotEmpty
    private String matchingPassword;
    
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
