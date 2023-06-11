package exemplo.java.spark.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief class GenericDao
 */
public class GenericDao<E> {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> type;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("exemplo-java-hibernate");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public GenericDao(Class<E> entidade) {
        this.type = entidade;
        em = emf.createEntityManager();
    }

    public GenericDao<E> begin() {
        em.getTransaction().begin();
        return this;
    }

    public GenericDao<E> end() {
        em.getTransaction().commit();
        return this;
    }

    public void create(E entity) {
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public GenericDao<E> delete(int id) {
        GenericDao<E> dao = new GenericDao<E>(type);
        E entidade = dao.findById(id);
        em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
        return this;
    }

    public E findById(Object id) {
        return em.find(type, id);
    }
    
    public List<E> findAll(){
        Query query = em.createQuery("FROM " + type.getSimpleName());
        return query.getResultList();
    }
}
