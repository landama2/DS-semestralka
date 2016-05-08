package utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by marek on 3.5.16.
 */
public class Resources {

//    @Produces
    @PersistenceContext
    private EntityManager em;

}
