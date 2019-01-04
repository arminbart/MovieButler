package de.bartmail.moviebutler.main;

import de.bartmail.moviebutler.bib.AddVideo;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.out.println("No command parameter provied.");
			System.out.println("Commands: add (add new video file to MovieBib)");
			System.exit(1);
		}

		try
		{
			switch (args[0].toLowerCase())
			{
				case "add":
					AddVideo.addVideo(args);
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
}
