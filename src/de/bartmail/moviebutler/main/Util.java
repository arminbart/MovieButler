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
}
