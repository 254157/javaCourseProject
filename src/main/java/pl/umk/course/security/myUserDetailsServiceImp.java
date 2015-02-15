package pl.umk.course.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.course.entities.UserEntity;
import pl.umk.course.repositories.UserEntityRepository;

import java.util.HashSet;
import java.util.Set;


@Component
public class myUserDetailsServiceImp implements UserDetailsService  {

	@Autowired
	private  UserEntityRepository userDAO;
		
	 @Transactional(readOnly = true)
     @Override
	  public UserDetails loadUserByUsername(String email)
	      throws UsernameNotFoundException, DataAccessException {

			UserEntity user = userDAO.findUserByEmail(email);
            System.out.println("+++++++++       +++++++" + user.toString());



			if (user == null) {
				throw new UsernameNotFoundException("Nie znaleziono uzytkownika o loginie: " + email);
			}

			Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(Role.USER.toString()));

			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(), 
					user.getPassword(), 
					enabled, 
					accountNonExpired, 
					credentialsNonExpired, 
					accountNonLocked, 
					roles
				);
			}

	}