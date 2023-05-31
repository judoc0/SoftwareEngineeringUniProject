
## Repository per il corso di Software Engineering 2021/2022

 Facoltà di Ingegneria - Laurea in Ingegneria dell’Informazione

 Elaborato Software per gli appelli da Giugno 2021 a Maggio 2022

---------------------------------------------------------------------------------------------------

### Descrizione


Una grande catena di mobili e prodotti per la casa vuole creare il sistema MyShop da installare su totem all’interno dei propri
punti vendita per migliorare l’esperienza di acquisto della clientela.


Per ulteriori informazioni rigurardo la traccia dell'elaborato software andare a vedere il file pdf [Traccia_elaborato_software_valida_da_Giugno_2021_a_Maggio_2022.pdf](Traccia_elaborato_software_valida_da_Giugno_2021_a_Maggio_2022.pdf)


---------------------------------------------------------------------------------------------------

### Indicazioni per lo svolgimento

Il sistema deve essere realizzato con il linguaggio di sviluppo ***Java***, adottando l’architettura software e i design pattern
analizzati durante il corso. I dati dovranno essere memorizzati in una base dati relazionale ***MySQL***. Si deve adottare un
processo di sviluppo ***agile Scrum*** e documentare il procedimento di lavoro adottato. Si devono realizzare gli ***unit test*** delle
contenute classi nel package ***DAO***.
La documentazione deve essere
composta da un unico documento con frontespizio e indice, contenente: 

1. analisi dei requisiti,

2. progettazione ***UML*** dell’architettura software, 

3. esplicita indicazione dei ***design pattern*** adottati, 

4. progettazione concettuale e logica della base dati, 

5. esiti degli ***unit test*** eseguiti, 

6. ***Scrum sprint backlog*** e ***burndown chart***.

Per consultare la doumentazione andare a vedere il file pdf [Progetto_PIS.pdf](Progetto_PIS.pdf)


--------------------------------------------------------------------------------------------------


### Database e IDE

Usare il file ***Dump.sql*** per generare il database.
Ricordare di cambiare 'userName' e 'pwd' nella classe ***DbUser*** del package ***DBInterface*** a seconda delle credenziali del proprio ***DBMS***


Per utilizzare gli utenti usati per testare il programma:

* ***Utente acquirente*** -> username: 1, password: 1

* ***Manager*** -> username: 2, password: 2

* ***Amministratore*** -> username: 3, password: 3

Questo progetto è stato sviluppato utilizzando ***IntelliJ IDEA Community Edition***.

Per poter eseguire correttamente il progetto utilizzando un IDE diverso come ***Eclipse***, assicurati di importare correttamente le librerie esterne da [Progetto/lib](Progetto/lib)

--------------------------------------------------------------------------------------------------

###  Versione 2023

Dopo circa un anno, ho deciso di caricare il progetto su github, ma non prima di aver fatto qualche piccolo aggiornamento


*	Il software utilizzava un account Gmail per inviare automaticamente eventuali email. A partire dal 30 maggio 2022, Google ha disabilito la funzione ***App meno sicure***, che ha provocato la cessazione 
	del supporto per l'utilizzo di app di terze parti o dispositivi che richiedono di accedere all'Account Google 
	solo tramite il nome utente e la password. Ora per poter inviare email attraverso app di terze parti tramite un 
	account Gmail, si deve generare una password per le app nel proprio account Google.
	Dopo aver seguito i passaggi per generare l'app password e modificato le credenziali nella classe MailJavaxApiClass 
	nel package business il software è tornato ad inviare le email automatiche.


*	Semplificazione della modalità di inserimento delle immagini rappresentative degli articoli attraverso 
	la classe ***JFileChooser***, della libreria ***Swing*** di ***Java***, per creare una finestra di dialogo per la selezione dei file o delle directory da parte dell'utente.
	(prima richiedevo di inserire a mano il percorso della foto)


*	Nel caso d'uso della rimozione di un articolo, se l'articolo è un prodotto ed è presente in un prodotto composito
	ora ti avvisa che non puoi eliminarlo senza prima rimuoverlo dal prodotto composito;
	(prima provava ad eliminarlo dandoti errore)

	

*	***ItemListener*** spostato per stare insieme agli altri listener dalla classe 
	***NewProdottoPanel*** a ***NewProdottoPanelListener***


*	Nella documentazione pdf ho dimenticato di dichiare che per ho usato il ***Composite Pattern*** per le classi
	***IProdotto***, ***Prodotto***, ***ProdottoComposito*** nel package ***Model***



