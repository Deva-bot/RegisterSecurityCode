package RegisterLoginSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RegisterLoginSecurity.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>
{
	Optional<Roles> findByRolesName(String rolesName);
}
