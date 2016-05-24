import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Divide {
	private HashMap<String, ArrayList<String>> data = new HashMap<>();
	private String feature;

	public void readFile() {
		float i=
		try {
			FileReader fileReader = new FileReader("midtermdata.csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			feature = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals(""))
					continue;
				String parts[] = line.split(",");
				if (data.containsKey(parts[1])) {
					data.get(parts[1]).add(line);
				} else {
					ArrayList<String> list = new ArrayList<>();
					list.add(line);
					data.put(parts[1], list);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Error ­­ " + e.toString());
		}
	}

	public void split() {
		Random random = new Random();
		ArrayList<String> nums = new ArrayList<>();
		while (nums.size() != 100) {
			nums.add("St" + (1 + random.nextInt(771)));
		}

		File file = new File("midtermdata-dev.csv");
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(feature + "\n");
			for (int i = 0; i < 100; i++) {
				ArrayList<String> list = data.get(nums.get(i));
				if (list != null) {
					for (int j = 0; j < list.size(); j++) {
						bw.write(list.get(j));
						bw.write("\n");
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file1 = new File("midtermdata-cv.csv");
		try {
			// if file doesnt exists, then create it
			if (!file1.exists()) {
				file1.createNewFile();
			}

			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(feature + "\n");
			for (String str : data.keySet()) {
				if (!nums.contains(str)) {
					ArrayList<String> list = data.get(str);
					for (int j = 0; j < list.size(); j++) {
						bw.write(list.get(j));
						bw.write("\n");
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Divide divide = new Divide();
		divide.readFile();
		divide.split();
	}
}
