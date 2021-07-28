import java.io.FileWriter;
import java.io.IOException;

import marking.MarkingManager;
import sudokuGUI.SudokuGUI;

/**
 * Classe encarregada de la resolució del sudoku.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class Sudoku {
	protected int[][] sudoku;
	private boolean[][] fixed;
	protected int sSize;
	protected int gSize;
	private String outputFile;
	private int outputMode;
	protected MarkingManager markingManager;
	private int tSolutions;
	private String outputFileContent;

	/**
	 * Constructor.
	 * @param sudoku: array amb el sudoku.
	 * @param fixed: array amb els valors fixats.
	 * @param sSize: nombre de valors del sudoku.
	 * @param gSize: mida lateral d'un quadrant.
	 * @param outputFile: fitxer de sortida.
	 * @param outputMode: mode de sortida.
	 * @param markingManager: gestor del marcatge del sudoku.
	 * @param tSolutions: nombre total de solucions.
	 * @param outputFileContent: informació del fitxer de sortida.
	 */
	public Sudoku(int[][] sudoku, boolean[][] fixed, int sSize, int gSize, String outputFile, int outputMode, MarkingManager markingManager, int tSolutions, String outputFileContent) {
		this.sudoku = sudoku;
		this.fixed = fixed;
		this.sSize = sSize;
		this.gSize = gSize;
		this.outputFile = outputFile;
		this.outputMode = outputMode;
		this.markingManager = markingManager;
		this.tSolutions = tSolutions;
		this.outputFileContent = outputFileContent;
	}

	/**
	 * Getter del sudoku.
	 * @return sudoku.
	 */
	public int[][] getSudoku() {
		return sudoku;
	}

	/**
	 * Setter del sudoku.
	 * @param sudoku: array amb el sudoku.
	 */
	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}

	/**
	 * Getter dels valors fixats
	 * @return valors fixats.
	 */
	public boolean[][] getFixed() {
		return fixed;
	}

	/**
	 * Setter dels valors fixats.
	 * @param fixed: array dels valors fixats.
	 */
	public void setFixed(boolean[][] fixed) {
		this.fixed = fixed;
	}

	/**
	 * Getter del nombre de valors del sudoku.
	 * @return nombre de valors del sudoku.
	 */
	public int getsSize() {
		return sSize;
	}

	/**
	 * Setter del nombre de valors del sudoku.
	 * @param sSize: nombre de valors del sudoku.
	 */
	public void setsSize(int sSize) {
		this.sSize = sSize;
	}

	/**
	 * Getter de la mida lateral d'un quadrant.
	 * @return mida lateral d'un quadrant.
	 */
	public int getgSize() {
		return gSize;
	}

	/**
	 * Setter de la mida lateral d'un quadrant.
	 * @param gSize: mida lateral d'un quadrant.
	 */
	public void setgSize(int gSize) {
		this.gSize = gSize;
	}

	/**
	 * Getter del fitxer de sortida.
	 * @return fitxer de sortida.
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * Setter del fitxer de sortida.
	 * @param outputFile: fitxer de sortida.
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * Getter del mode de sortida.
	 * @return mode de sortida.
	 */
	public int getOutputMode() {
		return outputMode;
	}

	/**
	 * Setter del mode de sortida.
	 * @param outputMode: mode de sortida.
	 */
	public void setOutputMode(int outputMode) {
		this.outputMode = outputMode;
	}

	/**
	 * Getter del total de solucions.
	 * @return total de solucions.
	 */
	public int gettSolutions() {
		return tSolutions;
	}

	/**
	 * Mètode encarregat de la resolució del sudoku.
	 * @param i: fila.
	 * @param j: columna.
	 * @throws IOException 
	 */
	public void solveSudoku(int i, int j) throws IOException {
		if (fixed[i][j] == true) {
			if (!sudokuEnd(i, j)) {
				solveSudoku(getNextRow(i, j), getNextColumn(i, j));
			}
		} else {
			preparaRecorregutNivell(i, j);

			while (hiHaSuccessor(i, j)) {
				seguentGerma(i, j);
				markingManager.mark(i, j, sudoku[i][j]);

				if (solucio(i, j)) {
					if (bona(i, j)) {
						tractarSolucio();
					}
				}

				if (!solucio(i, j)) {
					if (bona(i, j)) {
						if (!sudokuEnd(i, j)) {
							solveSudoku(getNextRow(i, j), getNextColumn(i, j));
						}
					}
				}

				markingManager.unmark(i, j, sudoku[i][j]);
			}

			preparaRecorregutNivell(i, j);
		}
	}

	/**
	 * Mètode encarregat d'inicialitzar a 0 el nivell.
	 * @param i: fila.
	 * @param j: columna.
	 */
	public void preparaRecorregutNivell(int i, int j) {
		sudoku[i][j] = 0;
	}

	/**
	 * Mètode encarregat de determinar si s'han tingut en compte tots els valors possibles a la casella.
	 * @param i: fila.
	 * @param j: columna.
	 * @return booleà.
	 */
	public boolean hiHaSuccessor(int i, int j) {
		return sudoku[i][j] < sSize;
	}

	/**
	 * Mètode encarregat de sumar 1 a la casella.
	 * @param i: fila.
	 * @param j: columna.
	 */
	public void seguentGerma(int i, int j) {
		sudoku[i][j]++;
	}

	/**
	 * Mètode encarregat de determinar si s'ha arribat al final del sudoku.
	 * @param i: fila.
	 * @param j: columna.
	 * @return booleà.
	 */
	public boolean solucio(int i, int j) {
		return i == sudoku.length - 1 && j == sudoku[i].length - 1;
	}

	/**
	 * Mètode encarregat de determinar si la solució és bona (s'aplicarà @Override a les subclasses).
	 * @param i: fila.
	 * @param j: columna.
	 * @return booleà.
	 */
	public boolean bona(int i, int j) {
		return false;
	}

	/**
	 * Mètode encarregat de tractar la solució.
	 */
	public void tractarSolucio() {
		tSolutions++; //Augmentem el comptador de solucions.

		switch (outputMode) {
			case 0:		//No es volen mostrar les solucions.
				//Res.
				break;

			case 1:		//Mostrar les solucions per línia de comandes.
				System.out.println("Solució nº " + tSolutions);
				showSudoku();
				break;

			case 2:		//Mostrar les solucions de forma gràfica.
				String title = "Solution nº " + tSolutions;
				boolean[][] toSolve = new boolean[fixed.length][fixed[0].length];

				for (int i = 0; i < toSolve.length; i++) {
					for (int j = 0; j < toSolve[i].length; j++) {
						toSolve[i][j] = !fixed[i][j];
					}
				}

				SudokuGUI sudokuGUI = new SudokuGUI(title, 0, 0, toSolve);
				sudokuGUI.updateBoard(sudoku);
				break;

			case 3:		//Emmagatzemar les solucions en un fitxer de sortida.
				outputFileContent = outputFileContent + "Solution nº " + tSolutions + "\n\n";

				for (int i = 0; i < sudoku.length; i++) {
					for (int j = 0; j < sudoku[i].length; j++) {
						if (sudoku[i][j] == -1) {
							outputFileContent = outputFileContent + "* ";
						} else {
							outputFileContent = outputFileContent + sudoku[i][j] + " ";
						}
					}

					outputFileContent = outputFileContent + "\n";
				}

				outputFileContent = outputFileContent + "\n";
				break;
		}
	}

	/**
	 * Mètode encarregat de determinar si s'ha arribat al final del sudoku (per tal de contemplar la següent casella disponible).
	 * @param i: fila.
	 * @param j: columna.
	 * @return booleà.
	 */
	public boolean sudokuEnd(int i, int j) {
		return i == sudoku.length - 1 && j == sudoku[i].length - 1;
	}

	/**
	 * Mètode encarregat d'obtenir la següent fila a examinar.
	 * @param i: fila.
	 * @param j: columna.
	 * @return fila a examinar.
	 */
	public int getNextRow(int i, int j) {
		int row;

		if (j < sudoku[i].length - 1) {
			row = i;
		} else {
			row = i + 1;
		}

		return row;
	}

	/**
	 * Mètode encarregat d'obtenir la següent columna a examinar.
	 * @param i: fila.
	 * @param j: columna.
	 * @return columna a examinar.
	 */
	public int getNextColumn(int i, int j) {
		int column;

		if (j < sudoku[i].length - 1) {
			column = j + 1;
		} else {
			column = 0;
		}

		return column;
	}

	/**
	 * Mètode encarregat de mostrar el sudoku.
	 */
	public void showSudoku() {
		//Mostrem el sudoku
	}

	/**
	 * Mètode encarregat de desar el fitxer de sortida.
	 * @throws IOException
	 */
	public void saveOutputFile() throws IOException {
		try (FileWriter file = new FileWriter("./" + outputFile)) {
			file.write(outputFileContent);
		}
	}
}
