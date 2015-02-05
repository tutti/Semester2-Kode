package main;

import ruter.EiendomRute;

public class MenneskeligSpiller extends Spiller {

	public MenneskeligSpiller(String navn) {
		super(navn);
	}

	@Override
	public boolean vilKjøpe(EiendomRute rute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int vilBy(EiendomRute rute, int forrigeBud) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void handelsFase() {
		// TODO Auto-generated method stub
		
	}

}
