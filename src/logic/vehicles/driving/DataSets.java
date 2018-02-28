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
        dataSet.inputs.add(0.5);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.9);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(1.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(1.0);//SURPASS
        dataSet.outputs.add(0.5);//FOLLOW_LANE
        dataSet.outputs.add(0.5);//SWITCH_LANE
        dataSet.outputs.add(0.6);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.6);//considerative
        dataSet.inputs.add(0.8);//desiredSpeed
        dataSet.inputs.add(1.0);//agressivity
        dataSet.inputs.add(0.4);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(1.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(1.0);//SURPASS
        dataSet.outputs.add(0.3);//FOLLOW_LANE
        dataSet.outputs.add(0.8);//SWITCH_LANE
        dataSet.outputs.add(0.7);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SURPASS
        dataSet = new DataSet();
        dataSet.inputs.add(0.6);//considerative
        dataSet.inputs.add(0.3);//desiredSpeed
        dataSet.inputs.add(0.3);//agressivity
        dataSet.inputs.add(0.5);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.0);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.1);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(1.0);//SURPASS
        dataSet.outputs.add(0.2);//FOLLOW_LANE
        dataSet.outputs.add(0.3);//SWITCH_LANE
        dataSet.outputs.add(0.8);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SURPASS
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(1.0);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.5);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.9);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.4);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(1.0);//SURPASS
        dataSet.outputs.add(0.1);//FOLLOW_LANE
        dataSet.outputs.add(0.4);//SWITCH_LANE
        dataSet.outputs.add(0.8);//ADJUST_SPEED
//5 SURPASS
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.1);//considerative
        dataSet.inputs.add(0.9);//desiredSpeed
        dataSet.inputs.add(0.9);//agressivity
        dataSet.inputs.add(0.5);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.9);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.1);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(1.0);//SURPASS
        dataSet.outputs.add(0.2);//FOLLOW_LANE
        dataSet.outputs.add(1.0);//SWITCH_LANE
        dataSet.outputs.add(0.1);//ADJUST_SPEED
//1 FOLLOW_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(1.0);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.4);//agressivity
        dataSet.inputs.add(0.3);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.7);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.5);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.1);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.4);//SWITCH_LANE
        dataSet.outputs.add(0.1);//ADJUST_SPEED
        dataset.add(dataSet);
//2 FOLLOW_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.4);//considerative
        dataSet.inputs.add(0.2);//desiredSpeed
        dataSet.inputs.add(0.3);//agressivity
        dataSet.inputs.add(0.1);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.8);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.7);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.35);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.2);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.1);//SWITCH_LANE
        dataSet.outputs.add(0.4);//ADJUST_SPEED
        dataset.add(dataSet);
//3 FOLLOW_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.0);//considerative
        dataSet.inputs.add(0.9);//desiredSpeed
        dataSet.inputs.add(1.0);//agressivity
        dataSet.inputs.add(0.2);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.6);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.5);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.2);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.6);//SWITCH_LANE
        dataSet.outputs.add(0.1);//ADJUST_SPEED
        dataset.add(dataSet);
//4 FOLLOW_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.5);//considerative
        dataSet.inputs.add(0.2);//desiredSpeed
        dataSet.inputs.add(0.3);//agressivity
        dataSet.inputs.add(0.3);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.9);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.8);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.3);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.4);//SWITCH_LANE
        dataSet.outputs.add(0.8);//ADJUST_SPEED
        dataset.add(dataSet);
//5 FOLLOW_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.7);//considerative
        dataSet.inputs.add(0.8);//desiredSpeed
        dataSet.inputs.add(0.7);//agressivity
        dataSet.inputs.add(0.1);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.2);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.4);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.8);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.4);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.3);//SWITCH_LANE
        dataSet.outputs.add(0.9);//ADJUST_SPEED
        dataset.add(dataSet);
