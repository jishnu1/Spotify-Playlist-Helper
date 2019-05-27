
import java.io.*;
import java.util.*;

public class SpotifyPlaylistHelper {
	
	HashSet<String> library;
	HashMap<String,ArrayList<Occurrence>> playlists;
	
	public SpotifyPlaylistHelper()
	{
		library = new HashSet<String>(100,2.0f);
		playlists = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
	}
	
	public void scan1() // (1) Scan for songs that appear in library but do not appear in playlists
	{
		File result = new File("Result.txt");
		
		try
		
		{
			result.createNewFile();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		try
		{
			FileWriter fileW = new FileWriter(result);
			BufferedWriter buffW = new BufferedWriter(fileW);
			
			for (String song : library)
			{
				if (!playlists.containsKey(song))
				{
					buffW.write(song);
					buffW.write("\n");
				}
			}
			buffW.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void scan2() // (2) Scan for songs that appear in playlists but do not appear in library
	{
		File result = new File("Result.txt");
		
		try
		{
			result.createNewFile();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		try
		{
			FileWriter fileW = new FileWriter(result);
			BufferedWriter buffW = new BufferedWriter(fileW);
			
			for (String song : playlists.keySet())
			{
				if (!library.contains(song))
				{
					boolean multiple = false;
					
					for (Occurrence occ : playlists.get(song))
					{
						if (!multiple)
						{
							buffW.write(song);
							buffW.write("\n");
							buffW.write(occ + "");
							buffW.write("\n");
							multiple = true;
						}
						else
						{
							buffW.write(occ + " | ");
							buffW.write("\n");
						}
					}
				}
			}
			buffW.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void scan3() // (3) Scan for songs that appear in multiple playlists
	{
		File result = new File("Result.txt");
		
		try
		{
			result.createNewFile();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		try
		{
			FileWriter fileW = new FileWriter(result);
			BufferedWriter buffW = new BufferedWriter(fileW);
			
			for (String song : playlists.keySet())
			{
				if (playlists.get(song).size()>1)
				{
					boolean multiple = false;
					
					for (Occurrence occ : playlists.get(song))
					{
						if (!multiple)
						{
							buffW.write(song);
							buffW.write("\n");
							buffW.write(occ + "");
							buffW.write("\n");
							multiple = true;
						}
						else
						{
							buffW.write(occ + "");
							buffW.write("\n");
						}
					}
				}
			}
			buffW.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void scan4() // (4) Scan for songs that appear in a playlist multiple times
	{
		File result = new File("Result.txt");
		
		try
		{
			result.createNewFile();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		try
		{
			FileWriter fileW = new FileWriter(result);
			BufferedWriter buffW = new BufferedWriter(fileW);
			
			for (String song : playlists.keySet())
			{
				boolean duplicate = false;
				
				for (Occurrence occ : playlists.get(song))
				{
					if (occ.frequency > 1)
					{
						if (!duplicate)
						{
							buffW.write(song);
							buffW.write("\n");
							buffW.write(occ + "");
							buffW.write("\n");
							duplicate = true;
						}
						else
						{
							buffW.write(occ + "");
							buffW.write("\n");
						}
					}
				}
			}
			buffW.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void makeindex(String namefile, String libfile)
	throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(libfile));
		while (sc.hasNext())
		{
			String song = sc.next();
			library.add(song);
		}
		sc = new Scanner(new File(namefile));
		while (sc.hasNext())
		{
			String listfile = sc.next();
			HashMap<String,Occurrence> playlist = loadsongs(listfile);
			mergesongs(playlist);
		}
		sc.close();
	}
	
	public HashMap<String,Occurrence> loadsongs(String listfile)
	throws FileNotFoundException {
		
		HashMap<String, Occurrence> playlist = new HashMap<>();
	    Scanner sc = new Scanner(new File(listfile));
	    
	   	while(sc.hasNext())
	   	{
	   		String song = sc.next();
	   		
	   		if (song != null)
	   		{
	   		    if (playlist.containsKey(song))
	   		    {
	   		    	playlist.get(song).frequency++;
	   		    }
                else
                {
                	playlist.put(song, new Occurrence(listfile, 1));
                }
            }
		}
	   	sc.close();
		return playlist;
	}
	
	public void mergesongs(HashMap<String,Occurrence> playlist)
	{
		for (String song : playlist.keySet())
		{
			ArrayList<Occurrence> occlist = new ArrayList<Occurrence>();

			if (playlists.containsKey(song))
			{
				occlist = playlists.get(song);
			}
			occlist.add(playlist.get(song));
			playlists.put(song, occlist);
		}
	}
	
}
