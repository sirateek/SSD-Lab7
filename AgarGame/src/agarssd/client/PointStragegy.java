package agarssd.client;

import agarssd.model.Item;
import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;
import java.lang.Math;

/// Point Strategy is the strategy that will always move the player
/// to the nearest item in the game.
public class PointStragegy implements MoveStrategy {

    private int calculateDistance(float[] playerPos, float[] targetPos) {
        int diffX = (int)(playerPos[0] - targetPos[0]);
        int diffY = (int)(playerPos[1] - targetPos[1]);
        return (int)Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }

    @Override
    public MoveCommand getNextMoveCommand(World world, Player myPlayer) {

        if(world == null) {
            return null;
        }

        int nearestX = 0;
        int nearestY = 0;

        int distance = 999999999;
        for (Item item: world.items) {
            float[] playerPos = {myPlayer.positionX, myPlayer.positionY};
            float[] targetPos = {item.positionX, item.positionY};

            int result = calculateDistance(playerPos, targetPos);

            if (result < distance) {
                    nearestX = (int)item.positionX;
                    nearestY = (int)item.positionY;
                    distance = result;
            }
        }

        
        MoveCommand moveCommand = new MoveCommand();
        moveCommand.toX = nearestX;
        moveCommand.toY = nearestY;
        System.out.println("Move to: " + nearestX + "," + nearestY);

        return moveCommand;
    }
    
}
