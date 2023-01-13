/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LibTest;

//import PetriObj.PetriObjModel;
import LibNet.NetLibrary;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriObjModel;
import PetriObj.PetriSim;

import java.util.ArrayList;




/**
 *
 * @author Inna V. Stetsenko
 */
public class TestPetriObjSimulation {  //Результати співпадають з аналітичними обрахунками
    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {

//        PetriObjModel model = get1();
//        model.setIsProtokol(false);
//        double timeModeling = 1000000;
//        model.go(timeModeling);
//        //статистика для 1 завдання
//        statistic(model, 1, 5, 11, 6, 12,4,10,17,15);
//
////        PetriObjModel model2 = get2();
//        model2.setIsProtokol(false);
//        double timeModeling2 = 1000000;
//        model2.go(timeModeling2);
//        //статистика для 2 завдання
//        statistic(model2, 1, 2, 6, 4, 7,3,13,16,9);


    }
    public static void statistic(PetriObjModel m, int arMean, int v1Mean, int v2Mean, int v1Capacity, int v2Capacity, int r1, int r2, int r3, int sklad ) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        System.out.println("Average queue at the entry point:     "+m.getListObj().get(0).getNet().getListP()[arMean].getMean());
        System.out.println("The average queue in front of machine 1:    "+ (m.getListObj().get(0).getNet().getListP()[v1Mean].getMean()));
        System.out.println("The middle queue in front of machine 2:    "+ (m.getListObj().get(0).getNet().getListP()[v2Mean].getMean()));
        double s1 = 1.0 - m.getListObj().get(0).getNet().getListP()[v1Capacity].getMean()/2.0;
        System.out.println("Average loading of machine 1:    "+ s1);
        double s2 = 1.0 - m.getListObj().get(0).getNet().getListP()[v2Capacity].getMean()/2.0;
        System.out.println("Average loading of machine 2:    "+ s2);
        System.out.println("Average occupation of robot 1:    "+ (1 - m.getListObj().get(0).getNet().getListP()[r1].getMean()));
        System.out.println("Average occupation of robot 2:    "+ (1 - m.getListObj().get(0).getNet().getListP()[r2].getMean()));
        System.out.println("Average occupation of robot 3:    "+ (1 - m.getListObj().get(0).getNet().getListP()[r3].getMean()));
        System.out.println("Details in stock:    "+ (m.getListObj().get(0).getNet().getListP()[sklad].getObservedMax()));
//        System.out.println("Details in stock:    "+ (m.getListObj().get(0).getNet().getListP()[sklad].getName()));


    }

//    public static PetriObjModel get1() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
//        ArrayList<PetriSim> list = new ArrayList<>();
//        list.add(new PetriSim(NetLibrary.CreateNetVakaliuk1()));
//        PetriObjModel model = new PetriObjModel(list);
//        return model;
//    }
//
//    public static PetriObjModel get2() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
//        ArrayList<PetriSim> list = new ArrayList<>();
//        list.add(new PetriSim(NetLibrary.CreateNetVakaliuk2()));
//        PetriObjModel model = new PetriObjModel(list);
//        return model;
//    }

}
