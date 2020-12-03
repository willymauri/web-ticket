package net.puntonet.ticket.ejb;

import java.util.List;

import javax.ejb.Local;

import net.puntonet.ticket.excepcion.ExceptionService;

@Local
public interface InterfaceService<T> {
	
	/**
	 * 
	 * @param entidad
	 * @return
	 * @throws ExceptionService
	 */
	public T save(T entidad) throws ExceptionService;
	/**
	 * 
	 * @param entidad
	 * @return
	 * @throws ExceptionService
	 */
	public T update(T entidad) throws ExceptionService;
	/**
	 * 
	 * @param entidad
	 * @return
	 * @throws ExceptionService
	 */
	public void delete(T entidad) throws ExceptionService;
	/**
	 * 
	 * @param query
	 * @param parametros
	 * @return
	 * @throws ExceptionService
	 */
	public List<T> queryAll() throws ExceptionService;
	
	/**
	 * 
	 * @param query
	 * @param parametros
	 * @return
	 * @throws ExceptionService
	 */
	public List<T> queryState(String state) throws ExceptionService;
}
