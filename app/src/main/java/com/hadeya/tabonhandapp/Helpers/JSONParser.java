package com.hadeya.tabonhandapp.Helpers;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class JSONParser
{
    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jObjA = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }
    OkHttpClient client = new OkHttpClient();

    void asd(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String ss= response.body().string();
            //String ss2= exam.body().string();
        }catch (Exception e){}
    }
    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method,List<NameValuePair> params) {
        String resp = null;
        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                // httpPost.setEntity(new UrlEncodedFormEntity(params));
                // Log.e("JSON PARAMS", params.toString().getBytes("UTF8") +
                // "");
                String ee = "{\"email\":\"" + "test@test.com"
                        + "\",\"password\":\"" + "123" + "\"}";
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setEntity(new ByteArrayEntity(ee.getBytes("UTF8")));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    //url += "?" + paramString;
                    HttpGet httpGet = new HttpGet(url);

                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    // handle gzip compression
                    Header contentEncodingHeader = httpEntity.getContentEncoding();
                    if (contentEncodingHeader != null) {
                        HeaderElement[] encodings = contentEncodingHeader.getElements();
                        for (HeaderElement encoding : encodings) {
                            if (encoding.getName().equalsIgnoreCase("gzip")) {
                                httpEntity = new GzipDecompressingEntity(httpEntity);
                                break;
                            }
                        }
                    }

// get response content
                    resp = EntityUtils.toString(httpEntity, "UTF-8");
                    //is = httpEntity.getContent();
                    //is = httpEntity.getContent();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
			/*BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			// Log.e("String Returned", sb.toString() + "");
			json = sb.toString();*/
            json = resp;
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }

    public JSONArray makeHttpRequestArray(String url, String method, List<NameValuePair> params) {
        String resp = null;
        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                // httpPost.setEntity(new UrlEncodedFormEntity(params));
                // Log.e("JSON PARAMS", params.toString().getBytes("UTF8") +
                // "");
                String ee = "{\"email\":\"" + "test@test.com"
                        + "\",\"password\":\"" + "123" + "\"}";
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setEntity(new ByteArrayEntity(ee.getBytes("UTF8")));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {

                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
			/*
				final HttpParams httpParameters = httpClient.getParams();

				HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
				HttpConnectionParams.setSoTimeout(httpParameters, 5000);
				*/
                //url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                // handle gzip compression
                Header contentEncodingHeader = httpEntity.getContentEncoding();
                if (contentEncodingHeader != null) {
                    HeaderElement[] encodings = contentEncodingHeader.getElements();
                    for (HeaderElement encoding : encodings) {
                        if (encoding.getName().equalsIgnoreCase("gzip")) {
                            httpEntity = new GzipDecompressingEntity(httpEntity);
                            break;
                        }
                    }
                }

// get response content
                resp = EntityUtils.toString(httpEntity, "UTF-8");
                //is = httpEntity.getContent();
                //is = httpEntity.getContent();
            }
/*
final HttpParams httpParameters = yourHttpClient.getParams();

HttpConnectionParams.setConnectionTimeout(httpParameters, connectionTimeOutSec * 1000);
HttpConnectionParams.setSoTimeout        (httpParameters, socketTimeoutSec * 1000);
//////
HttpGet httpGet = new HttpGet(url);//
HttpParams httpParameters = new BasicHttpParams();
// Set the timeout in milliseconds until a connection is established.
// The default value is zero, that means the timeout is not used.
int timeoutConnection = 3000;
HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
// Set the default socket timeout (SO_TIMEOUT)
// in milliseconds which is the timeout for waiting for data.
int timeoutSocket = 5000;
HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
HttpResponse exam = httpClient.execute(httpGet);//
 */
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
			/*BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			// Log.e("String Returned", sb.toString() + "");
			json = sb.toString();*/
            json = resp;
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObjA = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObjA;

    }
}
