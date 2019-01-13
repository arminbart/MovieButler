package de.bartmail.moviebutler.main;

import de.bartmail.moviebutler.bib.AddVideo;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length == 0)
			help();

		try
		{
			switch (args[0].toLowerCase())
			{
				case "add":
					AddVideo.addVideo(args);
					break;

				case "bulkadd":
					AddVideo.bulkAddVideos(args);
					break;

				default:
					help();
			}

			System.exit(0);
		}
		catch (MovieButlerException e)
		{
			System.out.println(e.getMessage());
			System.exit(2);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(3);
		}
	}

	private static void help()
	{
		System.out.println("No command parameter provied.");
		System.out.println("Commands: add LANG FILENAME");
		System.out.println("               (add new video file to MovieBib)");
		System.out.println("          bulkadd LANG PATHNAME");
		System.out.println("               (search a folder and add all found video files; subfolders included)");
		System.exit(1);
	}
}
