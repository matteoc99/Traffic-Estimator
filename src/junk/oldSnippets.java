package junk;

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
}

