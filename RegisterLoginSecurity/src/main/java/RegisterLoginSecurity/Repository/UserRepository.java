package RegisterLoginSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RegisterLoginSecurity.Entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>
{
	Optional<Users> findByEmail(String email);
	Optional<Users> findByUserName(String userName);
	
}
