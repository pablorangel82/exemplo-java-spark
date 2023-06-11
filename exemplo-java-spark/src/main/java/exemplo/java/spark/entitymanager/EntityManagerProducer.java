
package exemplo.java.spark.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief  class EntitymanagerProducer
 */
public class EntityManagerProducer {

    @PersistenceUnit(name = "primary")
    private EntityManagerFactory emf;

    @Produces 
    public EntityManager create() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}