# REPORT

<div style="font-size: 18px;">

## **Indice**
</div>

<div style="font-size: 16px;">
   
1. [Introduzione](#1-introduzione)

2. [Modello di Dominio](#2-modello-di-dominio)  

3. [Requisiti Specifici](#3-requisiti-specifici)
 
    - 3.1 [Requisiti Funzionali](#31-requisiti-funzionali)

    - 3.2 [Requisiti non Funzionali](#32-requisiti-non-funzionali)

4. [System Design](#4-system-design)

   - 4.1 [Diagramma dei Package](#41-diagramma-dei-package)

   - 4.2 [Architettura dell'Applicazione](#42-architettura-dellapplicazione)

   - 4.3 [Commenti sulle Decisioni prese](#43-commenti-sulle-decisioni-prese)

5. [OO Design](#5-oo-design)

   - 5.1 [Diagramma delle Classi e di Sequenza](#51-diagramma-delle-classi-e-di-sequenza)

   - 5.2 [Principi di OO Design](#52-principi-di-oo-design)

     - 5.2.1 [Principi SOLID](#521-principi-solid)

   - 5.3 [Commento sulle decisioni prese](#53-commento-sulle-decisioni-prese)

6. [Riepilogo dei Test](#6-riepilogo-dei-test)

   - 6.1 [Strumenti di Analisi e Testing del Codice utilizzati](#61-strumenti-di-analisi-e-testing-del-codice-utilizzati)

   - 6.2 [Presentazione esiti Test e Considerazioni](#62-presentazione-esiti-test-e-considerazioni)

   - 6.3 [Descrizione test effettuati](#63-descrizione-test-effettuati) 

7. [Manuale Utente](#7-manuale-utente)

   - 7.1 [Introduzione Manuale](#71-introduzione-manuale)
 
   - 7.2 [Azioni Preliminari](#72-azioni-preliminari)
     - 7.2.1 [Installazione e configurazione Docker](#721-installazione-e-configurazione-docker)
     - 7.2.2 [Autenticazione con GitHub](#722-autenticazione-con-github)
     - 7.2.3 [Avvio dell'Applicazione](#723-avvio-dellapplicazione)

   - 7.3 [Guida ai Comandi](#73-guida-ai-comandi)
     - 7.3.1 [Come si Gioca](#731-come-si-gioca)

   - 7.4 [Glossario e Termini Chiave](#74-glossario-e-termini-chiave)

8. [Processo di Sviluppo e Organizzazione del Lavoro](#8-processo-di-sviluppo-e-organizzazione-del-lavoro)

   - 8.1 [Introduzione Processo di Sviluppo](#81-introduzione-processo-di-sviluppo)

   - 8.2 [RoadMap degli Sprint](#82-roadmap-degli-sprint)

   - 8.3 [Gestione del Lavoro](#83-gestione-del-lavoro)

     - 8.3.1  [Sprint 0](#831-sprint-0)

     - 8.3.2 [Sprint 1](#832-sprint-1)

     - 8.3.3 [Sprint 2](#833-sprint-2)

   - 8.4 [Software Utilizzati](#84-software-utilizzati)

   - 8.5 [Comunicazione nel Team](#85-comunicazione-nel-team)

   - 8.6 [Problemi Riscontrati](#86-problemi-riscontrati)

9. [Analisi Retrospettiva](#9-analisi-retrospettiva)

   - 9.1 [Sprint 0](#91-sprint-0)

   - 9.2 [Sprint 1](#92-sprint-1)
   </div>
  

<div style="font-size: 16px;">



## **1. Introduzione**

</div>

<div style="font-size: 15px;">
Il progetto ha come obiettivo la realizzazione di una versione digitale del gioco da tavolo Ataxx, sviluppata utilizzando il linguaggio di programmazione Java e giocabile attraverso un terminale sfruttando la riga di comando (CLI).
</div>

### <span style="font-size: 112%;">Gioco Ataxx

<div style="font-size: 15px;">
Ataxx è un gioco di strategia che si sviluppa all'interno di un tavoliere composto da 49 caselle (7x7), dove gli sfidanti hanno lo scopo di convertire le pedine avversarie in proprie, in modo tale da conquistare la maggior parte del territorio. Vince chi riesce ad occupare il tavoliere con il maggior numero di pedine.
</div>

### <span style="font-size: 112%;">Descrizione funzionalità implementate
<div style="font-size: 15px;">
Il progetto comprende le suguenti funzionalità principali:

- **Modalità di Gioco**: Possibilità di giocare contro un altro giocatore sullo stesso schermo 

- **Tavoliere**: Un tavoliere composto da 49 caselle su cui è possibile spostare le pedine utilizzando le coordinate delle righe e delle colonne presenti

- **Elenco Comandi**: Implementazione di un elenco comandi che illustra tutti i possibili comandi utilizzabili all'interno del gioco ed accessibile in ogni momento digitanto *`/help`*, *`--help`* o *`-h`*. 

### <span style="font-size: 112%;">Metodo di Sviluppo
In questo report verranno illustrati in modo dettagliato i vari processi di sviluppo del codice, i problemi riscontrati dal team durante le implementazioni di esso e le risoluzioni adottate per portare a termine i vari Sprint. Il tutto verrà illustrato anche mediante l'utilizzo di screenshots esplicativi.
</div>

## **2. Modello di Dominio**

<div style="font-size: 15px;">

In questa sezione verrà mostrato il modello di dominio realizzato in UML tramite il software [StarUML](https://staruml.io/).
</div>

![ModelloDiDominio](./img/ModelloDominio.png)

## **3. REQUISITI SPECIFICI**

<div style="font-size: 16px;">

### **3.1 REQUISITI FUNZIONALI**
</div>


<div style="font-size: 15px;">

- **Identificatore**: [RF001](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/18#issue-2285800064)
    
    * **Descrizione**: Come giocatore voglio mostrare l'help con elenco comandi
    
    * **Condizioni iniziali**: L'utente ha appena avviato il gioco e si trova nella schermata inziale e non sa cosa cliccare per avviare il gioco.

    * **Azioni richieste**: L'utente deve digitare il comando *`/help`*, *`--help`* o *`-h`* per visualizzare il Menu di Help.

    * **Condizioni finali**: L'utente accede al Menu help e gli viene mostrato l'elenco con i comandi utilizzabili all'interno del software.

- **Identficatore**: [RF002](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/19#issue-2285802602)

    * **Descrizione**: Come giocatore voglio iniziare una nuova partita.

    * **Condizioni iniziali**: L'utente si trova nel menu principale e vuole iniziare una nuova partita.

    * **Azioni richieste**: Per iniziare una nuova partita, l'utente, deve digitare il comando *`/gioca`*.

    * **Condizioni finali**: All'utente viene mostrato il tavoliere di gioco con le pedine in posizione iniziale e si predispone ad effettuare la prima mossa di gioco, oppure potrebbe digitare altri comandi.


- **identificatore**: [RF003](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/20#issue-2285804100)

   * **Descrizione**: Come giocatore voglio mostrare il tavoliere vuoto con la numerazione.

   * **Condizioni iniziali**: L'utente si trova nel menu principale e vorrebbe visualizzare il tavoliere di gioco vuoto.

   * **Azioni richieste**: Per visulizzare il tabellone vuoto, l'utente, deve digitare il comando *`/vuoto`*.

   * **Condizioni finali**: All'utente viene mostrato il tavoliere con 49 caselle (7x7) con le righe numerate con numeri da 1 a 7, e le colonne numerate con lettere che vanno da 'a' a 'g'.


- **Identificatore**: [RF004](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/21#issue-2285805938)

   * **Descrizione**: Come giocatore voglio mostrare il tavoliere con le pedine e la numerazione

   * **Condizioni iniziali**: L'utente non ha ancora iniziato una nuova partita e gli viene suggerito il comando *`/gioca`* per iniziare una nuova partita.

   * **Azioni richieste**: Per poter visualizzare il tavoliere con le pedine e la numerazione, l'utente, deve prima iniziare una nuova partita e successivamente digitare il comando *`/tavoliere`*.

   * **Condizioni finali**: All'utente viene mostrato il tavoliere con 49 caselle (7x7) con le righe numerate con numeri da 1 a 7, e le colonne numerate con lettere che vanno da 'a' a 'g'. Inoltre, sono presenti le pedine posizionate nelle caselle iniziali al fine di poter effettuare le successive mosse.


- **Identificatore**: [RF005](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/22#issue-2285807427)

   * **Descrizione**: Come giocatore voglio visualizzare le mosse possibili di una pedina

   * **Condizioni iniziali**: L'utente ha iniziato una nuova partita digitando il comando *`/gioca`* e gli è stato mostrato il tavoliere contenente le pedine in posizione iniziale, e vorrebbe conoscere le mosse possibili da effettuare muovendo una determinata pedina.

   * **Azioni richieste**: Per visualizzare le mosse possibili, l'utente, deve digitare il comando *`/qualimosse`* e successivamente dovrà inserire le coordinate della pedina che vuole giocare.

   * **Condizioni finali**: Dopo aver digitato la posizione della pedina (inserendo riga e colonna corrispondenti), all'utente vengono mostrate con un colore differente, le mosse che potrebbero essere effettuate dalla pedina indicata.


- **Identificatore**: [RF006](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/23#issue-2285808665)

   * **Descrizione**: Come giocatore voglio abbandonare la partita.

   * **Condizioni iniziali**: L'utente sta giocando una partita ma vorrebbe abbandonare perchè le sue possibilità di vittoria sono quasi nulle.

   * **Azioni richieste**: Per poter abbandonare una partita, l'utente, dovrà digitare il comando *`/abbandona`*. L'app chiederà all'utente di confermare l'abbandono.

   * **Condizioni finali**: Se la conferma di abbandono risulta positiva, l'app comunica che il Bianco (o Nero) ha vinto per abbandono e dichiara come vincitore l'avversario per x a 0 dove x è il numero di pedine rimaste dell'avversario.
   Se la conferma è negativa, l'app si predispone a ricevere nuove mosse o comandi.


- **Identificatore**: [RF007](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/24#issue-2285809899)

   * **Descrizione**: Come giocatore voglio chiudere il gioco.

   * **Condizioni iniziali**: L'utente ha terminato una partita (oppure ha avviato l'app per errore) e vorrebbe uscire dall'applicazione.

   * **Azioni richieste**: Per poter chiudere il gioco l'utente dovrà digitare il comando *`/esci`*. L'app chiederà all'utente di confermare la sua scelta.

   * **Condizioni finali**: Se la conferma di chiusura del gioco è positiva, l'app si chiude e restituisce il controllo al sistema operativo.
   Se la conferma è negativa, l'app si predispone a ricevere nuovi comandi.

- **Identificatore**: [RF008](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/48#issue-2325219614)

  * **Descrizione**: Come giocatore voglio giocare una nuova pedina in una casella adiacente a una propria pedina.

  * **Condizioni iniziali**: L'utente sta giocando una partita, è il proprio turno, e vorrebbe muovere la pedina selezionata in una casella adiacente ad essa.

  * **Azioni richieste**: Per poter muovere la pedina nella posizione desiderata, l'utente, deve digitare ad esempio il comando *`a1-a2`*, dove a1 è la casella di partenza e a2 la casella adiacente.

  * **Condizioni finali**: Una volta digitato il comando, la pedina si sposterà nella casella indicata.

- **Identificatore**: [RF009](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/49#issue-2325222616)

  * **Descrizione**: Come giocatore voglio spostare una propria pedina saltando una casella adiacente.

  * **Condizioni iniziali**: L'utente sta giocando una partita, è il proprio turno, e vorrebbe muovere la pedina selezionata saltando una casella adiacente ad essa.

  * **Azioni richieste**:  Per poter muovere la pedina nella posizione desiderata, l'utente, deve digitare ad esempio il comando *`a1-a3`*, dove a1 è la casella di partenza e a3 la casella di arrivo.

  * **Condizioni finali**: Una volta digitato il comando, la pedina si sposterà nella casella indicata, a patto che la casella libera non sia adiacente alla casella originaria.

- **Identificatore**: [RF010](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/50#issue-2325228438)

  * **Descrizione**: Come giocatore voglio catturare una pedina avversaria come effetto di una mossa.

  * **Condizioni iniziali**: L'utente sta giocando una partita, è il proprio turno, e vorrebbe effettuare una mossa per catturare una pedina avversaria.

  * **Azioni richieste**: Per poter catturare una pedina avversaria, l'utente, deve digitare il comando con notazione algebrica per spostarsi nella casella desiderata.

  * **Condizioni finali**: L'utente ha effettuato la propria mossa e la pedina è stata spostata in una casella adiacente a pedine avversarie, dunque quest'ultime vengono catturate assumendo il colore del giocatore che ha effettuato la mossa.

- **identificatore**: [RF011](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/51#issue-2325251871)

  * **Descrizione**: Come giocatore voglio mostrare le mosse effettuate.

  * **Condizioni iniziali**: L'utente sta giocando una partita e vorrebbe visualizzare lo storico delle mosse effettuate.

  * **Azioni richieste**: Per visualizzare lo storico delle mosse effettuate, l'utente, deve digitare il comando *`/mosse`*.

  * **Condizioni finali**: Una volta digitato il comando, viene visualizzato lo storico delle mosse con notazione algerbrica, come ad esempio 
  *1. a1-a2 (N);  2. g7-g6 (B)*.

- **Identificatore**: [RF012](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/52#issue-2325255204)

  * **Descrizione**: Come giocatore voglio passare il turno per impossibilità di movimento.

  * **Condizioni iniziali**: L'utente sta giocando una partita, è il proprio turno, e vorrebbe passare il proprio turno per impossibilità di movimento.

  * **Azioni richieste**: Per passare il proprio turno, l'utente non deve digitare alcun comando.

  * **Condizioni finali**: Se il giocatore non può muovere nessuna pedina del proprio colore, l'app avvisa che il proprio turno passa all'altro giocatore.

- **Identificatore**: [RF013](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/53#issue-2325260166)

  * **Descrizione**: Come giocatore voglio visualizzare il fine partita con il vincitore e i punti segnati.

  * **Condizioni iniziali**: L'utente non può effettuare mosse perchè il tavoliere è stato tutto riempito.

  * **Azioni richieste**: Per visualizzare il fine partita e i punti segnati, l'utente non deve digitare alcun comando.

  * **Condizioni finali**: L'app dichiara il vincitore (Bianco o Nero) e riporta il punteggio di ogni giocatore contando le rispettive pedine.

- **Identificatore**: [RF014](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/54#issue-2325263349)

  * **Descrizione**: Come giocatore voglio mostrare il tempo di gioco.

  * **Condizioni iniziali**: L'utente sta giocando una partita e vorrebbe visualizzare il tempo di gioco.

  * **Azioni richieste**: Per poter visualizzare il tempo di gioco, l'utente deve digitare il comando *`/tempo`*.

  * **Condizioni finali**: Una volta digitato il comando verrà visualizzato a schermo il tempo effettivo di gioco nel formato *ore:minuti:secondi*.

- **Identificatore**: [RF015](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/55#issue-2325265844)

  * **Descrizione**: Come giocatore voglio impostare caselle non accessibli.

  * **Condizioni iniziali**: L'utente si trova nel menu principale, e prima di iniziare a giocare una nuova partita, vorrebbe delle caselle del tavoliere non accessibili.

  * **Azioni richieste**: Per poter far scelgiere all'utente quali caselle bloccare prima di iniziare a giocare, bisogna digitare il comando *`/blocca xn`* dove xn indica la coordinata della casella da bloccare.
  Non è possibile bloccare le caselle di partenza del gioco; tutte le caselle adiacenti a una casella di partenza del gioco; tutte le caselle a distanza 2 da una casella di partenza del gioco.
  Inoltre, non è possibile bloccare più di 9 caselle.

  * **Condizioni finali**: Una volta digitato il comando verrà visualizzato il tavoliere con le caselle bloccate dall'utente.
   </div>

<div style="font-size: 16px;">

### **3.2 REQUISITI NON FUNZIONALI**
</div>

<div style="font-size: 15px;">

Il funzionamento del software richiede:

- **RFN1**: il container Docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16

  **Elenco terminali supportati**:  
  
   **Linux** :
    * Terminal

    **Windows**:
    * Powershell
    * Gitbash (in questo caso il Docker ha come prefisso winpty)
    </div>

## **4. SYSTEM DESIGN**

<div style="font-size: 15px;">

### **4.1 Diagramma dei Package**

![DiagrammaPackage](./img/DiagrammaPackage.png)

### **4.2 Architettura dell'Applicazione**

La suddivisione in package è stata effettuata accomunando le classi in base alle loro responsabilità e compiti svolti. In particolare è stato utilizzato il pattern architetturale dell'Entity Control Boundary (ECB) che prevede la classificazione delle classi in tre categorie:

**CONTROL**: sono le classi che si occupano della logica del software. In particolare si occupano di gestire le interazioni tra le entità e di gestire le richieste dell'utente.

**BOUNDARY**: sono le classi che si occupano di interfacciarsi con l'utente e di gestire le logiche di presentazione. In particolare si occupano di ricevere i comandi dell'utente e di mostrare i risultati delle operazioni.

**ENTITY**: sono le classi che rappresentano le entità del dominio del problema. In particolare si occupano di rappresentare le entità del gioco e di gestire le loro interazioni.

Passiamo ora all'elenco dei package e delle classi in essi contenuti:

- Il package **app** è quello impostato di default alla creazione del progetto. Contiene la classe App che si occupa dell'inizializzazione e avvio del software. 
Contiene anche tutti i package e le classi contenuti in essi create dal team di sviluppo. 
E' evidente la suddivisione in package seguendo il pattern architetturale dell'Entity Control Boundary. 

- Il package **entity** contiene le seguenti classi: 
	- *Blocca*: classe che gestisce l'impostazione delle casella bloccate prima dell'inizio della Partita.
	- *Pedina*: classe che si occupa della creazione di pedine sul tavoliere.
	- *Giocatore* : classe che si occupa delle caratteristiche,stato e funzionalità di un giocatore durante una partita.
	- *Coordinat*: classe che rappresenta le coordinate di una pedina sul tavoliere di gioco.
	- *Mossa*: Classe utilizzata per rappresentare le mosse possibili di una pedina. 
	- *Duplicazione*: classe che si occupa della gestione della mossa di tipo A.
	- *Salto*: classe che si occupa della gestione della mossa di tipo B.
	- *Tavoliere*: classe che rappresenta un generico tavoliere di gioco.
	- *StampaTavoliere*: classe che contiene tutte le stampe delle possibili rappresentazioni del tavoliere.

Questo package contiene dunque tutte le classi di tipo Entity previste dallo standard ECB.

- Il package **control** contiene:
	-Partita: classe che contiene il controllo di tutta la partita del gioco.

Questo package contiene appunto l'unica classe principale di tipo control previste dallo standard ECB.

Il package **Boundary** contiene:
- *Costanti*: Classe che contiene le costanti del gioco. Questa classe fornisce variabili statiche finali utilizzate per la formattazione del testo, i limiti della griglia del gioco, messaggi di benvenuto, menu dei comandi, e le regole del gioco.
	
- *Tastiera*: Classe per gestire l'input da tastiera. Questa classe fornisce metodi per leggere diversi tipi di input dall'utente, come stringhe, interi e caratteri, gestendo eventuali errori di input.
  
- *Menu*: Questa classe gestisce l'interfaccia del menu per l'utente nel gioco. Fornisce metodi per visualizzare l'aiuto, confermare l'uscita e pulire lo schermo.


### **4.3 Commenti sulle Decisioni prese**

Il team di sviluppo ha deciso di utilizzare il pattern architetturale ECB per la sua semplicità. In particolare, il pattern ECB è stato scelto per la sua capacità di separare le classi in base alle loro responsabilità, in modo da rispettare gli OO Design, e per la sua capacità di rendere il codice più manutenibile e testabile, data la modularità delle componenti.


</div>

## **5. OO DESIGN**

<div style="font-size: 15px;">

### **5.1 Diagramma delle Classi e di Sequenza**
In questa sezione vengono mostrati i diagrammi delle classi a prospettiva software e i diagrammi di sequenza per le user story maggiormente significative.

* **[RF002](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/19#issue-2285802602)**: Come giocatore voglio iniziare una nuova partita.

  **Diagramma delle Classi**

  ![DiagramaClassiRF002](./img/DiagrammaClassiRF002.png)

  **Diagramma di Sequenza**

  ![DiagrammaSequenzaRF002](./img/DiagrammaSequenzaRF002.png)

  

* **[RF015](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/55#issue-2325265844)**: Come giocatore voglio impostare caselle non accessibili

  **Diagramma delle Classi**

  ![DiagrammaClassiRF015](./img/Diagramma_ClassiRF015.png)

  

  **Diagramma di Sequenza**

  ![DiagrammaSequenzaRF015](./img/Diagramma_SequenzaRF015.png)

  

* **[RF006](https://github.com/softeng2324-inf-uniba/progetto-minsky/issues/23#issue-2285808665)**: Come giocatore voglio abbandonare una partita

  **Diagramma delle Classi**

  ![DiagrammaClassiRF006](./img/Diagramma_ClassiRF006.png)

  
  **Diagramma di Sequenza**

  ![DiagrammaSequenzaRF006](./img/DiagrammaSeqeunzaRF006.png)

  
  

### **5.2 Principi di OO Design**


Data la realizzazione del progetto in Java, un linguaggio intrinsecamente Object-Oriented, l'architettura dell'applicazione segue i seguenti principi:

* **Information Hiding**

  Gli attributi di tutte le classi sono privati e sono stati messi a disposizione dei getter/setter per permettere un accesso controllato e corretto. Laddove un metodo utilizzasse un altro metodo di servizio, quest'ultimo è stato reso privato.

* **Alta Coesione**

  Le classi hanno una ben definita responsabilità, grazie alla tassonomia ECB (Entity-Control-Boundary).

* **Basso Accoppiamento**

  Poiché il progetto rispetta il principio dell'information hiding, non ci sono forti dipendenze tra componenti. Questo consente di non propagare i cambiamenti.

* **Presentazione Separata**

  Dato l'utilizzo della tassonomia ECB, le classi Boundary si occupano esclusivamente della logica di presentazione e dell'interfacciamento con l'utente, mentre le classe Partita nel package Control implementano la logica di dominio.


* **Do not Repeat Yourself (DRY)**

  Il principio DRY è stato rispettato: non ci sono duplicazioni di codice all'interno del progetto.

  Un esempio nel nostro codice è proprio la classe StampaTavoliere aderisce al principio Do Not Repeat Yourself (DRY) attraverso i seguenti approcci:

<div style = "margin:20px;"> 

#### **5.2.1 Principi SOLID**


I principi SOLID sono intesi come linee guida per lo sviluppo di software leggibile, estendibile e manutenibile:

**Single Responsibility**: ogni classe ha una sola responsabilità

**Open-Closed**: le classi sono aperte all'estensione e chiuse alle modifiche mediante i modificatori di accesso giusti e alla modularità fornita dall'architettura

**Liskov Substitution**: non è stato implementato il principio di Liskov Substitution

**Interface Segregation**: non sono state utilizzate interfacce
   
**Dependency Inversion**: Non è stato adoperato questo principio solid


### **5.3 Commento sulle Decisioni prese**

#### Considerazioni in merito alla decisione di non utilizzare file di testo:

La classe "Costanti" gestisce la stampa di una grande quantità di testo importante, motivo per cui inizialmente si era pensato all'utilizzo di file di testo. Ad esempio, nella classe, sono state definite delle "costanti" per la stampa di elementi come gli alieni o le scritte GAME e ATAXX, che originariamente si pensava di caricare da file di testo.

Tuttavia, questa soluzione non è stata possibile a causa delle limitazioni del workflow. La configurazione del workflow non permette di includere file di testo esterni al jar all'interno del container Docker, che viene creato dalla build di Gradle. Pertanto, il team ha rispettato il workflow e ha ripiegato sull'utilizzo di stampe dirette e la suddivisione in sottometodi per gestire stampe molto corpose.


</div>

## **6. RIEPILOGO DEI TEST**

<div style="font-size: 15px;">

### **6.1 Strumenti di Analisi e Testing del Codice utilizzati**

 * [JUNIT](https://junit.org/junit5/)

 * [CheckStyle](https://checkstyle.sourceforge.io/)

 * [SpotBugs](https://spotbugs.github.io/)

 * [PMD](https://pmd.github.io/)
 

### **6.2 Presentazione esiti Test e Considerazioni**

Nello Sprint 2, il gruppo "Minsky" si è adoperato per effettuare l'attività di Verification&Validation (V&V) che si suddivide in un processo statico (finalizzato all'analisi del codice con Checkstyle, Spotbugs e PMD) e un processo dinamico (finalizzato alla creazione di casi di test con JUnit 5 e verifica del funzionamento dei metodi).

Di seguito vengono presentati gli esiti delle fasi di test.

 * **Esito test JUnit5**
 
    Qui sotto un breve resoconto dei casi di test effettuati.


   ![TestSummary](./img/TestSummary.png)

* **Esito CheckStyle Main**

  ![CheckStyle](./img/CheckStyle_Main.png)

* **Esito CheckStyle Test**

  ![CheckStyle1](./img/CheckStyle_Test.png)

* **Esito PMD Main**

  ![PMD](./img/pmd.png)

* **Esito PMD Test**

  ![PMD1](./img/pmd.png)

* **Esito Build (GitHub Actions)**

  ![Gradle](./img/GradleBuild.png)

### **6.3 Descrizione Test effettuati**


Seguendo l'organizzazione canonica della test suite è stata redatta una classe di test per ogni classe del software a eccezione delle classi Boundary, in quanto è stato applicato il principio di presentazione separata, in quanto non presentano metodi da testare. Tutte le classi di test sono state raggruppate in un unico package denominato "test" separato dal resto del codice e la struttura delle classi nel package test rispecchia la struttura delle classi nel package main. Inoltre tutti i metodi di test hanno la stringa "test" come prefisso in testa al nome del metodo.



I test sono stati eseguiti utilizzando criteri funzionali, selezionando i casi di test basandosi esclusivamente sulle specifiche, senza conoscere la struttura interna del software. I criteri adottati sono:

**Suddivisione in classi di equivalenza**: I casi di test sono stati scelti per coprire tutti i possibili input, raggruppando i valori in classi di equivalenza.

**Ripetizione dei test**: Alcuni test sono stati ripetuti più volte con parametri diversi per coprire tutti i casi possibili

</div>



## **7. MANUALE UTENTE**

<div style="font-size: 18px;">

### [**Indice**](#indice)
</div>


<div style="font-size: 15px;">

### **7.1 Introduzione Manuale**
Benvenuto nel Manuale Utente del software **Ataxx**. Questo manuale è stato creato per guidarti nell'utilizzo del software in modo efficace e senza problemi.

#### <span style="font-size: 112%;">Obiettivo del Manuale
Il nostro obiettivo con questo manuale è fornirti istruzioni chiare e dettagliate su come utilizzare tutte le funzionalità di Ataxx. Attraverso istruzioni step by step, esempi pratici e suggerimenti utili, speriamo di rendere l'esperienza di utilizzo del software piacevole e senza stress.

#### <span style="font-size: 112%;">Struttura del Manuale
Il manuale è diviso in diverse sezioni, ognuna delle quali copre un aspetto specifico di Ataxx :

1. **Introduzione**: Questa sezione fornisce una panoramica del manuale, inclusi gli obiettivi di esso.

2. **Azioni preliminari**: Qui troverai istruzioni dettagliate su come installare e configurare software indispensabili per consentire all'applicazione di essere avviata. Troverai l'installazione e configurazione di Docker tramite l'utilizzo di GitHub.

3. **Guida ai Comandi**: Una guida rapida per aiutarti a iniziare rapidamente con le funzionalità del software.

4. **Glossario e Termini Chiave**: Una lista di termini e concetti chiave utilizzati nel software, con le relative definizioni.

#### <span style="font-size: 112%;">Come Utilizzare questo Manuale

Per ottenere il massimo beneficio da questo manuale, ti consigliamo di leggerlo in modo sequenziale, partendo dall'introduzione e procedendo attraverso le varie sezioni in ordine. Se hai bisogno di informazioni su una funzionalità specifica, puoi consultare direttamente la sezione corrispondente utilizzando l'indice.

<div style="font-size: 16px;">

### **7.2 Azioni Preliminari**
</div>

Prima di poter avviare l'applicazione si devono compiere delle azioni preliminari: installare Docker e configurare l'accesso con GitHub.

<div style="font-size: 16px;">

* #### 7.2.1 Installazione e Configurazione Docker
   </div>

   <div style="margin-left: 20px;">

   Per prima cosa bisogna scaricare [Docker Desktop](https://www.docker.com/products/docker-desktop/) (clicca il nome dell'applicazione per recarti al sito ufficale) dal sito ufficiale e assicurarsi che l'installazione sia andata a buon fine.
   </div>

<div style="font-size: 16px;">

* #### 7.2.2 Autenticazione con GitHub
</div>
  
  <div style="margin-left: 20px;">

  Eseguire l'accesso a Docker tramite account di GitHub in modo tale da poter ottenere l'applicazione. Sarà necessario creare il Personal Access Token di GitHub per poter collegare l'account (clicca [qui](https://docs.github.com/en/enterprise-server@3.9/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens) per leggere una guida su come creare il Token). Una volta creato il token assicurasi di memorizzarlo in un file denominato ad esempio `Token.txt`, perchè non sarà più visibile all'interno di GitHub.         
  Una volta effettuati questi passaggi, recarsi in un qualsiasi terminale a vostra disposizione (**Attenzione!** Assicurarsi che sia un terminale che soddisfi i [Requisiti non Funzionali](#32-requisiti-non-funzionali)), e digitare il comando `cat ./TOKEN.txt | docker login ghcr.io -u <USERNAME> --password-stdin` sostituendo `<USERNAME>` con il proprio username di GitHub.
  </div>

<div style="font-size: 16px;">

* #### 7.2.3 Avvio dell'Applicazione
</div>
  
  <div style="margin-left: 20px;">

  Per poter eseguire l'applicazione, assicurarsi di avere *Docker Desktop* in esecuzione, ed eseguire sul terminale il comando 
  `docker pull ghcr.io/softeng2324-inf-uniba/ataxx-minsky:latest ` per poter scaricare l'immagine dal docker.
  Per avviare il programma, digitare il comando                                
  `winpty docker run --rm -it ghcr.io/softeng2324-inf-uniba/ataxx-minsky:latest `
  

   ![DockerPull](./img/DockerPull1.png)

   ![DockerRun](./img/DockerRun.png)

   Durante lo sviluppo del nostro gioco, ci è stato richiesto di rappresentare il tavoliere utilizzando caratteri UNICODE per le pedine. Tuttavia, abbiamo optato per un approccio differente nella consegna del nostro sprint. Abbiamo scelto di visualizzare il tavoliere con le lettere N e R per indicare rispettivamente le pedine nere e rosse.

   Per poter visualizzare correttamente il contenuto del gioco (ad esempio le pedine) è consigliato scaricare un font per UNICODE (si lascia libera scelta). Successivamente bisogna avviare il terminale (è preferibile utilizzare il cmd di Windows) e digitare il comando
   `cd progetto-minsky` per spostarsi nella cartella corretta, e dopo di che sarà possibile digitare il comando `gradlew.bat build` per buildare il file gradle.
   Dopo aver digitato il comando attivare il font tramite il comando `chcp 65001`
   Infine avviare l'applicazione digitando il comando              
   `java -jar -D"stdout.encoding=UTF-8" build/libs/ataxx-all.jar`.

   Nonostante la scelta, siamo riusciti comunque a catturare uno screenshot delle pedine visualizzate in formato UNICODE.
   </div>

<div style="margin-left: 20px;">

  ![Pedine](./img/Tavoliere.png)
  </div>
   

### **7.3 Guida ai Comandi**
Una volta avviata l'applicazione, l'utente vedrà una schermata di benvenuto in cui verrà richiesto di digitare un comando. L'elenco dei comandi è reso disponibile all'utente digitando,in un qualsiasi momento, il comando `/help`, `--h` o `-h`.
I comandi disponibili, oltre al già citato /help, sono:

* `/gioca`   Questo comando permette all'utente di iniziare una nuova partita

* `/vuoto`   Questo comando permette di visualizzare il tavoliere di 49 caselle senza pedine
* `/tavoliere` Questo comando permette all'utente di visualizzare il tavoliere con le pedine poste nelle posizioni iniziali, pronte ad accettare una nuova mossa
* `/qualimosse` Questo comando permette al giocatore di visualizzare quali mosse può effettuare la pedina presa in considerazione
* `/mosse` Questo comando permette di visualizzare lo storico delle mosse effettuate
* `/tempo` Questo comando serve per vedere il tempo effettivo di gioco
* `/abbandona` Questo comando permette al giocatore di abbandonare una partita andando anche a decretare la sconfitta dello stesso
* `/esci` Questo comando permette all'utente di chiudere il gioco

<div style="font-size: 16px;">

 #### 7.3.1 Come si Gioca
 </div>

 Una volta avviata l'applicazione, verrà visualizzata la scritta *Ataxx* sotto al quale verrà suggerito di digitare il comando `/help`, `--h` o `-h` per visualizzare i comandi disponibili.
 
 ![PaginaIniziale](./img/PaginaIniziale.png)


Tornati nella schermata principale si potrà iniziare una nuova partita digitando il comando `/gioca`, oppure si potrà chiudere il gioco digitando `/esci`.

![Gioca](./img/Gioca.png)

![Tavoliere](./img/Tavoliere.png)

Se l'utente volesse visualizzare il tavoliere vuoto (senza pedine), dovrebbe digitare il comando `/vuoto`.

![Vuoto1](./img/Vuoto1.png)

![Vuoto2](./img/Vuoto2.png)

Prima di iniziare la partita, l'utente può decidere di bloccare alcune caselle del tavoliere tramite il comando `/blocca xn` dove xn indica la coordinata della casella.  Non sarà possibile bloccare le caselle di partenza del gioco; tutte le caselle adiacenti a una casella di partenza del gioco; tutte le caselle a distanza 2 da una casella di partenza del gioco.
Inoltre, non sarà possibile bloccare più di 9 caselle. Si potrà bloccare una sola casella alla volta, quindi nel caso si volesse bloccare più di una casella, bisognerà digitare nuovamente il comando.

![Blocca](./img/Blocca.png)

![CaselleBloccate](./img/CaselleBloccate.png)

Durante la partità è possibile visualizzare il menu help di gioco digitando il comando `/help`, `--h` o `-h`, in cui verranno mostrati i comandi digitabili durante la partita.

![Comandi](./img/Comandi.png)

Sarà possibile visualizzare le mosse disponibili digitando il comando `/qualimosse`. Verrà richiesto di inserire le coordinate della riga e della colonna di dove si vuole spostare la pedina.

![QualiMosse](./img/QualiMosse.png)

Per effettuare una mossa, l'utente dovrà inserire la coordinata della pedina che vuole muovere/duplicare, e la coordinata della casella d'arrivo *(ad esempio a1-a2)*.

![MossaEffettuata](./img/MossaEffettuata.png)

Dopo aver effettuato delle mosse, l'utente, potrà visualizzare lo storico delle mosse effettuate digitando il comando `/mosse`.

![Mosse1](./img/Mosse1.png)

![Mosse](./img/Mosse.png)

L'utente potrà anche visualizzare il tempo effettivo di gioco digitando il comando `/tempo`, in questo modo verrà visualizzato il tempo nel formato *ore:minuti:secondi*.

![Tempo1](./img/Tempo1.png)

![Tempo](./img/Tempo.png)


Per visualizzare il tavoliere durante la fase di gioco bisogna digitare il comando `/tavoliere`.

![QualiMosse](./img/Tavoliere.png)

Successivamente il giocatore potrà continuare ad effettuare le mosse oppure potrà abbandondare la partita tramite il comando `/abbandona`, decretando così la sua sconfitta(verrà mostrato il numero pedine dell'avversario contro le zero pedine di chi ha abbandonato la partita). Se il giocatore digita il comando per abbandonare la partita, verrà richiesto di confermare ciò attraverso il comando `si` oppure si può tornare alla partita digitando `no`. 

![Abbandona](./img/Abbandona.png)

![Abbandonato](./img/Abbandonato.png)

Se la partita termina non per abbandono, ma perchè è stato riempito il tavoliere, allora verrà visualizzato il vincitore indicando il numero di pedine a dispozione di entrambi i giocatori. Vince chi ha più pedine.

![Vincitore](./img/Vincitore.png)

Potrebbe capitare che un giocatore mangi tutte le pedine dell'avversario. In questo caso il turno sarà fino alla fine della partita, di chi ha ancora pedine presenti sul tavoliere.

![FineMosse](./img/FineMosse.png)

Infine si tornerà alla schermata iniziale dove si potrà iniziare una nuova partita oppure si potrà chiudere il gioco tramite `/esci`.

![Esci](./img/Esci.png)


### **7.4 Glossario e Termini Chiave**
All'interno dell'app vengono presentati dei termini che potrebbero causare incomprensioni, eccone alcuni:

 * Tavoliere : Il tavoliere è lo spazio di gioco. E' composto da 49 caselle (7x7) e su di esso verranno mosse le pedine.

 * Pedine: Le pedine sono i protagonisti del gioco. Sono nere o bianche e possono effettuare delle specifiche mosse che sono riportate in [Come si Gioca](#731-come-si-gioca).
 </div>

<div style="font-size: 15px;">

## **8. PROCESSO DI SVILUPPO E ORGANIZZAZIONE DEL LAVORO**
<div style="font-size: 15px;">

### **8.1 Introduzione Processo di Sviluppo**

Durante l'intero periodo di sviluppo del progetto, il gruppo ha adottato il metodo di sviluppo software *Agile*. A differenza di uno sviluppo a cascata, lo sviluppo Agile suddivide il lavoro in cicli brevi e incrementali, permettendo al team di rilasciare frequenti aggiornamenti del prodotto.

Il processo di sviluppo del software è stato condotto seguendo la metodologia Scrum, un framework agile che facilita la gestione di progetti complessi e il loro miglioramento continuo. Scrum si basa su delle iterazioni a tempo chiamate Sprint che sono caratterizzati da eventi:

 * **Sprint Planning**: All'inizio di ogni Sprint, il team si riunisce per pianificare le attività da svolgere. Durante questa riunione vengono definiti gli obiettivi dello Sprint e viene creato lo Sprint Backlog, ovvero un elenco delle attività da completare.

 * **Daily Scrum**: Ogni giorno il team tiene una breve riunione(di solito di durata 15 minuti) per sincronizzarsi sulle attività svolte, quelle pianificate per il giorno dopo e per identificare eventuali ostacoli. Questo aiuta a mantenere il team allineato e a risolvere rapidamente i problemi.

 * **Sprint Review**: Al termine di ogni Sprint, il team presenta il lavoro completato agli stakeholder (Product Owner, eventuali sponsor, clienti e utenti finali). Questa riunione fornisce un'opportunità per raccogliere feedback e adattare il prodotto alle esigenze degli utenti.

 * **Sprint Retrospective**: Dopo la fase di review, il team tiene una riunione retrospettiva per riflettere sull'andamento dello Sprint appena concluso. Durante questa riunione, il team identifica ciò che è andato bene, ciò che potrebbe essere migliorato e pianifica azioni per migliorare gli Sprint futuri.

Il professore ha interpretato il ruolo di **Product Owner**. Per ogni Sprint ci sono stati comunicati, in aula e attraverso la piattaforma *[Microsoft Teams](https://www.microsoft.com/it-it/microsoft-teams/log-in)*, i requisiti sotto forma di *Definition of Done* e *User Story*; successivamente il team ha provveduto a realizzare una Board per la gestione dello Sprint.

![ProductOwner](./img/Teams.png)

La Board è stata gestita tramite la sezione Project messa a disposizione da GitHub. L'organizzazione della board è stata impostata durante lo Sprint 0 dal professore, e per gli Sprint successivi il team ha deciso di mantenere lo stesso modello in quanto ottimale per l'organizzazione del lavoro.

La Board adottata segue uno stile Kanban con 5 sezioni:

 * **To Do**: Task da risolvere

 * **In Progress**: Task presa in carico e in via di completamento

 * **Review**: Task completata, ma in attesa di revisione

 * **Ready**: Task risolta e revisionata dai reviewers

 * **Done**: Task risolta, revisionata e approvata dal Product Owner

 

A ogni Sprint è stato dedicato un **Milestone** e una **Project Board** dove sono state inserite le issue da svolgere per lo Sprint corrente. In questo modo è stato più facile tenere traccia dei progressi fatti dal team grazie alla divisione efficiente della board.

Per la gestione del codice sorgente e la risoluzione delle issue, abbiamo adottato il *[GitHub Flow](https://docs.github.com/en/get-started/using-github/github-flow)*, ovvero un workflow semplice ed efficace per il versionamento del codice. Il flow segue diversi passaggi che sono stati effettuati tramite il terminale di *[GitBash](https://www.git-scm.com/downloads)*:

 * **Creazione di un Branch**: Per prima cosa bisogna creare un nuovo branch tramite comando  
 `git branch nomebranch`. Il nome del branch corrisponde al nome della issue che viene rappresentata come `issue#n` con n il numero della issue. E' utile creare un branch perchè crea una linea di sviluppo indipendente che serve per lavorare su modifiche ed estensioni.
 
   Al momento della creazione del branch si ci trova ancora sul branch main, quindi bisogna spostarsi su quello appena creato tramite comando `git checkout nomebranch`.

* **Modifiche** : Una volta creato il branch su cui lavorare è possibile apportare le modifiche al file preso in considerazione durante la issue.

* **Eseguire i commit**: Una volta effettuate le modifiche bisogna scrivere dei commit per commentare ciò che è stato modificato.
Prima di effettuare il commit bisogna digitare il comando `git add .` per apportare le modifiche. 
Per eseguire i commit bisogna digitare il comando  
`git commit -m "Descrizione commit"`.

* **Aprire Pull Request**: Una volta effettuati i commit bisogna inviare i file su GitHub tramite comando   
`git push origin nomebranch`.
Per aprire una Pull Request bisogna recarsi su GitHub nel proprio repository e cliccare il tasto `compare & Pull Request` che appare nella pagina principale, e in questo caso si potrà richiedere la review da parte di altri componenti del gruppo.

* **Effettuare il Merge**: Una volta creata la Pull Request bisogna recarsi nella sezione Pull Request di GitHub in modo da poter vedere i commenti effettuati dai reviewers. Se la revisione è andata a buon fine, è possibile effettuare il merge cliccando il tasto `Merge Pull Request`.

* **Eliminare il branch**: Per mantenere la sezione pulita, è buona norma eliminare il branch creato. Per eliminare il branch bisogna cliccare il tasto `delete branch` che appare su GitHub una volta effettuato il merge.  
Successivamente bisogna eliminare il branch in locale tramite comando  
`git branch -d nomebranch`, però prima è buona norma digitare il comando `git pull` per scaricare in locale tutte le modifiche apportate al repository.
Prima di eliminare il branch in locale bisogna spostarsi sul main branch tramite il comando `git checkout main`.
### **8.2 RoadMap degli Sprint**

| Sprint No.  | Descrizione                                               |Data Inizio |Data Fine |Data Feedback|
|-------------|---------------------------------------------------------- |------------|----------|-------------|
| 0           |Dimostrare familiarità con GitHub e il processo agile      |31/03/2024  |12/04/2024|18/04/2024   | 
| 1           |Implementare piccoli comandi                               |06/05/2024  |21/05/2024|28/05/2024   |
| 2           |Completare il gioco, assicurando la qualità del software   |29/05/2024  |12/06/2024|21/06/2024   |



### **8.3 Gestione del Lavoro**
Dato l'utilizzo del framework Scrum, gli Sprint si compongono di quattro fasi: Analisi, Progettazione, Implementazione e Testing.

In generale, dovendo risolvere per la maggior parte delle volte 7 issue principali, abbiamo deciso di assegnare 1 issue ad ogni componente del gruppo. Nel caso di issue aggiuntive, i componenti del team hanno avuto la facoltà di assegnarsi l'issue.



<div style="font-size: 19px;">

#### **8.3.1 Sprint 0**
</div>

<div style="font-size: 15px;">

Lo Sprint Goal era quello di dimostrare familiarità con Git, GitHub e il processo Agile di sviluppo.

* **Analisi**: Per questa fase si è deciso di incontrarsi in un Meeting sulla piattaforma Discord per circa 1 ora, al fine di assegnare i task e stabilire le modalità di lavoro.
In particolare si è deciso di effettuare una issue in condivisione schermo in modo che tutti imparassero a lavorare in modo autonomo con i tool messi a disposizione. Questa scelta si è rivelata efficiente a lungo termine, dato che non sono sorte problematiche da questo punto di vista.

* **Progettazione**: In questo Sprint le task riguardavano principalmente la stesura di documenti in formato MarkDown e il settaggio di alcune impostazioni su GitHub, dunque la fase di progettazione non è stata inclusa.

* **Implementazione**: Questa fase ha previsto la modifica del file [README.md](../README.md); la modifica del file [Guida per lo studente.md](Guida%20per%20lo%20studente.md); la creazione e stesura del file [ispiratore.md](ISPIRATORE.md) in cui è stata inclusa la biografia di chi ha ispirato il nome del gruppo; e la stesura del file [Code Of Conduct.md](CODE_OF_CONDUCT.md) dove sono state definite le regole interne del team.

* **Testing**: Questa fase ha previsto la revisione dal punto di vista ortografico e contenutistico dei file sopra citati, e anche l'eliminazione di eventuali immagini non utilizzate.
</div>

<div style="font-size: 19px;">

#### **8.3.2 Sprint 1**
</div>

<div style="font-size: 15px;">

Lo Sprint Goal era quello di implementare piccoli comandi.

* **Analisi**: Per questa fase si è deciso di incontrarsi in un Meeting sulla piattaforma Discord per circa 1 ora, al fine di assegnare i task e stabilire le modalità di lavoro in modo che fossero distribuite in modo equo tra tutti i componenti del team.

* **Progettazione**: In questa fase ogni elemento del team ha implementato il proprio comando all'interno del codice del gioco.
E' stato sviluppato il [modello di dominio](#2-modello-di-dominio) e questo ha richiesto una continua comunicazione tra i membri del team dato che il modello rappresenta una visione d'insieme del progetto.

* **Implementazione**: Questa fase ha previsto l'implementazione del file [Report.md](#1-introduzione) che ha il compito di spiegare all'utente finale come utilizzare il software.

* **Testing**: Questa fase è stata effettuata individualmente da ogni membro del team prima di effettuare la push del codice, avviando l'applicazione e testando tutti i possibili input.
Sono stati sfruttati anche gli elementi messi a disposizione dalle Pull Request di GitHub: questi si sono rilevati di particolare importanza per individuare eventuali errori di SpotBugs.
</div>

<div style="font-size: 19px;">

#### **8.3.3 Sprint 2**
</div>

Lo Sprint Goal era quello di completare il gioco, assicurando la qualità del software.

* **Analisi**: Per questa fase si è deciso di incontrarsi in un Meeting sulla piattaforma Discord per circa 1 ora, al fine di assegnare i task e stabilire le modalità di lavoro in modo che fossero distribuite in modo equo tra tutti i componenti del team.

* **Progettazione**: In questa fase ogni componente del gruppo ha implementato il comando o la funzionalità che gli era stata assegnata. Inoltre sono stati creati i diagrammi delle classi e di sequenza e per i principi di OO Design.

* **Implementazione**: Questa fase ha previsto l'implementazione di nuove sezioni all'interno del file [Report.md](#1-introduzione) che avevano lo scopo di spiegare le fasi di processo e di sviluppo del software. 

* **Testing**: Per questa fase sono stati effettuati dei test qualitativi sul codice mediante l'utilizzo di JUnit.

### **8.4 Software Utilizzati**
Per lo sviluppo del software sono stati utilizzati diversi programmi

 * **Organizzazione del lavoro e Review**

   - **Microsoft Teams**: per visulizzare i compiti definiti per ogni Sprint

   - **Discord**: per le riunioni interne del team

   - **WhatsApp**: per la comunicazione tra componenti del team.

* **Ambienti di Sviluppo**

  - **Visual Studio Code**: IDE utilizzato per lo sviluppo del codice in Java e anche come text editor per il MarkDown.

     - **Plugin Aggiuntivi**

       - *CheckStyle*: per controllare eventuali problemi di CheckStyle evitando di eseguire sempre il comando `./gradlew build`

       - *Gradle*: utilizzato per l'esecuzione del programma

* **Software per la creazione di diagrammi e tabelle**

  - **StarUML**: per la creazione di diagrammi UML

  - **Miro**: per la creazione della tabella per lo Sprint Retrospective


### **8.5 Comunicazione nel Team**
La comunicazione e le riunioni di retrospettiva sono state effettuate su un server Discord creato appositamente.

![Discord](./img/Discord.png)

![WhatsApp](./img/WhatsApp.png)

Per le riunioni in modalità sincrona abbiamo utilizzato l'aula studio messa a disposizione dal Dipartimento Di Informatica.

![Gruppo](./img/Gruppo.png)

### **8.6 Problemi Riscontrati**
Durante l'esecuzione degli Sprint, il componente del gruppo Bracciolioni Alberto, ha effettuato tutte le issue a lui commisionate complete di commit, ma non risulta nella sezione Insights, più precisamente nella sezione Contributors, presente sulla piattaforma di GitHub.



## **9. Analisi Retrospettiva**
In questa sezione verranno trasmesse le analisi retrospettive fatte dal team durante lo *Sprint Feedback*.
L'obiettivo principale di queste analisi è identificare cosa ha funzionato bene durante il periodo precedente, cosa non ha funzionato e quali miglioramenti possono essere apportati per i futuri Sprint.

<div style="font-size: 16px;">

* ### **9.1 Sprint 0**
</div>

  <div style="margin-left: 20px;">
  Lo Sprint 0 aveva lo scopo di dimostrare familiarità con GitHub e il processo agile. Di seguito viene riportata la tabella con gli stati emotivi provati dal team(arrabbiato, triste e felice) durante il lavoro, e le azioni che li hanno scatenati.

  </div>

   <div style="margin-left: 20px;">

   ![SprintRetrospective0](./img/SprintRetrospective.png)
   </div>

   <div style="font-size: 16px;">

* ### **9.2 Sprint 1**
</div>

<div style="margin-left: 20px;">  
Lo Sprint 1 aveva lo scopo di implementare piccoli comandi di gioco.
Di seguito viene riportata la tabella con gli stati emotivi provati dal team(arrabbiato, triste e felice) durante il lavoro, e le azioni che li hanno scatenati.

![SprintRetrospective1](./img/SprintRetrospective1.png)

</div>






  







