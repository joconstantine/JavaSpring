package main;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.OkHttpClient;

public class Test {

	public static void main(String[] args) {
		OkHttpClient httpclient = new OkHttpClient();
	    setSSL(httpclient);
	    setProxy(httpclient);
	    setConnectionTimeout(httpclient);
	    
	    RestTemplate restTemplate = new RestTemplate(new OkHttpClientHttpRequestFactory(httpclient));
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("User-Agent", "LTA_AHPFC_AGENT");
	    
	    MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
	    body.add("vehiclenumber", "BFF9124B");
	    body.add("chassisnumber", "TEST2020040903");
	    
	    
	    HttpEntity<Object> entity = new HttpEntity<Object>(body,headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    		"https://203.117.203.139/member/ltaahpfc/VRLServ.aspx", 
	    		HttpMethod.POST, entity, String.class);
	    
	    
	    
	    System.out.println("Financing - HTTP status code -> " + response.getStatusCode());
	    System.out.println(response.getBody());
	    
	    //Testing page
	    entity = new HttpEntity<Object>(null,headers);
	    
	    response = restTemplate.exchange(
	    		"https://203.117.203.139/member/ltaahpfc/ConnChecking.aspx", 
	    		HttpMethod.GET, entity, String.class);
	    
	    
	    
	    System.out.println("Health Check - HTTP status code -> " + response.getStatusCode());
	    System.out.println(response.getBody());
	}
	
	private static class AllowAllHostNameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;

		}
	}

	protected static void setSSL(OkHttpClient okclient) {

		String tls = "TLSv1.2";

		try {

			SSLContext context = SSLContext.getInstance(tls);
			
			context.init(null, new X509TrustManager[]{new X509TrustManager(){ 
                public void checkClientTrusted(X509Certificate[] chain, 
                                String authType) throws CertificateException {} 
                public void checkServerTrusted(X509Certificate[] chain, 
                                String authType) throws CertificateException {} 
                public X509Certificate[] getAcceptedIssuers() { 
                        return new X509Certificate[0]; 
                }}}, new SecureRandom()); 
			
			SSLSocketFactory sslsocketfactory = context.getSocketFactory();
			okclient.setSslSocketFactory(sslsocketfactory);

			okclient.setHostnameVerifier(new AllowAllHostNameVerifier());

			//use allEnableCipherSuites to get the supported Cipher Suite from JVM instead of specifying the CipherSuite.
			//this is to prevent handshake_failure when using IBM JVM which the Cipher Suite naming starts with SSL_* instead of TLS_*
			ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).allEnabledCipherSuites().tlsVersions(tls).build();

			System.out.println("Set Cipher Spec (MODERN TLS):");
			System.out.println(spec);
			okclient.setConnectionSpecs(Collections.singletonList(spec));

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException  e) {
			throw new RuntimeException(e);
		} 
	}

	protected static void setProxy(OkHttpClient okclient) {

		final String proxyHost = "172.20.5.11";
		final String proxyPort = "8080";

		boolean enableProxy;

		if (StringUtils.isBlank(proxyHost)) {
			enableProxy = false;
		}
		else {
			enableProxy = true;
		}

		if (enableProxy) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
			okclient.setProxy(proxy);

		}
	}

	protected static void setConnectionTimeout(OkHttpClient okclient) {

		long timeout = Long.valueOf(30);
		okclient.setConnectTimeout(timeout, TimeUnit.SECONDS);
		okclient.setReadTimeout(timeout, TimeUnit.SECONDS);
	}

}
