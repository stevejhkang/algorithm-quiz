package graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestTrie {
	@Test
	public void trieTest() {
		Trie trie = new Trie();
// insert 메서드
		assertTrue(trie.isRootEmpty());
		trie.insert("PI");
		trie.insert("PIE");
		trie.insert("POW");
		trie.insert("POP");
		assertFalse(trie.isRootEmpty());
// Contains 메서드
		assertTrue(trie.contains("POW"));
		assertFalse(trie.contains("PIES"));
// Delete 메서드
		trie.delete("POP");
		assertFalse(trie.contains("POP"));
		assertTrue(trie.contains("POW"));
// 없는 단어를 지울 때 > 에러발생
		trie.delete("PIES");
	}
}
