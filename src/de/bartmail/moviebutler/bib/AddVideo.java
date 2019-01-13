package de.bartmail.moviebutler.bib;

import java.io.IOException;
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
		int indention = args.length > 3 ? Integer.valueOf(args[3]) : 0;

		if (!Files.exists(file) || Files.isDirectory(file))
			throw new MovieButlerException("File '" + args[2] + "' not found or not a regular file.");

		if (!MovieBib.allowedType(file))
		{
			out("Skip unsupported file '" + args[2] + "'", indention);
			return;
		}

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

		if (lang.equalsIgnoreCase("test"))
			out(fileName, indention);
		else
			out(BibRequest.executePost(postParameters), indention);
	}

	public static void bulkAddVideos(String[] args) throws MovieButlerException
	{
		if (args.length < 3 || !MovieBib.allowedLang(args[1]))
			throw new MovieButlerException("Please provide valid language (de|en) and path name.");

		String lang = args[1].toLowerCase();
		Path path = Paths.get(args[2]);
		int indention = args.length > 3 ? Integer.valueOf(args[3]) : 0;

		if (!Files.exists(path) || !Files.isDirectory(path))
			throw new MovieButlerException("Path '" + args[2] + "' not found or not a directory.");

		out("Traverse path '" + path.getFileName() + "'", indention);
		try
		{
			// First the regular files...
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
			{
				for (Path file : stream)
					if (!Files.isDirectory(file))
						addVideo(new String[] { "add", lang, file.toString(), String.valueOf(indention + 1) });
			}

			// ...and then the subdirectories
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
			{
				for (Path file : stream)
					if (Files.isDirectory(file))
						bulkAddVideos(new String[] { "bulkadd", lang, file.toString(), String.valueOf(indention + 1) });
			}
		}
		catch (IOException e)
		{
			throw new MovieButlerException("Walking path '" + args[2] + "' failed.", e);
		}
	}

	public static void out(String msg, int indent)
	{
		if (indent > 0)
			System.out.print(new String(new char[indent * 3]).replace("\0", " "));
		System.out.println(msg);
	}
}
