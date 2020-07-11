package client;
import superClass.Appliance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import client.ApplianceGenerator;
import subClass.RegularAppliance;
import subClass.SmartAppliance;
public class PowerUsageSimulation {
	private static int generalCount=0;
	private static int locationCount=0;
	private static Appliance[] generalArr = new Appliance [10000];
	private static int[] locationArr = new int[10000];
	
	public static void algorithm(int x) {
		double totalPower=0;
		String fullDetail="";
		for(int i =0;i<generalArr.length&&generalArr[i]!=null;i++) {
			totalPower+=generalArr[i].getCurrentwhat();
		}
		System.out.println("The initial total power before implementing the algorithm is "+totalPower+"W");
		if(totalPower<x) {
			System.out.println("No location has been browned out and no smart devices has been switched to low.");
			fullDetail+="No location has been browned out and no smart devices has been switched to low."+System.lineSeparator();
		}
		else {
			fullDetail+="The following appliances have been set to low:"+System.lineSeparator();
			for(int i =0; i<generalArr.length&&generalArr[i]!=null&&totalPower>x;i++) {
				if(generalArr[i].getState()) {
					totalPower-=generalArr[i].getCurrentwhat();
					generalArr[i].turnLow();
					totalPower+=generalArr[i].getCurrentwhat();
					fullDetail+=generalArr[i].toString()+System.lineSeparator();
				}
			}
		}
		while(totalPower>x) {
			fullDetail+=System.lineSeparator()+"The following devices have been browned out:"+System.lineSeparator();
			float sumProb=0;
			int minLocation=0;
			double ave=0;
			double minAverageProbability=2;
			int count3=0;
			int count7=0;
			while(count7<locationCount) {
				for(int i=0;i<generalCount;i++) {
					if((generalArr[i].getLocation()==locationArr[count7])&&(generalArr[i].getState())) {
						sumProb+=generalArr[i].getProbability();
						count3++;
					}
				}
				ave=sumProb/count3;
				if(ave<minAverageProbability) {
					minAverageProbability=ave;
					minLocation=locationArr[count7];
				}
				count7++;
			}
			System.out.println("Location "+minLocation+" has been browned out.");
			
			for(int i=0;i<generalArr.length&&generalArr[i]!=null;i++) {
				if(generalArr[i].getLocation()==minLocation&&generalArr[i].getState()) {
					fullDetail+=generalArr[i].toString()+System.lineSeparator();
					//System.out.println(totalPower);
					totalPower-=generalArr[i].getCurrentwhat();
					generalArr[i].turnOff();
					System.out.print("");
				}
			}
		}
		System.out.println("The total power after implementing the algorithm is "+totalPower+"W\n");
		PrintWriter writer;
		try {
			writer = new PrintWriter("fullReport.txt", "UTF-8");
			writer.println(fullDetail);
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String [] Args) {
    	System.out.println("Hello! This is the power usage simulation speaking. Please select your option:");
        while(true){
    		System.out.println("Select \"A\" to add an appliance.");
    		System.out.println("Select \"D\" to delete an appliance.");
    		System.out.println("Select \"F\" to find an appliance.");
    		System.out.println("Select \"I\" to import a file filled of appliances.");
    		System.out.println("Select \"R\" to run the simulator.");
    		@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
            String opt1= scan.next();
	        switch (opt1) {
			case "A":
				System.out.println("Type \"R\" for regular appliance and \"S\" for smart appliance.");
				String in1 = scan.next();
				System.out.println("Type in the name of the appliance.");
				String in2 = scan.next();
				System.out.println("What is the wattage of the appliance?");
				int in3 = scan.nextInt();
				System.out.println("What is the location of the appliance? (the location has to start with a 1 and be 8 digits long)");
				int in4 = scan.nextInt();
				System.out.println("What is the probability that the appliance in on?");
				float in5 = scan.nextFloat();
				if(in1.equalsIgnoreCase("R")) { 
					generalArr[generalCount]=new RegularAppliance(in2, in4, in3, in5);
					System.out.println("The following regular appliance has been added to the database:");
					System.out.println(generalArr[generalCount].toString());
					generalCount++;
				}
				else {
					System.out.println("What is the reduction percentage on low state?(give in decimal)");
					float in6=scan.nextFloat();
					generalArr[generalCount]=new SmartAppliance(in2, in4, in3, in5,in6);
					System.out.println("The following smart appliance has been added to the database:");
					System.out.println(generalArr[generalCount].toString());
					generalCount++;
				}
				boolean a=true;
				for(int i=0;i<locationArr.length&&locationArr[i]!=0;i++) {
					if(locationArr[i]==in4) {
						a=false;
					}
				}
				if(a) {
					locationArr[locationCount]=in4;
					locationCount++;
				}
				break;
				
			case "D": 
				System.out.println("What is the name of appliance to be removed?");
				Scanner scan1 = new Scanner(System.in);
	            String in6= scan1.next();
	            
				Appliance[] temp=new Appliance[10000];
				int j=0;
				for(int i=0;i<generalArr.length&&generalArr[i]!=null;i++) {
					if(!(in6.equals(generalArr[i].getType()))) {
						temp[j]=generalArr[i];
						j++;
					}
					else {
						System.out.println("This appliance has been removed:\n"+generalArr[i].toString());
						generalCount--;
					}
				}
				generalArr=temp;
				j=0;
				break;
				
			case "F":
				System.out.println("What is the name of the appliance of the appliance you are looking for?");
				Scanner scan3 = new Scanner(System.in);
				String in7=scan3.nextLine();
				for(int i=0;i<generalArr.length&&generalArr[i]!=null;i++) {
					if(generalArr[i].getType().equals(in7)) {
						System.out.println(generalArr[i].toString());
					}
				}
				break;
				
			case"I":
				System.out.println("Input the file output.txt");
				try {
					ApplianceGenerator.main(null);
					File newFile = new File("output.txt");
					@SuppressWarnings("resource")
					Scanner scan2 = new Scanner(newFile);
					while(scan2.hasNextLine()){
						String []we = scan2.nextLine().split(",");
						if(we[4].equals("true")) {
							generalArr[generalCount]=new SmartAppliance(we[1],Integer.parseInt(we[0]),Integer.parseInt(we[2]),Float.parseFloat(we[3]),Float.parseFloat(we[5]));
							generalCount++;
							boolean b=true;
							for(int i=0;i<locationArr.length&&locationArr[i]!=0;i++) {
								if(locationArr[i]==Integer.parseInt(we[0])) {
									b=false;
								}
							}
							if(b) {
								locationArr[locationCount]=Integer.parseInt(we[0]);
								locationCount++;
							}
						}
						else {
							generalArr[generalCount]=new RegularAppliance(we[1],Integer.parseInt(we[0]),Integer.parseInt(we[2]),Float.parseFloat(we[3]));
							generalCount++;
							boolean b=true;
							for(int i=0;i<locationArr.length&&locationArr[i]!=0;i++) {
								if(locationArr[i]==Integer.parseInt(we[0])) {
									b=false;
								}
							}
							if(b) {
								locationArr[locationCount]=Integer.parseInt(we[0]);
								locationCount++;
							}
						}
					}
					for(int i=0;i<locationCount;i++) {
						System.out.println(locationArr[i]);;
					}
					for(int i=0;i<generalCount;i++) {
						System.out.println(generalArr[i].toString());
					}
					System.out.println(locationCount);
					System.out.println(generalCount);
				}	
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				break;
				
			case "R":
				System.out.println("What should be the maximum wattage of all appliances comnbined?");
				int z=scan.nextInt();
				EventSimulator.main(null);
				for(int k=0;k<EventSimulator.callCount;k++){
					double y = Math.random();
					System.out.println("Probability threshold: "+y+". Event "+(k+1)+":");
					for(int i=0;i<generalArr.length&&generalArr[i]!=null;i++) {
						if(generalArr[i].getProbability()>y) {
							generalArr[i].turnOff();
						}
					}
					algorithm(z);
					for(int i=0;i<generalCount;i++) {
							generalArr[i].turnOn();
					}
				}
				System.out.println("Quitting program");
				System.exit(0);
			default:
				System.out.println("Wrong option! Try again");
			break;
			}
        }
	}
}
