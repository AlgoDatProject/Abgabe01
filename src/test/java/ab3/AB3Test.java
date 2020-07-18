package ab3;

import ab3.impl.Oraze_Röttl_Miklau.AB3Impl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class AB3Test {
	private AB3 ab3 = new AB3Impl();
	private static int pts;

	@Test
	public void findPatternEndlAutomatBasic() {
		assertThat(ab3.findPatternEndlAutomat("aaaa", "bbbb"))
				.isEqualTo(Collections.emptyList());
		assertThat(ab3.findPatternEndlAutomat("aaaa", "aaaaa"))
				.isEqualTo(Collections.emptyList());

		assertThat(ab3.findPatternEndlAutomat("aaaa", "aaaa"))
				.isEqualTo(Collections.singletonList(0));
		assertThat(ab3.findPatternEndlAutomat("abcd", "abcd"))
				.isEqualTo(Collections.singletonList(0));

		assertThat(ab3.findPatternEndlAutomat("zzzaaaazzz", "aaaa"))
				.isEqualTo(Collections.singletonList(3));
		assertThat(ab3.findPatternEndlAutomat("zzzabcdzzz", "abcd"))
				.isEqualTo(Collections.singletonList(3));

		assertThat(ab3.findPatternEndlAutomat("zzzaaaa", "aaaa"))
				.isEqualTo(Collections.singletonList(3));
		assertThat(ab3.findPatternEndlAutomat("zzzabcd", "abcd"))
				.isEqualTo(Collections.singletonList(3));

		pts += 2;
	}

	@Test
	public void findPatternEndlAutomatExtended() {
		assertThat(ab3.findPatternEndlAutomat("aaaaa", "aaaa"))
				.isEqualTo(Arrays.asList(0, 1));
		assertThat(ab3.findPatternEndlAutomat("aaaaaa", "aaaa"))
				.isEqualTo(Arrays.asList(0, 1, 2));
		assertThat(ab3.findPatternEndlAutomat("abcdabcdabcd", "abcd"))
				.isEqualTo(Arrays.asList(0, 4, 8));
		assertThat(ab3.findPatternEndlAutomat("abcdabbcdabcd", "abcd"))
				.isEqualTo(Arrays.asList(0, 9));

		pts += 3;
	}

	@Test
	public void huffmanCoding1() {
		List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f');
		List<Integer> freqs = Arrays.asList(30, 25, 20, 10, 15, 5);

		HashMap<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < chars.size(); i++) {
			charMap.put(chars.get(i), freqs.get(i));
		}

		Set<AB3.SymbolCode> data = ab3.huffmanCoding(chars, freqs);
		assertThat(data.stream().mapToLong(c -> c.getEncoding().length() * charMap.get(c.getSymbol())).sum()).isEqualTo(255);

		checkPraefixfreiheit(data);

		pts += 1;
	}

	@Test
	public void huffmanCoding2() {
		List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f');
		List<Integer> freqs = Arrays.asList(30, 25, 20, 10, 0, 0);

		HashMap<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < chars.size(); i++) {
			charMap.put(chars.get(i), freqs.get(i));
		}

		Set<AB3.SymbolCode> data = ab3.huffmanCoding(chars, freqs);
		assertThat(data.stream().mapToLong(c -> c.getEncoding().length() * charMap.get(c.getSymbol())).sum())
				.isEqualTo(180);

		checkPraefixfreiheit(data);

		pts += 1;
	}

	@Test
	public void huffmanCoding3() {
		ArrayList<Character> chars = new ArrayList<>();
		ArrayList<Integer> freqs = new ArrayList<>();

		HashMap<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			char c = (char) i;
			chars.add(c);
			freqs.add(i);
			charMap.put(c, i);
		}

		Set<AB3.SymbolCode> data = ab3.huffmanCoding(chars, freqs);

		assertThat(data.stream().mapToLong(c -> c.getEncoding().length() * charMap.get(c.getSymbol())).sum())
				.isEqualTo(4851985L);

		checkPraefixfreiheit(data);

		pts += 1;
	}

	@Test
	public void huffmanCodingPerformance() {
		ArrayList<Character> chars = new ArrayList<>();
		ArrayList<Integer> freqs = new ArrayList<>();

		HashMap<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < 65535; i++) {
			char c = (char) i;
			chars.add(c);
			freqs.add(i);
			charMap.put(c, i);
		}

		// max 1 sec, Test auf Intel i7-7820HQ CPU @ 2.90GHz
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> ab3.huffmanCoding(chars, freqs));

		Set<AB3.SymbolCode> data = ab3.huffmanCoding(chars, freqs);

		assertThat(data.stream().mapToLong(c -> c.getEncoding().length() * charMap.get(c.getSymbol())).sum())
				.isEqualTo(33821245459L);

		pts += 2;
	}

	@Test
	public void findPatternKMP1() {

		AB3.SearchInfoKMP data = ab3.findPatternKMP("abcbababcabcbabcabcab", "abcabcabd");

		assertThat(data.getPositions())
				.isEqualTo(Collections.emptyList());
		assertThat(data.getJumps())
				.isEqualTo(Arrays.asList(3, 1, 2, 3, 3, 1));

		pts += 1;
	}

	@Test
	public void findPatternKMP2() {

		AB3.SearchInfoKMP data = ab3.findPatternKMP("abcbababcabcbabcabcabcab", "abcabcab");

		assertThat(data.getPositions())
				.isEqualTo(Arrays.asList(13, 16));
		assertThat(data.getJumps())
				.isEqualTo(Arrays.asList(3, 1, 2, 3, 3, 1, 3, 3));

		pts += 1;
	}

	@Test
	public void findPatternKMP3() {

		AB3.SearchInfoKMP data = ab3.findPatternKMP("abcbababcabcbabcabcabdcab", "abcabcabd");

		assertThat(data.getPositions())
				.isEqualTo(Collections.singletonList(13));
		assertThat(data.getJumps())
				.isEqualTo(Arrays.asList(3, 1, 2, 3, 3, 1, 9, 1));

		pts += 1;
	}

	@Test
	public void findPatternKMPPerformance() {
		String text = "a".repeat(1000000);

		// max 10 sec (sollte aber klar schneller sein), Test auf Intel i7-7820HQ CPU @ 2.90GHz
		assertTimeoutPreemptively(Duration.ofMillis(10000), () -> ab3.findPatternKMP(text, text));

		AB3.SearchInfoKMP data = ab3.findPatternKMP(text, text);

		assertThat(data.getPositions())
				.isEqualTo(Collections.singletonList(0));

		pts += 2;
	}

	private void checkPraefixfreiheit(Set<AB3.SymbolCode> data) {
		for (AB3.SymbolCode sc1 : data) {
			for (AB3.SymbolCode sc2 : data) {
				if (sc1 == sc2) {
					continue;
				}

				String code1 = sc1.getEncoding();
				String code2 = sc2.getEncoding();

				assertThat(code1).doesNotStartWith(code2);
				assertThat(code2).doesNotStartWith(code1);
			}
		}
	}

	@AfterAll
	public static void printPts() {
		System.out.println("Punkte: " + pts);
	}
}
