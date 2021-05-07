package com.cowin.app.web;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.cowin.app.db.User;
import com.cowin.app.job.CowinJob;
import com.cowin.app.job.CowinJobFactory;
import com.cowin.app.service.CowinService;
import com.google.inject.Inject;

@Path("cowin")
@Singleton
public class CowinController {

	@Inject
	public CowinService cowinService;

	@Inject
	public CowinJobFactory cowinJobFactory;

	@POST
	@Path("/saveuser")
	public void saveUser(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {

		String name = request.getParameter("name");
		String district = request.getParameter("district");
		String email = request.getParameter("email");
		Long mobile = Long.parseLong(request.getParameter("mobile"));
		Long pincode = Long.parseLong(request.getParameter("pincode"));

		User user = new User(name, district, email, mobile, pincode);
		request.setAttribute("resultStr", cowinService.saveUser(user));
		request.getRequestDispatcher("../result.jsp").forward(request, response);
	}

	@POST
	@Path("/startjob")
	public void startJob(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws SchedulerException, ServletException, IOException {

		JobDetail job = JobBuilder.newJob(CowinJob.class).withIdentity("cowinJob").build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		SchedulerFactory schFactory = new StdSchedulerFactory();
		Scheduler sch = schFactory.getScheduler();
		sch.setJobFactory(cowinJobFactory);
		sch.start();
		sch.scheduleJob(job, trigger);

		request.getRequestDispatcher("../user.jsp").forward(request, response);
	}
}
