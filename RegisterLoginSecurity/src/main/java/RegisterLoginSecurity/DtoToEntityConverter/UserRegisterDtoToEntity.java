package RegisterLoginSecurity.DtoToEntityConverter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import RegisterLoginSecurity.DTO.UserRegisterDto;
import RegisterLoginSecurity.Entity.Roles;
import RegisterLoginSecurity.Entity.Users;
import RegisterLoginSecurity.Repository.RoleRepository;

@Component
public class UserRegisterDtoToEntity {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users userRegisterDtoToEntity(UserRegisterDto userRegisterDto) {
        Users users = new Users();
        users.setUserName(userRegisterDto.getUserName());
        users.setPassword(passwordEncoder.encode(userRegisterDto.getPassword())); // Encode password
        users.setEmail(userRegisterDto.getEmail()); // Set email directly

        Set<Roles> roles = new HashSet<>();
        Roles role = roleRepository.findByRolesName("USER")
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setRolesName("USER");
                    return roleRepository.save(newRole);
                });
        roles.add(role);
        
        users.setRoles(roles);
        
        return users;
    }
}