package com.petstore.api.utilities;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;


public class DisableSSL {
	
	 public static void disableSslVerification() throws NoSuchAlgorithmException, KeyManagementException {
	        TrustManager[] trustAll = new TrustManager[] {
	            new X509TrustManager() {
	                public X509Certificate[] getAcceptedIssuers() { return null; }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
	                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
	            }
	        };

	        SSLContext sc = SSLContext.getInstance("TLS");
	        sc.init(null, trustAll, new java.security.SecureRandom());
	        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
	    }
}
