package LibNet;

import PetriObj.*;
import java.util.ArrayList;
import java.util.Random;

public class NetLibrary {

  /**
   * Creates Petri net that describes the dynamics of system of the mass
   * service (with unlimited queue)
   *
   * @param numChannel the quantity of devices
   * @param timeMean the mean value of service time of unit
   * @param name the individual name of SMO
   * @throws ExceptionInvalidTimeDelay if one of net's transitions has no input position.
   * @return Petri net dynamics of which corresponds to system of mass service with given parameters
   * @throws PetriObj.ExceptionInvalidNetStructure
   */
  public static PetriNet CreateNetSMOwithoutQueue(int numChannel, double timeMean, String name) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay{
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("P1",0));
    d_P.add(new PetriP("P2",numChannel));
    d_P.add(new PetriP("P3",0));
    d_T.add(new PetriT("T1",timeMean));
    d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
    d_T.get(0).setParamDeviation(0.0);
    d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
    d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
    d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
    d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
    PetriNet d_Net = new PetriNet("SMOwithoutQueue"+name,d_P,d_T,d_In,d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }

  /**
   * Creates Petri net that describes the dynamics of arrivals of demands for
   * service
   *
   * @param timeMean mean value of interval between arrivals
   * @return Petri net dynamics of which corresponds to generator
   * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
   * @throws PetriObj.ExceptionInvalidNetStructure
   */
  public static PetriNet CreateNetGenerator(double timeMean) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("P1",1));
    d_P.add(new PetriP("P2",0));
    d_T.add(new PetriT("T1", timeMean,Double.MAX_VALUE));
    d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
    d_T.get(0).setParamDeviation(0.0);
    d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
    d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
    d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
    PetriNet d_Net = new PetriNet("Generator",d_P,d_T,d_In,d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }
  /**
   * Creates Petri net that describes the route choice with given
   * probabilities
   *
   * @param p1 the probability of choosing the first route
   * @param p2 the probability of choosing the second route
   * @param p3 the probability of choosing the third route
   * @return Petri net dynamics of which corresponds to fork of routs
   * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
   * @throws PetriObj.ExceptionInvalidNetStructure
   */
  public static PetriNet CreateNetFork(double p1, double p2, double p3) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay{
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("P1",0));
    d_P.add(new PetriP("P2",0));
    d_P.add(new PetriP("P3",0));
    d_P.add(new PetriP("P4",0));
    d_P.add(new PetriP("P5",0));
    d_T.add(new PetriT("T1",0.0,Double.MAX_VALUE));
    d_T.get(0).setProbability(p1);
    d_T.add(new PetriT("T2",0.0,Double.MAX_VALUE));
    d_T.get(1).setProbability(p2);
    d_T.add(new PetriT("T3",0.0,Double.MAX_VALUE));
    d_T.get(2).setProbability(p3);
    d_T.add(new PetriT("T4",0.0,Double.MAX_VALUE));
    d_T.get(3).setProbability(1-p1-p2-p3);
    d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
    d_In.add(new ArcIn(d_P.get(0),d_T.get(1),1));
    d_In.add(new ArcIn(d_P.get(0),d_T.get(2),1));
    d_In.add(new ArcIn(d_P.get(0),d_T.get(3),1));
    d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
    d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
    d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
    d_Out.add(new ArcOut(d_T.get(3),d_P.get(4),1));
    PetriNet d_Net = new PetriNet("Fork",d_P,d_T,d_In,d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }
  /**
   * Creates Petri net that describes the route choice with given
   * probabilities
   *
   * @param numOfWay quantity of possibilities in choice ("ways")
   * @param probabilities set of values probabilities for each "way"
   * @return Petri net dynamics of which corresponds to fork of routs
   * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
   * @throws PetriObj.ExceptionInvalidNetStructure
   */
  public static PetriNet CreateNetFork(int numOfWay, double[] probabilities) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {

    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();

    d_P.add(new PetriP("P0", 0));
    for (int j = 0; j < numOfWay; j++) {
      d_P.add(new PetriP("P" + (j + 1), 0));
    }

    for (int j = 0; j < numOfWay; j++) {
      d_T.add(new PetriT("?????????? ???????????????? " + (j + 1), 0));
    }
    for (int j = 0; j < numOfWay; j++) {
      d_T.get(j).setProbability(probabilities[j]);
    }

    for (int j = 0; j < numOfWay; j++) {
      d_In.add(new ArcIn(d_P.get(0), d_T.get(j), 1));
    }

    for (int j = 0; j < numOfWay; j++) {
      d_Out.add(new ArcOut(d_T.get(j), d_P.get(j + 1), 1));
    }

    PetriNet d_Net = new PetriNet("Fork ", d_P, d_T, d_In, d_Out);

    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }

  public static PetriNet
  CreateNetRobotStaticPipeline(int priority, double moveDelayMean,
                               double carryDelayMean, double carryDelayStd)
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("Start", 0));
    d_P.add(new PetriP("Free Robot", 1));
    d_P.add(new PetriP("End", 0));
    d_P.add(new PetriP("Robot At Start", 0));
    d_T.add(new PetriT("Carry To End", carryDelayMean));
    d_T.get(0).setDistribution("unif", d_T.get(0).getTimeServ());
    d_T.get(0).setParamDeviation(carryDelayStd);
    d_T.add(new PetriT("Robot Moves To Start", moveDelayMean));
    d_T.get(1).setPriority(priority);
    d_In.add(new ArcIn(d_P.get(1), d_T.get(1), 1));
    d_In.add(new ArcIn(d_P.get(0), d_T.get(1), 1));
    d_In.add(new ArcIn(d_P.get(3), d_T.get(0), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(1), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(2), 1));
    d_Out.add(new ArcOut(d_T.get(1), d_P.get(3), 1));
    PetriNet d_Net = new PetriNet("RobotStaticPipeline", d_P, d_T, d_In, d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }

  public static PetriNet
  CreateNetRobotDynamicPipeline(double XMoveDelayMean, double YMoveDelayMean,
                                double ZMoveDelayMean, int XPriority,
                                int YPriority, int ZPriority,
                                double carryDelayMean, double carryDelayStd)
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("Start", 0));
    d_P.add(new PetriP("Free Robots (X)", 1));
    d_P.add(new PetriP("End", 0));
    d_P.add(new PetriP("Free Robots (Y)", 1));
    d_P.add(new PetriP("Free Robots (Z)", 1));
    d_P.add(new PetriP("Robot At Start", 0));
    d_T.add(new PetriT("Robot From X Moves To Start", XMoveDelayMean));
    d_T.get(0).setPriority(XPriority); // 2
    d_T.add(new PetriT("Robot From Y Moves To Start", YMoveDelayMean));
    d_T.get(1).setPriority(YPriority); // 1
    d_T.add(new PetriT("Robot From Z Moves To Start", ZMoveDelayMean));
    d_T.get(2).setPriority(ZPriority); // 0
    d_T.add(new PetriT("Carry To End", carryDelayMean));
    d_T.get(3).setDistribution("unif", d_T.get(3).getTimeServ());
    d_T.get(3).setParamDeviation(carryDelayStd);
    d_In.add(new ArcIn(d_P.get(0), d_T.get(0), 1));
    d_In.add(new ArcIn(d_P.get(1), d_T.get(0), 1));
    d_In.add(new ArcIn(d_P.get(0), d_T.get(1), 1));
    d_In.add(new ArcIn(d_P.get(0), d_T.get(2), 1));
    d_In.add(new ArcIn(d_P.get(3), d_T.get(1), 1));
    d_In.add(new ArcIn(d_P.get(4), d_T.get(2), 1));
    d_In.add(new ArcIn(d_P.get(5), d_T.get(3), 1));
    d_Out.add(new ArcOut(d_T.get(1), d_P.get(5), 1));
    d_Out.add(new ArcOut(d_T.get(3), d_P.get(1), 1));
    d_Out.add(new ArcOut(d_T.get(3), d_P.get(2), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(5), 1));
    d_Out.add(new ArcOut(d_T.get(2), d_P.get(5), 1));
    PetriNet d_Net =
        new PetriNet("RobotDynamicPipeline", d_P, d_T, d_In, d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }

  public static PetriNet
  CreateNetMachinePipeline(int numChannels, String delayDistribution,
                           double delayMean, double delayStd)
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("Start", 0));
    d_P.add(new PetriP("End", 0));
    d_P.add(new PetriP("Free channels", numChannels));
    d_T.add(new PetriT("Process", delayMean));
    d_T.get(0).setDistribution(delayDistribution, d_T.get(0).getTimeServ());
    d_T.get(0).setParamDeviation(delayStd);
    d_In.add(new ArcIn(d_P.get(0), d_T.get(0), 1));
    d_In.add(new ArcIn(d_P.get(2), d_T.get(0), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(1), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(2), 1));
    PetriNet d_Net = new PetriNet("MachinePipeline", d_P, d_T, d_In, d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }

  public static PetriNet
  CreateNetBusStation(double comingDelayMean, double busADelayMean,
                      double busBDelayMean, double exitDelayMean,
                      int maxQueueSize, int numInBus, boolean isBusInThisCity)
      throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
    ArrayList<PetriP> d_P = new ArrayList<>();
    ArrayList<PetriT> d_T = new ArrayList<>();
    ArrayList<ArcIn> d_In = new ArrayList<>();
    ArrayList<ArcOut> d_Out = new ArrayList<>();
    d_P.add(new PetriP("Person", 1));
    d_P.add(new PetriP("P2", 0));
    d_P.add(new PetriP("Queue", 0));
    d_P.add(new PetriP("Lost Money", 0));
    d_P.add(new PetriP("Empty Bus A In This City", isBusInThisCity ? 1 : 0));
    d_P.add(new PetriP("Empty Bus B In This City", isBusInThisCity ? 1 : 0));
    d_P.add(new PetriP("Earned Money", 0));
    d_P.add(new PetriP("Empty Bus A In Another City", isBusInThisCity ? 0 : 1));
    d_P.add(new PetriP("Empty Bus B In Another City", isBusInThisCity ? 0 : 1));
    d_T.add(new PetriT("Input", comingDelayMean));
    d_T.get(0).setDistribution("unif", d_T.get(0).getTimeServ());
    d_T.get(0).setParamDeviation(0.115);
    d_T.add(new PetriT("To Bus Stop", 0.0));
    d_T.add(new PetriT("Go Away", 0.0));
    d_T.get(2).setPriority(1);
    d_T.add(new PetriT("Bus B Moves To Another City And Exit",
                       busBDelayMean + exitDelayMean));
    d_T.get(3).setDistribution("unif", d_T.get(3).getTimeServ());
    d_T.get(3).setParamDeviation(3.464);
    d_T.add(new PetriT("Bus A Moves To Another City And Exit",
                       busADelayMean + exitDelayMean));
    d_T.get(4).setDistribution("unif", d_T.get(4).getTimeServ());
    d_T.get(4).setParamDeviation(3.464);
    d_T.get(4).setPriority(1);
    d_In.add(new ArcIn(d_P.get(0), d_T.get(0), 1));
    d_In.add(new ArcIn(d_P.get(1), d_T.get(1), 1));
    d_In.add(new ArcIn(d_P.get(2), d_T.get(2), maxQueueSize + 1));
    d_In.get(2).setInf(true);
    d_In.add(new ArcIn(d_P.get(1), d_T.get(2), 1));
    d_In.add(new ArcIn(d_P.get(2), d_T.get(4), numInBus));
    d_In.add(new ArcIn(d_P.get(4), d_T.get(4), 1));
    d_In.add(new ArcIn(d_P.get(5), d_T.get(3), 1));
    d_In.add(new ArcIn(d_P.get(2), d_T.get(3), numInBus));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(1), 1));
    d_Out.add(new ArcOut(d_T.get(0), d_P.get(0), 1));
    d_Out.add(new ArcOut(d_T.get(1), d_P.get(2), 1));
    d_Out.add(new ArcOut(d_T.get(2), d_P.get(3), 20));
    d_Out.add(new ArcOut(d_T.get(4), d_P.get(6), numInBus * 20));
    d_Out.add(new ArcOut(d_T.get(3), d_P.get(6), numInBus * 20));
    d_Out.add(new ArcOut(d_T.get(3), d_P.get(8), 1));
    d_Out.add(new ArcOut(d_T.get(4), d_P.get(7), 1));
    PetriNet d_Net = new PetriNet("BusStation", d_P, d_T, d_In, d_Out);
    PetriP.initNext();
    PetriT.initNext();
    ArcIn.initNext();
    ArcOut.initNext();

    return d_Net;
  }
}
