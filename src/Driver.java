
import java.io.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws IOException
	{
		SpotifyPlaylistHelper app = new SpotifyPlaylistHelper();
		app.makeindex("Playlists.txt", "Library.txt");
		
		System.out.println(app.library);
		System.out.println(app.playlists);
		
		System.out.println("Choose an operation:");
		System.out.println("(1) Scan for songs that appear in library but do not appear in playlists");
		System.out.println("(2) Scan for songs that appear in playlists but do not appear in library");
		System.out.println("(3) Scan for songs that appear in multiple playlists");
		System.out.println("(4) Scan for songs that appear in a playlist multiple times");
		
		Scanner s = new Scanner(System.in);
		int input = s.nextInt();
		s.close();
		
		if      (input == 1)
		{
			app.scan1();
			System.out.println("Scan 1 complete. See Result.txt for output.");
		}
		else if (input == 2)
		{
			app.scan2();
			System.out.println("Scan 2 complete. See Result.txt for output.");
		}
		else if (input == 3)
		{
			app.scan3();
			System.out.println("Scan 3 complete. See Result.txt for output.");
		}
		else if (input == 4)
		{
			app.scan4();
			System.out.println("Scan 4 complete. See Result.txt for output.");
		}
		else
		{
			System.out.println("Invalid input");
		}
	}

}