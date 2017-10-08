package com.xyz.hayhay.website.collector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.collections.map.HashedMap;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;

import com.xyz.hayhay.db.JDBCConnection;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class TranslateService {
	private static TranslateService instance;
	Map<String, String> dictionary = new HashedMap();
	BlockingQueue<TranslatedWord> words = new ArrayBlockingQueue<>(100);

	public static TranslateService getInstance() {
		if (instance == null)
			instance = new TranslateService();
		return instance;
	}

	public String getTranslatedWord(String word) throws Exception {
		if (dictionary.get(word) != null) {
			return dictionary.get(word);
		} else {
			return translate(word);
		}
	}

	private TranslateService() {
		try {
			loadDictionary();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		new Thread() {
			public void run() {
				while (true) {
					try {
						TranslatedWord w = words.take();
						try (Connection con = JDBCConnection.getInstance().getConnection()) {
							try (PreparedStatement stm = con
									.prepareStatement("insert into dictionary(word,meaning)values(?,?)")) {
								stm.setString(1, w.getWord());
								stm.setString(2, w.getMeaning());
								stm.execute();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	private void loadDictionary() throws SQLException {
		try (Connection con = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = con.prepareStatement("select * from dictionary")) {
				try (ResultSet rs = stm.executeQuery()) {
					while (rs.next()) {
						dictionary.put(rs.getString("word"), rs.getString("meaning"));
					}
				}
			}
		}

	}

	private String translate(String word) throws Exception {
		StringBuilder result = new StringBuilder();// "Not found!"
		Source s = getSource("http://dict.laban.vn/find?type=1&query=" + word);
		List<Element> content = s.getAllElementsByClass("content");
		if (content != null && content.size() > 0) {
			for (Element e : content.get(0).getChildElements()) {
				if ("green bold margin25 m-top15".equals(e.getAttributeValue("class"))) {
					result.append(e.getTextExtractor().toString()).append("\n");
				}
			}
		}
		if (!result.toString().isEmpty()) {
			words.add(new TranslatedWord(word, result.toString()));
			dictionary.put(word, result.toString());
		} else {
			result.append("Not Found!");
		}
		return result.toString();
	}

	public static void main(String[] args) throws Exception {
		String result = TranslateService.getInstance().translate("green");
		System.out.println(result);
	}

	private static Source getSource(String url) throws Exception {
		Parser pa = new Parser(url);
		NodeIterator no = pa.elements();
		StringBuilder content = new StringBuilder();
		while (no.hasMoreNodes()) {
			content.append(no.nextNode().toHtml());
		}
		Source s = new Source(content);
		return s;
	}
}
