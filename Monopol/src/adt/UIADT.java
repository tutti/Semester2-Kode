package adt;

public interface UIADT {
	
	// Fortell
	public static final int TERNING_KAST = 1; // Terningene kastes
	public static final int BANK_PENGER_ENDRET = 2; // Spillers penger går ned eller opp
	public static final int SPILLER_TUR_START = 3; // Spillers tur starter
	public static final int SPILLER_KONKURS = 4; // Spiller er konkurs
	public static final int SPILLER_FENGSEL = 5; // Spiller settes i fengsel
	public static final int SPILLER_MOTTA_EIENDOM = 6; // Spiller mottar en eiendom
	public static final int SPILLER_FJERN_EIENDOM = 7; // Spiller mister en eiendom
	public static final int SPILLER_PLASSERE = 8; // Spiller blir plassert på en rute
	public static final int EIENDOM_PANTSETTE = 9;
	public static final int EIENDOM_UTLØSE = 10;
	public static final int EIENDOM_KJØPE_HUS = 11;
	public static final int EIENDOM_SELGE_HUS = 12;
	public static final int LYKKEKORT = 13;
	
	// Spør
	public static final int SETT_OPP = -1; // Velg å legge til en spiller eller avslutte oppsett
	
	public void fortell(int hendelse_id, Object ... data);
	
	public Object spør(int spørsmål_id, Object ... data);
	
}
