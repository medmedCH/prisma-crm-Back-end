package resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Entities.Product;
import Entities.RepairRequest;
import Entities.Vehicule;
import Entities.VehiculeMaintenance;
import Interfaces.IVehiculeMtRemote;

@Path("maintenance")
@RequestScoped
public class VehiculeMaintenanceResource {
	@EJB
	IVehiculeMtRemote vehiculeMtRemote;

	@POST
	@Path("/add/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response addMaintenanceR(@PathParam(value = "id") int id, VehiculeMaintenance maintenance) {
		Vehicule xx = vehiculeMtRemote.getVehiculeById(id);

		if (xx != null) {
			maintenance.setVehicule(xx);
			vehiculeMtRemote.addMaintanceRequest(maintenance);
		}

		return Response.status(Status.CREATED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("find/{id}")
	public List<VehiculeMaintenance> vehiculeMByVeh(@PathParam("id") int id) {

		return vehiculeMtRemote.findMaintancebyVehicule(id);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findMostMaintained")
	public Response MostMaintainedVehicule() {

		return Response.status(Status.CREATED).entity(vehiculeMtRemote.findMostMaintainedVehicule()).build();

	}

	public Date getDateNow() {
		SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String pattern = "yyyy-MM-dd HH:mm:ss.SSS";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));

		String date = simpleDateFormat.format(new Date());
		System.out.println("DATE " + date);

		java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(date);
		java.util.Date improperUtilDate = sqlTimestamp;

		return improperUtilDate;
	}
}