# Les Aventuriers de le France

C'est une adaption d'un jeu de société en java.
Nous retrouvons 2 applications :
	- Une application de conception pour créer un plateau
	- Une application de Jeu pour jouer à partir d'un plateau créer précédement.

## Prérequis

- Java

## Installation

Récupération du dépôt :
```bash
git clone https://github.com/AdamPedersoli/Les-aventuriers-de-France.git
```

Installation de l'application de Conception et du Jeu :
```bash
cd Les-aventuriers-de-France
javac @compileConception.list -d ./class
javac @compileJeu.list -d ./class
```


## Utilisation

Pour exécuter l'application de Conception :
```bash
cd class
java Conception.ControleurConception
```

Pour exécuter l'application Jeu :
```bash
java Conception.ControleurJeu
```