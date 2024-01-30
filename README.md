# Medical-Equipment-back-end

Projekat Internet Softverske Arhitekture 2023/2024 -ISA-Tim6
Dobro došli!
FTN Novi Sad - Primenjene računarske nauke i informatika

Članovi tima:
• Student 1: Ivana Kovačević RA 15/2020
• Student 2: Anja Dučić RA 5/200
• Student 3: Milica Vujić RA 8/2020
• Student 4: Anja Lovrić RA 13/2020

Tehnologije korišćene na projektu
Spring Boot
Angular
Rest
PostgreSQL

Git pravila
Naziv commit-a:

[feat/fix] + ": " + opis

Naziv grane:

[feat/fix] + "/" + OPIS

Baza - PostgreSQL
Server baze podataka radi na podrazumevanom portu 5432
Nakon pokretanja pgAdmin-a, potrebno je kreirati bazu podataka pod nazivom jpa
Korisničko ime : postgres, lozinka : super
Postoji data-postgres.sql skripta u direktorijumu resursa koja se automatski pokreće pri pokretanju Spring Boot projekat ( back-end )
Namešteno u podešavanju Spring Boot projekat ( application.properties - spring.jpa.hibernate.ddl-auto = create-drop )

Koraci prilikom pokretanja back-end dela projekta u Eclipse razvojnom okruženju
Uvezite MedicalEquipment projekat u radni prostor ( workspace: Import -> Maven -> Existing Maven Project )
Zatim instalirajte sve zavisnosti iz datoteke pom.xml
Na kraju, kliknite desnim tasterom miša na projekat -> Run as -> Java Application / Spring Boot app ( ako je instaliran STS dodatak sa Eclipse marketplace-a )
Back-end projekat će biti pokrenut na portu 81

Koraci prilikom pokretanja front-end dela projekta u okruženju Visual Studio Code
Otvorite svoj Visual Studio Code i uvezite MedicalEquipment projekat
Otvorite terminal u Visual Studio Code-u, a zatim otkucajte sledeće komande:
$ npm install
$ ng serve
Web aplikacija će biti pokrenut na portu 4200

Monitoring je podržan korišćenjem alata Prometheus i Grafana. Za pokretanje potrebno je u terminalu izvršiti docker-compose up.
Prometheus se pokreće na putanji http://localhost:9090

Grafana se pokreće na putanji http://localhost:3000

Proof of Concept fajl sadrži predlog arhitekture naše aplikaciju u slučaju da broj korisnika prevaziđe mogućnosti jednog servera.

Simulator je pomoćna aplikacija koja ima ulogu simulatora lokacije. Sa aplikacijom MedicalEquipment komunicira putem RabbitMQ-a.
Simulator2 ima ulogu bolnice i omogućava slanje ugovora između bolnice kao naručioca opreme i glavne aplikacije putem RabbitMQ-a.