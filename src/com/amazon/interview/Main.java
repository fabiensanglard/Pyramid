package com.amazon.interview;

import java.util.Iterator;
import java.util.LinkedList;

import com.sun.tools.javac.util.Pair;


public class Main {

	
	private static class Human {

		//This is used when building pyramid. The base human has previous set to NULL.
		public Human previous;
		
		public int weight;
		public int strength;
		
		public Human(int weight, int strength){
			this.weight   =   weight;
			this.strength = strength;
		}
		
		//Return the size of the pyramid below this human, inclusive.
		public int size(){
			Human cursor = this;
			int count = 0;
			
			while(cursor != null){
				count++;
				cursor = cursor.previous;
			}
			  	
			return count;	
		}
	}
	
	
	// Perform a linear search in order to find the base. Will also remove element from the list.
	// The heuristic to elect a base for now is to find the heaviest and strongest individual
	private static Human findBaseAndRemove(LinkedList<Human> list)
	{
	    //Will hold what we found
        Human keeper = list.peek() ; //peek() return null if the list is empty contrary to first()
		
        
        //Linear search
	    Iterator<Human> iterator = list.iterator();
	    while(iterator.hasNext()){
	    	
	    	Human prospect = iterator.next();
	    	
	    	//Is heaviest and strongest individual ?
	    	if (prospect.weight > keeper.weight){
	    		if (prospect.strength > keeper.strength){
	    			keeper = prospect;
	    		}
	    	}
	    	
	    }
	    
	    //Remove the element from the list
	    if (keeper != null)
	    	list.remove(keeper);
	    	
		
	    return keeper;
	}
	
	// Will never be called with either parameter set to null
	// Check if the prospect can be added to the pyramid without violating any of the contraint
	// This to generate early reject
	private static boolean canAddToPyramid(Human pyramid, Human prospect) {
		
		Human cursor = pyramid;
		int accumulatedWeight = prospect.weight;
		
		while(cursor != null){
			
			if (accumulatedWeight > cursor.strength)
				return false;
			
			accumulatedWeight += cursor.weight;
			cursor = cursor.previous;
		}
		
		return true;
	}
	
	
	// Core of the algorithm. Recursive method searching linearly for the best pyramid
	// We could search more efficiently if could assume listOhHumans is sorted  on their mass first and strength second.
	private static int findBestPyramid_r(Human pyramid, LinkedList<Human> candidates) {
		
		int maxSize = pyramid.size();
		
		//We have exhausted the candidates, nothing to recurse on
		if (candidates == null || candidates.size() == 0){
			return maxSize;
		}
		
		Human headList = candidates.peek();
		
		do{
			
		    Human prospect = candidates.poll();	
		    
			if (canAddToPyramid(pyramid,prospect)){
				
				prospect.previous = pyramid;
				
				int size = findBestPyramid_r(prospect,candidates);
				
				if (size>maxSize)
					maxSize = size;
			}
			
			candidates.addLast(prospect);
			
		} while(candidates.peek() != headList);
		
		return maxSize;
	}
	
	
	
	public static void main(String[] argv){
		
		
		
		LinkedList<Human> listOfHumans = new LinkedList<Human>();
		listOfHumans.add(new Human(3,4));
		listOfHumans.add(new Human(2,2));
		listOfHumans.add(new Human(7,6));
		listOfHumans.add(new Human(4,5));
		

		//Find the base, set previous to null to mark that it is the root of the pyramid
		Human pyramid = findBaseAndRemove(listOfHumans);
		pyramid.previous = null;
		
		// Recurse. Note that we could search better if we took the time to sort the listOhHumans
		// based on their mass first and strength second.
		int highestPyramidCount = 0;
		highestPyramidCount = findBestPyramid_r(pyramid,listOfHumans);
		
		System.out.println(highestPyramidCount);
	}



}
