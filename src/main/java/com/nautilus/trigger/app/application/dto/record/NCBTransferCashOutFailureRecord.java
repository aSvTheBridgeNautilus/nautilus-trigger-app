package com.nautilus.trigger.app.application.dto.record;

import java.net.URI;

import com.nautilus.trigger.app.application.dto.interfaces.PublishedEventRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NCBTransferCashOutFailureRecord implements PublishedEventRequest {
	
	 private long transferId;
	 private String userId;
	 private String bankIdentifier;
	 private String accountReference;
	 private long accountId;
	 private String amount;
	 private String currency;
	 private String bankReferenceId;
	 private String errorDetails;
	 
	 
	@Override
	public String getEventType() {
		return "team.nautilus.event.ncb.transfer.cashout.failure";
	}


	@Override
	public URI getSource() {
		return URI.create("/external_banks/ncb_stream_proccesor");
	}


}
