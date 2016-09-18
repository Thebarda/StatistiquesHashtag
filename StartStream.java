package twitter;

public class StartStream {
	protected static final long serialVersionUID = 1L;
	public static void main(String args[]){
		Twitter twitter = new Twitter();
		/**ATTENTION : Les trois lignes suivantes sont à utiliser le jour même*/
		//twitter.startStream(); //Exécuter cette ligne pour récupérer les tweets
		twitter.lireFichier(); //Exécuter les deux lignes suivante pour afficher les stats des hashtags
		twitter.afficherHashtags();
		System.out.println("NOMBRE DE HASHTAG : "+twitter.countHashtag());
		System.out.println("NOMBRE DE TWEETS : "+twitter.countTweets());
	}
}
