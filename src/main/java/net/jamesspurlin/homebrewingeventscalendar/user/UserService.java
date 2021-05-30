package net.jamesspurlin.homebrewingeventscalendar.user;

import net.jamesspurlin.homebrewingeventscalendar.forms.UserForm;
import net.jamesspurlin.homebrewingeventscalendar.models.User;

public interface UserService {

    public User save(UserForm userForm) throws EmailExistsException;
    public User findByEmail(String email);

}
