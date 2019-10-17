package com.prestonsproductions.alexandra.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prestonsproductions.alexandra.dao.AbstractJpaDAO;
import com.prestonsproductions.alexandra.dao.UseCaseDAO;
import com.prestonsproductions.alexandra.model.UseCase;

/**
 * @author Preston Frazier
 *
 */
@Repository
@Component
public class UseCaseDAOImpl extends AbstractJpaDAO<UseCase> implements UseCaseDAO {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public UseCaseDAOImpl() {
		setClazz(UseCase.class);
	}

	@Override
	public UseCase getUseCase(String code) {
		logger.debug("Getting Use Case");
		
		TypedQuery<UseCase> query = entityManager.createNamedQuery("SELECT u FROM UseCase u WHERE u.ucCode= :code ", UseCase.class);
		query.setParameter("code", code);
		List<UseCase> usecases = query.getResultList();
		if (usecases.size() > 0) {
			return usecases.get(0);
		}	
		return null;
	}


}
