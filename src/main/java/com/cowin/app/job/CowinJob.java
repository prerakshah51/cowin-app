package com.cowin.app.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.cowin.app.service.CowinService;
import com.google.inject.Inject;

public class CowinJob implements Job {

	@Inject
	public CowinService cowinService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		cowinService.sendNotificationToAll();
	}
}
