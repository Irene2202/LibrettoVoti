package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Libretto {
	private List<Voto> voti;
	private Map<String, Voto> votiMap; //identity Map: nome esame -> oggetto Voto
	
	public Libretto() {
		this.voti=new ArrayList<Voto>();
		this.votiMap=new HashMap<>();
	}
	
	public void add(Voto v) {
		if((this.esisteConflitto(v) || this.esisteDuplicato(v))==false){
			this.voti.add(v);
			this.votiMap.put(v.getNome(), v);
		}
	}
	
	public void addMigliorato(Voto v) {
		if((this.esisteConflitto(v) || this.esisteDuplicato(v))==false){
			if(v.getVoto()>=18 && v.getVoto()<24) {
				v.setVoto(v.getVoto()+1);
			}
			else if(v.getVoto()>=24 && v.getVoto()<29) {
				v.setVoto(v.getVoto()+2);
			}
			else {
				v.setVoto(30);
			}
			
			this.voti.add(v);
			this.votiMap.put(v.getNome(), v);
		}
		
	}
	
	/* blah
	public String votiUguali (int punteggio) {
		//calcola stringa contenente i voti 
		//sarà poi il chiamante a stamparli
		//->solo nome?
		//->tutto il voto?
	}
	*/
	
	public List<Voto> listaVotiUguali(int punteggio){
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
		/*Voto risultato=null;
		for(Voto v:this.voti) {
			if(v.getNome().equals(nomeCorso)) {
				risultato=v;
				break;
			}
		}
		return risultato;*/
		return this.votiMap.get(nomeCorso);
	}
	
	/**
	 * cancella tutti i voti dal libretto con valutazione inferiore 
	 * a quella passata come parametro
	 * @param punteggio
	 */
	public void cancellaEsami(int punteggio) {
		for(Voto v:this.voti) {
			if(v.getVoto()<punteggio) {
				voti.remove(v);
				votiMap.remove(v.getNome());
			}
		}
	}
	
	/**
	 * Verifica se nel libretto c'è già un voto con stesso esame e
	 * stessa valutazione
	 * @param v
	 * @return
	 */
	public boolean esisteDuplicato(Voto v) {
		/*boolean trovato=false;
		for(Voto voto:this.voti) {
			if(voto.getNome().equals(v.getNome()) && voto.getVoto()==v.getVoto()) {
				trovato=true;
				break;
			}
		}
		return trovato;*/
		Voto trovato=this.votiMap.get(v.getNome());
		if(trovato==null)
			return false;
		if(trovato.getVoto()==v.getVoto())
			return true;
		else
			return false;
	}
	
	/**
	 * verifica se nel libretto c'è già lo stesso esame MA 
	 * valutazione diversa
	 * @param v
	 * @return
	 */
	public boolean esisteConflitto(Voto v) {
		/*
		boolean trovato=false;
		for(Voto voto:this.voti) {
			if(voto.getNome().equals(v.getNome()) && voto.getVoto()!=v.getVoto()) {
				trovato=true;
				break;
			}
		}
		return trovato;*/
		Voto trovato=this.votiMap.get(v.getNome());
		if(trovato==null)
			return false;
		if(trovato.getVoto()!=v.getVoto())
			return true;
		else
			return false;
	}
	
	public String toString() {
		String s="";
		for(Voto v:this.voti) {
			s=s+v.toString()+"\n";
		}
		return s;
	}
	
	public String toStringAlfabetico() {
		List<Voto> votiAlf=new ArrayList<>();
		votiAlf.addAll(voti);
		Collections.sort(votiAlf, new OrdinaAlfabeticamente());
		
		String s="";
		for(Voto v:votiAlf) {
			s=s+v.toString()+"\n";
		}
		return s;
	}
	
	public String toStringVoto() {
		List<Voto> votiDec=new ArrayList<>();
		votiDec.addAll(voti);
		Collections.sort(votiDec, new OrdinaVotiDecrescenti());
		
		String s="";
		for(Voto v:votiDec) {
			s=s+v.toString()+"\n";
		}
		return s;
	}

}
