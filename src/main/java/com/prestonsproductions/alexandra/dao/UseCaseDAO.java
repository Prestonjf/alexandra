package com.prestonsproductions.alexandra.dao;

import org.springframework.transaction.annotation.Transactional;

import com.prestonsproductions.alexandra.model.UseCase;

/**
 * @author Preston Frazier
 *
 */
public interface UseCaseDAO {
	
	@Transactional
	public UseCase getUseCase(String code);

}
