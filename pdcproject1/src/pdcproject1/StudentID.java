/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

import java.util.Random;

/**
 *
 * @author bryan
 */
public class StudentID {
    private static final int[] studentIDs = new int[10];
    private static int count = 0;
    
    public static int generateRandomStudentID() { //Generates random student ID
        Random random = new Random();
        int id = 0;
        int size = 0;
        for(int a = 0; a < studentIDs.length; a++){
            if(studentIDs[a] != 0){
                size++;
            }
        }
        while(count == size){
            id = 100000 + random.nextInt(900000);
            if(!exists(id)){
                studentIDs[count] = id;
                size++;
            }
        }
        count++;
        return id;
    }
    
    public static boolean exists(int id){
        boolean check = false;
        for(int i = 0; i <= count; i++){
            if(studentIDs[i] == id){
                check = true;
            }
        }
        return check;
    }
}