//1 SWITCH_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.2);//considerative
        dataSet.inputs.add(0.9);//desiredSpeed
        dataSet.inputs.add(1.0);//agressivity
        dataSet.inputs.add(0.6);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.2);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(1.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.3);//FOLLOW_LANE
        dataSet.outputs.add(1.0);//SWITCH_LANE
        dataSet.outputs.add(0.2);//ADJUST_SPEED
        dataset.add(dataSet);
//2 SWITCH_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.8);//considerative
        dataSet.inputs.add(0.4);//desiredSpeed
        dataSet.inputs.add(0.2);//agressivity
        dataSet.inputs.add(0.3);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.3);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(1.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.7);//FOLLOW_LANE
        dataSet.outputs.add(1.0);//SWITCH_LANE
        dataSet.outputs.add(0.4);//ADJUST_SPEED
        dataset.add(dataSet);
//3 SWITCH_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(1.0);//considerative
        dataSet.inputs.add(0.2);//desiredSpeed
        dataSet.inputs.add(0.0);//agressivity
        dataSet.inputs.add(0.3);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(1.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(1.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.8);//SURPASS
        dataSet.outputs.add(0.4);//FOLLOW_LANE
        dataSet.outputs.add(0.9);//SWITCH_LANE
        dataSet.outputs.add(0.8);//ADJUST_SPEED
        dataset.add(dataSet);
//4 SWITCH_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.5);//considerative
        dataSet.inputs.add(0.8);//desiredSpeed
        dataSet.inputs.add(0.5);//agressivity
        dataSet.inputs.add(0.3);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.2);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(1.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(1.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.8);//SURPASS
        dataSet.outputs.add(0.3);//FOLLOW_LANE
        dataSet.outputs.add(1.0);//SWITCH_LANE
        dataSet.outputs.add(0.4);//ADJUST_SPEED
        dataset.add(dataSet);
//5 SWITCH_LANE
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.2);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.8);//agressivity
        dataSet.inputs.add(0.6);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.2);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(1.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.6);//SURPASS
        dataSet.outputs.add(0.2);//FOLLOW_LANE
        dataSet.outputs.add(1.0);//SWITCH_LANE
        dataSet.outputs.add(0.2);//ADJUST_SPEED
        dataset.add(dataSet);
//1 ADJUST_SPEED
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.5);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.5);//agressivity
        dataSet.inputs.add(0.5);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.9);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.5);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.1);//SURPASS
        dataSet.outputs.add(1.0);//FOLLOW_LANE
        dataSet.outputs.add(0.4);//SWITCH_LANE
        dataSet.outputs.add(0.1);//ADJUST_SPEED
        dataset.add(dataSet);
//2 ADJUST_SPEED
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.8);//considerative
        dataSet.inputs.add(0.1);//desiredSpeed
        dataSet.inputs.add(0.1);//agressivity
        dataSet.inputs.add(0.2);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.9);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.0);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(1.0);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.0);//SURPASS
        dataSet.outputs.add(0.4);//FOLLOW_LANE
        dataSet.outputs.add(0.9);//SWITCH_LANE
        dataSet.outputs.add(0.9);//ADJUST_SPEED
        dataset.add(dataSet);
//3 ADJUST_SPEED
        dataSet = new DataSets.DataSet();
        dataSet.inputs.add(0.6);//considerative
        dataSet.inputs.add(0.5);//desiredSpeed
        dataSet.inputs.add(0.3);//agressivity
        dataSet.inputs.add(0.9);//distance car behinde(0-1) 0--> close
        dataSet.inputs.add(0.1);//distance car infront(0-1) 0--> close
        dataSet.inputs.add(0.5);//isLeftlaneFree 0 oder 1
        dataSet.inputs.add(0.5);//isRightLaneFree 0 oder 1
        dataSet.outputs.add(0.3);//SURPASS
        dataSet.outputs.add(0.5);//FOLLOW_LANE
        dataSet.outputs.add(0.6);//SWITCH_LANE
        dataSet.outputs.add(0.9);//ADJUST_SPEED
        dataset.add(dataSet);
    }
}
