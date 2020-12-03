/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.puntonet.ticket.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.puntonet.ticket.ejb.InterfaceService;
import net.puntonet.ticket.excepcion.ExceptionService;
import net.puntonet.ticket.modelo.Ticket;

@ManagedBean
@ViewScoped
public class TicketController {

    @EJB
    private InterfaceService<Ticket> ticketService;

    private List<Ticket> tickets;
    
    private Ticket ticket;
    
    private String status;

    @PostConstruct
    public void init() {
    	this.ticket = new Ticket();
        this.tickets = this.ticketService.queryAll();
    }

    /**
     * Registro  ticket
     */
    public void register() {
    	try {
    		this.ticketService.save(ticket);
    		this.mensaje("INFO", "Éxito!", "Registro exitoso");
    		init();
        } catch (ExceptionService e) {
        	mensaje("ERROR", "Registrado!", e.getMessage());
        }
    }

    /**
     * Actualiza ticket
     */
    public void update() {
    	try {
    		if(ticket.getEstado().equals("Abierto"))
    			ticket.setFechaini(new Date());
    		if(ticket.getEstado().equals("Cerrado"))
    			ticket.setFechafin(new Date());
    		this.ticketService.update(ticket);
    		this.mensaje("INFO", "Éxito!", "Actualización exitosa");
    		init();
        } catch (ExceptionService e) {
        	mensaje("ERROR", "Registrado!", e.getMessage());
        }
    }
    
    /**
     * Elimina ticket
     */
    public void delete() {
    	try {
    		this.ticketService.delete(ticket);
    		this.mensaje("INFO", "Éxito!", "Eliminación exitosa");
    		init();
        } catch (ExceptionService e) {
        	mensaje("ERROR", "Eliminado!", e.getMessage());
        }
    }
    
    /**
     * Consulta ticket por estado
     */
    public void queryState() {
    	try {
    		if(this.status == null)
    			this.tickets = this.ticketService.queryAll();
    		else
    			this.tickets = this.ticketService.queryState(this.status);
    		this.mensaje("INFO", "Consultado!", "Consulta exitosa");
        } catch (ExceptionService e) {
        	mensaje("ERROR", "Problema!", e.getMessage());
        }
    }
    
    public void clean() {
    	this.ticket = new Ticket();
    }
    /**
     * Muestra el mensaje
     */
    private void mensaje(String severity, String info, String msg) {
    	FacesMessage m = new FacesMessage(severity.equals("INFO")?FacesMessage.SEVERITY_INFO:severity.equals("WARN")?FacesMessage.SEVERITY_WARN:FacesMessage.SEVERITY_ERROR, info, msg);
        FacesContext.getCurrentInstance().addMessage(null, m);
    }
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
