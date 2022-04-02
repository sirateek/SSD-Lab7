package agarssd.client;

import java.util.Random;

import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;

public class RandomStrategy implements MoveStrategy {

    private Random random = new Random();
    private long lastCommand;

    @Override
    public MoveCommand getNextMoveCommand(World world, Player myPlayer) {
        if(world == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - lastCommand;
        if(diff < 5000) {
            return null;
        }
        lastCommand = System.currentTimeMillis();
        MoveCommand command = new MoveCommand();
        command.toX = random.nextInt(world.size);
        command.toY = random.nextInt(world.size);
        return command;
    }

}
