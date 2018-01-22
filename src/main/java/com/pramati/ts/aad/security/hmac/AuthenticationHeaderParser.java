package com.pramati.ts.aad.security.hmac;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

/**
 * Class AuthHeaderParser.
 *
 * @author nagarajan
 * @version 1.0
 * @since <pre>16/8/17 10:16 AM</pre>
 */
public class AuthenticationHeaderParser {
    private static final Pattern pattern = Pattern.compile("(\\S+) Credential=(\\S+), SignedHeaders=(\\S+), Signature=([\\S&&[^,]]+)");

    private static AuthenticationHeader parse(String headerString) {
        Matcher match = pattern.matcher(headerString);
        if (!match.find()) {
            return null;
        }

        AuthenticationHeader header = new AuthenticationHeader();
        header.setAlgorithm(match.group(1));
        header.setApiKey(match.group(2));
        header.setSignedHeaders(Arrays.asList(match.group(3).split(";")));
        header.setSignature(match.group(4));

        return header;
    }

    public static AuthenticationHeader getAuthHeader(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            // invalid authorization token
            return null;
        }

        return parse(authHeader);
    }
}
