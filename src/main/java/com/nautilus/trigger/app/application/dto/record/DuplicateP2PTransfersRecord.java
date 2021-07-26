
package com.nautilus.trigger.app.application.dto.record;

import java.net.URI;

import com.nautilus.trigger.app.application.dto.interfaces.PublishedEventRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Antonio Salazar Valero
 * Created on : Jul 12, 2021, 6:24:19 PM
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateP2PTransfersRecord implements PublishedEventRequest {
	
	  private long transferId;
	  private long sourceAccountId;
	  private String sourceUserId;
	  private double amount;
	  private String currency;
	  private long targetAccountId;
	  private String targetUserId;
	  private String comments;
	  private String datetime;
	  private String status;
	  
	@Override
	public String getEventType() {
		return "team.nautilus.event.p2p.transfer.new";
	}
	
	@Override
	public URI getSource() {
		return URI.create("/nautilus_core/transfer_ms");
	}

}
