# (2) Bases des systèmes distribués - Programmation Résaux

## Partie 1 : Modèle Multi Threads Blocking IO (java.io)
   ### Développer un serveur de Multi Thread Blocking IO de ChatServer
   * la classe "Conversation" 
            
       ![2](https://user-images.githubusercontent.com/92756846/220728908-d2cb612f-c02a-417e-ab68-07b85ea87dae.jpg)
            
   * Démarrer les conversations
       
       ![1](https://user-images.githubusercontent.com/92756846/220728890-fe8c3d6e-d179-4559-9b42-6f70aa38e80d.jpg) 
       
   * La communication entre |serveur| <----> |client|
   
       ![3](https://user-images.githubusercontent.com/92756846/220728922-7546ca80-c652-45ee-a208-146f0ca823a1.jpg)
   
   ### Tester le serveur avec un client Telnet
   * I/O
   
      ![4](https://user-images.githubusercontent.com/92756846/220728946-cc8cab54-9b7a-4ca3-9905-463ac21543a3.jpg)
      
   * new Thread => Communication |serveur| <----> |client|
      
      ![5](https://user-images.githubusercontent.com/92756846/220728978-cbfd3519-0996-4589-906d-de4de17d2199.jpg)
      
   * client Telnet
      
      ![6](https://user-images.githubusercontent.com/92756846/220728996-aee84afc-1331-47fc-984e-a443efb8bed7.jpg)
   
   ### Créer un client Java avec une interface graphique JavaFX
   
   ### Créer un client Python ou un autre langage quelconque
      
## Partie 2 : Modèle Single Thread avec Non Blocking IO (java.nio)
   ### Développer un serveur de Single Thread  utilisant des entrées sorties non bloquantes 
   
   ### Tester le serveur avec un client Telnet, un client java et un client d'un autre langage
      
## Partie 3 : Utiliser un outil Comme JMeeter pour tester les performances des deux serveurs
