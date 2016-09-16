package twitter;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
/**
 * Classe qui stream des tweets fran�ais, les enregistre dans un fichier JSON class� par date d'ex�cution et fait des stats des hashtags
 * @author Mojann
 * @version 1.0
 */
public class Twitter {
	protected static final long serialVersionUID = 1L;
	private static twitter4j.TwitterStream twitterStream;
	private static StatusListener listener;
	private static String tweets ="";
	private static String user = "";
	private static String text = "";
	private static List<Tweet> listHashtag;
	
	/**
	 * Constructeur par d�faut
	 */
	public Twitter(){
		//Initialise consumer_key, consumer_secret, access_token, access_token_secret
		twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.setOAuthConsumer("consumer_key", "consumer_secret");
		AccessToken access = new AccessToken("access_token", "access_token_secret");
		twitterStream.setOAuthAccessToken(access);
	}
	/**
	 * Commence le stream
	 * @param status
	 */
	public void startStream(){
		//Cr�er un listener qui va streamer tous les tweets en langue fran�aise viennent d'�tre tweet�
		listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	if(status.getLang().equals("fr")){
            		try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            		user = status.getUser().getScreenName();
            		text = status.getText();
            		setJson(status);
            	}
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {}

            @Override
            public void onStallWarning(StallWarning warning) {}

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
	}
	
	/**
	 * Lit un fichier json correspondant � la date courante
	 */
	public void lireFichier(){
		//Initialisation de la liste de hashtags
		listHashtag = new ArrayList<>();
		Date date = new Date();
		String year = ""+(date.getYear()+1900);
		String month = ""+(date.getMonth()+1);
		String day = ""+date.getDate();
		String file = "C:\\Users\\Public\\Documents"+year+month+day+".json";
		try {
			//Ouverture du fichier
			InputStream ips=new FileInputStream(file); 
			//Lecture du fichier tweet par tweet
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			try {
				//Tant qu'il reste des tweets � lire dans le fichier
				while((ligne=br.readLine())!=null){
					JSONObject obj; //on convertit la chaine en JsonObjet
					try {
						obj = new JSONObject(ligne);
						String text = (String) obj.get("tweet");
						String mots[] = text.split(" "); //Tableau qui contient chaque mot
						for(String mot : mots){ //On analyse chaque mot du tweet
							if(mot.contains("#")==true){ //Si le mot contient le caract�re '#'
								boolean estDedansListeHashtag=false;
								for(int i=0;i<listHashtag.size();i++){//On analyse la liste des hashtags
									Tweet tweet = listHashtag.get(i); //On r�cup�re l'objet Tweet dans la liste
									if(tweet.getHashtag().equals(mot)){ //Si l'objet Tweet contient le hashtag Alors on incr�mente de 1
										tweet.addNbCount(); 
										listHashtag.set(i, tweet);
										estDedansListeHashtag=true;
									}
								}
								if(estDedansListeHashtag==false){
									listHashtag.add(new Tweet(mot)); //Sinon on ajoute le hashtag � la liste
								}
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affiche les hashtags
	 */
	public void afficherHashtags(){
		for(Tweet tweet:listHashtag){
			if((tweet.getHashtag().charAt(0)=='#')){
				System.out.println(tweet.getHashtag()+" : "+tweet.getNbCount());
			}
		}
	}
	
	/**
	 * Construit le Json
	 * @param status
	 */
	public void setJson(Status status){
		JSONObject obj = new JSONObject();
		try {
			obj.put("tweet", status.getText());
			obj.put("user", status.getUser().getScreenName());
    		tweets = obj.toString();
    		tweets = tweets+"\n";
    		System.out.println(tweets);
    		this.ecrireJsonToFile(tweets);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Ecrit l'objet JSON dans un fichier
	 * @param tweet
	 */
	@SuppressWarnings("deprecation")
	public void ecrireJsonToFile(String tweet){
		Date date = new Date();
		String year = ""+(date.getYear()+1900);
		String month = ""+(date.getMonth()+1);
		String day = ""+date.getDate();
		String file = "C:\\Users\\Public\\Documents"+year+month+day+".json";
		try(FileWriter fw = new FileWriter(file, true)){
			fw.write(tweet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
