# Tema 1 - POO Pană Eduard-Ștefan

## Timp de rezolvare:

A durat aproximativ 12 ore de lucru sa implementez cerintele cerute,
folosind cat de des se poate github pentru a mi sincroniza munca, si a fi sigur
ca in caz de corupere a fisierului in care lucrez, am o copie online pe care
o pot accesa in acest caz nefavorabil.

## Explicarea pe scurt a functionalitatii programului:
In program se creeaza un simulator de alegeri, care primeste input de la tastatura un numar si il foloseste pe acesta pentru a determina ce dorinta are cel care da input programului, ulterior cerandui anumite date pentru a putea rula respectivul task.


## Explicarea pe larg a implementarii:

Un flag pentru a putea face programul sa ruleze in bucla pana la primirea comenzii 18. Initializez un obiect de tip input, acesta fiind unul ce imi va prelua comenzile si creerea instantelor necesare. Tratez fiecare input posibil, si citesc variabilele necesare pentru respectivul input. Imi initializez un vector de alegeri pentru a putea incepe rularea programului.

StartAlegeri: functia testeaza statusul in care se afla alegerea respectiva si returneaza eroarea aferenta. Singurul moment in care pot sa pornesc alegerile.

CreareAlegeri: creez o alegere noua, prima data cautand daca mai exista alta cu acelasi id. In acest caz, nu o creez pe cea noua si returnez eroarea aferenta.

AdaugareCircumscriptie: la fel ca la alegeri, testez daca mai exista vreo circumscriptie cu acelasi id. In caz afirmativ, returnez eroare, altfel creez circumscriptia. Pe langa asta, testez ca alegerile sa fie in desfasurare, pentru ca altfel nu pot adauga alta circumscriptie.

EliminareCircumscriptie: caut in vectorul de alegeri si identific dupa id alegerea corecta. Daca alegerea este in desfasurare, caut circumscriptia cu pricina in vectorul de circumscriptii iar daca exista o elimin. In caz contrar, in oricare din cele enuntate anterior, returnez o eroare aferenta.

AdaugareCandidat: prima data trebuie sa testez daca datele introduse sunt corecte (CNP care sa respecte anumite criterii, sa aiba o anumita varsta). Ulterior, caut alegerile, circumscriptia, si verific sa nu mai fie niciun candidat care sa aiba acelasi CNP.

EliminareCandidat: la eliminarea candidatului, caut alegerea corecta, circumscriptia, perioada de votare sa fie una aferenta, iar apoi candidatul daca exista in lista de candidati. In acest caz, il elimin, in caz contrar returnez eroarea corespunzatoare.

AdaugareVotant: pentru adaugarea votantului, verific ca datele introduse sa indeplineasca anumite criterii (varsta minima, CNP de o anumita marime si cu caracteristicile specifice). Verific ca alegerea sa existe si sa fie in stadiul corect, circumscriptia sa existe, si ca votantul sa nu impartaseasca acelasi CNP cu alta persoana. In caz contrar, returnez eroarea caracteristica pentru problema intampinata.

PrintareCandidati: verific daca exista alegerea si starea ei, si apoi apelez functia printare candidati, ce va afisa candidatii, cu datele in ordinea precizata in enunt.

PrintareVotanti: pentru printarea votantilor, caut alegerea corecta, circumscriptia corecta, si apelez functia printareVotanti care imi va printa fiecare votant.

Votare: in momentul in care un votant este neindemanatic, votul lui nu va fi luat in considerare si va fi trecut direct la frauda.

Caut alegerea data ca argument. Verific statusul ei. Caut circumscriptia corecta. Verific daca inainte de a vota, votantul a fost inscris in circumscriptie.

Nu exista circumscriptia. Votantul nu este inregistrat. Apelam functia care salveaza datele votantului, si ii asteptam raspunsul.

Alegeri invalide. Candidat inexistent.

Un votant incearca sa voteze la alta circumscriptie. Votantul a fost descoperit in alta circumscriptie ca fiind inscris, si incearca sa voteze la una diferita. Il declaram ca frauda.

OprireAlegeri: verificam daca exista alegerile si au statusul corect, iar apoi le schimbam statusul.

