package ru.anvarzhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anvarzhonov.models.User;

/**
 * todo Document type UserRepo
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
