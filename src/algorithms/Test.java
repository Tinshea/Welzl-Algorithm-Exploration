package algorithms;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import supportGUI.Circle;

public class Test {
	public static void main(String[] args) {
	    File folder = new File("./benchmark/Varoumas_benchmark/samples");
	    File[] listOfFiles = folder.listFiles();

	    PrintWriter timeWriter = null;
	    PrintWriter circleWriter = null;

	    try {
	        timeWriter = new PrintWriter("./CSV/executionTimes.csv", "UTF-8");
	        circleWriter = new PrintWriter("./CSV/circles.csv", "UTF-8");

	        // En-têtes pour les fichiers CSV
	        timeWriter.println("Fichier, Temps d'execution_AlgoNaif (ms), Temps d'execution_Welzl (ms)");
	        circleWriter.println("Fichier, rayon_AlgoNaif, centre_AlgoNaif, rayon_Welzl, centre_Welzl");
	        int i = 0;
	        for (File file : listOfFiles) {
	            if (file.isFile()) {
	                ArrayList<Point> points = loadPointsFromFile(file.getPath());
	                i++;
	                long startTimeNaif = System.nanoTime();
	                Circle minimalCircleNaif = DefaultTeam.AlgoNaif(points);
	                long endTimeNaif = System.nanoTime();

	                long startTimeWelzl = System.nanoTime();
	                Circle minimalCircleWelzl = DefaultTeam.Welzl(points);
	                long endTimeWelzl = System.nanoTime();

	                // Écrire le temps d'exécution dans executionTimes.csv
	                timeWriter.printf("%s, %.2f, %.2f%n", file.getName(), (endTimeNaif - startTimeNaif) / 1000000f , (endTimeWelzl - startTimeWelzl) / 1000000f );

	                // Écrire les informations des cercles dans circles.csv
	                circleWriter.printf("%s, %d, (%f %f), %d, (%f %f)%n", 
	                                     file.getName(), 
	                                     minimalCircleNaif.getRadius(), 
	                                     minimalCircleNaif.getCenter().getX(), 
	                                     minimalCircleNaif.getCenter().getY(),
	                                     minimalCircleWelzl.getRadius(),
	                                     minimalCircleWelzl.getCenter().getX(),
	                                     minimalCircleWelzl.getCenter().getY());
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
