package it.polito.tdp.librettovoti.model;

import java.time.LocalDate;
import java.util.List;

public class TestLibretto {
	
	public static void main(String[] args) {

		System.out.println("*** Test Libretto ***");
		Libretto libretto=new Libretto();
		
		Voto voto1=new Voto("Analisi 1", 30, LocalDate.of(2019,  2,  15));
		
		libretto.add(voto1);
		libretto.add(new Voto("Fisica 1", 28, LocalDate.of(2019,  7, 15)));
		libretto.add(new Voto("Informatica", 24, LocalDate.of(2019,  9,  15)));
		libretto.add(new Voto("Chimica", 25, LocalDate.of(2019,  7,  18)));

		System.out.println(libretto);
		
		List<Voto> venticinque=libretto.listaVotiUguali(25);
		System.out.println(venticinque);
		
		Libretto librettoventicinque=libretto.votiUguali(25);
		System.out.println(librettoventicinque);
		
		Voto analisi=libretto.ricercaCorso("Analisi 1");
		System.out.println(analisi);
		Voto analisi3=libretto.ricercaCorso("Analisi 3");
		System.out.println(analisi3);
		
		Voto chimica=libretto.ricercaCorso("Chimica");
		Voto chimica_doppio=new Voto("Chimica", 25, LocalDate.of(2019,  7,  18));
		Voto chimica_conflitto=new Voto("Chimica", 30, LocalDate.of(2019,  7,  18));
		System.out.println(chimica + " doppione di "+chimica_conflitto+"? -> "+libretto.esisteDuplicato(chimica_doppio));
		System.out.println(chimica + " conflitto con "+chimica_conflitto+"? -> "+libretto.esisteConflitto(chimica_doppio));
		System.out.println(chimica + " doppione di "+chimica_conflitto+"? -> "+libretto.esisteDuplicato(chimica_conflitto));
		System.out.println(chimica + " conflitto con "+chimica_conflitto+"? -> "+libretto.esisteConflitto(chimica_conflitto));

		libretto.add(chimica_doppio);
		libretto.add(chimica_conflitto);
		System.out.println("\n\n Stampa libretto dopo aggiunta voto doppio e in conflitto\n"+libretto);
		
		Libretto librettoMigliorato=new Libretto();
		librettoMigliorato.addMigliorato(voto1);
		librettoMigliorato.addMigliorato(new Voto("Fisica 1", 28, LocalDate.of(2019,  7, 15)));
		librettoMigliorato.addMigliorato(new Voto("Informatica", 24, LocalDate.of(2019,  9,  15)));
		librettoMigliorato.addMigliorato(new Voto("Chimica", 25, LocalDate.of(2019,  7,  18)));

		
		System.out.println("\n\n***Libretto***\n"+libretto);
		System.out.println("\n***Libretto Migliorato\n"+librettoMigliorato);
		
		System.out.println("\n***Libretto Ordine Alfabetico***\n"+libretto.toStringAlfabetico());
		System.out.println("\n***Libretto Ordine Voti Decrescenti***\n"+libretto.toStringVoto());
		
		libretto.cancellaEsami(26);
		System.out.println("\n\n***Libretto (cancellati voti minori di 26***\n"+libretto);

	}
}
