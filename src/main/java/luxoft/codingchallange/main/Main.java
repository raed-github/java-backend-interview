package luxoft.codingchallange.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import luxoft.codingchallange.filesystem.File;
import luxoft.codingchallange.filesystem.FileSystem;
import luxoft.codingchallange.filesystem.Permissions;

public class Main {

	public static void main(String[] args) {
		File[][] files = FileSystem.files;
		displayMatrix(files);

		System.out.println();
		System.out.println();

		System.out.println("eg1: all files with name containg \"asd\" or \"20\"");
		Predicate<File> searchCombination1 = f -> f.getName().contains("20");
		List<File> foundFiles = findFile(files, searchCombination1);
		printFiles(foundFiles);
		System.out.println();
		System.out.println();

		System.out.println("eg2: all files that have Permission W and created before 18-05-2022");

		Predicate<File> searchCombination2 = f -> f.getPermission().equals(Permissions.W)
				&& f.getTimeCreated().isBefore(LocalDateTime.of(2022, 12, 5, 12, 12));
		foundFiles = findFile(files, searchCombination2);
		printFiles(foundFiles);
		System.out.println();
		System.out.println();

		System.out.println("eg3: file with size 0 and Permission R created on 1-12-2022 having name file00");
		Predicate<File> searchCombination3 = f -> f.getName().equalsIgnoreCase("file00") && f.getSize() == 0
				&& f.getPermission().equals(Permissions.R)
				&& f.getTimeCreated().isEqual(LocalDateTime.of(2022, 12, 1, 12, 12));

		foundFiles = findFile(files, searchCombination3);
		printFiles(foundFiles);

	}

	/**
	 * search file in a matrix using diagonal traversal algorithm
	 * 
	 * @param data
	 * @param predicate
	 * @return
	 */
	public static List<File> findFile(File[][] data, Predicate<File> predicate) {
		List<File> appendFile = new ArrayList<File>();
		int row = data.length;
		int col = data[0].length;
		// Matrix First Half
		for (int i = 0; i < col; ++i) {
			for (int j = i; j >= 0 && i - j < row; --j) {
				if (fileExists(data[i - j][j], predicate)) {
					appendFile.add(data[i - j][j]);
				}
			}
		}
		// Matrix Second Half
		for (int i = 1; i < row; ++i) {
			for (int j = col - 1, k = i; j >= 0 && k < row; --j, k++) {
				if (fileExists(data[k][j], predicate)) {
					appendFile.add(data[k][j]);
				}
			}
		}
		return appendFile;
	}

	/**
	 * find file based on a specific search combination in the form of predicate
	 * 
	 * @param file
	 * @param predicate
	 * @return
	 */
	public static boolean fileExists(File file, Predicate<File> predicate) {
		return predicate.test(file);
	}

	/**
	 * print a matrix
	 * 
	 * @param files
	 */
	private static void displayMatrix(File[][] files) {
		for (int r = 0; r < files.length; r++) {
			for (int c = 0; c < files[0].length; c++) {
				System.out.print(files[r][c] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * print list of files
	 * 
	 * @param files
	 */
	private static void printFiles(List<File> files) {
		for (File file : files) {
			System.out.println(files.toString());
		}

	}
}
