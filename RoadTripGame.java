package comp2402a5;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadTripGame {

	/**
	 * Your code goes here
	 * 
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// TODO: Your solution goes here.
		ArrayList<String> country = new ArrayList<String>();
		AdjacencyLists adj = new AdjacencyLists(0);

		String characters = r.readLine();
		String charString = "";
		do {

			// if(characters.getClass().getName() != "String"){
			// continue;
			// }
			// System.out.println(characters);
			char first = characters.charAt(0);
			char last = characters.charAt(characters.length() - 1);
			charString = "" + first + last;

			if (!country.contains(charString)) {
				country.add(charString);
				adj.addVertex();
			}

			characters = r.readLine();
			if (characters == null) {
				break;
			}
		} while (true);
		for (int i = 0; i < country.size(); i++) {
			for (int j = 0; j < country.size(); j++) {

				if (country.get(i).charAt(country.get(i).length() - 1) == country.get(j).charAt(0)) {
					adj.addEdge(i, j);
				}
			}
		}
		int edgeLength = Algorithms.bfs(adj, 0, country.size() - 1);

		if (country.get(0).equals(charString)) {
			w.println(1);
		} else {
			w.println(1 + edgeLength);
		}
	}

	/**
	 * The driver. Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call
	 * doIt.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop - start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}