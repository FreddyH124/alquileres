package org.arso.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.arso.factory.FactoriaServicios;
import org.arso.interfaces.services.IServicioAlquileres;

@Path("alquileres")
public class ControladorRest {
	
	private IServicioAlquileres servicioAlquileres = FactoriaServicios.getServicio(IServicioAlquileres.class);
	
	@Context
	private UriInfo uriInfo;
	
	//http://localhost:8080/api/alquileres
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario( @PathParam("id") String id) throws Exception {
		
		try {
			servicioAlquileres.getUsuario(id);
		} catch (IllegalArgumentException e) {
			
			Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).entity(servicioAlquileres.getUsuario(id)).build();
		
		
	}
	
	@POST
	@Path("{id}/reserva/{idBicicleta}")
	public Response reservarBicileta( @PathParam("id") String id,
			@PathParam("idBicicleta") String idBicicleta) throws Exception {
		
		try {
			servicioAlquileres.reservarBicicleta(id, idBicicleta);
		} catch (IllegalArgumentException e) {
			
			Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).build();
		
	}
	
	@PUT
	@Path("{id}//reserva/confirmar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmarReserva(@PathParam("id") String id) throws Exception {
		try {
			servicioAlquileres.confirmarReserva(id);
		} catch (IllegalStateException e) {
			Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
	
	//Seguir con alquilarBicicleta
	
	
	
	

}