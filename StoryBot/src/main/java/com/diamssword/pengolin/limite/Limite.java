package com.diamssword.pengolin.limite;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Limite {

	public static List<String> getCards(String path)
	{
		List<String> res= new ArrayList<String>();
		InputStream str =Limite.class.getClassLoader().getResourceAsStream("com/diamssword/pengolin/assets/"+path);
		Scanner s =new Scanner(str,"utf-8");
		while(s.hasNextLine())
		{
			res.add(s.nextLine());
		}
		return res;
	}
	public static List<String> getWhiteCards()
	{
		return getCards("blanches.txt");
	}
	public static List<String> getBlackCards()
	{
		return getCards("noirs.txt");
	}
}
