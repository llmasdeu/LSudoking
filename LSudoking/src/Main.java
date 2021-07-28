import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Main.
 * @author Lluís Masdeu
 * @author Patrick Albó
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {		//Nom executable <nom_fitxer_e.txt> <mode_sortida> [<nom_fitxer_s.txt>]
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SS");
		GregorianCalendar date = new GregorianCalendar();
		int error = 0;

		System.out.println("Inici de la execució: " + dateFormat.format(date.getTime()));

		long start = System.currentTimeMillis();

		if (args.length < 2 || args.length > 3) {
			error = 1;
		} else {
			String[] params = new String[3];

			params[0] = args[0];					//Nom fitxer d'entrada
			params[1] = args[1];					//Mode de sortida
			int mode = params[1].charAt(0) - '0';	//Mode de sortida

			if (args.length == 3) {
				params[2] = args[2];				//Fitxer de sortida
			} else {
				params[2] = "";
			}

			if (mode == 3 && params[2] == "") {
				error = 1;
			} else {
				LSudoking lsudoking = new LSudoking(params[0], mode, params[2]);
				lsudoking.sudokuManager();
			}
		}

		if (error == 1) {
			System.out.println("Error! No has inserit els paràmetres sol·licitats!");
		}

		long time = System.currentTimeMillis() - start;

		long millisecond = (time / 1000);
		long second = (time / 1000) % 60;
		long minute = (time / (1000 * 60)) % 60;
		long hour = (time / (1000 * 60 * 60)) % 24;

		date = new GregorianCalendar();

		System.out.println("Final de la execució: " + dateFormat.format(date.getTime()));
		System.out.println("Temps d'execució: " + hour + ":" + minute + ":" + second + ":" + millisecond);
	}
}
