package com.colin.app.config.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import com.colin.app.entity.domain.User;

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private boolean alreadySetup = false;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("In SetupDataLoader.onApplicationEvent() method");
		if (alreadySetup) {
			return;
		}
		final User user = new User();
		user.setUsername("colin");

		alreadySetup = true;
		LOGGER.info("In SetupDataLoader.onApplicationEvent() Initial Data loading finished.");
	}
}
