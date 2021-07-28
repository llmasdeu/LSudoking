package marking;

/**
 * Classe amb el marcatge del sudoku.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class Marking {
	private int[][] rows;
	private int[][] columns;
	private int[][] grids;

	/**
	 * Constructor.
	 * @param rows: array amb les files.
	 * @param columns: array amb les columnes.
	 * @param grids: array amb els quadrants.
	 */
	public Marking(int[][] rows, int[][] columns, int[][] grids) {
		this.rows = rows;
		this.columns = columns;
		this.grids = grids;
	}

	/**
	 * Mètode encarregat de marcar la fila.
	 * @param row: fila.
	 * @param value: valor.
	 */
	public void markRow(int row, int value) {
		rows[row][value - 1]++;						//Sumem 1.
	}

	/**
	 * Mètode encarregat de marcar la columna.
	 * @param column: columna.
	 * @param value: valor.
	 */
	public void markColumn(int column, int value) {
		columns[column][value - 1]++;				//Sumem 1.
	}

	/**
	 * Mètode encarregat de marcar el quadrant.
	 * @param grid: quadrant.
	 * @param value: valor.
	 */
	public void markGrid(int grid, int value) {
		grids[grid][value - 1]++;					//Sumem 1.
	}

	/**
	 * Mètode encarregat de desmarcar la fila.
	 * @param row: fila.
	 * @param value: valor.
	 */
	public void unmarkRow(int row, int value) {
		rows[row][value - 1]--;						//Restem 1.
	}

	/**
	 * Mètode encarregat de desmarcar la columna.
	 * @param column: columna.
	 * @param value: valor.
	 */
	public void unmarkColumn(int column, int value) {
		columns[column][value - 1]--;				//Restem 1.
	}

	/**
	 * Mètode encarregat de desmarcar el quadrant.
	 * @param grid: quadrant.
	 * @param value: valor.
	 */
	public void unmarkGrid(int grid, int value) {
		grids[grid][value - 1]--;					//Restem 1.
	}

	/**
	 * Mètode encarregat de comprovar si el valor es repeteix a la fila.
	 * @param row: fila.
	 * @param value: valor.
	 * @return booleà.
	 */
	public boolean checkRow(int row, int value) {
		return rows[row][value - 1] > 1;			//N'hi ha més d'1?
	}

	/**
	 * Mètode encarregat de comprovar si el valor es repeteix a la columna.
	 * @param column: columna.
	 * @param value: valor.
	 * @return booleà.
	 */
	public boolean checkColumn(int column, int value) {
		return columns[column][value - 1] > 1;		//N'hi ha més d'1?
	}

	/**
	 * Mètode encarregat de comprovar si el valor es repeteix al quadrant.
	 * @param grid: quadrant.
	 * @param value: valor.
	 * @return booleà.
	 */
	public boolean checkGrid(int grid, int value) {
		return grids[grid][value - 1] > 1;			//N'hi ha més d'1?
	}

	/**
	 * Mètode encarregat de mostrar les dades del marcatge de les files.
	 */
	public void showRowValues() {
		for (int i = 0; i < rows.length; i++) {
			int row = i + 1;
			System.out.print("ROW " + row + ": ");

			for (int j = 0; j < rows[i].length; j++) {
				System.out.print(rows[i][j] + " ");
			}

			System.out.println("");
		}
	}

	/**
	 * Mètode encarregat de mostrar les dades del marcatge de les columnes.
	 */
	public void showColumnValues() {
		for (int i = 0; i < columns.length; i++) {
			int column = i + 1;
			System.out.print("COLUMN " + column + ": ");

			for (int j = 0; j < columns[i].length; j++) {
				System.out.print(columns[i][j] + " ");
			}

			System.out.println("");
		}
	}

	/**
	 * Mètode encarregat de mostrar les dades del marcatge dels quadrants.
	 */
	public void showGridValues() {
		for (int i = 0; i < grids.length; i++) {
			int grid = i + 1;
			System.out.print("GRID " + grid + ": ");

			for (int j = 0; j < grids[i].length; j++) {
				System.out.print(grids[i][j] + " ");
			}

			System.out.println("");
		}
	}

	/**
	 * Mètode encarregat de reiniciar les dades del marcatge de les files.
	 */
	public void resetRowValues() {
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				rows[i][j] = 0;
			}
		}
	}

	/**
	 * Mètode encarregat de reiniciar les dades del marcatge de les columnes.
	 */
	public void resetColumnValues() {
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				columns[i][j] = 0;
			}
		}
	}

	/**
	 * Mètode encarregat de reiniciar les dades del marcatge dels quadrants.
	 */
	public void resetGridValues() {
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				grids[i][j] = 0;
			}
		}
	}
}
