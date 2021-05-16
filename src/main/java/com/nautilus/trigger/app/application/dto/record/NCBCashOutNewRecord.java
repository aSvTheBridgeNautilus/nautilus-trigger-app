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
public class NCBCashOutNewRecord implements PublishedEventRequest {

	private long transferId;
	private String userId;
	private String bankIdentifier;
	private String accountReference;
	private long accountId;
	private String amount;
	private String currency;

	/*
	 * https://dev.azure.com/nautilus-org/Nautilus%20Project/_wiki/wikis/Nautilus-Project.wiki/135/NCB-Cash-out-New
	 * */
	
	@Override
	public String getEventType() {
		return "team.nautilus.event.ncb.cashout.new";
	}

	@Override
	public URI getSource() {
		return URI.create("/nautilus_core/transfer_ms");
	}
}
