package de.bartmail.moviebutler.bib;

import java.io.*;
import java.net.*;

import de.bartmail.moviebutler.main.MovieButlerException;

public class BibRequest
{
	public static final String MOVIE_BIB_URL = "http://video.bartmail.de/api.php";


	@SuppressWarnings("serial")
	public static class PostParams extends java.util.ArrayList<BibRequest.PostParam>
	{
		public void addParam(String k, String v)
		{
			add(new PostParam(k, v));
		}
	}

	public static class PostParam
	{
		public String key;
		public String value;

		public PostParam(String k, String v)
		{
			key = k;
			value = v;
		}

		public String encoded() throws UnsupportedEncodingException
		{
			return URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
		}
	}


	public static String executePost(PostParams postParameters) throws MovieButlerException
	{
		return executePost(MOVIE_BIB_URL, postParameters);
	}

	public static String executePost(String targetURL, PostParams postParameters) throws MovieButlerException
	{
		HttpURLConnection connection = null;

		try
		{
			// Create POST data
			String postBody = createPostBody(postParameters);

			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(postBody.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");  

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream os = new DataOutputStream(connection.getOutputStream());
			os.writeBytes(postBody);
			os.close();

			// Get Response  
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			String line;

			while ((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			rd.close();

			return response.toString();
		}
		catch (Exception e)
		{
			throw new MovieButlerException("POST request to '" + targetURL + "' failed.", e);
		}
		finally
		{
			if (connection != null)
				connection.disconnect();
		}
	}

	public static String createPostBody(PostParams postParameters) throws UnsupportedEncodingException
	{
		StringBuffer sb = new StringBuffer();

		for (PostParam param : postParameters)
			sb.append(sb.length() > 0 ? "&" : "").append(param.encoded());

		return sb.toString();
	}
}
