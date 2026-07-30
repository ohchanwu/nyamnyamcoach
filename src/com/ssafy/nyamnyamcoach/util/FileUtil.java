package com.ssafy.nyamnyamcoach.util;

import java.io.*;

public class FileUtil {
	public static String read(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			String line = null;
			// an assignment expression in Java
			// returns the assigned value
			while ((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} catch (IOException e) {
			// even if you declare a method with `throws`,
			// you still have to manually `throw`
			// think of `throws` as a warning label
			// and `throw` as the actual action
			throw e;
		}
		
		return sb.toString();
	}
	
	public static void write(String path, String content) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
			bw.write(content);
		} catch (IOException e) {
			throw e;
		}
	};
}