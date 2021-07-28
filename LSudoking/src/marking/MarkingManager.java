package marking;

/**
 * Classe amb la qual gestionem el marcatge del sudoku.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class MarkingManager {
	private Marking[] marking;
	private int gSize;
	private int nGrids;
	private int sType;

	/**
	 * Constructor.
	 * @param marking: array de marcatge.
	 * @param gSize: mida lateral d'un quadrant.
	 * @param nGrids: nombre lateral de quadrants.
	 * @param sType: indicador del tipus de sudoku.
	 */
	public MarkingManager(Marking[] marking, int gSize, int nGrids, int sType) {
		this.marking = marking;
		this.gSize = gSize;
		this.nGrids = nGrids;
		this.sType = sType;
	}

	/**
	 * Mètode encarregat de gestionar el marcatge.
	 * @param row: fila.
	 * @param column: columna.
	 * @param value: valor.
	 */
	public void mark(int row, int column, int value) {
		int grid = (Math.floorDiv(row, gSize) * nGrids) + Math.floorDiv(column, gSize);

		switch (sType) {
			case 0:		//9x9
				marking[0].markRow(row, value);
				marking[0].markColumn(column, value);
				marking[0].markGrid(grid, value);
				break;

			case 1:		//16x16
				marking[0].markRow(row, value);
				marking[0].markColumn(column, value);
				marking[0].markGrid(grid, value);
				break;

			case 2:		//Samurai
				int nRow = -1;																	//Samurai
				int nColumn = -1;																//sudoku[0] --> dalt-esquerra
				gSize = 3;																		//sudoku[1] --> dalt-dreta
																								//sudoku[2] --> centre
				if (row >= 0 && row <= 8 && column >= 0 && column <= 8) {						//sudoku[3] --> baix-esquerra
					nRow = row;																	//sudoku[4] --> baix-dreta
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[0].markRow(nRow, value);
					marking[0].markColumn(nColumn, value);
					marking[0].markGrid(grid, value);
				}

				if (row >= 0 && row <= 8 && column >= 12 && column <= 20) {
					nRow = row;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[1].markRow(nRow, value);
					marking[1].markColumn(nColumn, value);
					marking[1].markGrid(grid, value);
				}

				if (row >= 6 && row <= 14 && column >= 6 && column <= 14) {
					nRow = row - 6;
					nColumn = column - 6;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[2].markRow(nRow, value);
					marking[2].markColumn(nColumn, value);
					marking[2].markGrid(grid, value);
				}

				if (row >= 12 && row <= 20 && column >= 0 && column <= 8) {
					nRow = row - 12;
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[3].markRow(nRow, value);
					marking[3].markColumn(nColumn, value);
					marking[3].markGrid(grid, value);
				}

				if (row >= 12 && row <= 20 && column >= 12 && column <= 20) {
					nRow = row - 12;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[4].markRow(nRow, value);
					marking[4].markColumn(nColumn, value);
					marking[4].markGrid(grid, value);
				}
				break;
		}
	}

	/**
	 * Mètode encarregat de gestionar el desmarcatge.
	 * @param row: fila.
	 * @param column: columna.
	 * @param value: valor.
	 */
	public void unmark(int row, int column, int value) {
		int grid = (Math.floorDiv(row, gSize) * nGrids) + Math.floorDiv(column, gSize);

		switch (sType) {
			case 0:		//9x9
				marking[0].unmarkRow(row, value);
				marking[0].unmarkColumn(column, value);
				marking[0].unmarkGrid(grid, value);
				break;

			case 1:		//16x16
				marking[0].unmarkRow(row, value);
				marking[0].unmarkColumn(column, value);
				marking[0].unmarkGrid(grid, value);
				break;

			case 2:		//Samurai
				int nRow = -1;
				int nColumn = -1;

				if (row >= 0 && row <= 8 && column >= 0 && column <= 8) {
					nRow = row;
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[0].unmarkRow(nRow, value);
					marking[0].unmarkColumn(nColumn, value);
					marking[0].unmarkGrid(grid, value);
				}

				if (row >= 0 && row <= 8 && column >= 12 && column <= 20) {
					nRow = row;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[1].unmarkRow(nRow, value);
					marking[1].unmarkColumn(nColumn, value);
					marking[1].unmarkGrid(grid, value);
				}

				if (row >= 6 && row <= 14 && column >= 6 && column <= 14) {
					nRow = row - 6;
					nColumn = column - 6;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[2].unmarkRow(nRow, value);
					marking[2].unmarkColumn(nColumn, value);
					marking[2].unmarkGrid(grid, value);
				}

				if (row >= 12 && row <= 20 && column >= 0 && column <= 8) {
					nRow = row - 12;
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[3].unmarkRow(nRow, value);
					marking[3].unmarkColumn(nColumn, value);
					marking[3].unmarkGrid(grid, value);
				}

				if (row >= 12 && row <= 20 && column >= 12 && column <= 20) {
					nRow = row - 12;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					marking[4].unmarkRow(nRow, value);
					marking[4].unmarkColumn(nColumn, value);
					marking[4].unmarkGrid(grid, value);
				}
				break;
		}
	}

	/**
	 * Mètode encarregat de gestionar la comprovació.
	 * @param row: fila.
	 * @param column: columna.
	 * @param value: valor.
	 * @return booleà.
	 */
	public boolean checkValue(int row, int column, int value) {
		boolean ok = false;
		int grid = (Math.floorDiv(row, gSize) * nGrids) + Math.floorDiv(column, gSize);

		switch (sType) {
			case 0:		//9x9
				if (marking[0].checkRow(row, value)) {
					ok = true;
				}

				if (marking[0].checkColumn(column, value)) {
					ok = true;
				}

				if (marking[0].checkGrid(grid, value)) {
					ok = true;
				}
				break;

			case 1:		//16x16
				if (marking[0].checkRow(row, value)) {
					ok = true;
				}

				if (marking[0].checkColumn(column, value)) {
					ok = true;
				}

				if (marking[0].checkGrid(grid, value)) {
					ok = true;
				}
				break;

			case 2:		//Samurai
				int nRow = -1;
				int nColumn = -1;

				if (row >= 0 && row <= 8 && column >= 0 && column <= 8) {
					nRow = row;
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					if (marking[0].checkRow(nRow, value)) {
						ok = true;
					}

					if (marking[0].checkColumn(nColumn, value)) {
						ok = true;
					}

					if (marking[0].checkGrid(grid, value)) {
						ok = true;
					}
				}

				if (row >= 0 && row <= 8 && column >= 12 && column <= 20) {
					nRow = row;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					if (marking[1].checkRow(nRow, value)) {
						ok = true;
					}

					if (marking[1].checkColumn(nColumn, value)) {
						ok = true;
					}

					if (marking[1].checkGrid(grid, value)) {
						ok = true;
					}
				}

				if (row >= 6 && row <= 14 && column >= 6 && column <= 14) {
					nRow = row - 6;
					nColumn = column - 6;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					if (marking[2].checkRow(nRow, value)) {
						ok = true;
					}

					if (marking[2].checkColumn(nColumn, value)) {
						ok = true;
					}

					if (marking[2].checkGrid(grid, value)) {
						ok = true;
					}
				}

				if (row >= 12 && row <= 20 && column >= 0 && column <= 8) {
					nRow = row - 12;
					nColumn = column;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					if (marking[3].checkRow(nRow, value)) {
						ok = true;
					}

					if (marking[3].checkColumn(nColumn, value)) {
						ok = true;
					}

					if (marking[3].checkGrid(grid, value)) {
						ok = true;
					}
				}

				if (row >= 12 && row <= 20 && column >= 12 && column <= 20) {
					nRow = row - 12;
					nColumn = column - 12;
					grid = (Math.floorDiv(nRow, gSize) * 3) + Math.floorDiv(nColumn, gSize);

					if (marking[4].checkRow(nRow, value)) {
						ok = true;
					}

					if (marking[4].checkColumn(nColumn, value)) {
						ok = true;
					}

					if (marking[4].checkGrid(grid, value)) {
						ok = true;
					}
				}
				break;
		}

		return ok;
	}

	/**
	 * Mètode encarregat de mostrar les dades del marcatge.
	 */
	public void showMarkingData() {
		for (int i = 0; i < marking.length; i++) {
			System.out.println("marking[" + i + "]");

			marking[i].showRowValues();
			marking[i].showColumnValues();
			marking[i].showGridValues();

			System.out.println("");
		}
	}

	/**
	 * Mètode encarregat de reiniciar les dades del marcatge.
	 */
	public void resetMarkingData() {
		for (int i = 0; i < marking.length; i++) {
			marking[i].resetRowValues();
			marking[i].resetColumnValues();
			marking[i].resetGridValues();
		}
	}
}
