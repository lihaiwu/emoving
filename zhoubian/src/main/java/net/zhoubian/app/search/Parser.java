package net.zhoubian.app.search;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;

public abstract class Parser {

	/**
	 * 索引、搜索所用的分词解析器，默认为支持中文分词的“庖丁解牛”分析器
	 * @return
	 */
	protected Analyzer getAnalyzer(){
		return new PaodingAnalyzer();
	}
}
