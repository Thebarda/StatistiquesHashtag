package twitter;
/**
 * Classe qui comptabilise le nombre d'itération d'un hashtag
 * @author tomda
 * @version 1.0
 */
public class Tweet {
	protected static final long serialVersionUID = 1L;
	private final String hashtag;
	private int nbCount;
	/**
	 * Constructeur
	 * @param hashtag
	 */
	public Tweet(String hashtag){
		this.hashtag = hashtag;
		this.nbCount = 1;
	}
	
	/**
	 * Retourne le hashtag
	 * @return hashtag
	 */
	public String getHashtag(){
		return this.hashtag;
	}
	
	/**
	 * Retourne le nombre d'itération d'un hashtag
	 * @return itération
	 */
	public int getNbCount(){
		return this.nbCount;
	}
	
	/**
	 * Ajoute une itération
	 */
	public void addNbCount(){
		this.nbCount=nbCount+1;
	}
}
