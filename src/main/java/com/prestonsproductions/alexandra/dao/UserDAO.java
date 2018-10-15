package com.prestonsproductions.alexandra.dao;

import com.prestonsproductions.alexandra.model.User;

public interface UserDAO {

	public User findByUsername(String username);
}
