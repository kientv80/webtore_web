package com.xyz.hayhay.website.collector;

public class TranslatedWord {
	private String word;
	private String meaning;
	public TranslatedWord(String word, String meaning) {
		super();
		this.setWord(word);
		this.setMeaning(meaning);
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	
}
