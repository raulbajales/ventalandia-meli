package com.ventalandia.framework.persistence;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Simple Guice {@link Provider} to give {@link PersistenceManager} instances.
 * 
 * All request run in a single {@link Thread}. To support all kind of request (all of them will be
 * related to persistence) persistence must be associated with the whole request lifecycle.
 * 
 * @author matias
 * 
 */
public class PersistenceManagerProvider implements Provider<PersistenceManager> {

  @Inject
  private PersistenceManagerFactory persistenceManagerFactory;

  /**
   * Check if there is a {@link PersistenceManager} associated to the {@link PersistenceContext}. If
   * there is no {@link PersistenceManager} it will get a new one, set it to the
   * {@link PersistenceContext} and then it will return it. If there is one it will just return it.
   * 
   * @see PersistenceContext
   * 
   * @return a {@link PersistenceManager} associated with the {@link PersistenceContext}
   */
  @Override
  public PersistenceManager get() {
    PersistenceManager persistenceManager = PersistenceContext.get();

    if (persistenceManager != null) {
      return persistenceManager;
    }

    PersistenceContext.set(this.persistenceManagerFactory.getPersistenceManager());

    return PersistenceContext.get();
  }

  public void remove() {
    PersistenceContext.remove();
  }

}
