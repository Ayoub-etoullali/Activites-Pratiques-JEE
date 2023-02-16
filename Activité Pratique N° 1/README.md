# (1) Inversion de contrôle et Injection des dépendances

## Partie 1 :
1. Créer l'interface IDao avec une méthode getDate + implémentation
![](images/1.jpg)
2. Créer l'interface IMetier avec une méthode calcul + implémentation en utilisant le couplage faible
![](images/2.jpg)
3. Faire l'injection des dépendances          
   ##### a. Par instanciation statique 
      ![](images/3.a..jpg) 
   ##### b. Par instanciation dynamique   
      ![](images/3.b..jpg)
   ##### c. En utilisant le Framework Spring
      - Version XML
      ![](images/3.c.1..jpg)
      - Version annotations
      ![](images/3.c.2..jpg)
              
### Partie 2 : Mini Projet (Framework Injection des dépendance)
- Développer un mini Framework qui permet de faire l'injection des dépendances avec ses deux version XML et Annotations
- Concevoir et créer un mini Framework d'injection des dépendances similaire à Spring IOC
- Le Framework doit permettre à un programmeur de faire l'injection des dépendances entre les différents composant de son application respectant les possibilités suivantes : 

1- A travers un fichier XML de configuration en utilisant Jax Binding (OXM : Mapping Objet XML)

2- En utilisant les annotations   

3- Possibilité d'injection via :
   ###### a. Le constructeu      
   ###### b. Le Setter
   ###### c. Attribut (accès direct à l'attribut : Field)
