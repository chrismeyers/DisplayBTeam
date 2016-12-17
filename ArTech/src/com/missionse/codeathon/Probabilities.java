/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.missionse.codeathon;

import java.util.ArrayList;


public class Probabilities {
    
    int PROB_INCREASE_VAL = 1;
    int AVG_WALK_SPEED = 3; // miles per hour
    int AVG_RUN_SPEED = 16; // miles per hour
    
    double MILE_TO_KM =  1.61;
    
    double getMovementSpeed(){
        return AVG_WALK_SPEED;
    }
    
    
    int getLastKnownPositionX(){
       return 32;//gridLastKnownPosion.x; 
    }
    
    int getLastKnownPositionY(){
       return 32;//gridLastKnownPosion.y; 
    }
    
    int getTimeSinceLastKnowPosition(){
        return 60*60*5; // this is in seconds
    }
    
    int calMaxTravalDistanceKm(){
        return (int)Math.ceil(((getTimeSinceLastKnowPosition()/60)/60) * getMovementSpeed() * MILE_TO_KM);
    }

    ArrayList<GridSquare> removeOutOfRangeCells(ArrayList <GridSquare> cells){
        ArrayList<GridSquare> grid = new ArrayList<>();
        for (GridSquare cell : cells) {
           
            
            if(cell.x > getLastKnownPositionX()){
                if(cell.x - getLastKnownPositionX() > calMaxTravalDistanceKm()){
                    cell.setReachable(false);
                }
            }
            else{
                if(getLastKnownPositionX() - cell.x > calMaxTravalDistanceKm()){
                    cell.setReachable(false);
                }
            }
            if(cell.y > getLastKnownPositionY()){
                if(cell.y - getLastKnownPositionY() > calMaxTravalDistanceKm()){
                    cell.setReachable(false);
                }
            }
            else{
                if(getLastKnownPositionY() - cell.y > calMaxTravalDistanceKm()){
                    cell.setReachable(false);
                }
            }
            
            
            if(cell.isReachable()){
                grid.add(cell);
            }
        }
        return grid;
    }
    
    void calCellValues(ArrayList<GridSquare> cells){ 
        for(GridSquare currentCell : cells){
            
            if(false == currentCell.isReachable()){
                break;
            }
            
            for(int i = -5; i < 6 ; i++){
                for(int j = -5; j < 6 ; j++){
                    if(currentCell.x - i < 0 || currentCell.y - j < 0){
                        break;
                    }
                    
                    for(GridSquare checkCell : cells){
                        if(currentCell.x-i == checkCell.x && currentCell.y-j == checkCell.y && checkCell.hasPath()){
                            currentCell.numLines++;
                        }
                    }
                }
            }
        }   
    }

    
    double normalization(double valueToNormalize, double maxValue, double dataLow, double max, double min){
       // return max + (((valueToNormalize - minValue)* (max - min)) / (maxValue - minValue)); 
   
        return ((valueToNormalize - dataLow) 
            / (maxValue - dataLow))
            * (max - min) + min;
    }
    
    void normGrid(ArrayList<GridSquare> cells){
        int max = 0, min = 1000;
        for(GridSquare currentCell : cells){
            if(currentCell.numLines > max)
                max = currentCell.getNumLines();
            
            if(currentCell.numLines < min && min > 0)
                min = currentCell.getNumLines();
        }
        
        for(GridSquare currentCell : cells){
            currentCell.setProbablity(normalization(currentCell.numLines, max, min, 1, 0));
        }
        
    }
    
    
}
