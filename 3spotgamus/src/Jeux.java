
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
			System.out.println("Le Joueur R commence à jouer."+System.lineSeparator());
			int numéroDeplacement=0;

			while(partieFinie()){  
				System.out.println(plateau.toString()); 
	            System.out.println();
				System.out.println("Le JOUEUR " + pionCouleur + " c'est à votre tour "); 
				System.out.println();
				DeplacementPossible(pionCouleur); // méthode qui permet d'avoir les déplacements possibles de la pièce du joueur à qui c'est le tour de jouer
				System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":");// input est un variable qui va stocké tout la ligne saisie par le joueur
				System.out.println(plateauDeplacementPossible.toString()); // méthode qui affiche le plateau avec les déplacements possibles
				 
				while(true) { // Afin de dire si l'utilisateur entre une donnée valide
					String input = scan.nextLine(); 
					try {
						
						int c = Integer.parseInt(input); 
						if(1 <= c && c <= getCptrPositionPossibles(pionCouleur)) { // condition pour que la donnée saisie par l'utilisateur soit un des déplacement proposé
							numéroDeplacement = c; // on stocke cette position dans la variable numéroDéplacement qui sera utilisé par la suite
							break; // on sort de la boucle si la condition est valide 
							}
						System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":"); //on affiche un message si  la donné saisie n'est pas valide
					}catch (Exception e) {
						System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles(pionCouleur)+":");     // on affiche un message pour indiqué au joueur que la chaine de caractère saisie n'était pas un chiffre attendu
					    
					}
				}
				    
					deplacer(numéroDeplacement, pionCouleur); // on effectue le déplacement grâce à la méthode déplacer qui prend comme paramètre d'entré le numéro saisie auparavant par le joueur et la couleur de son pion 
					System.out.println();
					System.out.println("Voici le plateau après le déplacement de votre pion " +pionCouleur+":");
					System.out.println(plateau.toString()); // Affichage du plateau avec le déplacement effectué
					System.out.println("Déplacer maintenant le pion W:"); // affiche message pour dire au joueur qui doit déplacer la pièce "White"
					DeplacementPossible("W"); // méthode qui permet d'avoir les déplacements possibles de la pièce blanche "WHITE"
					System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
					System.out.println(plateauDeplacementPossible.toString());	//affiche les déplacements possibles pour la pièce blache "WHITE" 
					
  					while(true) { // Permet au programme de pas exploser si le joueur ne rentre pas une donnée valide
						
						String input = scan.nextLine();
						try {
							int c = Integer.parseInt(input);
							if(1 <= c && c <= getCptrPositionPossibles("W")) {
								numéroDeplacement = c;
								break;
								}
							System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
						}catch (Exception e) {
							System.out.println("Entrer un chiffre entre 1 et " +getCptrPositionPossibles("W")+":");
						}
					}
					deplacer(numéroDeplacement, "W"); // méthode qui déplace la pièce W à sa nouvelle position indiquer 

					if(pionCouleur.equals("R") ){ // condition qu permet de changer de tour si le joueur qui a jouer en premier  était égal a  "R"
						pionCouleur = "B"; // alors le joueur courrant devient le joueur Bleu
						pointR = pointsR() ; // la méthode pointR compte le nb de points  au cours de la partie et on stocke le nbre de point du joueur Rouge dans la variable pointR
						System.out.println("Le score du joueur Rouge est: " + pointR +"points"+System.lineSeparator()); // on affiche le nombre de point du joueur Rouge qui vient de joueur(effectuer ses déplacements)
                        System.out.println("Voici le plateau:");
					}
					
					else { //si le joueur courrant est le joueur Bleu
						pionCouleur = "R"; //le joueur courrant devent le joueur Rouge
						pointB = pointsB(); // la méthode pointB compte le nb de points accumulé au cours de la partie et on stocke score du joueur Bleu dans la variable pointB
						System.out.println("Le score du joueur Bleu est: " + pointB +"points"+System.lineSeparator()); // on affiche le nombre de point du joueur Bleu qui vient de joueur(effectuer ses déplacements)
						System.out.println("Voici le plateau:");
					}
			}
			
			System.out.println(plateau.toString()); //si la partie est fini on affiche le plateau final avec les pièces 
			System.out.println(MessageGagnant()); // on affiche le MessageGagnant 

		}
		
		
		
		public boolean partieFinie() { // méthode qui permet de savoir si la partie est terminé, cette méthode renvoie un booléen
			boolean a= true; 
			
			if( pointR > POINTMAX || pointB > POINTMAX) // si 1 des 2 joueurs a gagné, atteint les 12points ou plus alors la variable "a" aura la valeur false qui permettra de savoir si il y a MessageGagnant 
				a = false;
			 
			return a;
		}

		
		public String getMessageGagnant() { // méthode qui renvoie un string avec la couleur du joueur 
			
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
		
		public String MessageGagnant(){ //méthode qui permet d'afficher le message qui indique qui a gagné
			String s="";
		if( pointR > POINTMAX || pointB> POINTMAX  && pointB >=  POINTMIN || pointR >= POINTMIN  ) //condition si un des 2 joueurs à 12points ou plus et que l'autre joueur à plus de 6pts(nb minimum de point à avoir si l'autre à 12points pour qu'il gagne)
			s+="Le JOUEUR  "+ getMessageGagnant() +" à gagné la partie! Félicitations"; // message pour indiqué le MessageGagnant en fonction de qui a gagné la partie
			
		else   // Cas où un des 2 joueurs a 12 points ou plus et l'autre joueur moins de 6pts alors c'est lui qui gagne
			s+="Vous avez perdu JOUEUR"+ getMessageGagnant() +"! Dommage :("; // message indiquant le MessageGagnant
		
	
		return s;
		 
		}
		
		public int pointsR() { //Permet de calculer  le nb de point du joueur Rouge à un moment

			for (int ligneNumber = 0; ligneNumber < 3; ++ligneNumber) { //boucle qui parcourt chaque ligne du plateau 

				if (this.plateau.plateau[ligneNumber][2].contains("R") ) //si il y a un pion R dans la colonne 2 du plateau à la ligne ligneNumber alors incrementation a 1
					pointR++; 
							
			
			         }
			return pointR;
		}
		
		public int  pointsB () { // Permet de calculer  le nb de point du joueur Bleu à un moment
			                      // même fonctionnement que la méthode au dessus
			for (int ligneNumber = 0; ligneNumber < 3; ++ligneNumber) { 

				if (this.plateauDeplacementPossible.plateau[ligneNumber][2].contains("B")) 
					pointB++;
		
			         }
			return pointB;
		}	
		

		
		public void DeplacementPossible(String pionCouleurs) { //prend en compte tout les déplacement possible pour un pion (en paramètre d'entré)
			
			int cptrPositionPossibles = 0; 
			this.plateauDeplacementPossible.copyMatrice(this.plateau); // on copie le plateau dans le plateauDeplacementpossible
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { // on parcourt chaque ligne

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 

					// tester si position verticale possible

					if (testVertical(ligneNumber, coloneNumber, pionCouleurs))
					{
						cptrPositionPossibles++; // si c'est possiblle on incrémente de 1 le compteur de Position 
						ajoutDeplacementPossible(ligneNumber, coloneNumber, cptrPositionPossibles); // on ajoute  cette position possible à la ligne et à la colonne correspondante avec le  numéro (cptrPositionPossibles)
						
					}
                      //tester si position horizontale possible
					if (testHorizontal(ligneNumber, coloneNumber, pionCouleurs)) {

						cptrPositionPossibles++; // si c'est possiblle on incrémente de 1 le compteur de Position
						ajoutDeplacementPossible(ligneNumber, coloneNumber, cptrPositionPossibles); // on ajoute  cette position possible à la ligne et à la colonne correspondante avec le  numéro (cptrPositionPossibles)
								
					}

				}

			}

		}
		
		public boolean deplacer(int numeroDeplcement, String pionCouleurs) { //  méthode qui permet de déplacer un pion pour un numéro de déplacement donné
			boolean deplacementFait=false; // booléen qui permet de savoir si le déplacement  est fait et initialisé à false 
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { 

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 
					if(this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].contains(String.valueOf(numeroDeplcement))) { // test pour savoir si on est dans la case du numéroDeplacement
				
						if(testVertical(ligneNumber, coloneNumber, pionCouleurs) && this.plateauDeplacementPossible.plateau[ligneNumber][coloneNumber].charAt(0)==String.valueOf(numeroDeplcement).charAt(0)) {
							this.plateau.removeCouleurInitO(pionCouleurs); // re-afficher 'O' quand le pion était sur la colonne 2 après qu'il soit supprimé 
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
		
		public boolean testVertical(int ligneNumber, int coloneNumber, String pionCouleurs) { // test si le déplacement est possible en verticale (ligne-1) pour un pion donné
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
		
		
		public boolean testHorizontal(int ligneNumber, int coloneNumber, String pionCouleurs) { // test si le déplacement est possible en horizontale (colone +1 , deplacement vers la droite) pour un pion donné 
			return (this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("")
					|| this.plateau.plateau[ligneNumber][coloneNumber].equals("O")) && ((coloneNumber + 1) < 3) && // coloneNumber+1<3
					(this.plateau.plateau[ligneNumber][coloneNumber + 1].equals(pionCouleurs)
							|| this.plateau.plateau[ligneNumber][coloneNumber + 1].equals("")
							|| this.plateau.plateau[ligneNumber][coloneNumber + 1].equals("O"))
					&& (!this.plateau.plateau[ligneNumber][coloneNumber].equals(pionCouleurs)
							|| !this.plateau.plateau[ligneNumber][coloneNumber + 1].equals(pionCouleurs));
		}
		
		public void ajoutDeplacementPossible(int ligneNumber, int coloneNumber, int cptrPositionPossibles) { // ajoute le déplacement à la matrice déplacement possible
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

		
		public int getCptrPositionPossibles( String pionCouleur ) { //méthode pour savoir le nbMaximal de position possible proposé
			
			int cptrPositionPossibles = 0; 
			
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) { 

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) { 


					if (testVertical(ligneNumber, coloneNumber, pionCouleur)) // si le déplacement est possible à la ligne ligneNumber et à la colonne coloneNumber en vertical 
					
						cptrPositionPossibles++;						
					

					if (testHorizontal(ligneNumber, coloneNumber, pionCouleur))  //si le déplacement est possible à la ligne ligneNumber et à la colonne coloneNumber en horizontale 

						cptrPositionPossibles++;  
					

				}

			}

			return cptrPositionPossibles; 
		}
		
		
		public static void start() {
			// TODO Auto-generated method stub
			
		}

		
		
		
	}
