package com.example.cgodawson.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tool {
	
	
	public synchronized static  List<String> getFiles(String path)
	{
		List<String> list = new ArrayList<String>();
		File root = new File(path);
		if(!root.exists())
		{
			return list;
		}
		File[] files = root.listFiles();
		if(files==null)
		{
			return list;
		}
		
		for(File file:files)
		{
			list.add(file.getPath());
		}
		
	    return list;	
	}

}
