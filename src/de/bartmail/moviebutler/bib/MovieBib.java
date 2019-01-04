package de.bartmail.moviebutler.bib;

import de.bartmail.moviebutler.main.Util;

public class MovieBib
{
	public static final String[] ALLOWED_LANGUAGES = {"de", "en"};

	public static boolean allowedLang(String lang)
	{
		return Util.isAnyOf(lang, ALLOWED_LANGUAGES);
	}
}
