package mbs.domain.repository.user;

import mbs.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
	User findByUserId(String userId);
}