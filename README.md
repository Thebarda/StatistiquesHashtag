# StatistiquesHashtag
## Version 1.0

## Version 1.1 
- Triage des hashtags par occurences
- Affichage du nombre tweets/hashtags streamés

## Version 1.2
- La lecture et les affichages sont regroupés en une méthode "afficherResultat"

## Version 1.3
- Ajout de stats de ratio "Retweets/tweets" et "Hashtags/tweets"
- 

## Description

Ce programme permet de faire des statistiques sur les différents hashtags utilisés sur les tweets français
lors d'un stream.
Le but du stream est de récupérer les tweets récemment postés afin de pouvoir les enregistré dans un fichier json.

Les fichiers json sont classés par date (exemple : tweets2016916.json) dans le répertoire "C:\Users\Public\Documents".

##Fonctionnement
Vous avez juste a créer un projet dans "Eclipse" ou autre... puis importer les 3 fichier java StartStream.java, Twitter.java, Tweet.java !
Vous aurez également à ajouter des fichiers 'jar' externes.

### D'autres versions seront à disposition prochainement :)

### Remerciements
Merci à l'API Twitter4j pour le stream des tweets -> [Lien vers le site web](http://twitter4j.org/en/index.html)

Merci à json-lib pour tout ce qui est json -> [Lien vers le site web](http://json-lib.sourceforge.net)