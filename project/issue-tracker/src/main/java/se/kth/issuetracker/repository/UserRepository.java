package se.kth.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.issuetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
