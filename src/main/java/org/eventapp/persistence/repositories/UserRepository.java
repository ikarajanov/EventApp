package org.eventapp.persistence.repositories;

import org.eventapp.persistence.datamodels.User;


public interface UserRepository extends BaseRepository<User> {

  User getUserByEmailAndPassword(String email, String password);

  User getUserByEmail(String email);

  User getUserById(String id);
}