public class Matrice {

	// remplacer char to string
		String[][] plateau = new String[3][3];

		Matrice() {
			initplateau();// constructeur
		}

		public void initplateau() {// Initialisation du plateau càd premier plateau

			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) {

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) {

					if (ligneNumber == 0) {
						if (coloneNumber != 0) {

							this.plateau[ligneNumber][coloneNumber] = "R";
						} else {
							this.plateau[ligneNumber][coloneNumber] = "";
						}

					}

					if (ligneNumber == 1) {
						if (coloneNumber != 0) {
							this.plateau[ligneNumber][coloneNumber] = "W";
						} else {
							this.plateau[ligneNumber][coloneNumber] = "";
						}
					}

					if (ligneNumber == 2) {
						if (coloneNumber != 0) {
							this.plateau[ligneNumber][coloneNumber] = "B";
						} else {
							this.plateau[ligneNumber][coloneNumber] = "";
						}
					}

				}
			}

		}

		public String toString() {// Affichage du plateau 
			String s="";
			
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) {
				s+="* * * * * * * * * * * * *"+System.lineSeparator();
				s+="*       *       *       *"+System.lineSeparator();
				s+="*"; // debut de lignes

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) {

					 if(this.plateau[ligneNumber][coloneNumber].contains("-"))
							s+=" "+this.plateau[ligneNumber][coloneNumber] + " *"; 
		                    
		                   else if(this.plateau[ligneNumber][coloneNumber].equals(""))
                              s+="    "+this.plateau[ligneNumber][coloneNumber] + "   *";

		                   else 
		                    	s+="   "+this.plateau[ligneNumber][coloneNumber] + "   *";//on affiche le contenu de la case 
					 
									}
			     s+=System.lineSeparator();
				s+="*       *       *       *"+System.lineSeparator();

			}

			    s+="* * * * * * * * * * * * *"+System.lineSeparator();
          return s;
		}

		public void copyMatrice(Matrice matriceACopier) {//Copie d'une matrice pris en parametre afin de la modifier

			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) {

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) {

					this.plateau[ligneNumber][coloneNumber] = matriceACopier.plateau[ligneNumber][coloneNumber];

				}

			}

		}

		public void removeCouleurInitO(String couleur) {// Pour afficher le O une fois que la couleur est supprimé
			for (int ligneNumber = 0; ligneNumber < 3; ligneNumber++) {

				for (int coloneNumber = 0; coloneNumber < 3; coloneNumber++) {

					if(plateau[ligneNumber][coloneNumber].equals(couleur)) {
						if(coloneNumber==2) {
							plateau[ligneNumber][coloneNumber]="O";
						}else {
							plateau[ligneNumber][coloneNumber]="";
						}
					}

				}
			}
		}

	}
		

