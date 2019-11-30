package edu.uoc.elearn.lti.provider.service.ags;

import edu.uoc.elc.lti.platform.ags.ToolLineItemServiceClient;
import edu.uoc.elc.spring.lti.tool.AgsServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.lti.ags.LineItem;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@RequiredArgsConstructor
public class LineItemVisitor {
	private final ToolProvider toolProvider;

	public boolean canGet() {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		return agsServiceProvider.hasAgsService();
	}

	public List<LineItem> getAll() {
		return getAll(null, null, null, null);
	}

	public List<LineItem> getAll(Integer limit, Integer page, String tag, String resourceId) {
		final ToolLineItemServiceClient lineItemsServiceClient = getServiceClient();
		return lineItemsServiceClient.getLineItems(limit, page, tag, resourceId);
	}

	public LineItem get(String id) {
		final ToolLineItemServiceClient lineItemsServiceClient = getServiceClient();
		return lineItemsServiceClient.getLineItem(id);
	}

	public LineItem create(LineItem lineItem) {
		final ToolLineItemServiceClient lineItemsServiceClient = getServiceClient();
		return lineItemsServiceClient.createLineItem(lineItem);
	}

	private ToolLineItemServiceClient getServiceClient() {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		return agsServiceProvider.getLineItemsServiceClient();
	}
}
