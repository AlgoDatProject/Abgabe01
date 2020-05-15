package ab2;

public interface Ab2 {
	/**
	 * Konstruiert aus dem übergebenen Array einen Max-Heap. Nach dem
	 * Aufruf soll im übergebenen Array die Heap-Bedingung H(1,n) erfüllt
	 * sein, wobei n die Länge des Arrays ist.
	 *
	 * @param data das Array, das in einen Max-Heap umgewandelt werden soll
	 */
	public void toHeap(int[] data);

	/**
	 * Sucht das k-größte Element in einer unsortierten Liste mittels des
	 * Median-of-Medians Algorithmus, und liefert dieses zurück. Achtung:
	 * z.B. befindet sich das dritt-größte Element (k = 3) in einem
	 * sortierten Array an der Stelle 2! Sie können davon ausgehen, dass
	 * für alle Inputs gilt: 0 < k < n+1, wobei n die Anzahl der Elemente
	 * im Input-Arrays ist.
	 *
	 * @param data ein (unsortiertes) Array
	 * @param k der Wert k
	 * @return den k-größten Wert im Array
	 */
	public int quickselect(int[] data, int k);

	/**
	 * Fügt ein Element in ein Array ein, welches eine Menge an
	 * Integer-Werten in Form einer Hashtabelle repräsentiert. Leere
	 * Stellen im Hashtabellen-Array sind mit dem Integer-Wert '-1'
	 * gefüllt. Zum Einfügen soll f(k) = k mod n als Hashfunktion verwendet
	 * werden, wobei n gleich der Länge des übergebenen Arrays ist. Bei
	 * Kollisionen soll lineares Sondieren angewendet werden. Sie können
	 * davon ausgehen, nur Arrays übergeben zu bekommen, in denen alle
	 * Werte -1 sind, oder in die Elemente mittels dieser Methode eingefügt
	 * wurden.
	 *
	 * @param hashtable eine Hashtabelle in Form eines Arrays
	 * @param element das einzufügende Element (sicher größer als -1)
	 */
	public void insertIntoHashSet(int[] hashtable, int element);

	/**
	 * Überprüft, ob ein Element in einem Hashtable-Array vorkommt. Leere
	 * Stellen im Array sind mit dem Integer-Wert '-1' gefüllt. Zum Suchen
	 * soll f(k) = k mod n als Hashfunktion verwendet werden, wobei n
	 * gleich der Länge des übergebenen Arrays ist. Bei Kollisionen soll
	 * lineares Sondieren angewendet werden. Sie können davon ausgehen, nur
	 * Arrays übergeben zu bekommen, in die Elemente mittels der
	 * insertIntoHashSet-Methode eingefügt wurden.
	 *
	 * @param hashtable eine Hashtabelle in Form eines Arrays
	 * @param element das zu suchende Element (sicher größer als -1)
	 * @return true falls gefunden, sonst false
	 */
	public boolean containedInHashSet(int[] hashtable, int element);
}
