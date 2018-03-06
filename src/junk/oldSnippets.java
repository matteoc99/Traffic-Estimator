package junk;

import logic.vehicles.driving.DataSets;

/**
 * @author Matteo Cosi
 * @since 28.02.2018
 */
public class oldSnippets {

    //old driving -> getAction
    /*
     // evaluate all actions (0 points each)
        // evaluate possible moves (+1 point)
        // multiply each point with a number given by the character traits
        // Note: Abort can never be a regular nextAction

        double surpassPoints = 0;
        double stepAsidePoints = 0;
        double followLanePoints = 0;
        double switchLanePoints = 0;
        double adjustSpeedPoints = 0;

        surpassPoints = (evaluateAction(Action.SURPASS) == Action.SURPASS)? 1:0;
        stepAsidePoints = (evaluateAction(Action.STEP_ASIDE) == Action.STEP_ASIDE)? 1:0;
        followLanePoints = (evaluateAction(Action.FOLLOW_LANE) == Action.FOLLOW_LANE)? 1:0;
        switchLanePoints = (evaluateAction(Action.SWITCH_LANE) == Action.SWITCH_LANE)? 1:0;
        adjustSpeedPoints = (evaluateAction(Action.ADJUST_SPEED) == Action.ADJUST_SPEED)? 1:0;

        // Surpass: for each
        if (surpassPoints != 0) surpassPoints *= getSurpassMultiplier();
        if (stepAsidePoints != 0) stepAsidePoints *= getStepAsideMultiplier();
        if (followLanePoints != 0) followLanePoints *= getFollowLaneMultiplier();
        if (switchLanePoints != 0) switchLanePoints *= getSwitchLaneMultiplier();
        if (adjustSpeedPoints != 0) adjustSpeedPoints *= getAdjustSpeedMultiplier();

        Action nextAction = null;
        double highestPoints = 0;

        if (surpassPoints > highestPoints) {
            nextAction = Action.SURPASS;
            highestPoints = surpassPoints;
        }
        if (stepAsidePoints > highestPoints) {
            nextAction = Action.STEP_ASIDE;
            highestPoints = stepAsidePoints;
        }
        if (followLanePoints > highestPoints) {
            nextAction = Action.FOLLOW_LANE;
            highestPoints = followLanePoints;
        }
        if (switchLanePoints > highestPoints) {
            nextAction = Action.SWITCH_LANE;
            highestPoints = switchLanePoints;
        }
        if (adjustSpeedPoints > highestPoints) {
            nextAction = Action.ADJUST_SPEED;
        }

        if (nextAction == null)
            throw new RuntimeException("No nextAction possible!");
        else
            return nextAction;
     */


    //data sets
    public void test(){
/*
        //1 SURPASS
        DataSets.DataSet dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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
        dataSet = new DataSets.DataSet();
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

double[] data = new double[network.getLayerByIndex(0).getNeuronCount()];
        data[0] = considerative;
        data[1] = desiredSpeed;
        data[2] = agressivity;
        data[3] = getDistanceCarBehind();
        data[4] = getDistanceCarInfront();
        data[5] = isLeftLaneFree() ? 1 : 0;
        data[6] = isRightLaneFree() ? 1 : 0;
        double[] result = network.processData(data);
        double max = Double.MIN_VALUE;
        int indexMax = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                indexMax = i;
                max = result[i];
            }
        }
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(Action.SURPASS);
        actions.add(Action.FOLLOW_LANE);
        actions.add(Action.SWITCH_LANE);
        actions.add(Action.ADJUST_SPEED);

 */
    }
}

