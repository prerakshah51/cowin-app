package com.cowin.app.module;

import org.quartz.Job;
import com.cowin.app.job.CowinJob;
import com.cowin.app.service.CowinService;
import com.cowin.app.service.CowinServiceImpl;
import com.cowin.app.web.CowinController;
import com.google.inject.AbstractModule;

public class CowinModule extends AbstractModule {

	@Override
	public void configure() {

		bind(CowinController.class);
		bind(CowinService.class).to(CowinServiceImpl.class);
		bind(Job.class).to(CowinJob.class);
	}
}
