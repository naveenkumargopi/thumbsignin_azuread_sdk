package com.pramati.ts.aad.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.pramati.ts.aad.domain.Membership;
import com.pramati.ts.aad.domain.User;
import com.pramati.ts.aad.util.HttpClientHelper;
import com.pramati.ts.aad.util.JSONHelper;

@Service
public class AzureApiService {

	@Value("${azure.ad.client.id}")
    private String azureClientId;
	
	@Value("${azure.ad.client.secret}")
    private String azureClientSecret;
	
	@Value("${azure.ad.tenant.id}")
    private String azureTenantId;
	
	@Value("${azure.ad.tenant.authority}")
    private String azureAuthorityUrl;
	
	@Value("${azure.ad.graph.api.endpoint}")
    private String azureGraphApiEndpoint;
	
	private String acquireAccessTokenForClientApp() {
    	String accessToken = "";
    	try {
			ExecutorService service = Executors.newFixedThreadPool(1);
			AuthenticationContext context = new AuthenticationContext(azureAuthorityUrl+azureTenantId+"/", false, service);
			
			ClientCredential credential = new ClientCredential(azureClientId, azureClientSecret);
			Future<AuthenticationResult> future = context.acquireToken(azureGraphApiEndpoint, credential, null);
			AuthenticationResult result = future.get();
			accessToken = result.getAccessToken();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
    	return accessToken;
    }
	
	public List<Membership> getUserMembershipInfoFromGraph(String userid) {
		List<Membership> listOfUserMemberships = new ArrayList<>();
    	try
    	{
    		String urlStr = String.format("%s/%s/users/%s/memberOf?api-version=1.6", azureGraphApiEndpoint, azureTenantId, userid);
    		
    		URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", acquireAccessTokenForClientApp());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json;odata=minimalmetadata");
            
            String responseStr = HttpClientHelper.getResponseStringFromConn(conn, true);
            int responseCode = conn.getResponseCode();
            JSONObject response = HttpClientHelper.processGoodRespStr(responseCode, responseStr);
            JSONArray userMemberships;
            
            userMemberships = JSONHelper.fetchDirectoryObjectJSONArray(response);
            
            Membership membership;
            for (int i = 0; i < userMemberships.length(); i++) {
                JSONObject userMembershipJSONObject = userMemberships.optJSONObject(i);
                membership = new Membership();
                JSONHelper.convertJSONObjectToDirectoryObject(userMembershipJSONObject, membership);
                listOfUserMemberships.add(membership);
            }
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return listOfUserMemberships;
	}
	
	public String getUserNameByIdFromGraph(String userid) {
    	User user = new User();
    	try
    	{
    		String urlStr = String.format("%s/%s/users/%s?api-version=1.6", azureGraphApiEndpoint, azureTenantId, userid);
    		
    		URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", acquireAccessTokenForClientApp());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json;odata=minimalmetadata");
            
            String responseStr = HttpClientHelper.getResponseStringFromConn(conn, true);
            int responseCode = conn.getResponseCode();
            JSONObject response = HttpClientHelper.processGoodRespStr(responseCode, responseStr);
            JSONObject userJsonObj = JSONHelper.fetchDirectoryObjectJSONObject(response);
            JSONHelper.convertJSONObjectToDirectoryObject(userJsonObj, user);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return user.getGivenName();
      }
	
}
