package no.hib.dat102;

public class LitenKøKlient {
	
	public static void main(String[] args) throws EmptyCollectionException {
		String streng = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sollicitudin eros eget gravida scelerisque. Fusce elit nulla, ornare eget justo et, dapibus efficitur nunc. Nullam venenatis pellentesque risus ac iaculis. Praesent fringilla justo eget nibh suscipit luctus. Donec maximus ex sed lacus aliquet maximus. In tempor, nisl quis interdum vestibulum, magna ligula efficitur orci, non suscipit dui metus eget est. Suspendisse scelerisque tortor nec scelerisque sodales. Duis luctus ante at ipsum porttitor, ut imperdiet augue rhoncus. Mauris suscipit commodo dignissim. Nulla ac aliquam nunc. Phasellus laoreet porttitor nisi a blandit. Sed tempus velit quis lectus elementum auctor. Donec non risus tincidunt, condimentum diam sed, dignissim dui. Integer interdum, erat rhoncus vestibulum venenatis, nunc justo auctor nisl, vel dapibus metus ligula et est. Etiam at massa imperdiet, vestibulum nibh sed, laoreet leo.";
		
		KøADT<Character> kø = new SirkulærKø<Character>(streng.length());
		
		for (int i=0; i<streng.length(); ++i) {
			kø.innKø(streng.charAt(i));
		}
		
		StringBuilder sb = new StringBuilder();
		while (!kø.erTom()) {
			sb.append(kø.utKø());
		}
		
		String utStreng = sb.toString();
		System.out.println(streng.equals(utStreng));
	}

}
