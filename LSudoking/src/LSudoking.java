import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import marking.Marking;
import marking.MarkingManager;

/**
 * Classe encarregada de gestionar el programa.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class LSudoking {
	private String inputFile;
	private int outputMode;
	private String outputFile;

	/**
	 * Constructor.
	 * @param inputFile: fitxer d'entrada.
	 * @param outputMode: mode de sortida.
	 * @param outputFile: fitxer de sortida.
	 */
	public LSudoking(String inputFile, int outputMode, String outputFile) {
		this.inputFile = inputFile;
		this.outputMode = outputMode;
		this.outputFile = outputFile;
	}

	/**
	 * Mètode encarregat de gestionar la configuració del sudoku.
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void sudokuManager() throws InterruptedException, IOException {
		String dimensions = getSudokuDimensions();
		int sSize = getSudokuSize(dimensions);
		int gSize = getGridSize(dimensions);
		int nGrids = getNumberGrids(dimensions);
		int[][] sudoku = getSudoku(gSize, nGrids);
		boolean[][] fixed = getFixedValues(sudoku, gSize, nGrids);
		MarkingManager markingManager = definirMarcatge(dimensions, sudoku, fixed, gSize, nGrids);
		Sudoku sudokuSolver;

		if (dimensions.equalsIgnoreCase("9x9")) {
			sudokuSolver = new NineByNine(sudoku, fixed, sSize, gSize, outputFile, outputMode, markingManager, 0, "");
			sudokuSolver.solveSudoku(0, 0);

			if (outputMode == 3) {
				sudokuSolver.saveOutputFile();
			}

			int tSolutions = sudokuSolver.gettSolutions();

			if (tSolutions > 1 || tSolutions == 0) {
				System.out.println("Aquest sudoku té " + tSolutions + " solucions!");
			} else {
				System.out.println("Aquest sudoku té " + tSolutions + " solució!");
			}
		}

		if (dimensions.equalsIgnoreCase("16x16")) {
			sudokuSolver = new SixteenBySixteen(sudoku, fixed, sSize, gSize, outputFile, outputMode, markingManager, 0, "");
			sudokuSolver.solveSudoku(0, 0);

			if (outputMode == 3) {
				sudokuSolver.saveOutputFile();
			}

			int tSolutions = sudokuSolver.gettSolutions();

			if (tSolutions > 1 || tSolutions == 0) {
				System.out.println("Aquest sudoku té " + tSolutions + " solucions!");
			} else {
				System.out.println("Aquest sudoku té " + tSolutions + " solució!");
			}
		}

		if (dimensions.equalsIgnoreCase("Samurai")) {
			sudokuSolver = new Samurai(sudoku, fixed, sSize, gSize, outputFile, outputMode, markingManager, 0, "");
			sudokuSolver.solveSudoku(0, 0);

			if (outputMode == 3) {
				sudokuSolver.saveOutputFile();
			}

			int tSolutions = sudokuSolver.gettSolutions();

			if (tSolutions > 1 || tSolutions == 0) {
				System.out.println("Aquest sudoku té " + tSolutions + " solucions!");
			} else {
				System.out.println("Aquest sudoku té " + tSolutions + " solució!");
			}
		}
	}

	/**
	 * Mètode encarregat d'obtenir el tipus de sudoku.
	 * @return tipus de sudoku.
	 */
	public String getSudokuDimensions() {
		String dimensions = "";

		for (int i = 0; i < inputFile.length(); i++) {
			if (inputFile.charAt(i) == '_') {
				dimensions = inputFile.substring(0, i);
			}
		}

		return dimensions;
	}

	/**
	 * Mètode encarregat d'obtenir el nombre de valors del sudoku.
	 * @param dimensions: tipus de sudoku.
	 * @return nombre de valors del sudoku.
	 */
	public int getSudokuSize(String dimensions) {
		int sSize = 0;

		if (dimensions.equalsIgnoreCase("9x9")) {
			sSize = 9;
		}

		if (dimensions.equalsIgnoreCase("16x16")) {
			sSize = 16;
		}

		if (dimensions.equalsIgnoreCase("Samurai")) {
			sSize = 9;
		}

		return sSize;
	}

	/**
	 * Mètode encarregat d'obtenir el tamany lateral d'un quadrant del sudoku.
	 * @param dimensions: tipus de sudoku.
	 * @return tamany lateral d'un quadrant.
	 */
	public int getGridSize(String dimensions) {
		int gSize = 0;

		if (dimensions.equalsIgnoreCase("9x9")) {
			gSize = 3;
		}

		if (dimensions.equalsIgnoreCase("16x16")) {
			gSize = 4;
		}

		if (dimensions.equalsIgnoreCase("Samurai")) {
			gSize = 3;
		}

		return gSize;
	}

	/**
	 * Mètode encarregat d'obtenir el nombre lateral de quadrants.
	 * @param dimensions: tipus de sudoku.
	 * @return nombre lateral de quadrants.
	 */
	public int getNumberGrids(String dimensions) {
		int nGrids = -1;

		if (dimensions.equalsIgnoreCase("9x9")) {
			nGrids = 3;
		}

		if (dimensions.equalsIgnoreCase("16x16")) {
			nGrids = 4;
		}

		if (dimensions.equalsIgnoreCase("Samurai")) {
			nGrids = 7;
		}

		return nGrids;
	}

	/**
	 * Mètode encarregat d'obtenir el sudoku.
	 * @param gSize: mida lateral d'un quadrant.
	 * @param nGrids: nombre de quadrants.
	 * @return sudoku.
	 */
	public int[][] getSudoku(int gSize, int nGrids) {
		int[][] sudoku = new int[gSize * nGrids][gSize * nGrids];
		String fileContent = getFileContent(getFilePath());

		int i = 0;
		int j = 0;

		for (int k = 0; k < fileContent.length(); k++) {
			if (fileContent.charAt(k) >= '0' && fileContent.charAt(k) <= '9') {
				sudoku[i][j] = (sudoku[i][j] * 10) + (fileContent.charAt(k) - '0');
			}

			if (fileContent.charAt(k) == '-') {
				sudoku[i][j] = 0;
			}

			if (fileContent.charAt(k) == '*') {
				sudoku[i][j] = -1;
			}

			if (fileContent.charAt(k) == ' ') {
				if (j < sudoku[i].length - 1) {
					j++;
				} else {
					i++;
					j = 0;
				}
			}
		}

		return sudoku;
	}

	/**
	 * Mètode encarregat d'obtenir la ruta del fitxer.
	 * @return ruta del fitxer.
	 */
	public String getFilePath() {
		String filePath = "./" + inputFile;

		return filePath;
	}

	/**
	 * Mètode encarregat d'emmagatzemar el contingut d'un fitxer.
	 * @param filePath: ruta del fitxer.
	 * @return contingut del fitxer.
	 */
	public String getFileContent(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		String fileContent = "";

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";

			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + " ";
			}

		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}

		return fileContent;
	}

	/**
	 * Mètode encarregat d'obtenir els valors fixats del sudoku.
	 * @param sudoku: array del sudoku.
	 * @param gSize: mida lateral del quadrant.
	 * @param nGrids: nombre lateral de quadrants.
	 * @return array amb els valors fixats.
	 */
	public boolean[][] getFixedValues(int[][] sudoku, int gSize, int nGrids) {
		boolean[][] fixed = new boolean[gSize * nGrids][gSize * nGrids];

		//Marquem els valors inicials com a fixats.
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				if (sudoku[i][j] != 0) {
					fixed[i][j] = true;
				} else {
					fixed[i][j] = false;
				}
			}
		}

		return fixed;
	}

	/**
	 * Mètode encarregat de fer una definició inicial del markatge, i els seus paràmetres.
	 * @param dimensions: tipus de sudoku.
	 * @param sudoku: array amb el sudoku.
	 * @param fixed: array amb els valors fixats.
	 * @param gSize: mida lateral del quadrant.
	 * @param nGrids: nombre lateral de quadrants.
	 * @return gestor de marcatge, amb la configuració inicial.
	 */
	public MarkingManager definirMarcatge(String dimensions, int[][] sudoku, boolean[][] fixed, int gSize, int nGrids) {
		Marking[] marking = new Marking[1];
		int[][] rows = new int[1][1];
		int[][] columns = new int[1][1];
		int[][] grids = new int[1][1];
		int sType = -1;

		//Definim els paràmetres, segons el tipus de sudoku.
		if (dimensions.equalsIgnoreCase("9x9")) {
			marking = new Marking[1];

			rows = new int[9][9];
			columns = new int[9][9];
			grids = new int[9][9];
			sType = 0;

			marking[0] = new Marking(rows, columns, grids);
		}

		if (dimensions.equalsIgnoreCase("16x16")) {
			marking = new Marking[1];

			rows = new int[16][16];
			columns = new int[16][16];
			grids = new int[16][16];
			sType = 1;

			marking[0] = new Marking(rows, columns, grids);
		}

		if (dimensions.equalsIgnoreCase("Samurai")) {
			marking = new Marking[5];
			sType = 2;

			for (int i = 0; i < marking.length; i++) {
				rows = new int[9][9];
				columns = new int[9][9];
				grids = new int[9][9];

				marking[i] = new Marking(rows, columns, grids);
			}
		}

		MarkingManager markingManager = new MarkingManager(marking, gSize, nGrids, sType);

		//Marquem els valors fixats
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				if (fixed[i][j] == true && sudoku[i][j] != -1) {
					markingManager.mark(i, j, sudoku[i][j]);
				}
			}
		}

		return markingManager;
	}
}
