package com.goeuro.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.goeuro.test.config.AppConfig;
import com.goeuro.test.service.MainService;
import com.goeuro.test.service.MainServiceImpl;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		
		try {

			logger.info("application starts...");

			if (logger.isDebugEnabled()) {
				logger.debug("args.length={}", args.length);
				for (int i = 0 ; i < args.length ; i++) {
					logger.debug("args[{}]={}", i, args[i]);
				}
			}

			final String searchText = getSearchText(args);

			ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

			MainService mainService = ctx.getBean(MainServiceImpl.class);

			mainService.execute(searchText);

			((AnnotationConfigApplicationContext)ctx).close();

			logger.info("application ended successfully.");

		} catch (Throwable t) {
			logger.error("application terminated unexpectedly.", t);
		}
	}

	private static String getSearchText(String[] args) {

		String searchText = null;

		if (args.length > 0) {
			searchText = args[0];

			if (searchText != null) {
				searchText = searchText.trim();

				if (!searchText.isEmpty()) {
					return searchText;
				}
			}
		}

		return null;
	}
}
