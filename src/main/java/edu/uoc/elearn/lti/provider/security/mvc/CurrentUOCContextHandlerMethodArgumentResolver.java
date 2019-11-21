package edu.uoc.elearn.lti.provider.security.mvc;

import edu.uoc.elc.spring.security.lti.User;
import edu.uoc.elearn.lti.provider.security.UOCContext;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Component
public class CurrentUOCContextHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(UOCContext.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)  {
		if (this.supportsParameter(methodParameter)) {
			Principal principal = nativeWebRequest.getUserPrincipal();
			if (principal != null) {
				User user = (User) ((Authentication) principal).getPrincipal();
				if (user != null) {
					return new UOCContext(user.getTool());
				}
			}
		}
		return WebArgumentResolver.UNRESOLVED;
	}
}
