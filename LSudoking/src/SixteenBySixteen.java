import marking.MarkingManager;

/**
 * Subclasse de sudoku, encarregada dels sudokus 16x16.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class SixteenBySixteen extends Sudoku {
	/**
	 * Constructor.
	 * @param sudoku: array amb el sudoku.
	 * @param fixed: array amb els valors fixats.
	 * @param sSize: nombre de valors del sudoku.
	 * @param gSize: mida lateral d'un quadrant.
	 * @param outputFile: fitxer de sortida.
	 * @param outputMode: mode de sortida.
	 * @param markingManager: gestor del marcatge.
	 * @param tSolutions: total de solucions.
	 * @param outputFileContent: informació del fitxer de sortida.
	 */
	public SixteenBySixteen(int[][] sudoku, boolean[][] fixed, int sSize, int gSize, String outputFile, int outputMode, MarkingManager markingManager, int tSolutions, String outputFileContent) {
		super(sudoku, fixed, sSize, gSize, outputFile, outputMode, markingManager, tSolutions, outputFileContent);
	}

	/**
	 * Mètode encarregat de determinar si el valor proposat és correcte.
	 */
	@Override
	public boolean bona(int i, int j) {
		return !markingManager.checkValue(i, j, sudoku[i][j]);
	}

	/**
	 * Mètode encarregat de mostrar el sudoku.
	 */
	@Override
	public void showSudoku() {
		for (int i = 0; i < sudoku.length * 1.25; i++) {
			System.out.print("- ");
		}

		System.out.println("");

		for (int i = 0; i < sudoku.length; i++) {
			System.out.print("| ");

			for (int j = 0; j < sudoku[i].length; j++) {
				System.out.print(sudoku[i][j] + " ");
			}

			System.out.println("|");
		}

		for (int i = 0; i < sudoku.length * 1.25; i++) {
			System.out.print("- ");
		}

		System.out.println("");
	}
}
