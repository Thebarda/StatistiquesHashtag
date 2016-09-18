package twitter;

import java.util.Comparator;

public class TweetComparator implements Comparator<Tweet> {

	@Override
	public int compare(Tweet o1, Tweet o2) {
		if(o1.getNbCount()>o2.getNbCount()){
			return 1;
		}else if(o1.getNbCount()<o2.getNbCount()){
			return -1;
		}else{
			return 0;
		}
	}
	
}
