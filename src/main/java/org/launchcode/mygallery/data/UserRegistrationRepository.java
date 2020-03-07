package org.launchcode.mygallery.data;

import org.launchcode.mygallery.GeneralUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//written by Jen Buck

@Repository
public interface UserRegistrationRepository extends CrudRepository<GeneralUser, Integer> {

    GeneralUser findByUsername(String username);
}
