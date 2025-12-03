// Hari Rangarajan, Mufaddal Kapadia; 12/3/2025
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class PTest {
	public static void main(String[] args) {
		String[] input_files = {"book1.txt", "book2.txt"};
		String[] output_files = {"book1_output.txt", "book2_output.txt"};

		ExecutorService exe = Executors.newFixedThreadPool(2);

		Future<Double>[] futures = new Future[input_files.length];
		long start = System.nanoTime();

		for(int i = 0; i<futures.length;i++){
			final int index = i;
			futures[i] = exe.submit(() -> {
				long start1 = System.nanoTime();
				processFile(input_files[index], output_files[index]);
				long end = System.nanoTime();

                return (end - start1) / 1_000_000_000.0;
			});
		}

		for(int i = 0; i<futures.length;i++){
			try{
				double threadTime = futures[i].get();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		long end = System.nanoTime();
		System.out.println("Total time: " + (end - start) / 1000000.0);
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