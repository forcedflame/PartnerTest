// Hari Rangarajan, Mufaddal Kapadia; 12/3/2025
import java.util.*;
import java.io.*;
public class PTest {
	public static void main(String[] args) {
		String[] input_files = {"book1.txt", "book2.txt"};
		String[] output_files = {"book1_output.txt", "book2_output.txt"};

		Thread thread = new Thread(() -> {
			processFile("book1.txt", "book1_output.txt");
			processFile("book2.txt", "book2_output.txt");
		});

		long start = System.nanoTime();

		thread.start();

		try {
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(int i = 0; i < input_files.length; i++) {
			processFile(input_files[i], output_files[i]);
		}

		long end = System.nanoTime();
		double time = (end - start) / 1000000000.0;
		System.out.println("Total time: " + time + " s");
	}

	public static void processFile(String input_file, String output_file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));

			String line;
			while((line = br.readLine()) != null) {
				StringBuilder sb = new StringBuilder(line);
				sb.append(line);
				bw.write(sb.toString());
				bw.newLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}