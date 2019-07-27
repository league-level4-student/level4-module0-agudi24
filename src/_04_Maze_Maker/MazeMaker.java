package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(randGen.nextInt(width), randGen.nextInt(height)));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		if(getUnvisitedNeighbors(currentCell).size() > 0) {
		//C. if has unvisited neighbors,
		
			//C1. select one at random.
			
			//C2. push it to the stack
			uncheckedCells.push(getUnvisitedNeighbors(currentCell).get(randGen.nextInt(getUnvisitedNeighbors(currentCell).size())));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, uncheckedCells.lastElement());
			//C4. make the new cell the current cell and mark it as visited
			currentCell = uncheckedCells.pop();
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
			
		//D. if all neighbors are visited
		if(getUnvisitedNeighbors(currentCell).size() > 0) {
			//D1. if the stack is not empty
			if(!uncheckedCells.empty()) {
				
				// D1a. pop a cell from the stack
				
				// D1b. make that the current cell
				currentCell.equals(uncheckedCells.pop());
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX()) {
			if(c1.getY() - 1 == c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}else if(c1.getY() + 1 == c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		if(c.getX() != 0 && maze.getCell(c.getX() -1, c.getY()).hasBeenVisited() == false) {
			unvisited.add(maze.getCell(c.getX()-1, c.getY()));
		
		}
		if(c.getY() != 0 && maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()	== false) {
			unvisited.add(maze.getCell(c.getX(), c.getY()-1));
			
		}
		if(c.getX() != width -1 && maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited() == false) {
			unvisited.add(maze.getCell(c.getX()+1, c.getY()));
			
			
		}
		if(c.getY() != height -1 && maze.getCell(c.getX(), c.getY()+1).hasBeenVisited() == false) {
			unvisited.add(maze.getCell(c.getX(), c.getY()+1));
			
		}
		return unvisited;
	}
}
