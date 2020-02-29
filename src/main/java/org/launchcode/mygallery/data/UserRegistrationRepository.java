package org.launchcode.mygallery.data;

import org.launchcode.mygallery.GeneralUser;
import org.springframework.data.repository.CrudRepository;

//written by Jen Buck
public interface UserRegistrationRepository extends CrudRepository<GeneralUser, Integer> {

    GeneralUser findByUsername(String username);
}
