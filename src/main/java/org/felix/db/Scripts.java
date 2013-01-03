package org.felix.db;
 
import java.util.Map;
import java.util.Map.Entry;

import org.felix.io.FileIO;

public class Scripts
{
	public static void genScripts() throws Exception
	{
		String dirPath = "D:\\Dropbox\\coding\\Second_Life\\Scripts\\T-Shirts\\";
		String template = dirPath + "t-shirt-template.lsl";
		String textures = dirPath + "t-shirts-textures.txt";
		String scripts = FileIO.readAsString(template);
		Map<String, String> texts = FileIO.readAsMap(textures, ",");

		for (Entry<String, String> entry : texts.entrySet())
		{
			String id = entry.getKey();
			String texture = entry.getValue();

			String newScript = scripts.replace("t-shirt-id", id);
			newScript = newScript.replace("t-shirt-texture", texture);

			FileIO.writeString(dirPath + "\\Prims\\" + id + ".lsl", newScript);
		}
	}

	public static void main(String[] args) throws Exception
	{
		Scripts.genScripts();
	}
}
