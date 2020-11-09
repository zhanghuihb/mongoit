package com.mongoit.common.wx;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HttpClientUtil {
	// 获得ConnectionManager，设置相关参数
	private static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
	private static int connectionTimeOut = 50000;
	private static int socketTimeOut = 100000;
	private static int maxConnectionPerHost = 20;
	private static int maxTotalConnections = 200;
	
	private static int connectionManageTimeOut = 50000;

	// 标志初始化是否完成的flag
	private static boolean initialed = false;

	// 初始化ConnectionManger的方法
	public static void SetPara() {
		manager.getParams().setConnectionTimeout(connectionTimeOut);
		manager.getParams().setSoTimeout(socketTimeOut);
		manager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		manager.getParams().setMaxTotalConnections(maxTotalConnections);
		initialed = true;
	}

	// 通过get方法获取网页内容
	public static String getGetResponseWithHttpClient(String url, String encode) {
		HttpClient client = new HttpClient(manager);
		client.getParams().setConnectionManagerTimeout(connectionManageTimeOut);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);

		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(get);
			// 在目标页面情况未知的条件下，不推荐使用getResponseBodyAsString()方法
			// String strGetResponseBody = post.getResponseBodyAsString();
			BufferedReader in = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(), get.getResponseCharSet()));

			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = resultBuffer.toString();
			// iso-8859-1 is the default reading encode
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), get.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			return "";
		} finally {
			get.releaseConnection();
		}
	}

	public static String getPostResponseWithHttpClient(String url, String encode) {
		HttpClient client = new HttpClient(manager);
		client.getParams().setConnectionManagerTimeout(connectionManageTimeOut);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		PostMethod post = new PostMethod(url);
		post.setFollowRedirects(false);
		StringBuffer resultBuffer = new StringBuffer();
		String result = null;
		try {
			client.executeMethod(post);
			BufferedReader in = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), post.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), post.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			return "";
		} finally {
			post.releaseConnection();
		}
	}
	
	public static String getPostResponseWithHttpClient(String url,String encode, NameValuePair[] nameValuePair) {
		HttpClient client = new HttpClient(manager);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		PostMethod post = new PostMethod(url);
		post.setRequestBody(nameValuePair);
		post.setFollowRedirects(false);

		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(post);
			BufferedReader in = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), post.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), post.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			return "";
		} finally {
			post.releaseConnection();
		}
	}
	
	public static String getPutResponseWithHttpClient(String url,String encode, String file) {
		HttpClient client = new HttpClient(manager);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		
		PutMethod put = new PutMethod(url);
		put.setRequestHeader("Content-Type", "image/jpeg;charset=UTF-8");
		put.setFollowRedirects(false);

		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(file));
			put.setRequestEntity(requestEntity);
			client.executeMethod(put);
			BufferedReader in = new BufferedReader(new InputStreamReader(put.getResponseBodyAsStream(), put.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), put.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			put.releaseConnection();
		}
	}
	
	public static String getPostResponseWithHttpClient(String url,String encode, String json) {
		HttpClient client = new HttpClient(manager);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		PostMethod post = new PostMethod(url);
		post.setRequestBody(json);
		post.setFollowRedirects(false);
		post.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(post);
			BufferedReader in = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), post.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), post.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			post.releaseConnection();
		}
	}

	
	public static InputStream getPostResponseAsStream(String url,String encode, Map<String,Object> param) {
		HttpClient client = new HttpClient(manager);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		PostMethod post = new PostMethod(url);
		if(null != param && !param.isEmpty()){
			post.setRequestBody(JSON.toJSONString(param));
		}
		post.setFollowRedirects(false);
		post.setRequestHeader("Content-Type", "octet-stream");
		try {
			client.executeMethod(post);
			return post.getResponseBodyAsStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public static String yuntongxunPostResponseWithHttpClient(String url,String encode, String xml, String authorization) {
		HttpClient client = new HttpClient(manager);
		if (!initialed) {
			HttpClientUtil.SetPara();
		}
		PostMethod post = new PostMethod(url);
		post.setRequestBody(xml);
		post.setFollowRedirects(false);
		post.setRequestHeader("Accept", "application/xml");
		post.setRequestHeader("Content-Type", "application/xml;charset=utf-8");
		post.setRequestHeader("Content-Length", "256");
		post.setRequestHeader("Authorization", authorization);
		
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			client.executeMethod(post);
			BufferedReader in = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), post.getResponseCharSet()));
			String inputLine = null;
			
			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			in.close();
			result = HttpClientUtil.ConverterStringCode(resultBuffer.toString(), post.getResponseCharSet(), encode);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			post.releaseConnection();
		}
	}

	private static String ConverterStringCode(String source, String srcEncode,String destEncode) {
		if (source != null) {
			try {
				return new String(source.getBytes(srcEncode), destEncode);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
	private static String mapToString(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		map = new TreeMap<String, String>(map);
		Set es = map.entrySet();
		Iterator it = es.iterator();
		String k ="";
		String v ="";
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			k = (String)entry.getKey();
			v = (String)entry.getValue();
			if(v == null)
				v = "";
			sb.append(k + "=" + v + "&");
		}
		return StringUtils.removeEnd(sb.toString(), "&");
	}
	
}
