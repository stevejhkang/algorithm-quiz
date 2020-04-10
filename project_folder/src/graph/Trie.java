package graph;

public class Trie {
	// [ 변수 ]
	// 루트 노드
	private TrieNode rootNode;

	// [ 생성자 ]
	Trie() {
		rootNode = new TrieNode();
	}

	// [ 메서드 ]
	// 자식 노드 추가
	void insert(String word) {
		TrieNode thisNode = this.rootNode;
		for (int i = 0; i < word.length(); i++) {
			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			// 없으면 새로 word.charAt(i)키를 갖는 map를 만들어주고 그것을 thisNode에 넣어준다.
		}
		thisNode.setIsLastChar(true); // 마지막 노드는 true로 표시해준다.
	}

	// 특정 단어가 들어있는지 확인
	boolean contains(String word) {
		TrieNode thisNode = this.rootNode;
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			TrieNode node = thisNode.getChildNodes().get(character);

			if (node == null) // 검색이 안되면 false
				return false;
			thisNode = node;
		}
		return thisNode.isLastChar(); // 마지막 노드가 last이면 true리턴
	}

	// 특정 단어 지우기
	void delete(String word) {
		delete(this.rootNode, word, 0); // 최초로 delete 던지는 부분
	}

	private void delete(TrieNode thisNode, String word, int index) {
		char character = word.charAt(index);
		TrieNode childNode = thisNode.getChildNodes().get(character);
		index++;
		if (index == word.length()) { //마지막까지 도달 했을 때
			if (!childNode.getChildNodes().containsKey(character)) //마지막이 없을 경우
				throw new Error("There is no [" + word + "] in this Trie.");
			if (!childNode.isLastChar()) //마지막이 아닐 경우
				throw new Error("There is no [" + word + "] in this Trie.");
			childNode.setIsLastChar(false);
			if (childNode.getChildNodes().isEmpty()) //마지막일 경우 
				thisNode.getChildNodes().remove(character); //삭제
		} else {
			delete(childNode, word, index); // 콜백함수부분
			if (!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
				thisNode.getChildNodes().remove(character);
		}
	}

	boolean isRootEmpty() {
		return this.rootNode.getChildNodes().isEmpty();
	}

}
