package org.tanujb.gateway.security.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.tanujb.gateway.security.exception.ApplicationRuntimeException;
import org.tanujb.gateway.security.service.ContextRootProvider;
import org.tanujb.gateway.security.service.DelegationService;
import org.tanujb.gateway.security.vo.DelegationResponse;
import org.tanujb.gateway.util.GatewayUtil;

@Service
@Configuration
@PropertySource(value = "classpath:application/application.properties")
public class RestTemplateDelegationService implements DelegationService {

	@Value("#{'${response.header.skiplist}'.split(',')}")
	private Set<String> headerSkipList;

	@Autowired
	private ContextRootProvider contextRootProvider;

	@Override
	public DelegationResponse processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		RequestCallback requestCallback = getRequestCallback(request);
		ResponseExtractor<DelegationResponse> responseExtractor = getResponseExtractor();
		String path = GatewayUtil.getURL(request);
		String context = contextRootProvider.getContextRoot(path);
		String url = context + path;
		DelegationResponse restResponse = new RestTemplate().execute(url,
				HttpMethod.valueOf(request.getMethod()), requestCallback,
				responseExtractor, GatewayUtil.getPlaceHolderDetails(request));

		return restResponse;
	}

	private RequestCallback getRequestCallback(HttpServletRequest request) {

		final HttpServletRequest httpRequest = request;

		return new RequestCallback() {
			@Override
			public void doWithRequest(ClientHttpRequest clientRequest)
					throws IOException {
				// Setting header
				HttpHeaders requestHeader = clientRequest.getHeaders();
				for (Map.Entry<String, String> entry : GatewayUtil
						.getHeadersInfo(httpRequest).entrySet()) {

					requestHeader.add(entry.getKey(), entry.getValue());
				}
				// Setting request Body
				String requestBody = GatewayUtil.getRequestBody(httpRequest);
				if (requestBody != null) {
					try {
						clientRequest.getBody().write(requestBody.getBytes());
					} catch (IOException e) {
						throw new ApplicationRuntimeException(
								"Error occured while writing request body.", e);
					}
				}
			}
		};
	}

	private ResponseExtractor<DelegationResponse> getResponseExtractor() {

		return new ResponseExtractor<DelegationResponse>() {
			@Override
			public DelegationResponse extractData(
					ClientHttpResponse clientResponse) throws IOException {
				DelegationResponse delegationResponse = null;

				Integer responseStaus = null;
				Map<String, String> responseHeader = new HashMap<String, String>();
				byte[] responseByteArray = null;

				try {
					responseStaus = clientResponse.getRawStatusCode();
					responseHeader = GatewayUtil.getHeadersInfo(clientResponse,
							headerSkipList);
					// Getting response body
					InputStream ioStream = clientResponse.getBody();
					if (ioStream != null) {
						responseByteArray = IOUtils.toByteArray(ioStream);
					}
					delegationResponse = new DelegationResponse(responseStaus,
							responseHeader, responseByteArray);
				} catch (IOException e) {
					throw new ApplicationRuntimeException(
							"Error occured while reading response body.", e);
				}
				return delegationResponse;
			}
		};
	}
}
