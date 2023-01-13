package LibTest;

import LibNet.NetLibrary;
import PetriObj.*;
import java.util.ArrayList;

public class ManufSystem {
  public static int NUM_RUNS = 10;
  public static int MODELLING_TIME = 100000;

  public static void main(String[] args)
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    double numProcessed = 0;
    for (int runIdx = 0; runIdx < NUM_RUNS; ++runIdx) {
      var model = getDynamicModel();
      model.setIsProtokol(false);
      model.go(MODELLING_TIME);
      numProcessed +=
          model.getListObj().get(5).getNet().getListP()[2].getObservedMax() /
          (double)NUM_RUNS;
    }
    System.out.printf("Number of processed = %f", numProcessed);
  }

  public static PetriObjModel getStaticModel()
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    var petriObjects = new ArrayList<PetriSim>();
    // петрі об'єкти
    petriObjects.add(new PetriSim(NetLibrary.CreateNetGenerator(40.0)));
    petriObjects.add(new PetriSim(
        NetLibrary.CreateNetRobotStaticPipeline(0, 6.0, 22.0, 1.155)));
    petriObjects.add(new PetriSim(
        NetLibrary.CreateNetMachinePipeline(3, "norm", 60.0, 10.0)));
    petriObjects.add(new PetriSim(
        NetLibrary.CreateNetRobotStaticPipeline(0, 7.0, 23.0, 1.155)));
    petriObjects.add(
        new PetriSim(NetLibrary.CreateNetMachinePipeline(3, "exp", 100, 0.0)));
    petriObjects.add(new PetriSim(
        NetLibrary.CreateNetRobotStaticPipeline(0, 5.0, 21.0, 1.155)));
    // зв'язки
    petriObjects.get(0).getNet().getListP()[1] =
        petriObjects.get(1).getNet().getListP()[0]; // GEN -> POS1
    petriObjects.get(1).getNet().getListP()[2] =
        petriObjects.get(2).getNet().getListP()[0]; // POS 1 -> QUEUE 1
    petriObjects.get(2).getNet().getListP()[1] =
        petriObjects.get(3).getNet().getListP()[0]; // WB1 -> POS2
    petriObjects.get(3).getNet().getListP()[2] =
        petriObjects.get(4).getNet().getListP()[0]; // POS 2 -> QUEUE 2
    petriObjects.get(4).getNet().getListP()[1] =
        petriObjects.get(5).getNet().getListP()[0]; // WB 2 to POS 3
    return new PetriObjModel(petriObjects);
  }

  public static PetriObjModel getDynamicModel()
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    var petriObjects = new ArrayList<PetriSim>();
    // Petri objects
    petriObjects.add(new PetriSim(NetLibrary.CreateNetGenerator(40.0)));
    petriObjects.add(new PetriSim(NetLibrary.CreateNetRobotDynamicPipeline(
        6.0, 13.0, 18.0, 2, 1, 0, 22, 1.155)));
    petriObjects.add(new PetriSim(
        NetLibrary.CreateNetMachinePipeline(3, "norm", 60.0, 10.0)));
    petriObjects.add(new PetriSim(NetLibrary.CreateNetRobotDynamicPipeline(
        7.0, 0.0, 12.0, 1, 2, 0, 23, 1.155)));
    petriObjects.add(
        new PetriSim(NetLibrary.CreateNetMachinePipeline(3, "exp", 100, 0.0)));
    petriObjects.add(new PetriSim(NetLibrary.CreateNetRobotDynamicPipeline(
        5.0, 0.0, 7.0, 1, 2, 0, 21, 1.155)));
    // зв'язки
    // POS 1
    petriObjects.get(0).getNet().getListP()[1] =
        petriObjects.get(1).getNet().getListP()[0]; // BEGIN
    petriObjects.get(1).getNet().getListP()[2] =
        petriObjects.get(2).getNet().getListP()[0]; // END
    petriObjects.get(1).getNet().getListP()[3] =
        petriObjects.get(3).getNet().getListP()[1]; // POS 2
    petriObjects.get(1).getNet().getListP()[4] =
        petriObjects.get(5).getNet().getListP()[1]; // POS 3
    // POS 2
    petriObjects.get(2).getNet().getListP()[1] =
        petriObjects.get(3).getNet().getListP()[0]; // BEGIN
    petriObjects.get(3).getNet().getListP()[2] =
        petriObjects.get(4).getNet().getListP()[0]; // END
    petriObjects.get(3).getNet().getListP()[3] =
        petriObjects.get(1).getNet().getListP()[1]; // POS 1
    petriObjects.get(3).getNet().getListP()[4] =
        petriObjects.get(5).getNet().getListP()[1]; // POS 3
    // POS 3
    petriObjects.get(4).getNet().getListP()[1] =
        petriObjects.get(5).getNet().getListP()[0]; // BEGIN
    petriObjects.get(5).getNet().getListP()[3] =
        petriObjects.get(3).getNet().getListP()[1]; // POS 2
    petriObjects.get(5).getNet().getListP()[4] =
        petriObjects.get(1).getNet().getListP()[1]; //  POS 3
    return new PetriObjModel(petriObjects);
  }
}
