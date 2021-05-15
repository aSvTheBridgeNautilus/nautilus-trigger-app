package com.nautilus.trigger.app.service.interfaces;

import com.nautilus.trigger.app.application.dto.interfaces.PublishedEventRequest;

public interface ICLoudEventProvider {
	
	void publish(PublishedEventRequest request);

}
