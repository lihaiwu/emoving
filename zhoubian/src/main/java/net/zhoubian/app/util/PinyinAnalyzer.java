package net.zhoubian.app.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinAnalyzer {

	/**
	 * 获取指定中文的拼音及拼音首字母缩写，
	 * @param chinese
	 * @return 两元素的字符数组，第一个元素为中文拼音，第二个元素为拼音的首字母组合
	 */
	public static String[] getPinyinAndAbstract(String chinese){
		char[] chars = chinese.toCharArray();
		String pinyin = "";
		String pinyinAbstract = "";
		try {
			for (char c : chars) {
				String[] result = PinyinHelper.toHanyuPinyinStringArray(c, getFormatter());
				pinyin += result[0];
				pinyinAbstract += result[0].substring(0, 1);
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return new String[]{pinyin, pinyinAbstract};
	}
	
	/**
	 * 获取给定中文的汉语拼音
	 * @param chinese
	 * @return
	 */
	public static String getPinyin(String chinese){
		char[] chars = chinese.toCharArray();
		String pinyin = "";
		try {
			for (char c : chars) {
				String[] result = PinyinHelper.toHanyuPinyinStringArray(c, getFormatter());
				pinyin += result[0];
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return pinyin;
	}

	private static HanyuPinyinOutputFormat getFormatter() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		return format;
	}
	
	public static void main(String[] args) {
		String chinese = "中华人民共和国";
		String[] result = getPinyinAndAbstract(chinese);
		for(String s : result)
			System.out.println(s);
	}
}
