package edu.iastate.cs228.hw4;

/**
 *  
 * @author Trace Ohrt 
 * 
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.InputMismatchException; 
import java.io.PrintWriter;
import java.util.Random; 
import java.util.Scanner;


/**
 * 
 * This class implements Graham's scan that constructs the convex hull of a finite set of points. 
 *
 */

public class ConvexHull 
{
	// ---------------
	// Data Structures 
	// ---------------
	
	/**
	 * The array points[] holds an input set of Points, which may be randomly generated or 
	 * input from a file.  Duplicates may appear. 
	 */
	private ArrayList<Point> points2;
	private Point[] points;    
	private int numPoints;            // size of points[]
	

	/**
	 * Lowest point from points[]; and in case of a tie, the leftmost one of all such points. 
	 * To be set by the private method lowestPoint(). 
	 */
	//FIX VISIBILITY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!private
	public Point lowestPoint; 

	
	/**
	 * This array stores the same set of points from points[] with all duplicates removed. 
	 */
	private Point[] pointsNoDuplicate; 
	private int numDistinctPoints;    // size of pointsNoDuplicate[]

	
	/**
	 * Points on which Graham's scan is performed.  They are copied from pointsNoDuplicate[] 
	 * with some points removed.  More specifically, if multiple points from the array 
	 * pointsNoDuplicate[] have the same polar angle with respect to lowestPoint, only the one 
	 * furthest away from lowestPoint is included. 
	 */
	private Point[] pointsToScan; 
	private int numPointsToScan;     // size of pointsToScan[]
	
	
	/**
	 * Vertices of the convex hull in counterclockwise order are stored in the array 
	 * hullVertices[], with hullVertices[0] storing lowestPoint. 
	 */
	private Point[] hullVertices;
	private int numHullVertices;     // number of vertices on the convex hull
	
	
	/**
	 * Stack used by Grahma's scan to store the vertices of the convex hull of the points 
	 * scanned so far.  At the end of the scan, it stores the hull vertices in the 
	 * counterclockwise order. 
	 */
	private PureStack<Point> vertexStack;  

	

	// ------------
	// Constructors
	// ------------
	
	
	/**
	 * Generate n random points within the box range [-50, 50] x [-50, 50]. Duplicates are 
	 * allowed. Store the points in the private array points[]. 
	 * 
	 * @param n >= 1; otherwise, exception thrown.  
	 */
	public ConvexHull(int n) throws IllegalArgumentException 
	{
		if (n<1) throw new IllegalArgumentException();			 //obligatory throws
		
		numPoints=n;
		Random rand = new Random();								 //random for point creation	
		points = new Point[numPoints]; 							//array initialization
		
		for(int i=0; i<n ; i++){								//loop creates random point and places it in array
			int x=rand.nextInt(101)-50;							//random numbers between -50 and 50 for x and y coordinates of point
			int y=rand.nextInt(101)-50;
			Point p = new Point(x, y);
			points[i]=p;
		}
		
		
		
	}
	
	/**
	 * Read integers from an input file.  Every pair of integers represent the x- and y-coordinates 
	 * of a point.  Generate the points and store them in the private array points[]. The total 
	 * number of integers in the file must be even.
	 * 
	 * You may declare a Scanner object and call its methods such as hasNext(), hasNextInt() 
	 * and nextInt(). An ArrayList may be used to store the input integers as they are read in 
	 * from the file.  
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	public ConvexHull(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		// TODO 
		
		File file = new File(inputFileName);			
		Scanner scanner = new Scanner(file);	//scanner for input file
		points2 = new ArrayList<Point>(); 		//initialize arraylist
		
		
		while(scanner.hasNext()){				//creates points while the file has points to create
			int x=scanner.nextInt();			//variables to store x and y coordinates for point
			int y=scanner.nextInt();
			Point p = new Point(x, y);
			points2.add(p);
			
		}
		scanner.close();
		
		//fill array with data in arraylist
		numPoints=points2.size();
		points = new Point[points2.size()];
		
		for(int i=0; i<points2.size(); i++){
			points[i]=points2.get(i);
		}
		
		
		
	}

	
	
	// -------------
	// Graham's scan
	// -------------
	
	/**
	 * This method carries out Graham's scan in several steps below: 
	 * 
	 *     1) Call the private method lowestPoint() to find the lowest point from the input 
	 *        point set and store it in the variable lowestPoint. 
	 *        
	 *     2) Call the private method setUpScan() to sort all points by polar angle with respect
	 *        to lowestPoint.  After elimination of all duplicates in points[], the points are 
	 *        stored in pointsNoDuplicate[].  Next, for multiple points having the same polar angle 
	 *        with respect to lowestPoint, keep only the one furthest from lowestPoint.  The points
	 *        after the second round of elimination are stored in the array pointsToScan[].  
	 *        
	 *     3) Perform Graham's scan on the points in the array pointsToScan[]. To initialize the 
	 *        scan, push pointsToScan[0] and pointsToScan[1] onto vertexStack.  
	 * 
     *     4) As the scan terminates, vertexStack holds the vertices of the convex hull.  Pop the 
     *        vertices out of the stack and add them to the array hullVertices[], starting at index
     *        numHullVertices - 1, and decreasing the index toward 0.  Set numHullVertices.  
     *        
     * Two special cases below must be handled: 
     * 
     *     1) The array pointsToScan[] could contain just one point, in which case the convex
     *        hull is the point itself. 
     *     
     *     2) Or it could contain two points, in which case the hull is the line segment 
     *        connecting them.   
	 */
	public void GrahamScan()
	{
		// TODO 
	}

	
	
