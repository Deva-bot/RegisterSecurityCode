package RegisterLoginSecurity.ServiceImpl;

import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.grammars.hql.HqlParser.SecondContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import RegisterLoginSecurity.DTO.LoginDto;
import RegisterLoginSecurity.DTO.UserRegisterDto;
import RegisterLoginSecurity.DtoToEntityConverter.UserRegisterDtoToEntity;
import RegisterLoginSecurity.Entity.Users;
import RegisterLoginSecurity.Exception.EmailAlreadyExistsException;
import RegisterLoginSecurity.Exception.UserNameAlreadyExistsException;
import RegisterLoginSecurity.Repository.RoleRepository;
import RegisterLoginSecurity.Repository.UserRepository;
import RegisterLoginSecurity.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserRegisterDtoToEntity userRegisterDtoToEntity;

	@Override
	public String userRegister(UserRegisterDto userRegisterDto) 
	{
		Optional<Users> existsEmail = userRepository.findByEmail(userRegisterDto.getEmail());

		if (existsEmail.isPresent()) 
		{
			throw new EmailAlreadyExistsException("Email already exists with: " + userRegisterDto.getEmail());
		}

		Optional<Users> existsUserName = userRepository.findByUserName(userRegisterDto.getUserName());
		if (existsUserName.isPresent()) 
		{
			throw new UserNameAlreadyExistsException(
					"UserName already exists with userName: " + userRegisterDto.getUserName());
		}

		Users users = userRegisterDtoToEntity.userRegisterDtoToEntity(userRegisterDto);
		userRepository.save(users);

		return "User Registers Successfully!";
	}

	
//	@Override
//	public String userlogin(LoginDto loginDto)
//	{
//	    org.springframework.security.core.Authentication authentication = authenticationManager
//	            .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//	    
//	    SecurityContextHolder.getContext().setAuthentication(authentication); 
//	    return "User logged in successfully!";
//	}
}
