package dmacc.service;

import dmacc.beans.Users;
import dmacc.beans.TeamManager;
import dmacc.beans.UserDto;

public interface IUserService {
    Users registerNewUserAccount(UserDto userDto);

	Users findByEmail(String email);
}