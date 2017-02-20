package project.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import project.persistence.entities.BMI;

@Service
public class FormulaService {

    /**
     * Returns the Body Mass Index string
     * @param takes in 2 int value height and weight, calculate using the formula 
     * @return String
     */
    public BMI BMICalculate(int height, int weight){
    	BMI bmi = new BMI();
    	double heightInMeters = height/(double)100;
        double BMI = weight/(heightInMeters*heightInMeters);
        double BMIIndex = Math.floor(BMI * 100) / 100;
        String idealWeight;
        
        if(BMIIndex < 18.5) {
        	idealWeight = "Underweight";
        }
        else if(BMIIndex >= 18.5 && BMIIndex <= 24.9) {
        	idealWeight = "Normal weight";
        }
        else if(BMIIndex > 24.9 && BMIIndex < 30) {
        	idealWeight = "Overweight";
        }
        else {
        	idealWeight = "Obesity";
        }
        
        bmi.setBMIIndex(BMIIndex);
        bmi.setIdealWeight(idealWeight);
        return bmi;
    }
    
    /**
     * Takes in Date birthday and changes the format to dd/MM/yyyy
     * @param birthday
     * @return String containing birthday in new format
     */
    public String changeDateFormat(Date birthday) {
    	String NEW_FORMAT = "dd/MM/yyyy";
    	String newBirthday;
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
    	newBirthday = formatter.format(birthday);
    	
    	return newBirthday;
    }
}
