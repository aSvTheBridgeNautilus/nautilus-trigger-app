package com.nautilus.trigger.app.application.dto.interfaces;

import java.net.URI;

public interface PublishedEventRequest {
	
	String getEventType();
	
	URI getSource();

}
