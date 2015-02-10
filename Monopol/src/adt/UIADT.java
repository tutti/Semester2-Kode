package adt;

public interface UIADT {
	
	public static final int TERNING_KAST = 0; // Terningene kastes
	public static final int BANK_PENGER_ENDRET = 1; // Spillers penger går ned eller opp
	public static final int SPILLER_TUR_START = 2; // Spillers tur starter
	public static final int SPILLER_KONKURS = 3; // Spiller er konkurs
	public static final int SPILLER_FENGSEL = 4; // Spiller settes i fengsel
	public static final int SPILLER_MOTTA_EIENDOM = 5; // Spiller mottar en eiendom
	public static final int SPILLER_FJERN_EIENDOM = 6; // Spiller mister en eiendom
	public static final int SPILLER_PLASSERE = 7; // Spiller blir plassert på en rute
	public static final int EIENDOM_PANTSETTE = 8;
	public static final int EIENDOM_UTLØSE = 9;
	public static final int EIENDOM_KJØPE_HUS = 10;
	public static final int EIENDOM_SELGE_HUS = 11;
	public static final int LYKKEKORT = 12;
	
	public void hendelse(int hendelse_id, Object ... data);
	
}
