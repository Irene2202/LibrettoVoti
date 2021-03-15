package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List;

public class Libretto {
	private List<Voto> voti;
	
	public Libretto() {
		this.voti=new ArrayList<Voto>();
	}
	
	public void add(Voto v) {
		this.voti.add(v);
	}
	
	/* blah
	public String votiUguali (int punteggio) {
		//calcola stringa contenente i voti 
		//sarà poi il chiamante a stamparli
		//->solo nome?
		//->tutto il voto?
	}
	*/
	
	public List<Voto> listaVotiUguali2(int punteggio){
		//restituisco lista di voti uguali al criterio
		//difetto: espone all'esterno il tipo di struttura dati usato internamente
		//se cambio poi struttura interna devo camiare tutti questi metodi
		List<Voto> risultato=new ArrayList<Voto>();
		for(Voto v:this.voti) {
			if(v.getVoto()==punteggio) {
				risultato.add(v);
			}
		}
		return risultato;
	}
	
	
	public Libretto votiUguali(int punteggio) {
		Libretto risultato=new Libretto();
		for(Voto v:this.voti) {
			if(v.getVoto()==punteggio) {
				risultato.add(v);
			}
		}
		return risultato;
	}
	
	/**
	 * ricerca un Voto del corso di cuii è specificato il nome
	 * e se il corso non esiste restituisce null
	 * @param nomeCorso
	 * @return
	 */
	public Voto ricercaCorso(String nomeCorso) {
		Voto risultato=null;
		for(Voto v:this.voti) {
			if(v.getNome().equals(nomeCorso)) {
				risultato=v;
				break;
			}
		}
		return risultato;
	}
	
	
	public String toString() {
		String s="";
		for(Voto v:this.voti) {
			s=s+v.toString()+"\n";
		}
		return s;
	}

}
