package lt.codeacademy.blog_site.service;

import lt.codeacademy.blog_site.entity.User;
import lt.codeacademy.blog_site.exception.UserNotFoundException;
import lt.codeacademy.blog_site.repository.RoleRepository;
import lt.codeacademy.blog_site.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public User createUser(User user) throws RoleNotFoundException {

        if (userRepository.getUserByUsername(user.getUsername()).isPresent()) {
            return null;
        }

        user.setRoles(Set.of(roleRepository.getRoleByName("USER").orElseThrow(() -> new RoleNotFoundException("USER"))));
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

}
