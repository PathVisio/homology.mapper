package org.wp.homology;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bridgedb.bio.Organism;
import org.pathvisio.core.model.ConverterException;
import org.pathvisio.core.model.Pathway;

public class PathwayReader {

	public static Map<String, Pathway> readPathwaysOld(Organism organism, File inputDir) {
		Map<String, Pathway> pathways = new HashMap<String, Pathway>();		
		
		for(File f : inputDir.listFiles()) {
			if(f.getName().endsWith(".gpml")) {
				Pathway pathway = new Pathway();
				try {
					pathway.readFromXml(f, true);
				} catch (ConverterException e) {
					System.out.println("Could not parse pathway: " + f.getName());
				}
				pathways.put(f.getName(), pathway);
			}
		}
		return pathways;
	}

	public static Map<String, Pathway> readPathways(Organism organism, File inputDir) {
		Map<String, Pathway> pathways = new HashMap<String, Pathway>();
		for(File f : inputDir.listFiles()) {
			if (f.isDirectory()) {
                for (File pf: f.listFiles()) {
    				if(pf.getName().endsWith(".gpml")) {
    					Pathway pathway = new Pathway();
    					try {
    						pathway.readFromXml(pf, true);
    					} catch (ConverterException e) {
    						System.out.println("Could not parse pathway: " + pf.getName());
    					}
    					pathways.put(f.getName(), pathway);
    				}
                }
			} else {
			    if(f.getName().endsWith(".gpml")) {
    				Pathway pathway = new Pathway();
    				try {
    					pathway.readFromXml(f, true);
    				} catch (ConverterException e) {
    					System.out.println("Could not parse pathway: " + f.getName());
    				}
    				pathways.put(f.getName(), pathway);
   				}
			}
			}	
		return pathways;
	}
}
