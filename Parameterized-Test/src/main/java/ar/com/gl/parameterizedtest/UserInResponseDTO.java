package ar.com.gl.parameterizedtest;

import java.util.ArrayList;
import java.util.Collection;


public class UserInResponseDTO {

	private Collection<UserDto> user;
	

    public UserInResponseDTO() {
        this.user = new ArrayList<>();
    }

    public void add(UserDto user) {
        this.user.add(user);
    }
}
