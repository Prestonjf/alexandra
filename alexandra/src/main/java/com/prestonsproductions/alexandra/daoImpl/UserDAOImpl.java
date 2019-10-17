package com.prestonsproductions.alexandra.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prestonsproductions.alexandra.dao.AbstractJpaDAO;
import com.prestonsproductions.alexandra.dao.UserDAO;
import com.prestonsproductions.alexandra.model.User;

@Repository
@Component
public class UserDAOImpl extends AbstractJpaDAO<User> implements UserDAO {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public UserDAOImpl() {
		setClazz(User.class);
	}
	
	@Override
	public User findByUsername(String username) {
		logger.debug("Getting User");		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username ", User.class);
		query.setParameter("username", username);
		List<User> u = query.getResultList();
		if (u.size() > 0) {
			return u.get(0);
		}	
		return null;
	}
}