RaportCircumscriptie: validez existenta alegerii, statusul corect, iar apoi afisez un raport pentru circumscriptia data. Apeleaz functia care imi va afisa si calcula numarul de voturi pentru fiecare candidat.

RaportNational: validez existenta alegerii, statusul corect, iar apoi afisez un raport pe tara. Fiecare candidat are un atribut de tip int numit numarVoturi, care se incrementeaza de fiecare data cand cineva voteaza acea persoana indiferent din ce circumscriptie, creeand un numar de voturi national.

Sortez candidatii dupa numarul de voturi, iar in caz de egalitate, dupa cnp in ordine (in ordine descrescatoare). Dupa sortare, creed un string auxiliar in care adaug fiecare candidat cu datele sale, pentru a le putea returna functiei main.

AnalizaCircumscriptie: dupa verificarea existentei alegerii, statusul ei si existenta circumscriptiei, apelez functia care imi va calcula numarul de voturi si imi va returna candidatul castigator.

AnalizaNationala: pentru fiecare regiune in parte, trec prin vectorul de circumscriptii, iar daca gasesc o circumscriptie intr-o regiune pe care inca nu am adaugat-o in vectorul de regiuni, initializez regiunea si copiez candidatii alaturi de numarul lor de voturi pe circumscriptia respectiva. In cazul in care dau de o regiune pe care o am deja in vectorul de regiuni, doar calculez numarul de voturi pentru fiecare candidat in circumscriptia respectiva, si adaug la numarul de voturi pe care le aveau respectivii candidati deja in regiunea respectiva. Incrementez numarul de voturi cu cel din noua circumscriptie gasita in aceeasi regiune.

NumarVoturiNationale: calculez suma voturilor nationale.

RaportFraude: pentru fiecare circumscriptie, afisez vectorul de fraude care s-au comis. Daca intampin o neregula, returnez eroarea aferenta. Incrementez numarul de voturi cu cel din noua circumscriptie gasita in aceeasi regiune.

EliminareAlegeri: caut daca alegerea exista in vectorul de alegeri, apoi o elimin, astfel eliminandu-se elementele acesteia.

ListareAlegeri: parcurg vectorul de alegeri si afisez fiecare alegere alaturi de datele pe care le are.

NumarVoturiRegiune: returneaza suma voturilor de pe o intreaga regiune.

Castigator: desemnez un castigator, ordonand vectorul descrescator, castigator revenind primul.

PrintareVotanti: afisez fiecare votant dintr-o circumscriptie.

AdaugareVot: functia care adauga votul unui votant si verifica date despre el. Daca se gaseste deja in lista, inseamna ca acesta a incercat sa comita o frauda.

AdaugareFrauda: se initializeaza un nou element de tip frauda, pe care il adaugam in lista.

Raport: pentru o anumita circumscriptie, creez un vector de candidati in care fiecare element are un atribut numit nrVoturi. Voi trece prin lista de voturi de la circumscriptia respectiva, si voi incrementa numarul voturilor unui candidat in momentul in care gasesc un vot pentru acesta, identificand votul cu candidatul dupa cnp.

RaportCircumscriptie: pentru a putea numara voturile pentru o anumita circumscriptie, creez o copie a vectorului de candidati, unde initializez numarul de voturi cu 0 si il incrementez de fiecare data cand dau de un vot pentru acel candidat. Dupa sortarea inversa dupa nr de voturi, primul candidat din lista va fi cel cu cele mai multe voturi, adica castigatorul.

NumarVoturiPeCircumscriptie: functia imi returneaza un vector in care fiecare candidat are nr de voturi egal cu cel pe care l-a obtinut in circumscriptia respectiva.

Pentru fiecare vot, pentru a-mi face ulterior cautarea mai usoara, ratin 2 cnp-uri pentru a identifica de unde vine, si catre cine merge votul.

AdaugareCircumscriptie: o functie in care adaug in lista de circumscriptii un element sau returnez eroarea corespunzatoare.

EliminareCandidat: stergerea unui candidat.

PrintareCandidati: printarea tuturor candidatilor ce participa la o anumita alegere. Sortez candidatii dupa cnp crescator.

IncNrVoturi: incrementeaza numarul de voturi ale unui candidat.

GetNrVoturi: returneaza numarul de voturi obtinut de un anumit candidat.