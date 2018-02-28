package logic.vehicles.driving;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 28.02.2018
 */
public class DataSets {

    public ArrayList<DataSet> dataset = new ArrayList<>();

    public ArrayList<DataSet> getDataset() {
        return dataset;
    }

    public class DataSet {
        public ArrayList<Double> inputs = new ArrayList<>();
        public ArrayList<Double> outputs = new ArrayList<>();

        public double[] getInputs(){
            double [] ret = new double[inputs.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i]= inputs.get(i);
            }

            return ret;
        }
    }

    public void generateDataSets() {
        //10 for each output state
//1 SURPASS
        DataSet dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//since its fun to write datasets, 10 more :P MAXIIII
        //1 SURPASS
        DataSet dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 FOLLOW_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 STEP_ASIDE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 SWITCH_LANE
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//1 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//2 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//3 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//4 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//5 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//6 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//7 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//8 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//9 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
//10 ADJUST_SPEED
        dataSet = new DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.0);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.0);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.inputs.add(0.0);//ampel state 0->red  1-> green
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.0);//FOLLOW_LANE
        dataSet.outputs.add(0.0);//STEP_ASIDE
        dataSet.outputs.add(0.0);//SWITCH_LANE
        dataSet.outputs.add(0.0);//ADJUST_SPEED
        dataset.add(dataSet);
    }
}
