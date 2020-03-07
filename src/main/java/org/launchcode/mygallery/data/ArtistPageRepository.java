package org.launchcode.mygallery.data;

import com.sun.xml.bind.v2.model.core.ID;
import org.launchcode.mygallery.ArtistUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistPageRepository extends CrudRepository<ArtistUser, Integer> {
}

