API (backend) et formulaire (frontend) permettant de créer et gérer des sessions pour le jeu KillerParty (CF : https://github.com/FlorentMartinier/KillerParty).
L'API est utilisée par l'application mobile du jeu pour créer une session. Un lien de session peut être alors partagé aux joueurs, et ces derniers utilisent le formulaire pour s'intégrer à la partie en cours.

De la CI/CD a été mise en place pour déployer automatiquement cette application en production après chaque push sur la branche main.
- Le back et le front sont déployés sur Heroku
- La base de données (Postgres) est déployée sur CockroachDB
