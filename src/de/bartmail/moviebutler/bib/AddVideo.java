package de.bartmail.moviebutler.bib;

import java.nio.file.*;

import de.bartmail.moviebutler.main.MovieButlerException;

public class AddVideo
{
	public static void addVideo(String[] args) throws MovieButlerException
	{
		if (args.length < 3 || !MovieBib.allowedLang(args[1]))
			throw new MovieButlerException("Please provide valid language (de|en) and file name.");

		String lang = args[1].toLowerCase();
		Path file = Paths.get(args[2]);

		if (!Files.exists(file))
			throw new MovieButlerException("File '" + args[2] + "' not found.");

		BibRequest.PostParams postParameters = new BibRequest.PostParams();
		String fileName = file.getFileName().toString();
		String filePath = file.toAbsolutePath().getParent().toString();

		if (filePath.startsWith("\\\\empire\\video\\"))
			filePath = filePath.substring("\\\\empire\\video\\".length());
		if (filePath.startsWith("C:\\Users\\Armin\\Downloads\\otvr\\"))
			filePath = filePath.substring("C:\\Users\\Armin\\Downloads\\otvr\\".length());

		postParameters.addParam("file", fileName);
		postParameters.addParam("location", filePath);
		postParameters.addParam("lang", lang);

		String response = BibRequest.executePost(postParameters);
		System.out.println(response);
	}
}
