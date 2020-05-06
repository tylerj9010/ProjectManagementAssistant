package dmacc.service;

import dmacc.beans.Users;
import dmacc.beans.ChangeEmailPasswordDto;
import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;
import dmacc.beans.UserDto;

public interface IUserService {
    TeamManager registerNewTeamManagerAccount(UserDto userDto);
    
    TeamMember registerNewTeamMemberAccount(UserDto userDto);
    
    Users changeEmailPassword(ChangeEmailPasswordDto changeEmailPasswordDto);

	Users findByEmail(String email);
}