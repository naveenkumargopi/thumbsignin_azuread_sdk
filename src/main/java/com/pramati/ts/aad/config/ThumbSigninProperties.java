package com.pramati.ts.aad.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("thumbSigninProperties")
@ConfigurationProperties(prefix = "thumbsignin")  // find thumbsignin.* values
public class ThumbSigninProperties {

    private List<ApiUser> apiUsers;

    public static class ApiUser {
        private String key;        
		private String secret;    

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}
		
        @Override
        public String toString() {
            return "ApiUser{" +
                    "key='" + key + '\'' +
                    ", secret='" + secret + '\'' +
                    '}';
        }
    }

	public List<ApiUser> getApiUsers() {
		return apiUsers;
	}

	public void setApiUsers(List<ApiUser> apiUsers) {
		this.apiUsers = apiUsers;
	}
    
}
