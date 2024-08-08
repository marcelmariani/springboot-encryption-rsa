package com.marcelmariani.utils;

public class TextManagementUtils {

	/**
	 * Substitui espaços em uma string por %2B.
	 *
	 * @param input a string de entrada
	 * @return a string com espaços substituídos por %2B
	 */
	public static String replaceSpacesWithPlus(String input) {
		if (input == null) {
			return null;
		}
		return input.replace(" ", "+");
	}
}
