package org.launchcode.mygallery.data;

import org.launchcode.mygallery.Socials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialsRepository extends CrudRepository<Socials,Integer> {
}
