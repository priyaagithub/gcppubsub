package com.migration;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.migration.gcp.GoogleMessageReceiver;
import com.migration.utility.HTTPRequestUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GcpPubsubApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GcpPubsubApplication.class, args);
	}

	@Bean
	public HTTPRequestUtility getHTTPRequestUtility(){
		return new HTTPRequestUtility();
	}

	@Autowired
	HTTPRequestUtility beanUtility;

	@Override
	public void run(String... args) throws Exception {
		String PROJECT_ID = ServiceOptions.getDefaultProjectId();
		//String SUBSCRIPTION_ID = "bill-webhooks-tract-webhooks-staging";
		String SUBSCRIPTION_ID = "bill-webhooks-tract";

		ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID,SUBSCRIPTION_ID);

		//Only to testing API
		beanUtility.get();

		Subscriber subscriber = null;
		try{
			subscriber = Subscriber.newBuilder(subscriptionName, new GoogleMessageReceiver()).build();
			subscriber.startAsync().awaitRunning();
			subscriber.awaitTerminated();
		}catch(IllegalStateException e){
			e.printStackTrace();
		}
	}

}
