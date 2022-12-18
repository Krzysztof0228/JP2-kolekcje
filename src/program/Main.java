package program;

import java.util.*;

public class Main {

    public Set<Person> people = new HashSet<>();
    public Set<Person> people2 = new TreeSet<>();

    public List<Person> people3 = new ArrayList<>();
    public List<Person> people4 = new LinkedList<>();

    public Map<Integer, Person> people5 = new HashMap<>();
    public Map<Integer, Person> people6 = new TreeMap<>();

    Integer key5 = 0;
    Integer key6 = 0;

    private static final String MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Podaj dane nowej osoby \n" +
                    "2 - Usuń dane osoby        \n" +
                    "3 - Modyfikuj dane osoby   \n" +
                    "4 - Wczytaj dane z pliku   \n" +
                    "5 - Zapisz dane do pliku   \n" +
                    "6 - Wyświetlenie HashSet \n" +
                    "7 - Wyświetlenie TreeSet \n" +
                    "8 - Wyświetlenie ArrayList \n" +
                    "9 - Wyświetlenie LinkedList \n" +
                    "10 - Wyświetlenie HashMap \n" +
                    "11 - Wyświetlenie TreeMap \n" +
                    "12 - Usunięcie elementu z HashSet \n" +
                    "13 - Usunięcie elementu z TreeSet \n" +
                    "14 - Usunięcie elementu z ArrayList \n" +
                    "15 - Usunięcie elementu z LinkedList \n" +
                    "16 - Usunięcie elementu z HashMap \n" +
                    "17 - Usunięcie elementu z TreeMap \n" +
                    "0 - Zakończ program        \n";

    private static final String CHANGE_MENU =
            "   Co zmienić?     \n" +
                    "1 - Imię           \n" +
                    "2 - Nazwisko       \n" +
                    "3 - Rok urodzenia  \n" +
                    "4 - Stanowisko     \n" +
                    "0 - Powrót do menu głównego\n";

    private Person currentPerson = null;

    private static final ConsoleUserDialog UI = new ConsoleUserDialog();

    public void runMainLoop() {

        while (true) {
            UI.clearConsole();
            showCurrentPerson();

            try {
                switch (UI.enterInt(MENU + "==>> ")) {
                    case 1:
                        // utworzenie nowej osoby
                        currentPerson = createNewPerson();
                        break;
                    case 2:
                        // usunięcie danych aktualnej osoby.
                        currentPerson = null;
                        UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
                        people.remove(currentPerson);
                        break;
                    case 3:
                        // zmiana danych dla aktualnej osoby
                        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
                        changePersonData(currentPerson);
                        break;
                    case 4: {
                        // odczyt danych z pliku tekstowego.
                        String file_name = UI.enterString("Podaj nazwę pliku: ");
                        currentPerson = Person.readFromFile(file_name);
                        UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
                    }
                    break;
                    case 5: {
                        // zapis danych aktualnej osoby do pliku tekstowego
                        String file_name = UI.enterString("Podaj nazwę pliku: ");
                        Person.printToFile(file_name, currentPerson);
                        UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
                    }
                    break;
                    case 6: {
                        // wyświetlanie HashSet
                        for(var person : people)
                            System.out.println("| " + person.toString() + " |");
                    }
                    break;
                    case 7: {
                        // wyświetlanie TreeSet
                        for(var person : people2)
                            System.out.println("| " + person.toString() + " |");
                    }
                    break;
                    case 8: {
                        // wyświetlanie ArrayList
                        for(var person : people3)
                            System.out.println("| " + person.toString() + " |");
                    }
                    break;
                    case 9: {
                        // wyświetlenie LinkedList
                        for(var person : people4)
                            System.out.println("| " + person.toString() + " |");
                    }
                    break;
                    case 10: {
                        // wyświetlenie HashMap
                        for(var personKey : people5.keySet())
                            System.out.println("| " + personKey + " : " + people5.get(personKey) + " |");
                    }
                    break;
                    case 11: {
                        // wyświetlenie TreeMap
                        for(var personKey : people6.keySet())
                            System.out.println("| " + personKey + " : " + people6.get(personKey) + " |");
                    }
                    break;
                    case 12: {
                        // usunięcie elementu z HashSet
                        people.remove(currentPerson);
                    }
                    break;
                    case 13: {
                        // usunięcie elementu z TreeSet
                        people2.remove(currentPerson);
                    }
                    break;
                    case 14:{
                        // usunięcie elementu z ArrayList
                        int x;
                        System.out.println("Podaj indeks elementu do usunięcia");
                        Scanner scanner = new Scanner(System.in);
                        x = scanner.nextInt();
                        people3.remove(x);
                    }
                    break;
                    case 15:{
                        // usunięcie elementu z LinkedList
                        int x;
                        System.out.println("Podaj indeks elementu do usunięcia");
                        Scanner scanner = new Scanner(System.in);
                        x = scanner.nextInt();
                        people4.remove(x);
                    }
                    break;
                    case 16:{
                        // usunięcie elementu z HashMap
                        int x;
                        System.out.println("Podaj indeks elementu do usunięcia");
                        Scanner scanner = new Scanner(System.in);
                        x = scanner.nextInt();
                        people5.remove(x);
                    }
                    break;
                    case 17:{
                        // usunięcie elementu z HashMap
                        int x;
                        System.out.println("Podaj indeks elementu do usunięcia");
                        Scanner scanner = new Scanner(System.in);
                        x = scanner.nextInt();
                        people6.remove(x);
                    }
                    break;
                    case 0:
                        // zakończenie działania programu
                        UI.printInfoMessage("\nProgram zakończył działanie!");
                        System.exit(0);
                    default:
                        throw new IllegalStateException("Unexpected value: " + UI.enterInt(MENU + "==>> "));
                } // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }

    /*
     *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
     *  pamiętanej w zmiennej currentPerson.
     */
    void showCurrentPerson() {
        showPerson(currentPerson);
    }

    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    static void showPerson(Person person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append( "Brak danych osoby\n" );
        UI.printMessage( sb.toString() );
    }

    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    Person createNewPerson(){
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        Person person;
        try {
            // Utworzenie nowego obiektu klasy Person oraz
            // ustawienie wartości wszystkich atrybutów.
            person = new Person(first_name, last_name);
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) {
            // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
            // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
            // poszczególnych atrybutów.
            // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        key5++;
        key6++;
        people.add(person);
        //people2.add(person);
        people3.add(person);
        people4.add(person);
        people5.put(key5, person);
        people6.put(key6, person);
        return person;
    }

    static void changePersonData(Person person)
    {
        while (true) {
            UI.clearConsole();
            showPerson(person);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1:
                        person.setFirstName(UI.enterString("Podaj imię: "));
                        break;
                    case 2:
                        person.setLastName(UI.enterString("Podaj nazwisko: "));
                        break;
                    case 3:
                        person.setBirthYear(UI.enterString("Podaj rok ur.: "));
                        break;
                    case 4:
                        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
                        person.setJob(UI.enterString("Podaj stanowisko: "));
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {

        Main application = new Main();
        application.runMainLoop();
    }
}
