package de.bartmail.moviebutler.bib;

import java.nio.file.Path;

import de.bartmail.moviebutler.main.Util;

public class MovieBib
{
	public static final String[] ALLOWED_LANGUAGES = {"test", "de", "en"};
	public static final String[] ALLOWED_TYPES = {"AVI", "MP4", "MKV", "ISO"};

	public static boolean allowedLang(String lang)
	{
		return Util.isAnyOf(lang, ALLOWED_LANGUAGES);
	}

	public static boolean allowedType(Path file)
	{
		return Util.isAnyOf(Util.getExtension(file.getFileName().toString()), ALLOWED_TYPES);
	}
}
