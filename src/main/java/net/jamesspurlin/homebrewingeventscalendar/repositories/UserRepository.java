package net.jamesspurlin.homebrewingeventscalendar.repositories;

import net.jamesspurlin.homebrewingeventscalendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
