package ab1;

public interface Ab1 {
    /**
     * Sucht das element in den daten und liefert den Index des gesuchten Elements zurück, andernfalls -1.
     *
     * @param data    die Daten
     * @param element das zu suchende Element
     * @return der Index des gesuchten Elements oder -1 falls das Element nicht gefunden wurde
     */
    public int binarySearch(int[] data, int element);

    /**
     * Verwendet den Shellsort-Algorithmus, um die Daten zu sortieren. Der Algorithmus muss In-Place arbeiten,
     * dh er darf maximal O(1) zusätzlichen Speicher verwenden (dh ein zweites Array anzulegen ist nicht erlaubt).
     *
     * @param data die zu sortierenden Daten
     */
    public void shellSort(Integer[] data);

    /**
     * Verwendet den Quicksort-Algorithmus, um die Daten zu sortieren. Der Algorithmus muss In-Place arbeiten,
     * dh er darf maximal O(1) zusätzlichen Speicher verwenden (dh ein zweites Array anzulegen ist nicht erlaubt).
     * Zudem muss der Algorithmus stabil sortieren.
     *
     * @param data die zu sortierenden Daten
     */
    public void quickSortStable(Integer[] data);

    /**
     * Multipliziert die zwei Matritzen m1 und m2 mithilfe des Strassen-Algorithmus. Beachten Sie, dass der
     * Strassen-Algorithmus rekursiv arbeitet und somit eine Zerlegung in sehr kleine Matritzen sehr viel Overhead
     * bedeutet und der Algorithmus dann langsam wird. Schalten Sie deshalb unter einer von Ihnen selbst gewählten
     * Dimension (dh Seitenlänge der Matrix) auf den Standardalgorithmus zum Multiplizieren um (dh ihr Algorithmus
     * arbeitet hybrid).
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Produkt von m1 und m2
     */
    public int[][] mult(int[][] m1, int[][] m2);
}
