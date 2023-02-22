# (1) Inversion de contrôle et Injection des dépendances

## Partie 1 :
1. Créer l'interface IDao avec une méthode getDate + implémentation
![1](https://user-images.githubusercontent.com/92756846/220727785-a1d68111-bedd-4562-83c9-4796f00f0983.jpg)
2. Créer l'interface IMetier avec une méthode calcul + implémentation en utilisant le couplage faible
![2](https://user-images.githubusercontent.com/92756846/220727850-2ea160fe-828f-457a-8dff-5fd5c5ec8e01.jpg)
3. Faire l'injection des dépendances          
   ##### a. Par instanciation statique 
      ![3 a](https://user-images.githubusercontent.com/92756846/220727897-a1a9dd29-c145-4c98-9b9c-e28cd9d80467.jpg)
   ##### b. Par instanciation dynamique   
      ![3 b](https://user-images.githubusercontent.com/92756846/220727938-eb3e9f17-0408-43f3-8a91-44523abb682c.jpg)
   ##### c. En utilisant le Framework Spring
      - Version XML
      ![3 c 1](https://user-images.githubusercontent.com/92756846/220727996-553f2d20-5eba-4440-9062-928eef05e893.jpg)
      - Version annotations
      ![3 c 2](https://user-images.githubusercontent.com/92756846/220728029-cf7102ac-03e9-4cb0-a5a5-820505c61ed1.jpg)
              
## Partie 2 : Mini Projet (Framework Injection des dépendance)
- Développer un mini Framework qui permet de faire l'injection des dépendances avec ses deux version XML et Annotations
- Concevoir et créer un mini Framework d'injection des dépendances similaire à Spring IOC
- Le Framework doit permettre à un programmeur de faire l'injection des dépendances entre les différents composant de son application respectant les possibilités suivantes : 

1- A travers un fichier XML de configuration en utilisant Jax Binding (OXM : Mapping Objet XML)

2- En utilisant les annotations   

3- Possibilité d'injection via :
          a. Le constructeu      
           b. Le Setter
               c. Attribut (accès direct à l'attribut : Field)
