package ab3;

import java.util.List;
import java.util.Set;

public interface AB3 {

    /**
     * Liefert das zu dem Text und dem Muster passende SearchInfo-Objekt. Dieses
     * Objekt enthält alle Startpositionen des Musters im Text als auch alle
     * angewendeten Sprungweiten, welche bei einem Mismatch bzw. vollständigem Match
     * im Rahmen der Suche getätigt wurden. Die Positionen fangen bei 0 an.
     * Gibt es keine Übereinstimmung, so wird eine leere Liste zurück gegeben.
     * Der KMP-Algorithmus ist zu verwenden und performant zu implementieren.
     */
    public SearchInfoKMP findPatternKMP(String text, String pattern);

    /**
     * Liefert alle Startpositionen des Musters im Text. Die Positionen fangen bei 0 an.
     * Gibt es keine Übereinstimmung, so wird eine leere Liste zurück gegeben.
     * Ein endlicher Automat ist zu verwenden und performant zu implementieren.
     * Definieren sie selbst alle Klassen (zB Knoten des Graphen).
     */
    public List<Integer> findPatternEndlAutomat(String text, String pattern);

    /**
     * Erzeugt anhand der Huffman-Codierung Codes für die übergebenen Symbole sowie
     * die dazugehörigen Wahrscheinlichkeiten. Die symbols- als auch frequency-Listen
     * sind gleich lang. Die Häufigkeit für ein Symbol steht an der entsprechende Stelle
     * der frequency-Liste.
     * Für jedes Symbol muss ein Binärcode erzeugt werden.
     * Die Codes sind Strings bestehend aus den Zeichen '0 und '1'.
     * Der Huffman-Algorithmus ist zu verwenden und performant zu implementieren.
     */
    public Set<SymbolCode> huffmanCoding(List<Character> symbols, List<Integer> frequency);

    /**
     * Suchinformation des KMP-Algorithmus
     */
    class SearchInfoKMP {
        private final List<Integer> positions;
        private final List<Integer> jumps;

        public SearchInfoKMP(List<Integer> positions, List<Integer> jumps) {
            this.positions = positions;
            this.jumps = jumps;
        }

        /**
         * Liefert die Startpositionen, an denen das Muster im Text gefunden wurde.
         */
        public List<Integer> getPositions() {
            return positions;
        }

        /**
         * Liefert die Sprungläng nach jedem Mismatch bzw. vollständigen Match.
         */
        public List<Integer> getJumps() {
            return jumps;
        }
    }

    /**
     * Symbolcodierung
     */
    class SymbolCode {
        public final char symbol;
        public final String encoding;

        public SymbolCode(char symbol, String encoding) {
            this.symbol = symbol;
            this.encoding = encoding;
        }

        /**
         * Liefert das codierte Symbol
         */
        public char getSymbol() {
            return symbol;
        }

        /**
         * Liefert die Codierung des Symbols (0-1 String)
         */
        public String getEncoding() {
            return encoding;
        }
    }
}
