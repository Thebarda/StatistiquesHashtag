package twitter;

import java.util.Scanner;

public class StartStream {
	protected static final long serialVersionUID = 1L;
	public static void main(String args[]){
		Twitter twitter = new Twitter();
		Scanner sc = new Scanner(System.in);
		int choix;
		boolean bonNumeros = true;
		do{
			System.out.println("1) Démarrer le stream de tweets");
			System.out.println("2) Afficher les statistiques");
			choix = sc.nextInt();
			sc.nextLine();
			if((choix>=1)&&(choix<=2)){
				bonNumeros=true;
			}else{
				bonNumeros=false;
			}
		}while(bonNumeros==false);
		if(bonNumeros==true){
			switch(choix){
			case 1: twitter.startStream();
			break;
			default: twitter.affichageResultat();
			break;
			}
		}
	}
}