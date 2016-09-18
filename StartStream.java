package twitter;

public class StartStream {
	protected static final long serialVersionUID = 1L;
	public static void main(String args[]){
		Twitter twitter = new Twitter();
		/**ATTENTION : Les trois lignes suivantes sont � utiliser le jour m�me*/
		//twitter.startStream(); //Ex�cuter cette ligne pour r�cup�rer les tweets
		twitter.lireFichier(); //Ex�cuter les deux lignes suivante pour afficher les stats des hashtags
		twitter.afficherHashtags();
		System.out.println("NOMBRE DE HASHTAG : "+twitter.countHashtag());
		System.out.println("NOMBRE DE TWEETS : "+twitter.countTweets());
	}
}
