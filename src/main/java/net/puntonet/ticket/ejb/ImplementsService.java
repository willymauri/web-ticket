package net.puntonet.ticket.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import net.puntonet.ticket.excepcion.ExceptionService;

@Stateless
public class ImplementsService<T> implements InterfaceService<T>{
	
	private static final Logger log = Logger.getLogger(ImplementsService.class.getName());
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	
	@Override
	public T save(T entidad) throws ExceptionService {
		try {
			this.em.persist(entidad);
			this.em.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService("No se pudieron registrar los datos");
		}
		return entidad;
	}

	@Override
	public T update(T entidad) throws ExceptionService {
		try {
			entidad = this.em.merge(entidad);
			this.em.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService("No se pudieron atualizar los datos");
		}
		return entidad;
	}

	@Override
	public void delete(T entidad) throws ExceptionService {
		try {
			this.em.remove(this.em.merge(entidad));
			this.em.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService("No se pudo eliminar los datos");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() throws ExceptionService {
		List<T> result = new ArrayList<T>();
		try{
			Query qry = this.em.createNamedQuery("Ticket.findAll");
			result = qry.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService("No se pudo consultar los datos");
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryState(String state) throws ExceptionService {
		List<T> result = new ArrayList<T>();
		try{
			Query qry = this.em.createNamedQuery("Ticket.findStatus");
			qry.setParameter("estado", state);
			result = qry.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService("No se pudo consultar los datos con estado: " + state);
		}
		return result;
	}
}
