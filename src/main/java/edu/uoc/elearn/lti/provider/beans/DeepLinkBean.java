package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.elc.lti.tool.deeplinking.Settings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeepLinkBean {
	private Settings settings;
	private List<DeepLinkForm> deepLinks;
}
