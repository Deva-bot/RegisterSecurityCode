package RegisterLoginSecurity.Service;

import RegisterLoginSecurity.DTO.LoginDto;
import RegisterLoginSecurity.DTO.UserRegisterDto;

public interface AuthService 
{
	public String userRegister(UserRegisterDto userRegisterDto);
//	public String userlogin(LoginDto loginDto);
}
