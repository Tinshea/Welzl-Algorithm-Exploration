# Projet CPA : Problème du cercle minimum

N'oubliez de rajouter pour le projet dans le Build path le fichier jars/supportGUI.jar.

## Interface Graphique

Pour lancer l'application avec l'interface graphique :

1. Ouvrez votre environnement de développement intégré (IDE) et importez le projet.
2. Localisez le fichier `build.xml`.
3. Exécutez le fichier en faisant `run`-> `2 Ant Build`

Cela lancera une fênetre graphique en appuyant sur `c`, il executera l'algorithme dans calculCercleMin(ArrayList<Point> points) sur input.points 


## Exécution des Tests

Des tests automatisés sont disponibles pour évaluer la performance des algorithmes implémentés dans le dossier `algorithms`.

### Tests sur le fichier de Varoumas
Pour exécuter les tests de l'algorithme naïf et de Welz et générer un fichier CSV contenant les temps d'exécution sur le benchmark de Varoumas, ainsi qu'un autre fichier avec le calcul du rayon et du centre :

1. Localisez le fichier `Test.java` dans le répertoire `algorithms`.
2. Exécutez ce fichier via votre IDE. Cela va générer un fichier CSV dans  dans le répertoire `CSV`.

### Tests de l'Algorithme Naïf
Pour exécuter les tests de l'algorithme Naïf et générer un fichier CSV contenant les temps d'exécution sur le benchmark Personnalisé.

1. Localisez le fichier `TestScalabiliteNaif.java` dans le répertoire `algorithms`.
2. Exécutez ce fichier via votre IDE. Cela va générer un fichier CSV dans le répertoire `CSV`.

### Tests de l'Algorithme de Welzl

Pour exécuter les tests de l'algorithme Welz et générer un fichier CSV dans le répertoire `CSV` contenant les temps d'exécution sur le benchmark Personnalisé.

1. Localisez le fichier `TestScalabiliteWelzl.java` dans le même répertoire.
2. Exécutez le fichier pour lancer les tests. Comme pour l'algorithme naïf, un fichier CSV sera créé avec les résultats des tests.

### Benchmark Personnalisé

Un benchmark personnalisé pour tester l'algorithme naïf et Welz selon le nombre de points est également disponible dans le fichier `benchmark` -> `Malek_benchmark`->`Samples` il est généré par un script python. Suivez les étapes pour le test de l'algorithme naïf pour utiliser ce benchmark.

