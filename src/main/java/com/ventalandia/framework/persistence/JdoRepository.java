package com.ventalandia.framework.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.inject.Inject;

/**
 * Abstract behavior of a repository. It define basic CRUD operations.
 * 
 * @author matias
 * 
 */
public abstract class JdoRepository<T> implements Repository<T> {

    private PersistenceManagerProvider persistenceManagerProvider;
    
    @Inject
    public JdoRepository(PersistenceManagerProvider persistenceManagerProvider) {
		this.persistenceManagerProvider = persistenceManagerProvider;
	}

	/**
     * @return the {@linkplain PersistenceManager} associated with the current
     * {@linkplain Thread}.
     */
    protected final PersistenceManager getPersistenceManager() {
        return this.persistenceManagerProvider.get();
    }

    /**
     * Add an instance to the repository.
     * 
     * @param an instance to add.
     */
    public final void add(T t) {
        this.getPersistenceManager().makePersistent(t);
        this.afterAdd(t);
    }

    /**
     * Template method. Add here what you want to do after an Entity is presisted.
     * @param t a recently persisted entity
     */
    protected void afterAdd(T t) {
        // do nothing
    }

    /**
     * Retrieve an object by its ID.<br>
     * The ID number must exist, if not the {@link PersistenceManager} will
     * throw a {@link JDOObjectNotFoundException}.
     * 
     * @param id
     * @return a persisted instance.
     */
    public final T get(Long id) {
        return (T) this.getPersistenceManager().getObjectById(this.getPersistedType(), id);
    }

    /**
     * As we have a Repository per Entity {@link JdoRepository} must be
     * extended. This way the new implementation has to indicate which Entity
     * Class will be assigned to the new implementation of the
     * {@link JdoRepository}.
     * 
     * @return the type associated to the Repository. It must not return a null
     * value.
     */
    @SuppressWarnings("unchecked")
    private Class<T> getPersistedType() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        return (Class<T>) type;
    }

    /**
     * Execute a block of code in a transaction context. This block is a simple
     * {@link Runnable} implementation which is use like a call back.
     * 
     * @param runnable the block of to execute.
     */
    protected final void executeInTransaction(final Runnable runnable) {
        Transaction tx = this.getPersistenceManager().currentTransaction();
        try {
            boolean commit = false;

            if (!tx.isActive()) {
                tx.begin();
                commit = true;
            }

            runnable.run();

            if (commit) {
                tx.commit();
            }
        }
        finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * Perform a query and wrap the result into a {@link List}. This is useful
     * to return the result of a query to the view.
     * 
     * @param query to be executed.
     * @return the wrapped result of the query.
     */
    protected final List<T> list(Query query) {
        return toList(query.execute());
    }

    /**
     * Perform a query and wrap the result into a {@link List}. This is useful
     * to return the result of a query to the view.
     * 
     * @param query to be executed.
     * @param object which is a parameter for the query.
     * @return the wrapped result of the query.
     */
    protected List<T> list(Query query, Object object) {
        return toList(query.execute(object));
    }

    // cast
    @SuppressWarnings("unchecked")
    private List<T> toList(Object res) {
        return new ArrayList<T>((List<T>) res);
    }

    protected List<T> list(Query query, Object object1, Object object2) {
        return toList(query.execute(object1, object2));
    }

    protected List<T> listWithMap(Query query, Map<String, Object> map) {
        return toList(query.executeWithMap(map));
    }

    /**
     * Create a query using the repository class definition.
     * 
     * @return a pre-set {@link Query}
     */
    protected final Query createQuery() {
        return this.getPersistenceManager().newQuery(this.getPersistedType());
    }

    public final void update(final T t) {
        this.getPersistenceManager().makePersistent(t);
    }

    public List<T> search(Map<String, Object> params) {
        return toList(createQuery().executeWithMap(params));
    }

}
