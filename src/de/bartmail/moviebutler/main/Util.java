package de.bartmail.moviebutler.main;

public class Util
{
	public static boolean isAnyOf(String str, String[] list)
	{
		for (String entry : list)
			if (str.equalsIgnoreCase(entry))
				return true;

		return false;
	}

	// Stolen function that is clamed to be tested
	public static String getExtension(String fileName)
	{
		char ch;
		int len;

		if (fileName == null ||
				(len = fileName.length())==0 ||
				(ch = fileName.charAt(len-1))=='/' || ch=='\\' || //in the case of a directory
				 ch=='.') //in the case of . or ..
			return "";

		int dotInd = fileName.lastIndexOf('.');
		int sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (dotInd <= sepInd)
			return "";
		else
			return fileName.substring(dotInd+1).toLowerCase();
	}
}
