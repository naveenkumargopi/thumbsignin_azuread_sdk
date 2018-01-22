package com.pramati.ts.aad.security.hmac;

/**
 * Represents contents for HMAC HTTP <code>Authorization</code> header.
 */
import java.util.List;

/**
 * Bean class representing the authentication header, which looks like
 *
 * <pre>
 * Authentication: ALGORITHM Credential=CREDENTIAL, SignedHeaders=SIGNED_HEADERS, Signature=SIGNATURE
 * </pre>
 *
 * Where:
 * ALGORITHM := The signing algorithm used for the apiKey, ex. DTAv1-SHA-256
 * CREDENTIAL := KEYID/DATE.
 * SIGNED_HEADERS := lower cased header names sorted by byte order joined with semicolons.
 * SIGNATURE := The signature calculated by the signing algorithm.
 * KEYID := The public id for the sceret key used to calculate the signature.
 * DATE := The date the message was signed in YYMMDD format. This is used to generate the daily key.
 */
public class AuthenticationHeader {
    private String algorithm;
    private String apiKey;
    private List<String> signedHeaders;
    private String signature;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSignedHeaders(List<String> signedHeaders) {
        this.signedHeaders = signedHeaders;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getApiKey() {
        return apiKey;
    }

    public List<String> getSignedHeaders() {
        return signedHeaders;
    }

    public String getSignature() {
        return signature;
    }
}
