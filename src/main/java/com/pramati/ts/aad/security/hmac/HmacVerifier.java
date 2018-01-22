package com.pramati.ts.aad.security.hmac;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

/**
 * Class HmacVerifier.
 *
 * @author nagarajan
 * @version 1.0
 */
@Component("hmacVerifier")
public class HmacVerifier {

    //private static final Logger log = LoggerFactory.getLogger(HmacVerifier.class);
	
	@Value("${thumbsignin.api.secret}")
    private String thumbsigninApiSecret;

    public AuthenticationHeader verify(CachingRequestWrapper requestWrapper/*, CredentialsProvider credentialsProvider*/) throws IOException {
        final AuthenticationHeader authHeader = AuthenticationHeaderParser.getAuthHeader(requestWrapper);

        if (authHeader == null) {
            //log.warn("Authorization header is missing");
            throw new BadCredentialsException("Authorization header is missing or invalid");
        }

        String dateHeader = requestWrapper.getHeader(HmacSignatureBuilder.X_TS_DATE_HEADER);

        //String apiSecret = credentialsProvider.getApiSecret(authHeader.getApiKey());
        String apiSecret = thumbsigninApiSecret;

        final HmacSignatureBuilder signatureBuilder = new HmacSignatureBuilder.Builder(authHeader.getApiKey(), apiSecret).algorithm(authHeader.getAlgorithm())
                .scheme(requestWrapper.getScheme())
                //.host(requestWrapper.getServerName() + ":" + requestWrapper.getServerPort())
                .httpMethod(requestWrapper.getMethod())
                .canonicalURI(requestWrapper.getRequestURI())
                .headers(getCanonicalizeHeaders(requestWrapper, authHeader))
                //.queryParams(getCanonicalizeQueryString(requestWrapper))
                .date(dateHeader)
                //.payload(new String(requestWrapper.getContentAsByteArray()))
                .build();

        if (!signatureBuilder.verify(authHeader.getSignature())) {
            //log.warn("Signature verification failed  {}" , authHeader.getSignature());
            throw new BadCredentialsException("Signature verification failed");
        }

        return authHeader;
    }

    public static TreeMap<String, String> getCanonicalizeHeaders(HttpServletRequest request, AuthenticationHeader authenticationHeader) {

        TreeMap<String, String> canonicalizeHeaders = new TreeMap<>();
        List<String> signedHeaders = authenticationHeader.getSignedHeaders();

        Collections.list(request.getHeaderNames())
                .stream()
                .filter(s -> signedHeaders.contains(s.toLowerCase()))
                .forEach(s -> canonicalizeHeaders.put(s.toLowerCase(), request.getHeader(s)));
        return canonicalizeHeaders;
    }

    public static TreeMap<String, String> getCanonicalizeQueryString(HttpServletRequest request) {

        TreeMap<String, String> canonicalizeQueryStrings = new TreeMap<>();

        Collections.list(request.getParameterNames()).stream().forEach(s -> canonicalizeQueryStrings.put(s, request.getParameter(s)));
        return canonicalizeQueryStrings;
    }
}
