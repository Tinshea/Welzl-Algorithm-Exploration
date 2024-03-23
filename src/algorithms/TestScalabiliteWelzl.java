package algorithms;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TestScalabiliteWelzl {
	public static void main(String[] args) {
	    File folder = new File("./benchmark/Malek_benchmark/samples");
	    File[] listOfFiles = folder.listFiles();

	    PrintWriter timeWriter = null;
	    PrintWriter circleWriter = null;

	    try {
	        timeWriter = new PrintWriter("./CSV/executionTimesScalableWelz.csv", "UTF-8");

	        // En-têtes pour les fichiers CSV
	        timeWriter.println("Nombre de points, Temps d'execution_AlgoWelzl (ms)");
	        int i = 0;
	        for (File file : listOfFiles) {
	            if (file.isFile()) {
	                ArrayList<Point> points = loadPointsFromFile(file.getPath());
	                i++;
	                long startTimeNaif = System.nanoTime();
	                DefaultTeam.Welzl(points);
	                long endTimeNaif = System.nanoTime();

	             // fileName est la variable contenant le nom du fichier avec l'extension
	                String baseName = file.getName().substring(0, file.getName().lastIndexOf('.'));


	                // Écrire le temps d'exécution dans executionTimes.csv
	                timeWriter.printf("%s, %.2f%n", baseName, (endTimeNaif - startTimeNaif) / 1000000f);

	            }
	        }
	        System.out.println("Fichier lu :" + i);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (timeWriter != null) timeWriter.close();
	        if (circleWriter != null) circleWriter.close();
	    }
	}


    private static ArrayList<Point> loadPointsFromFile(String path) {
        ArrayList<Point> points = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                points.add(new Point(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }
}
