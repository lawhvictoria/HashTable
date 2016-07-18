/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victoria
 */

/*
 * A class for a Student object
 */
public class Student {

    private long id;        //A private variable containing the ID of the student.
    private String lastName; //A private variable for the last Name of the student.

    /*
     * A constructor to create a student Object.
     */
    public Student(long id, String lastName){
        this.id = id;
        this.lastName = lastName;
    }

    /*
     * A method to check if a student is the same as another student.
     */
    public boolean equals(Object other){
        if (other instanceof Student) {
            Student o = (Student) other;
            if (id == o.id) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /*
     * A toString method for outputting a Student Object
     * in the correct format.
     */
    public String toString(){
        return "{ id: " + id + ", name: " + lastName + " }";
    }

    /*
     * A method to get the hashCode of a student object.
     */
    public int hashCode(){
        return ((Long)id).hashCode();
    }
}

