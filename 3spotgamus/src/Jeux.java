
	import java.util.Scanner;

	public class Jeux {

		private Matrice plateau = new Matrice();  
		private Matrice plateauDeplacementPossible = new Matrice(); 
		private Scanner scan = new Scanner(System.in); 
		private String pionCouleur = "R"; 
		private int pointB=0; 
		private int pointR=0; 
		private final int POINTMAX= 11;
		private final int POINTMIN= 6;
		
		//Constructeur
		Jeux() {  
			System.out.println("3 spot game:"+System.lineSeparator());
			System.out.println("Le Joueur R commence � jouer."+System.lineSeparator());
			int num�roDeplacement=0;

			while(partieFinie()){  
				System.out.println(plateau.toString()); 
	            System.out.println();
				System.out.println("Le JOUEUR " + pionCouleur + " c'est � votre tour "); 
				System.out.println();
				DeplacementPossible(pionCouleur); // m�thode qui permet d'avoir les d�placements possibles de la pi�ce du joueur � qui c'est le tour de jouer
				System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":");// input est un variable qui va stock� tout la ligne saisie par le joueur
				System.out.println(plateauDeplacementPossible.toString()); // m�thode qui affiche le plateau avec les d�placements possibles
				 
				while(true) { // Afin de dire si l'utilisateur entre une donn�e valide
					String input = scan.nextLine(); 
					try {
						
						int c = Integer.parseInt(input); 
						if(1 <= c && c <= getCptrPositionPossibles(pionCouleur)) { // condition pour que la donn�e saisie par l'utilisateur soit un des d�placement propos�
							num�roDeplacement = c; // on stocke cette position dans la variable num�roD�placement qui sera utilis� par la suite
							break; // on sort de la boucle si la condition est valide 
							}
						System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":"); //on affiche un message si  la donn� saisie n'est pas valide
					}catch (Exception e) {
						System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":");     // on affiche un message pour indiqu� au joueur que la chaine de caract�re saisie n'�tait pas un chiffre attendu
					    
					}
				}
				    
					deplacer(num�roDeplacement, pionCouleur); // on effectue le d�placement gr�ce � la m�thode d�placer qui prend comme param�tre d'entr� le num�ro saisie auparavant par le joueur et la couleur de son pion 
					System.out.println();
					System.out.println("Voici le plateau apr�s le d�placement de votre pion " +pionCouleur+":");
					System.out.println(plateau.toString()); // Affichage du plateau avec le d�placement effectu�
					System.out.println("D�placer maintenant le pion W:"); // affiche message pour dire au joueur qui doit d�placer la pi�ce "White"
					DeplacementPossible("W"); // m�thode qui permet d'avoir les d�placements possibles de la pi�ce blanche "WHITE"
					System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
					System.out.println(plateauDeplacementPossible.toString());	//affiche les d�placements possibles pour la pi�ce blache "WHITE" 
					
  					while(true) { // Permet au programme de pas exploser si le joueur ne rentre pas une donn�e valide
						
						String input = scan.nextLine();
						try {
							int c = Integer.parseInt(input);
							if(1 <= c && c <= getCptrPositionPossibles("W")) {
								num�roDeplacement = c;
								break;
								}
							System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
						}catch (Exception e) {
							System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
						}
					}
					deplacer(num�roDeplacement, "W"); // m�thode qui d�place la pi�ce W � sa nouvelle position indiquer 

					if(pionCouleur.equals("R") ){ // condition qu permet de changer de tour si le joueur qui a jouer en premier  �tait �gal a  "R"
						pionCouleur = "B"; // alors le joueur courrant devient le joueur Bleu
						pointR = pointsR() ; // la m�thode pointR compte le nb de points  au cours de la partie et on stocke le nbre de point du joueur Rouge dans la variable pointR
						System.out.println("Le score du joueur Rouge est: " + pointR +"points"+System.lineSeparator()); // on affiche le nombre de point du joueur Rouge qui vient de joueur(effectuer ses d�placements)
                        System.out.println("Voici le plateau:");
					}
					
					else { //si le joueur courrant est le joueur Bleu
						pionCouleur = "R"; //le joueur courrant devent le joueur Rouge
						pointB = pointsB(); // la m�thode pointB compte le nb de points accumul� au cours de la partie et on stocke score du joueur Bleu dans la variable pointB
						System.out.println("Le score du joueur Bleu est: " + pointB +"points"+System.lineSeparator()); // on affiche le nombre de point du joueur Bleu qui vient de joueur(effectuer ses d�placements)
						System.out.println("Voici le plateau:");
					}
			}
			
			System.out.println(plateau.toString()); //si la partie est fini on affiche le plateau final avec les pi�ces 
			System.out.println(MessageGagnant()); // on affiche le MessageGagnant 

		}
		
		
		
		public boolean partieFinie() { // m�thode qui permet de savoir si la partie est termin�, cette m�thode renvoie un bool�en
			boolean a= true; 
			
			if( pointR > POINTMAX || pointB > POINTMAX) // si 1 des 2 joueurs a gagn�, atteint les 12points ou plus alors la variable "a" aura la valeur false qui permettra de savoir si il y a MessageGagnant 
				a = false;
			 
			return a;
		}

		
		public String getMessageGagnant() { // m�thode qui renvoie un string avec la couleur du joueur 
			
			String s="";
			
			if(pointR > POINTMAX && pointB >= POINTMIN )  
				return s+="pion Rouge";
			
			else if(pointB > POINTMAX && pointR >= POINTMIN  ) 
				return s+="pion Bleu";
				
			else if(pointR > POINTMAX  && pointB < POINTMIN ) 
				return s+=" Bleu ";
			
			else if(pointB > POINTMAX  && pointR < POINTMIN) 
				return s+=" Rouge ";
			
			
			return s;
			
		}
		
		public String MessageGagnant(){ //m�thode qui permet d'afficher le message qui indique qui a gagn�
			String s="";
		if( pointR > POINTMAX || pointB> POINTMAX  && pointB >=  POINTMIN || pointR >= POINTMIN  ) //condition si un des 2 joueurs � 12points ou plus et que l'autre joueur � plus de 6pts(nb minimum de point � avoir si l'autre � 12points pour qu'il gagne)
			s+="Le JOUEUR  "+ getMessageGagnant() +" � gagn� la partie! F�licitations"; // message pour indiqu� le MessageGagnant en fonction de qui a gagn� la partie
			
		else   // Cas o� un des 2 joueurs a 12 points ou plus et l'autre joueur moins de 6pts alors c'est lui qui gagne
			s+="Vous avez perdu JOUEUR"+ getMessageGagnant() +"! Dommage :("; // message indiquant le MessageGagnant
		
	
		return s;
		 
		}
		
		public int pointsR() { //Permet de calculer  le nb de point du joueur Rouge � un moment

			for (int ligneNumber = 0; ligneNumber < 3; ++ligneNumber) { //boucle qui parcourt chaque ligne du plateau 

				if (this.plateau.plateau[ligneNumber][2].contains("R") ) //si il y a un pion R dans la colonne 2 du plateau � la ligne ligneNumber alors incrementation a 1
					pointR++; 
							
			
			         }
			return pointR;
		}
		
		public int  pointsB () { // Permet de calculer  le nb de point du joueur Bleu � un moment
			                      // m�me fonctionnement que la m�thode au dessus
			for (int ligneNumber = 0; ligneNumber < 3; ++ligneNumber) { 

				if (this.plateauDeplacementPossible.plateau[ligneNumber][2].contains("B")) 
					pointB++;
		
			         }
			return pointB;
		}	
		

		
		public void DeplacementPossible(String pionCouleurs) { //prend en compte tout les d�placement possible pour un pion (en param�tre d'entr�)
			
			int cptrPositionPossibles = 0; 
			this.plateauDeplacementPossible.copyMatrice(this.plateau); // on copie le plateau dans le plateauDeplacementpossible
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { // on parcourt chaque ligne

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 

					// tester si position verticale possible

					if (testVertical(ligneNumber, coloneNumber, pionCouleurs))
					{
						cptrPositionPossibles++; // si c'est possiblle on incr�mente de 1 le compteur de Position 
						ajoutDeplacementPossible(ligneNumber, coloneNumber, cptrPositionPossibles); // on ajoute  cette position possible � la ligne et � la colonne correspondante avec le  num�ro (cptrPositionPossibles)
						
					}
                      //tester si position horizontale possible
					if (testHorizontal(ligneNumber, coloneNumber, pionCouleurs)) {

						cptrPositionPossibles++; // si c'est possiblle on incr�mente de 1 le compteur de Position
						ajoutDeplacementPossible(ligneNumber, coloneNumber, cptrPositionPossibles); // on ajoute  cette position possible � la ligne et � la colonne correspondante avec le  num�ro (cptrPositionPossibles)
								
					}

				}

			}

		}
		
		public boolean deplacer(int numeroDeplcement, String pionCouleurs) { //  m�thode qui permet de d�placer un pion pour un num�ro de d�placement donn�
			boolean deplacementFait=false; // bool�en qui permet de savoir si le d�placement  est fait et initialis� � false 
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { 

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 
					if(this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].contains(String.valueOf(numeroDeplcement))) { // test pour savoir si on est dans la case du num�roDeplacement
				
						if(testVertical(ligneNumber, coloneNumber, pionCouleurs) && this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].charAt(0)==String.valueOf(numeroDeplcement).charAt(0)) {
							this.plateau.removeCouleurInitO(pionCouleurs); // re-afficher 'O' quand le pion �tait sur la colonne 2 apr�s qu'il soit supprim� 
							this.plateau.plateau[ligneNumber][coloneNumber]=pionCouleurs;
							this.plateau.plateau[ligneNumber-1][coloneNumber]=pionCouleurs;
							deplacementFait=true;
							
						} else if(testHorizontal(ligneNumber, coloneNumber, pionCouleurs)) {
							this.plateau.removeCouleurInitO(pionCouleurs);
							this.plateau.plateau[ligneNumber][coloneNumber]=pionCouleurs;
							this.plateau.plateau[ligneNumber][coloneNumber+1]=pionCouleurs;
							deplacementFait=true;
						}
					}
				}
			}
			return deplacementFait;
		}
		
		public boolean testVertical(int ligneNumber, int coloneNumber, String pionCouleurs) { // test si le d�placement est possible en verticale (ligne-1) pour un pion donn�
			return (this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("")
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("O")
					) && 
					((ligneNumber - 1) >= 0 // Verification des lignes ou il peut deplacer	
					) && 
					(this.plateau.plateau[ligneNumber - 1][coloneNumber].equals(pionCouleurs)
							|| this.plateau.plateau[ligneNumber - 1][coloneNumber].equals("")
							
							 ||this.plateau.plateau[ligneNumber - 1][coloneNumber].equals("O")
					)
					&& (!this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
							|| !this.plateau.plateau[ligneNumber-1][coloneNumber].equals(pionCouleurs));
		}
		
		
		public boolean testHorizontal(int ligneNumber, int coloneNumber, String pionCouleurs) { // test si le d�placement est possible en horizontale (colone +1 , deplacement vers la droite) pour un pion donn� 
			return (this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("")
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("O")) && ((coloneNumber + 1) < 3) && // coloneNumber+1<3
					(this.plateau.plateau[ligneNumber][coloneNumber + 1].equals(pionCouleurs)
							|| this.plateau.plateau[ligneNumber][coloneNumber + 1].equals("")
							|| this.plateau.plateau[ligneNumber][coloneNumber + 1].equals("O"))
					&& (!this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
							|| !this.plateau.plateau[ligneNumber][coloneNumber + 1].equals(pionCouleurs));
		}
		
		public void ajoutDeplacementPossible(int ligneNumber, int coloneNumber, int cptrPositionPossibles) { // ajoute le d�placement � la matrice d�placement possible
			if(this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].equals("") || this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].equals("O") 
					 || this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].equals("W")
					 || this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].equals("R")
					 || this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].equals("B")
					) {
				this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber] = String.valueOf(cptrPositionPossibles);
			} else {
				this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber] += " - "+String.valueOf(cptrPositionPossibles);

		}
		}

		
		public int getCptrPositionPossibles( String pionCouleur ) { //m�thode pour savoir le nbMaximal de position possible propos�
			
			int cptrPositionPossibles = 0; 
			
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { 

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 


					if (testVertical(ligneNumber, coloneNumber, pionCouleur)) // si le d�placement est possible � la ligne ligneNumber et � la colonne coloneNumber en vertical 
					
						cptrPositionPossibles++;						
					

					if (testHorizontal(ligneNumber, coloneNumber, pionCouleur))  //si le d�placement est possible � la ligne ligneNumber et � la colonne coloneNumber en horizontale 

						cptrPositionPossibles++;  
					

				}

			}

			return cptrPositionPossibles; 
		}
		
		
		public static void start() {
			// TODO Auto-generated method stub
			
		}

		
		
		
	}
