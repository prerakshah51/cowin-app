package com.cowin.app.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.simpl.SimpleJobFactory;
import org.quartz.spi.TriggerFiredBundle;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class CowinJobFactory extends SimpleJobFactory {

	private final Injector guice;

	@Inject
	public CowinJobFactory(final Injector guice) {
		this.guice = guice;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {

		JobDetail jobDetail = triggerFiredBundle.getJobDetail();
		Class jobClass = jobDetail.getJobClass();

		try {
			return (Job) guice.getInstance(jobClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		}
	}
}
