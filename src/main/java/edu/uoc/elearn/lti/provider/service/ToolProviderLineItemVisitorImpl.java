package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.ags.ToolLineItemServiceClient;
import edu.uoc.elc.spring.lti.tool.AgsServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.lti.ags.LineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@RequiredArgsConstructor
@Service
public class ToolProviderLineItemVisitorImpl implements LineItemVisitor {
	private final ToolProvider toolProvider;


	@Override
	public boolean canGet() {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		return agsServiceProvider.hasAgsService();
	}

	@Override
	public List<LineItem> getAll() {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		final ToolLineItemServiceClient lineItemsServiceClient = agsServiceProvider.getLineItemsServiceClient();
		return lineItemsServiceClient.getLineItems(null, null, null, null);
	}
}
