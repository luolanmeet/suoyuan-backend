package com.sy.word.dictionary;

import com.sy.word.object.TrieNode;

import lombok.extern.slf4j.Slf4j;

/**
 * 前缀树
 *
 * @author cck
 */
@Slf4j
public class DictionaryTrie {

	// 词表的首字母数量在一个可控范围内，默认值为24000
	private static final int INDEX_LENGTH = 24000;
	private final TrieNode[] ROOT_NODES_INDEX = new TrieNode[INDEX_LENGTH];
	private int maxLength;

	public void add(String item) {
		item = item.trim();

		int len = item.length();
		if (len < 1) {
			return;
		}
		if (len > maxLength) {
			maxLength = len;
		}

		// 从根节点开始添加
		// 获取根节点
		TrieNode node = getRootNode(item.charAt(0));
		for (int i = 1; i < len; i++) {
			char character = item.charAt(i);
			TrieNode child = node.getChildIfNotExistThenCreate(character);
			// 改变顶级节点
			node = child;
		}
		// 设置终结字符，表示从根节点遍历到此是一个合法的词
		node.setTerminal(true);
	}

	/**
	 * 获取根节点，如果不存在则创建一个
	 *
	 * @param charAt
	 * @return
	 */
	private TrieNode getRootNode(char character) {

		// 计算节点的存储索引
		int index = character % INDEX_LENGTH;
		TrieNode trieNode = ROOT_NODES_INDEX[index];

		while (trieNode != null && character != trieNode.getCharacter()) {
			// 如果节点已经存在，则设置为兄弟节点，之后需要链式查找
			trieNode = trieNode.getSibling();
		}

		if (trieNode == null) {
			trieNode = new TrieNode(character);
			addRootNode(trieNode);
		}

		return trieNode;
	}

	/**
	 * 增加一个根节点
	 *
	 * @param rootNode
	 */
	private void addRootNode(TrieNode rootNode) {
		// 计算节点的存储索引
		int index = rootNode.getCharacter() % INDEX_LENGTH;
		// 检查索引是否存在
		TrieNode existTrieNode = ROOT_NODES_INDEX[index];
		if (existTrieNode != null) {
			// 有冲突，将冲突节点附加到当前节点之后
			rootNode.setSibling(existTrieNode);
		}

		// 新增的节点总是在最前
		ROOT_NODES_INDEX[index] = rootNode;
	}

	public boolean contains(String item) {

		return contains(item, 0, item.length());
	}

	public boolean contains(String item, int start, int length) {

		if (start < 0 || length < 1) {
			return false;
		}
		if (item == null || item.length() < length) {
			return false;
		}
		log.debug("开始查词典：" + item.substring(start, start + length));
		// 从根节点开始查找
		// 获取根节点
		TrieNode node = getRootNode(item.charAt(start));
		if (node == null) {
			// 不存在根节点，结束查找
			return false;
		}
		// 存在根节点，继续查找
		for (int i = 1; i < length; i++) {
			char character = item.charAt(i + start);
			TrieNode child = node.getChild(character);
			if (child == null) {
				// 未找到匹配节点
				return false;
			} else {
				// 找到节点，继续往下找
				node = child;
			}
		}
		if (node.isTerminal()) {
			log.debug("在词典中查到词：" + item.substring(start, start + length));
			return true;
		}
		return false;
	}

	public void show() {
		for (TrieNode node : ROOT_NODES_INDEX) {
			if (node != null) {
				show(node, "");
			}
		}
	}

	private void show(TrieNode node, String indent) {
		if (node.isTerminal()) {
			log.info(indent + node.getCharacter() + "(T)");
		} else {
			log.info(indent + node.getCharacter());
		}
		for (TrieNode item : node.getChildren()) {
			show(item, indent + "\t");
		}
	}

	public static void main(String[] args) {
		DictionaryTrie trie = new DictionaryTrie();
		trie.add("广东");
		trie.add("广阔");
		trie.add("广东人");
		trie.add("广东省");
		trie.add("广东省潮州市");
		trie.add("广阔天地");
		trie.show();
		log.info("{}", trie.contains("广东"));
		log.info("{}", trie.contains("广东1"));
	}

}