	// ------------------------------------------------------------
	// toString() and Files for Convex Hull Plotting in Mathematica
	// ------------------------------------------------------------
	
	/**
	 * The string displays the convex hull with vertices in counter clockwise order starting at  
	 * lowestPoint.  When printed out, it will list five points per line with three blanks in 
	 * between. Every point appears in the format "(x, y)".  
	 * 
	 * For illustration, the convex hull example in the project description will have its 
	 * toString() generate the output below: 
	 * 
	 * (-7, -10)   (0, -10)   (10, 5)   (0, 8)   (-10, 0)   
	 * 
	 * lowestPoint is listed only ONCE.  
	 */
	public String toString()
	{
		// TODO - not sorted according to specs
		String s="";
		int count=0;
		for (int i=1; i<numPoints; i++){
			if (count==5){
				s+="\n";
				count=0;
			}
			s+= "(" + (int)points[i].getX() + ", " + (int)points[i].getY() + ")   ";
			count++;
		}
		return s; 
	}
	
	
	/** 
	 * For plotting in Mathematica. 
	 * 
	 * Writes to the file "hull.txt" the vertices of the constructed convex hull in counterclockwise 
	 * order for rendering in Mathematica.  These vertices are in the array hullVertices[], starting 
	 * at lowestPoint.  Every line in the file displays the x and y coordinates of only one point.  
	 * Write the coordinates of lowestPoint again to end the file.   
	 * 
	 * For instance, the file "hull.txt" generated for the convex hull example in the project 
	 * description will have the following content: 
	 * 
     *  -7 -10 
     *  0 -10
     *  10 5
     *  0  8
     *  -10 0
     *  -7 -10 
	 * 
	 * Note that lowestPoint (-7, -10) has its coordinates listed in the first and last lines.  This 
	 * is for Mathematica to plot the hull as a polygon rather than one missing the edge connecting
	 * (-10, 0) and (-7, -10).  
	 * 
	 * Called only after GrahamScan().  
	 * 
	 * 
	 * @throws IllegalStateException  if hullVertices[] has not been populated (i.e., the convex 
	 *                                   hull has not been constructed)
	 */
	public void hullToFile() throws IllegalStateException 
	{
		// TODO 
	}
	
	
	/**
	 * For plotting in Mathematica. 
	 * 
	 * Writes to the file "points.txt" the points stored in the array pointsNoDuplicate[]. The 
	 * format is the same as required for the method hullToFile(), except that the coordinates 
	 * of lowestPoint appear only once.
	 * 
	 * Called only after setUpScan() or GrahamScan(). 
	 * 
	 * @throws IllegalStateException  if pointsNoDuplicate[] has not been populated. 
	 */
	public void pointsToFile() throws IllegalStateException 
	{
		// TODO 
	}

	
	/**
	 * Also implement this method for testing purpose. 
	 * 
	 * Writes to the file "pointsScanned.txt" the points stored in the array pointsToScan[]. The 
	 * format is the same as required for the method pointsToFile(). 
	 * 
	 * Called only after setUpScan() or GrahamScan().  
	 * 
	 * @throws IllegalStateException  if pointsToScan[] has not been populated. 
	 */
	public void pointsScannedToFile() throws IllegalStateException 
	{
		// TODO 
	}

	
	// ---------------
	// Private Methods
	// ---------------
	
	/**
	 * Find the point in the array points[] that has the smallest y-coordinate.  In case of a tie, 
	 * pick the point with the smallest x-coordinate. Set the variable lowestPoint to the found 
	 * point. 
	 * 
	 * Multiple elements from points[] could coincide at the lowestPoint (i.e., they are the same 
	 * point).  This situation could happen, though with a very small chance.  In this case, any of 
	 * them can be lowestPoint. 
	 * 
	 * Ought to be private, but is made public for testing convenience. 
	 */
	public void lowestPoint()
	{
		// TODO 
		int indexOfSmallest=0;											
		
		for(int i=1; i<numPoints; i++){									
			if (points[indexOfSmallest].getY()>points[i].getY()){		//if point is smaller than previously selected point
				indexOfSmallest=i;										//update variable
			}
			else if(points[indexOfSmallest].getY()==points[i].getY()){	//if there's a y value tie
				if (points[indexOfSmallest].getX()>points[i].getX()){	//smallest x value gets precedence
					indexOfSmallest=i;				}
			}
		}
		lowestPoint=points[indexOfSmallest];
		
	}
	
	
	/**
	 * Call quickSort() on points[].  After the sorting, duplicates in points[] will appear next 
	 * to each other, with those equal to lowestPoint at the beginning of the array.  
	 * 
	 * Copy the points from points[] into the array pointsNoDuplicate[], eliminating all duplicates. 
	 * Update numDistinctPoints. 
	 * 
	 * Copy the points from pointsNoDuplicate[] into the array pointsToScan[] and eliminate some 
	 * as follows.  If multiple points have the same polar angle, eliminate all but the one that is 
	 * the furthest from lowestPoint.  Update numPointsToScan. 
	 * 
	 * Ought to be private, but is made public for testing convenience. 
	 *
	 */
	public void setUpScan()
	{
		// TODO 
	}

	
	/**
	 * Sort the array points[] in the increasing order of polar angle with respect to lowestPoint.  
	 * Use quickSort.  Construct an object of the pointComparator class with lowestPoint as the 
	 * argument for point comparison.  
	 * 
	 * Ought to be private, but is made public for testing convenience.   
	 */
	public void quickSort()
	{
		// TODO 
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		// TODO
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// TODO 
		return 0; 
	}	
		
			
}
